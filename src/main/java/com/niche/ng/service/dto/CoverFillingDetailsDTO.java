package com.niche.ng.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the CoverFillingDetails entity.
 */
public class CoverFillingDetailsDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull(message = "Quantity cannot be blank.")
    private Integer quantity;

    @NotNull(message = "Date cannot be blank.")
    private LocalDate date;

    private Integer status;

    private String description;

    private Long coverFillingId;

    @NotNull(message = "Damage Type cannot be blank.")
    private Long damageTypeId;

    private String damageTypePickListValue;

    private Long coverFillingDamageDescriptionId;

    private String coverFillingDamageDescriptionPickListValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCoverFillingId() {
        return coverFillingId;
    }

    public void setCoverFillingId(Long coverFillingId) {
        this.coverFillingId = coverFillingId;
    }

    public Long getDamageTypeId() {
        return damageTypeId;
    }

    public void setDamageTypeId(Long pickListValueId) {
        this.damageTypeId = pickListValueId;
    }

    public String getDamageTypePickListValue() {
        return damageTypePickListValue;
    }

    public void setDamageTypePickListValue(String pickListValuePickListValue) {
        this.damageTypePickListValue = pickListValuePickListValue;
    }

    public Long getCoverFillingDamageDescriptionId() {
        return coverFillingDamageDescriptionId;
    }

    public void setCoverFillingDamageDescriptionId(Long pickListValueId) {
        this.coverFillingDamageDescriptionId = pickListValueId;
    }

    public String getCoverFillingDamageDescriptionPickListValue() {
        return coverFillingDamageDescriptionPickListValue;
    }

    public void setCoverFillingDamageDescriptionPickListValue(String pickListValuePickListValue) {
        this.coverFillingDamageDescriptionPickListValue = pickListValuePickListValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CoverFillingDetailsDTO coverFillingDetailsDTO = (CoverFillingDetailsDTO) o;
        if (coverFillingDetailsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), coverFillingDetailsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CoverFillingDetailsDTO{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            ", date='" + getDate() + "'" +
            ", status=" + getStatus() +
            ", description='" + getDescription() + "'" +
            ", coverFilling=" + getCoverFillingId() +
            ", damageType=" + getDamageTypeId() +
            ", damageType='" + getDamageTypePickListValue() + "'" +
            ", coverFillingDamageDescription=" + getCoverFillingDamageDescriptionId() +
            ", coverFillingDamageDescription='" + getCoverFillingDamageDescriptionPickListValue() + "'" +
            "}";
    }
}
