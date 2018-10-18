/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs GodownPurchaseDetailsCriteria and
                            declared the table fields, data types for GodownPurchaseDetails table.
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
 * Criteria class for the GodownPurchaseDetails entity. This class is used in GodownPurchaseDetailsResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /godown-purchase-details?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class GodownPurchaseDetailsCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LongFilter quantity;

    private LocalDateFilter date;

    private FloatFilter price;

    private StringFilter ownedBy;

    private StringFilter vendorName;

    private StringFilter vendorAddress;

    private LongFilter vendorPhone;

    private StringFilter description;

    private IntegerFilter status;

    private LongFilter pickListVarietyId;

    private LongFilter pickListCategoryId;

    private LongFilter pickListQuantityTypeId;

    private LongFilter godownId;

    private LongFilter financialYearGodownPurchaseId;

    public GodownPurchaseDetailsCriteria() {
    }

    /**
     * To Get the Id from GodownPurchaseDetails table
     * 
     * @return id
     */
    public LongFilter getId() {
        return id;
    }

    /**
     * To set the id values for the GodownPurchaseDetails table.
     * 
     * @param id id value
     */
    public void setId(LongFilter id) {
        this.id = id;
    }

    /**
     * To Get the quantity from GodownPurchaseDetails table
     * 
     * @return quantity
     */
    public LongFilter getQuantity() {
        return quantity;
    }

    /**
     * To set the quantity values for the GodownPurchaseDetails table.
     * 
     * @param quantity quantity of the godownPurchaseDetails
     */
    public void setQuantity(LongFilter quantity) {
        this.quantity = quantity;
    }

    /**
     * To Get the date from GodownPurchaseDetails table
     * 
     * @return date
     */
    public LocalDateFilter getDate() {
        return date;
    }

    /**
     * To set the date values for the GodownPurchaseDetails table.
     * 
     * @param date date of the godownPurchaseDetails
     */
    public void setDate(LocalDateFilter date) {
        this.date = date;
    }

    /**
     * To Get the price from GodownPurchaseDetails table
     * 
     * @return price
     */
    public FloatFilter getPrice() {
        return price;
    }

    /**
     * To set the price values for the GodownPurchaseDetails table.
     * 
     * @param price price of the godownPurchaseDetails
     */
    public void setPrice(FloatFilter price) {
        this.price = price;
    }

    /**
     * To Get the ownedBy from GodownPurchaseDetails table
     * 
     * @return ownedBy
     */
    public StringFilter getOwnedBy() {
        return ownedBy;
    }

    /**
     * To set the ownedBy values for the GodownPurchaseDetails table.
     * 
     * @param ownedBy ownedBy of the godownPurchaseDetails
     */
    public void setOwnedBy(StringFilter ownedBy) {
        this.ownedBy = ownedBy;
    }

    /**
     * To Get the vendorName from GodownPurchaseDetails table
     * 
     * @return vendorName
     */
    public StringFilter getVendorName() {
        return vendorName;
    }

    /**
     * To set the vendorName values for the GodownPurchaseDetails table.
     * 
     * @param vendorName vendorName of the godownPurchaseDetails
     */
    public void setVendorName(StringFilter vendorName) {
        this.vendorName = vendorName;
    }

    /**
     * To Get the vendorAddress from GodownPurchaseDetails table
     * 
     * @return vendorAddress
     */
    public StringFilter getVendorAddress() {
        return vendorAddress;
    }

    /**
     * To set the vendorAddress values for the GodownPurchaseDetails table.
     * 
     * @param vendorAddress vendorAddress of the godownPurchaseDetails
     */
    public void setVendorAddress(StringFilter vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

    /**
     * To Get the vendorPhone from GodownPurchaseDetails table
     * 
     * @return vendorPhone
     */
    public LongFilter getVendorPhone() {
        return vendorPhone;
    }

    /**
     * To set the vendorPhone values for the GodownPurchaseDetails table.
     * 
     * @param vendorPhone vendorPhone of the godownPurchaseDetails
     */
    public void setVendorPhone(LongFilter vendorPhone) {
        this.vendorPhone = vendorPhone;
    }

    /**
     * To Get the description from GodownPurchaseDetails table
     * 
     * @return description
     */
    public StringFilter getDescription() {
        return description;
    }

    /**
     * To set the description values for the GodownPurchaseDetails table.
     * 
     * @param description description of the godownPurchaseDetails
     */
    public void setDescription(StringFilter description) {
        this.description = description;
    }

    /**
     * To Get the status from GodownPurchaseDetails table
     * 
     * @return status
     */
    public IntegerFilter getStatus() {
        return status;
    }

    /**
     * To set the status values for the GodownPurchaseDetails table.
     * 
     * @param status status of the godownPurchaseDetails
     */
    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    /**
     * To Get the pickListVarietyId from GodownPurchaseDetails table
     * 
     * @return pickListVarietyId
     */
    public LongFilter getPickListVarietyId() {
        return pickListVarietyId;
    }

    /**
     * To set the pickListVarietyId values for the GodownPurchaseDetails table.
     * 
     * @param pickListVarietyId pickListVarietyId of the godownPurchaseDetails
     */
    public void setPickListVarietyId(LongFilter pickListVarietyId) {
        this.pickListVarietyId = pickListVarietyId;
    }

    /**
     * To Get the pickListCategoryId from GodownPurchaseDetails table
     * 
     * @return pickListCategoryId
     */
    public LongFilter getPickListCategoryId() {
        return pickListCategoryId;
    }

    /**
     * To set the pickListCategoryId values for the GodownPurchaseDetails table.
     * 
     * @param pickListCategoryId pickListCategoryId of the godownPurchaseDetails
     */
    public void setPickListCategoryId(LongFilter pickListCategoryId) {
        this.pickListCategoryId = pickListCategoryId;
    }

    /**
     * To Get the pickListQuantityTypeId from GodownPurchaseDetails table
     * 
     * @return pickListQuantityTypeId
     */
    public LongFilter getPickListQuantityTypeId() {
        return pickListQuantityTypeId;
    }

    /**
     * To set the pickListQuantityTypeId values for the GodownPurchaseDetails table.
     * 
     * @param pickListQuantityTypeId pickListQuantityTypeId of the godownPurchaseDetails
     */
    public void setPickListQuantityTypeId(LongFilter pickListQuantityTypeId) {
        this.pickListQuantityTypeId = pickListQuantityTypeId;
    }

    /**
     * To Get the godownId from GodownPurchaseDetails table
     * 
     * @return godownId
     */
    public LongFilter getGodownId() {
        return godownId;
    }

    /**
     * To set the godownId values for the GodownPurchaseDetails table.
     * 
     * @param godownId godownId of the godownPurchaseDetails
     */
    public void setGodownId(LongFilter godownId) {
        this.godownId = godownId;
    }

    /**
     * To Get the financialYearGodownPurchaseId from GodownPurchaseDetails table
     * 
     * @return financialYearGodownPurchaseId
     */
    public LongFilter getFinancialYearGodownPurchaseId() {
        return financialYearGodownPurchaseId;
    }

    /**
     * To set the financialYearGodownPurchaseId values for the GodownPurchaseDetails table.
     * 
     * @param financialYearGodownPurchaseId financialYearGodownPurchaseId of the godownPurchaseDetails
     */
    public void setFinancialYearGodownPurchaseId(LongFilter financialYearGodownPurchaseId) {
        this.financialYearGodownPurchaseId = financialYearGodownPurchaseId;
    }

    /**
     * toString to get the values
     * 
     * @return values
     */
    @Override
    public String toString() {
        return "GodownPurchaseDetailsCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (quantity != null ? "quantity=" + quantity + ", " : "") +
                (date != null ? "date=" + date + ", " : "") +
                (price != null ? "price=" + price + ", " : "") +
                (ownedBy != null ? "ownedBy=" + ownedBy + ", " : "") +
                (vendorName != null ? "vendorName=" + vendorName + ", " : "") +
                (vendorAddress != null ? "vendorAddress=" + vendorAddress + ", " : "") +
                (vendorPhone != null ? "vendorPhone=" + vendorPhone + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (pickListVarietyId != null ? "pickListVarietyId=" + pickListVarietyId + ", " : "") +
                (pickListCategoryId != null ? "pickListCategoryId=" + pickListCategoryId + ", " : "") +
                (pickListQuantityTypeId != null ? "pickListQuantityTypeId=" + pickListQuantityTypeId + ", " : "") +
                (godownId != null ? "godownId=" + godownId + ", " : "") +
                (financialYearGodownPurchaseId != null ? "financialYearGodownPurchaseId=" + financialYearGodownPurchaseId + ", " : "") +
            "}";
    }

}
