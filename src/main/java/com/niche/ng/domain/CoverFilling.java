package com.niche.ng.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A CoverFilling.
 */
@Entity
@Table(name = "cover_filling")
public class CoverFilling extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "no_of_cover", nullable = false)
    private Integer noOfCover;

    @NotNull
    @Column(name = "jhi_date", nullable = false)
    private LocalDate date;

    @Size(max = 255)
    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "status")
    private Integer status;

    @Column(name = "damage_quantity")
    private Integer damageQuantity;

    @OneToMany(mappedBy = "coverFilling")
    private Set<CoverFillingDetails> coverFillingDetails = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNoOfCover() {
        return noOfCover;
    }

    public CoverFilling noOfCover(Integer noOfCover) {
        this.noOfCover = noOfCover;
        return this;
    }

    public void setNoOfCover(Integer noOfCover) {
        this.noOfCover = noOfCover;
    }

    public LocalDate getDate() {
        return date;
    }

    public CoverFilling date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public CoverFilling description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public CoverFilling status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDamageQuantity() {
        return damageQuantity;
    }

    public CoverFilling damageQuantity(Integer damageQuantity) {
        this.damageQuantity = damageQuantity;
        return this;
    }

    public void setDamageQuantity(Integer damageQuantity) {
        this.damageQuantity = damageQuantity;
    }

    public Set<CoverFillingDetails> getCoverFillingDetails() {
        return coverFillingDetails;
    }

    public CoverFilling coverFillingDetails(Set<CoverFillingDetails> coverFillingDetails) {
        this.coverFillingDetails = coverFillingDetails;
        return this;
    }

    public CoverFilling addCoverFillingDetails(CoverFillingDetails coverFillingDetails) {
        this.coverFillingDetails.add(coverFillingDetails);
        coverFillingDetails.setCoverFilling(this);
        return this;
    }

    public CoverFilling removeCoverFillingDetails(CoverFillingDetails coverFillingDetails) {
        this.coverFillingDetails.remove(coverFillingDetails);
        coverFillingDetails.setCoverFilling(null);
        return this;
    }

    public void setCoverFillingDetails(Set<CoverFillingDetails> coverFillingDetails) {
        this.coverFillingDetails = coverFillingDetails;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CoverFilling coverFilling = (CoverFilling) o;
        if (coverFilling.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), coverFilling.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CoverFilling{" +
            "id=" + getId() +
            ", noOfCover=" + getNoOfCover() +
            ", date='" + getDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            ", damageQuantity=" + getDamageQuantity() +
            "}";
    }
}
