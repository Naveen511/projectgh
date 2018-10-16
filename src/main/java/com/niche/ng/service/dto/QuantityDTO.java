package com.niche.ng.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Quantity entity.
 */
public class QuantityDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private Long approxQuantity;

    private Long pickListVarietyId;

    private String pickListVarietyPickListValue;

    private Long pickListCategoryId;

    private String pickListCategoryPickListValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApproxQuantity() {
        return approxQuantity;
    }

    public void setApproxQuantity(Long approxQuantity) {
        this.approxQuantity = approxQuantity;
    }

    public Long getPickListVarietyId() {
        return pickListVarietyId;
    }

    public void setPickListVarietyId(Long pickListValueId) {
        this.pickListVarietyId = pickListValueId;
    }

    public Long getPickListCategoryId() {
        return pickListCategoryId;
    }

    public void setPickListCategoryId(Long pickListValueId) {
        this.pickListCategoryId = pickListValueId;
    }

    public String getpickListVarietyPickListValue() {
        return pickListVarietyPickListValue;
    }

    public void setpickListVarietyPickListValue(String pickListVarietyPickListValue) {
        this.pickListVarietyPickListValue = pickListVarietyPickListValue;
    }

    public String getpickListCategoryPickListValue() {
        return pickListCategoryPickListValue;
    }

    public void setpickListCategoryPickListValue(String pickListCategoryPickListValue) {
        this.pickListCategoryPickListValue = pickListCategoryPickListValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QuantityDTO quantityDTO = (QuantityDTO) o;
        if (quantityDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quantityDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuantityDTO{" +
            "id=" + getId() +
            ", approxQuantity=" + getApproxQuantity() +
            ", pickListVariety=" + getPickListVarietyId() +
            ", pickListCategory=" + getPickListCategoryId() +
            ", pickListVarietyPickListValue=" + getpickListVarietyPickListValue() +
            ", pickListCategoryPickListValue=" + getpickListCategoryPickListValue() +
            "}";
    }
}
