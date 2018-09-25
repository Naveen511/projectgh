package com.niche.ng.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Quantity.
 */
@Entity
@Table(name = "quantity")
public class Quantity extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "approx_quantity", nullable = false)
    private Long approxQuantity;

    @ManyToOne
    @JsonIgnoreProperties("quantitysVarieties")
    private PickListValue pickListVariety;

    @ManyToOne
    @JsonIgnoreProperties("quantitysCategories")
    private PickListValue pickListCategory;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApproxQuantity() {
        return approxQuantity;
    }

    public Quantity approxQuantity(Long approxQuantity) {
        this.approxQuantity = approxQuantity;
        return this;
    }

    public void setApproxQuantity(Long approxQuantity) {
        this.approxQuantity = approxQuantity;
    }

    public PickListValue getPickListVariety() {
        return pickListVariety;
    }

    public Quantity pickListVariety(PickListValue pickListValue) {
        this.pickListVariety = pickListValue;
        return this;
    }

    public void setPickListVariety(PickListValue pickListValue) {
        this.pickListVariety = pickListValue;
    }

    public PickListValue getPickListCategory() {
        return pickListCategory;
    }

    public Quantity pickListCategory(PickListValue pickListValue) {
        this.pickListCategory = pickListValue;
        return this;
    }

    public void setPickListCategory(PickListValue pickListValue) {
        this.pickListCategory = pickListValue;
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
        Quantity quantity = (Quantity) o;
        if (quantity.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quantity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Quantity{" +
            "id=" + getId() +
            ", approxQuantity=" + getApproxQuantity() +
            "}";
    }
}
