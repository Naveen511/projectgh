/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs PickListDTO
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the PickList entity.
 */
public class PickListDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull(message = "Pick list name cannot be blank.")
    private String pickListName;

    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPickListName() {
        return pickListName;
    }

    public void setPickListName(String pickListName) {
        this.pickListName = pickListName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PickListDTO pickListDTO = (PickListDTO) o;
        if (pickListDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pickListDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PickListDTO{" +
            "id=" + getId() +
            ", pickListName='" + getPickListName() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
