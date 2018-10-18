/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs CoverFillingDTO and
                            declared the table fields, data types for CoverFillingDetails table.
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the CoverFilling entity.
 * 
 * CoverFillingDTO class implements Serializable to convert instance class into series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
public class CoverFillingDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull(message = "No of cover should not be empty")
    // @NotBlank(message = "No of cover should not be empty")
    private Integer noOfCover;

    @NotNull(message = "No of cover should not be empty")
    // @NotBlank(message = "No of cover should not be empty")
    private LocalDate date;

    @Size(max = 255, message = "Description should not exist 255 character.")
    private String description;

    private Integer status;

    private Integer damageQuantity;

    /**
     * To Get the Id from CoverFilling table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To Set the Id for CoverFilling table
     * 
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the Id from CoverFilling table
     * 
     * @return noOfCover
     */
    public Integer getNoOfCover() {
        return noOfCover;
    }

    /**
     * To Set the noOfCover for CoverFilling table
     * 
     * @param noOfCover noOfCover on cover filling
     */
    public void setNoOfCover(Integer noOfCover) {
        this.noOfCover = noOfCover;
    }

    /**
     * To Get the Id from CoverFilling table
     * 
     * @return date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * To Set the date for CoverFilling table.
     * 
     * @param date date while cover filling
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * To Get the description from CoverFilling table
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * To Set the description for CoverFilling table.
     * 
     * @param description description in cover filling
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * To Get the status from CoverFilling table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To Set the status for CoverFilling table.
     * 
     * @param status status in cover filling
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the damageQuantity from CoverFilling table
     * 
     * @return damageQuantity
     */
    public Integer getDamageQuantity() {
        return damageQuantity;
    }

    /**
     * To Set the damageQuantity for CoverFilling table.
     * 
     * @param damageQuantity damage quantity in cover filling
     */
    public void setDamageQuantity(Integer damageQuantity) {
        this.damageQuantity = damageQuantity;
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

        CoverFillingDTO coverFillingDTO = (CoverFillingDTO) o;
        if (coverFillingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), coverFillingDTO.getId());
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
