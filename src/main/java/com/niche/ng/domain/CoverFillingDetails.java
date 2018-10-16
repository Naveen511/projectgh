package com.niche.ng.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A CoverFillingDetails.
 */
@Entity
@Table(name = "cover_filling_details")
public class CoverFillingDetails extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @NotNull
    @Column(name = "jhi_date", nullable = false)
    private LocalDate date;

    @Column(name = "status")
    private Integer status;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JsonIgnoreProperties("coverFillingDetails")
    private CoverFilling coverFilling;

    @ManyToOne
    @JsonIgnoreProperties("coverFillingDetails")
    private PickListValue damageType;

    @ManyToOne
    @JsonIgnoreProperties("")
    private PickListValue coverFillingDamageDescription;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public CoverFillingDetails quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public CoverFillingDetails date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public CoverFillingDetails status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public CoverFillingDetails description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CoverFilling getCoverFilling() {
        return coverFilling;
    }

    public CoverFillingDetails coverFilling(CoverFilling coverFilling) {
        this.coverFilling = coverFilling;
        return this;
    }

    public void setCoverFilling(CoverFilling coverFilling) {
        this.coverFilling = coverFilling;
    }

    public PickListValue getDamageType() {
        return damageType;
    }

    public CoverFillingDetails damageType(PickListValue pickListValue) {
        this.damageType = pickListValue;
        return this;
    }

    public void setDamageType(PickListValue pickListValue) {
        this.damageType = pickListValue;
    }

    public PickListValue getCoverFillingDamageDescription() {
        return coverFillingDamageDescription;
    }

    public CoverFillingDetails coverFillingDamageDescription(PickListValue pickListValue) {
        this.coverFillingDamageDescription = pickListValue;
        return this;
    }

    public void setCoverFillingDamageDescription(PickListValue pickListValue) {
        this.coverFillingDamageDescription = pickListValue;
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
        CoverFillingDetails coverFillingDetails = (CoverFillingDetails) o;
        if (coverFillingDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), coverFillingDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CoverFillingDetails{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            ", date='" + getDate() + "'" +
            ", status=" + getStatus() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
