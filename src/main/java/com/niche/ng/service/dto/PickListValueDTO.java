/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs PickListValueDTO
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the PickListValue entity.
 */
public class PickListValueDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull(message = "Pick list value cannot be blank.")
    private String pickListValue;

    private Integer status;

    private Long pickListId;

    private String pickListPickListName;

    private Long pickValueId;

    private String pickValuePickListValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPickListValue() {
        return pickListValue;
    }

    public void setPickListValue(String pickListValue) {
        this.pickListValue = pickListValue;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getPickListId() {
        return pickListId;
    }

    public void setPickListId(Long pickListId) {
        this.pickListId = pickListId;
    }

    public String getPickListPickListName() {
        return pickListPickListName;
    }

    public void setPickListPickListName(String pickListPickListName) {
        this.pickListPickListName = pickListPickListName;
    }

    public Long getPickValueId() {
        return pickValueId;
    }

    public void setPickValueId(Long pickListValueId) {
        this.pickValueId = pickListValueId;
    }

    public String getPickValuePickListValue() {
        return pickValuePickListValue;
    }

    public void setPickValuePickListValue(String pickListValuePickListValue) {
        this.pickValuePickListValue = pickListValuePickListValue;
    }

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

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

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
