/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs BatchQuantityDTO and
                            declared the table fields, data types for BatchQuantity table.
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the BatchQuantity entity.
 * 
 * BatchQuantityDTO class implements Serializable to convert instance class into 
 * series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
public class BatchQuantityDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull(message = "Quantity cannot be blank.")
    private Integer quantity;

    private LocalDate date;

    private String remarks;

    private Integer status;

    private Long batchId;

    private String batchBatchName;

    /**
     * To Get the Id from batch quantity table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To Set the Id for batch quantity table
     *
     * @param id Value of the batch quantity
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the quantity from batch quantity table
     * 
     * @return id
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * To Set the quantity for batch quantity table
     *
     * @param quantity quantity in batch quantity table
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * To Get the date from batch quantity table
     * 
     * @return date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * To Set the date for batch quantity table
     *
     * @param date date in batch quantity table
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * To Get the remarks from batch quantity table
     * 
     * @return remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * To Set the remarks for batch quantity table
     *
     * @param remarks remarks in batch quantity table
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * To Get the status from batch quantity table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To Set the status for batch quantity table
     * 
     * @param status status in batch quantity table
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the batchId from batchId quantity table
     * 
     * @return batchId
     */
    public Long getBatchId() {
        return batchId;
    }

    /**
     * To Set the batchId for batch quantity table
     * 
     * @param batchId batchId in batch quantity table
     */
    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    /**
     * To Get the batchBatchName from batchBatchName quantity table
     * 
     * @return batchBatchName
     */
    public String getBatchBatchName() {
        return batchBatchName;
    }

    /**
     * To Set the batchId for batch quantity table
     * 
     * @param batchId batchId in batch quantity table
     */
    public void setBatchBatchName(String batchBatchName) {
        this.batchBatchName = batchBatchName;
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

        BatchQuantityDTO batchQuantityDTO = (BatchQuantityDTO) o;
        if (batchQuantityDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), batchQuantityDTO.getId());
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
        return "BatchQuantityDTO{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            ", date='" + getDate() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", status=" + getStatus() +
            ", batch=" + getBatchId() +
            ", batch='" + getBatchBatchName() + "'" +
            "}";
    }
}
