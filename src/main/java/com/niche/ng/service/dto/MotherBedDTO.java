/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs MotherBedDTO and
                            declared the table fields, data types for MotherBed table.
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the MotherBed entity.
 * 
 * MotherBedDTO class implements Serializable to convert instance class into series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
public class MotherBedDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull(message = "Number cannot be blank.")
    @NotBlank(message = "Number cannot be blank.")
    private String value;

    private Integer status;

    @NotNull(message = " cannot be blank.")
    private Long nurseryId;

    private String nurseryNurseryName;

    /**
     * To Get the Id from MotherBed table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To set the id values for the MotherBed table.
     * 
     * @param id id value of the MotherBed
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the value from MotherBed table
     * 
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * To set the id values for the MotherBed table.
     * 
     * @param value value of the MotherBed
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * To Get the status from MotherBed table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To set the status for the MotherBed table.
     * 
     * @param status status of the MotherBed
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the nurseryId from MotherBed table
     * 
     * @return nurseryId
     */
    public Long getNurseryId() {
        return nurseryId;
    }

    /**
     * To set the nurseryId values for the MotherBed table.
     * 
     * @param nurseryId nurseryId value of the MotherBed
     */
    public void setNurseryId(Long nurseryId) {
        this.nurseryId = nurseryId;
    }

    /**
     * To Get the nurseryNurseryName from MotherBed table
     * 
     * @return nurseryNurseryName
     */
    public String getNurseryNurseryName() {
        return nurseryNurseryName;
    }

    /**
     * To set the nurseryNurseryName values for the MotherBed table.
     * 
     * @param nurseryNurseryName nurseryNurseryName value of the MotherBed
     */
    public void setNurseryNurseryName(String nurseryNurseryName) {
        this.nurseryNurseryName = nurseryNurseryName;
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

        MotherBedDTO motherBedDTO = (MotherBedDTO) o;
        if (motherBedDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), motherBedDTO.getId());
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
        return "MotherBedDTO{" +
            "id=" + getId() +
            ", value='" + getValue() + "'" +
            ", status=" + getStatus() +
            ", nursery=" + getNurseryId() +
            ", nursery='" + getNurseryNurseryName() + "'" +
            "}";
    }
}
