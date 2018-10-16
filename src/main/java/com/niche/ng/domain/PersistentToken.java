/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs PersistentToken Generation.
 *
 *******************************************************************************/
package com.niche.ng.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * Persistent tokens are used by Spring Security to automatically log in users.
 *
 * @see com.niche.ng.security.PersistentTokenRememberMeServices
 */
@Entity
@Table(name = "jhi_persistent_token")
public class PersistentToken implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final int MAX_USER_AGENT_LEN = 255;

    @Id
    private String series;

    @JsonIgnore
    @NotNull
    @Column(name = "token_value", nullable = false)
    private String tokenValue;
    
    @Column(name = "token_date")
    private LocalDate tokenDate;

    //an IPV6 address max length is 39 characters
    @Size(min = 0, max = 39)
    @Column(name = "ip_address", length = 39)
    private String ipAddress;

    @Column(name = "user_agent")
    private String userAgent;
        

    @JsonIgnore
    @ManyToOne
    private User user;
    /**
     * To Get the series from persistentToken table
     * 
     * @return series
     */
    public String getSeries() {
        return series;
    }

    /**
     * To Set the series for persistentToken table
     *
     * @param series the series in persistentToken table
     */
    public void setSeries(String series) {
        this.series = series;
    }

    /**
     * To Get the tokenValue from persistentToken table
     * 
     * @return tokenValue
     */
    public String getTokenValue() {
        return tokenValue;
    }

    /**
     * To Set the tokenValue for persistentToken table
     *
     * @param tokenValue the tokenValue in persistentToken table
     */
    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    /**
     * To Get the tokenDate from persistentToken table
     * 
     * @return tokenDate
     */
    public LocalDate getTokenDate() {
        return tokenDate;
    }

    /**
     * To Set the tokenDate for persistentToken table
     *
     * @param tokenDate the tokenDate in persistentToken table
     */
    public void setTokenDate(LocalDate tokenDate) {
        this.tokenDate = tokenDate;
    }

    /**
     * To Get the ipAddress from persistentToken table
     * 
     * @return ipAddress
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * To Set the ipAddress for persistentToken table
     *
     * @param ipAddress the ipAddress in persistentToken table
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * To Get the userAgent from persistentToken table
     * 
     * @return userAgent
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * To Set the userAgent for persistentToken table
     *
     * @param userAgent the userAgent in persistentToken table
     */
    public void setUserAgent(String userAgent) {
        if (userAgent.length() >= MAX_USER_AGENT_LEN) {
            this.userAgent = userAgent.substring(0, MAX_USER_AGENT_LEN - 1);
        } else {
            this.userAgent = userAgent;
        }
    }

    /**
     * To Get the user from persistentToken table
     * 
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * To Set the user for persistentToken table
     *
     * @param user the user in persistentToken table
     */
    public void setUser(User user) {
        this.user = user;
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

        PersistentToken that = (PersistentToken) o;

        if (!series.equals(that.series)) {
            return false;
        }

        return true;
    }

    /**
     * hash code for the id
     * 
     * @return objects
     */
    @Override
    public int hashCode() {
        return series.hashCode();
    }

    /**
     * toString to get the values
     * 
     * @return values
     */
    @Override
    public String toString() {
        return "PersistentToken{" +
            "series='" + series + '\'' +
            ", tokenValue='" + tokenValue + '\'' +
            ", tokenDate=" + tokenDate +
            ", ipAddress='" + ipAddress + '\'' +
            ", userAgent='" + userAgent + '\'' +
            "}";
    }
}
