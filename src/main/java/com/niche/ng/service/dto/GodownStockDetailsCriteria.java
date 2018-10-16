/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs GodownStockDetailsCriteria and
                            declared the table fields, data types for GodownStockDetails table.
 *
 *******************************************************************************/
package com.niche.ng.service.dto;

import java.io.Serializable;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;


import io.github.jhipster.service.filter.LocalDateFilter;



/**
 * Criteria class for the GodownStockDetails entity. This class is used in GodownStockDetailsResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /godown-stock-details?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class GodownStockDetailsCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LocalDateFilter date;

    private LongFilter quantity;

    private StringFilter description;

    private IntegerFilter status;

    private FloatFilter price;

    private LongFilter godownStockId;

    private LongFilter financialYearGodownStockDetailsId;

    public GodownStockDetailsCriteria() {
    }

    /**
     * To Get the Id from GodownStockDetails table
     * 
     * @return id
     */
    public LongFilter getId() {
        return id;
    }

    /**
     * To set the id values for the GodownStockDetails table.
     * 
     * @param id id of the GodownStockDetails
     */
    public void setId(LongFilter id) {
        this.id = id;
    }

    /**
     * To Get the date from GodownStockDetails table
     * 
     * @return date
     */
    public LocalDateFilter getDate() {
        return date;
    }

    /**
     * To set the date values for the GodownStockDetails table.
     * 
     * @param date date of the GodownStockDetails
     */
    public void setDate(LocalDateFilter date) {
        this.date = date;
    }

    /**
     * To Get the quantity from GodownStockDetails table
     * 
     * @return quantity
     */
    public LongFilter getQuantity() {
        return quantity;
    }

    /**
     * To set the quantity values for the GodownStockDetails table.
     * 
     * @param quantity quantity of the GodownStockDetails
     */
    public void setQuantity(LongFilter quantity) {
        this.quantity = quantity;
    }

    /**
     * To Get the description from GodownStockDetails table
     * 
     * @return description
     */
    public StringFilter getDescription() {
        return description;
    }

    /**
     * To set the description values for the GodownStockDetails table.
     * 
     * @param description description of the GodownStockDetails
     */
    public void setDescription(StringFilter description) {
        this.description = description;
    }

    /**
     * To Get the status from GodownStockDetails table
     * 
     * @return status
     */
    public IntegerFilter getStatus() {
        return status;
    }

    /**
     * To set the status values for the GodownStockDetails table.
     * 
     * @param status status of the GodownStockDetails
     */
    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    /**
     * To Get the price from GodownStockDetails table
     * 
     * @return price
     */
    public FloatFilter getPrice() {
        return price;
    }

    /**
     * To set the price values for the GodownStockDetails table.
     * 
     * @param price price of the GodownStockDetails
     */
    public void setPrice(FloatFilter price) {
        this.price = price;
    }

    /**
     * To Get the godownStockId from GodownStockDetails table
     * 
     * @return godownStockId
     */
    public LongFilter getGodownStockId() {
        return godownStockId;
    }

    /**
     * To set the godownStockId values for the GodownStockDetails table.
     * 
     * @param godownStockId godownStockId of the GodownStockDetails
     */
    public void setGodownStockId(LongFilter godownStockId) {
        this.godownStockId = godownStockId;
    }

    /**
     * To Get the financialYearGodownStockDetailsId from GodownStockDetails table
     * 
     * @return financialYearGodownStockDetailsId
     */
    public LongFilter getFinancialYearGodownStockDetailsId() {
        return financialYearGodownStockDetailsId;
    }

    /**
     * To set the financialYearGodownStockDetailsId values for the GodownStockDetails table.
     * 
     * @param financialYearGodownStockDetailsId of the GodownStockDetails
     */
    public void setFinancialYearGodownStockDetailsId(LongFilter financialYearGodownStockDetailsId) {
        this.financialYearGodownStockDetailsId = financialYearGodownStockDetailsId;
    }

    /**
     * toString to get the values
     * 
     * @return values
     */
    @Override
    public String toString() {
        return "GodownStockDetailsCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (date != null ? "date=" + date + ", " : "") +
                (quantity != null ? "quantity=" + quantity + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (price != null ? "price=" + price + ", " : "") +
                (godownStockId != null ? "godownStockId=" + godownStockId + ", " : "") +
                (financialYearGodownStockDetailsId != null ? "financialYearGodownStockDetailsId=" + financialYearGodownStockDetailsId + ", " : "") +
            "}";
    }

}
