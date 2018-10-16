/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs GodownStockDetailsDTO and
                            declared the table fields, data types for GodownStockDetails table.
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the GodownStockDetails entity.
 * 
 * GodownStockDetailsDTO class implements Serializable to convert instance class into series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
 */
public class GodownStockDetailsDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull(message = "Date cannot be blank")
    private LocalDate date;

    @NotNull(message = "Quantity cannot be blank.")
    private Long quantity;

    private String description;

    private Integer status;

    private Float price;

    private Long godownStockId;

    private Long financialYearGodownStockDetailsId;

    private String financialYearGodownStockDetailsBatchName;

    /**
     * To Get the Id from GodownStockDetails table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To set the id values for the GodownStockDetails table.
     * 
     * @param id id of the GodownStockDetails
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the date from GodownStockDetails table
     * 
     * @return date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * To set the date values for the GodownStockDetails table.
     * 
     * @param date date of the GodownStockDetails
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * To Get the quantity from GodownStockDetails table
     * 
     * @return quantity
     */
    public Long getQuantity() {
        return quantity;
    }

    /**
     * To set the quantity values for the GodownStockDetails table.
     * 
     * @param quantity quantity of the GodownStockDetails
     */
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    /**
     * To Get the description from GodownStockDetails table
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * To set the description values for the GodownStockDetails table.
     * 
     * @param description description of the GodownStockDetails
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * To Get the status from GodownStockDetails table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To set the status values for the GodownStockDetails table.
     * 
     * @param status status of the GodownStockDetails
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the price from GodownStockDetails table
     * 
     * @return price
     */
    public Float getPrice() {
        return price;
    }

    /**
     * To set the price values for the GodownStockDetails table.
     * 
     * @param price price of the GodownStockDetails
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * To Get the godownStockId from GodownStockDetails table
     * 
     * @return godownStockId
     */
    public Long getGodownStockId() {
        return godownStockId;
    }

    /**
     * To set the godownStockId values for the GodownStockDetails table.
     * 
     * @param godownStockId godownStockId of the GodownStockDetails
     */
    public void setGodownStockId(Long godownStockId) {
        this.godownStockId = godownStockId;
    }

    /**
     * To Get the financialYearGodownStockDetailsId from GodownStockDetails table
     * 
     * @return financialYearGodownStockDetailsId
     */
    public Long getFinancialYearGodownStockDetailsId() {
        return financialYearGodownStockDetailsId;
    }

    /**
     * To set the financialYearGodownStockDetailsId values for the GodownStockDetails table.
     * 
     * @param financialYearGodownStockDetailsId of the GodownStockDetails
     */
    public void setFinancialYearGodownStockDetailsId(Long financialYearSettingsId) {
        this.financialYearGodownStockDetailsId = financialYearSettingsId;
    }

    /**
     * To Get the financialYearGodownStockDetailsBatchName from GodownStockDetails table
     * 
     * @return financialYearGodownStockDetailsBatchName
     */
    public String getFinancialYearGodownStockDetailsBatchName() {
        return financialYearGodownStockDetailsBatchName;
    }

    /**
     * To set the financialYearGodownStockDetailsBatchName values for the GodownStockDetails table.
     * 
     * @param financialYearGodownStockDetailsBatchName of the GodownStockDetails
     */
    public void setFinancialYearGodownStockDetailsBatchName(String financialYearSettingsBatchName) {
        this.financialYearGodownStockDetailsBatchName = financialYearSettingsBatchName;
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

        GodownStockDetailsDTO godownStockDetailsDTO = (GodownStockDetailsDTO) o;
        if (godownStockDetailsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), godownStockDetailsDTO.getId());
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
        return "GodownStockDetailsDTO{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", quantity=" + getQuantity() +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            ", price=" + getPrice() +
            ", godownStock=" + getGodownStockId() +
            ", financialYearGodownStockDetails=" + getFinancialYearGodownStockDetailsId() +
            ", financialYearGodownStockDetails='" + getFinancialYearGodownStockDetailsBatchName() + "'" +
            "}";
    }
}
