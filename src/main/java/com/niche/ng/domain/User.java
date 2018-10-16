/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs User Generation.
 *  Declared the table fields and data types for the User table.
 *  Defined the following Relation for the User Table :
 *  OneToMany Relation to User Table
 *
 *******************************************************************************/
package com.niche.ng.domain;

import com.niche.ng.config.Constants;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.BatchSize;
import javax.validation.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.time.Instant;

/**
 * User Domain Class
 * 
 * User class implements Serializable to convert instance class into 
 * series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
@Entity
@Table(name = "jhi_user")

public class User extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    private String login;

    @JsonIgnore
    @NotNull
    @Size(min = 60, max = 60)
    @Column(name = "password_hash", length = 60, nullable = false)
    private String password;

    @Size(max = 50)
    @Column(name = "first_name", length = 50)
    private String firstName;

    @Size(max = 50)
    @Column(name = "last_name", length = 50)
    private String lastName;

    @Email
    @Size(min = 5, max = 254)
    @Column(length = 254, unique = true)
    private String email;

    @NotNull
    @Column(nullable = false)
    private boolean activated = false;

    @Size(min = 2, max = 6)
    @Column(name = "lang_key", length = 6)
    private String langKey;

    @Size(max = 256)
    @Column(name = "image_url", length = 256)
    private String imageUrl;

    @Size(max = 20)
    @Column(name = "activation_key", length = 20)
    @JsonIgnore
    private String activationKey;

    @Size(max = 20)
    @Column(name = "reset_key", length = 20)
    @JsonIgnore
    private String resetKey;

    @Column(name = "reset_date")
    private Instant resetDate = null;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "jhi_user_authority",
        joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})

    @BatchSize(size = 20)
    private Set<Authority> authorities = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    private Set<PersistentToken> persistentTokens = new HashSet<>();

    /**
     * To Get the Id from user table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To Set the Id for user table
     *
     * @param id the id in user table
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the login from user table
     * 
     * @return login
     */
    public String getLogin() {
        return login;
    }

    // Lowercase the login before saving it in database
    public void setLogin(String login) {
        this.login = StringUtils.lowerCase(login, Locale.ENGLISH);
    }

    /**
     * To Get the password from user table
     * 
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * To Set the password for user table
     *
     * @param password the password in user table
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * To Get the firstName from user table
     * 
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * To Set the firstName for user table
     *
     * @param firstName the firstName in user table
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * To Get the lastName from user table
     * 
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * To Set the lastName for user table
     *
     * @param lastName the lastName in user table
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * To Get the email from user table
     * 
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * To Set the email for user table
     *
     * @param email the email in user table
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * To Get the imageUrl from user table
     * 
     * @return imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * To Set the imageUrl for user table
     *
     * @param imageUrl the imageUrl in user table
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * To Get the activated from user table
     * 
     * @return activated
     */
    public boolean getActivated() {
        return activated;
    }

    /**
     * To Set the activated for user table
     *
     * @param activated the activated in user table
     */
    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    /**
     * To Get the activationKey from user table
     * 
     * @return activationKey
     */
    public String getActivationKey() {
        return activationKey;
    }

    /**
     * To Set the activationKey for user table
     *
     * @param activationKey the activationKey in user table
     */
    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    /**
     * To Get the resetKey from user table
     * 
     * @return resetKey
     */
    public String getResetKey() {
        return resetKey;
    }

    /**
     * To Set the resetKey for user table
     *
     * @param resetKey the resetKey in user table
     */
    public void setResetKey(String resetKey) {
        this.resetKey = resetKey;
    }

    /**
     * To Get the resetDate from user table
     * 
     * @return resetDate
     */
    public Instant getResetDate() {
        return resetDate;
    }

    /**
     * To Set the resetDate for user table
     *
     * @param resetDate the resetDate in user table
     */
    public void setResetDate(Instant resetDate) {
        this.resetDate = resetDate;
    }

    /**
     * To Get the langKey from user table
     * 
     * @return langKey
     */
    public String getLangKey() {
        return langKey;
    }

    /**
     * To Set the langKey for user table
     *
     * @param langKey the langKey in user table
     */
    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    /**
     * To Get the authorities from user table
     * 
     * @return authorities
     */
    public Set<Authority> getAuthorities() {
        return authorities;
    }

    /**
     * To Set the authorities for user table
     *
     * @param authorities the authorities in user table
     */
    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    /**
     * To Get the persistentTokens from user table
     * 
     * @return persistentTokens
     */
    public Set<PersistentToken> getPersistentTokens() {
        return persistentTokens;
    }

    /**
     * To Set the persistentTokens for user table
     *
     * @param persistentTokens the persistentTokens in user table
     */
    public void setPersistentTokens(Set<PersistentToken> persistentTokens) {
        this.persistentTokens = persistentTokens;
    }

    /**
     * To check the equals
     * 
     * @return objects
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;
        return !(user.getId() == null || getId() == null) && Objects.equals(getId(), user.getId());
    }

    /**
     * hash code for the id
     * 
     * @return objects
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    /**
     * toString to get the values
     * 
     * @return values
     */
    @Override
    public String toString() {
        return "User{" +
            "login='" + login + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", imageUrl='" + imageUrl + '\'' +
            ", activated='" + activated + '\'' +
            ", langKey='" + langKey + '\'' +
            ", activationKey='" + activationKey + '\'' +
            "}";
    }
}
