package com.niche.ng.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the CoverFilling entity.
 */
public class CoverFillingDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull(message = "No of cover should not be empty")
    private Integer noOfCover;

    @NotNull(message = "No of cover should not be empty")
    private LocalDate date;

    @Size(max = 255)
    private String description;

    private Integer status;

    private Integer damageQuantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNoOfCover() {
        return noOfCover;
    }

    public void setNoOfCover(Integer noOfCover) {
        this.noOfCover = noOfCover;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDamageQuantity() {
        return damageQuantity;
    }

    public void setDamageQuantity(Integer damageQuantity) {
        this.damageQuantity = damageQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CoverFillingDTO coverFillingDTO = (CoverFillingDTO) o;
        if (coverFillingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), coverFillingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CoverFillingDTO{" +
            "id=" + getId() +
            ", noOfCover=" + getNoOfCover() +
            ", date='" + getDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            ", damageQuantity=" + getDamageQuantity() +
            "}";
    }
}
