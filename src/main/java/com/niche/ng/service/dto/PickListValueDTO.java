/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs PickListValueDTO and
                            declared the table fields, data types for PickListValue table.
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the PickListValue entity.
 * 
 * PickListValueDTO class implements Serializable to convert instance class into series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
public class PickListValueDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull(message = "Pick list value cannot be blank.")
    @NotBlank(message = "Pick list value cannot be blank.")
    private String pickListValue;

    private Integer status;

    private Long pickListId;

    private String pickListPickListName;

    private Long pickValueId;

    private String pickValuePickListValue;

    /**
     * To Get the Id from PickListValue table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To set the id values for the PickListValue table.
     * 
     * @param id id of the PickListValue
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the pickListValue from PickListValue table
     * 
     * @return pickListValue
     */
    public String getPickListValue() {
        return pickListValue;
    }

    /**
     * To set the pickListValue values for the PickListValue table.
     * 
     * @param pickListValue pickListValue of the PickListValue
     */
    public void setPickListValue(String pickListValue) {
        this.pickListValue = pickListValue;
    }

    /**
     * To Get the status from PickListValue table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To set the status values for the PickListValue table.
     * 
     * @param status status of the PickListValue
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the pickListId from PickListValue table
     * 
     * @return pickListId
     */
    public Long getPickListId() {
        return pickListId;
    }

    /**
     * To set the pickListId values for the PickListValue table.
     * 
     * @param pickListId pickListId of the PickListValue
     */
    public void setPickListId(Long pickListId) {
        this.pickListId = pickListId;
    }

    /**
     * To Get the pickListPickListName from PickListValue table
     * 
     * @return pickListPickListName
     */
    public String getPickListPickListName() {
        return pickListPickListName;
    }

    /**
     * To set the pickListPickListName values for the PickListValue table.
     * 
     * @param pickListPickListName pickListPickListName of the PickListValue
     */
    public void setPickListPickListName(String pickListPickListName) {
        this.pickListPickListName = pickListPickListName;
    }

    /**
     * To Get the pickValueId from PickListValue table
     * 
     * @return pickValueId
     */
    public Long getPickValueId() {
        return pickValueId;
    }

    /**
     * To set the pickValueId values for the PickListValue table.
     * 
     * @param pickValueId pickValueId of the PickListValue
     */
    public void setPickValueId(Long pickListValueId) {
        this.pickValueId = pickListValueId;
    }

    /**
     * To Get the pickValuePickListValue from PickListValue table
     * 
     * @return pickValuePickListValue
     */
    public String getPickValuePickListValue() {
        return pickValuePickListValue;
    }

    /**
     * To set the pickValuePickListValue values for the PickListValue table.
     * 
     * @param pickValuePickListValue pickValuePickListValue of the PickListValue
     */
    public void setPickValuePickListValue(String pickListValuePickListValue) {
        this.pickValuePickListValue = pickListValuePickListValue;
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

        PickListValueDTO pickListValueDTO = (PickListValueDTO) o;
        if (pickListValueDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pickListValueDTO.getId());
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
        return "PickListValueDTO{" +
            "id=" + getId() +
            ", pickListValue='" + getPickListValue() + "'" +
            ", status=" + getStatus() +
            ", pickList=" + getPickListId() +
            ", pickList='" + getPickListPickListName() + "'" +
            ", pickValue=" + getPickValueId() +
            ", pickValue='" + getPickValuePickListValue() + "'" +
            "}";
    }
}
