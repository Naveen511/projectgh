/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs MotherBedDTO
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the MotherBed entity.
 */
public class MotherBedDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private String value;

    private Integer status;

    private Long nurseryId;

    private String nurseryNurseryName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getNurseryId() {
        return nurseryId;
    }

    public void setNurseryId(Long nurseryId) {
        this.nurseryId = nurseryId;
    }

    public String getNurseryNurseryName() {
        return nurseryNurseryName;
    }

    public void setNurseryNurseryName(String nurseryNurseryName) {
        this.nurseryNurseryName = nurseryNurseryName;
    }

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

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MotherBedDTO{" +
            "id=" + getId() +
            ", value=" + getValue() +
            ", status=" + getStatus() +
            ", nursery=" + getNurseryId() +
            ", nursery='" + getNurseryNurseryName() + "'" +
            "}";
    }
}
