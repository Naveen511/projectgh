/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs PointOfSaleDetailsCriteria and
                            declared the table fields, data types for PointOfSaleDetails table.
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
 * Criteria class for the PointOfSaleDetails entity. This class is used in PointOfSaleDetailsResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /point-of-sale-details?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PointOfSaleDetailsCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private IntegerFilter quantity;

    private StringFilter purposeOfTaking;

    private StringFilter donorName;

    private StringFilter donorAddress;

    private StringFilter contactNo;

    private FloatFilter discount;

    private FloatFilter discountAmount;

    private FloatFilter collectedAmount;

    private LocalDateFilter date;

    private IntegerFilter status;

    private LongFilter pickListVarietyId;

    private LongFilter pickListCategoryId;

    private LongFilter nurseryStockId;

    /**
     * PointOfSaleDetailsCriteria from PointOfSaleDetails table
     */
    public PointOfSaleDetailsCriteria() {
    }

    /**
     * To Get the Id from PointOfSaleDetails table
     * 
     * @return id
     */
    public LongFilter getId() {
        return id;
    }

    /**
     * To set the id values for the PointOfSaleDetails table.
     * 
     * @param id id of the PointOfSaleDetails
     */
    public void setId(LongFilter id) {
        this.id = id;
    }

    /**
     * To Get the quantity from PointOfSaleDetails table
     * 
     * @return quantity
     */
    public IntegerFilter getQuantity() {
        return quantity;
    }

    /**
     * To set the quantity values for the PointOfSaleDetails table.
     * 
     * @param quantity quantity of the PointOfSaleDetails
     */
    public void setQuantity(IntegerFilter quantity) {
        this.quantity = quantity;
    }

    /**
     * To Get the purposeOfTaking from PointOfSaleDetails table
     * 
     * @return purposeOfTaking
     */
    public StringFilter getPurposeOfTaking() {
        return purposeOfTaking;
    }

    /**
     * To set the purposeOfTaking values for the PointOfSaleDetails table.
     * 
     * @param purposeOfTaking purposeOfTaking of the PointOfSaleDetails
     */
    public void setPurposeOfTaking(StringFilter purposeOfTaking) {
        this.purposeOfTaking = purposeOfTaking;
    }

    /**
     * To Get the donorName from PointOfSaleDetails table
     * 
     * @return donorName
     */
    public StringFilter getDonorName() {
        return donorName;
    }

    /**
     * To set the donorName values for the PointOfSaleDetails table.
     * 
     * @param donorName donorName of the PointOfSaleDetails
     */
    public void setDonorName(StringFilter donorName) {
        this.donorName = donorName;
    }

    /**
     * To Get the donorAddress from PointOfSaleDetails table
     * 
     * @return donorAddress
     */
    public StringFilter getDonorAddress() {
        return donorAddress;
    }

    /**
     * To set the donorAddress values for the PointOfSaleDetails table.
     * 
     * @param donorAddress donorAddress of the PointOfSaleDetails
     */
    public void setDonorAddress(StringFilter donorAddress) {
        this.donorAddress = donorAddress;
    }

    /**
     * To Get the contactNo from PointOfSaleDetails table
     * 
     * @return contactNo
     */
    public StringFilter getContactNo() {
        return contactNo;
    }

    /**
     * To set the contactNo values for the PointOfSaleDetails table.
     * 
     * @param contactNo contactNo of the PointOfSaleDetails
     */
    public void setContactNo(StringFilter contactNo) {
        this.contactNo = contactNo;
    }

    /**
     * To Get the discount from PointOfSaleDetails table
     * 
     * @return discount
     */
    public FloatFilter getDiscount() {
        return discount;
    }

    /**
     * To set the discount values for the PointOfSaleDetails table.
     * 
     * @param discount discount of the PointOfSaleDetails
     */
    public void setDiscount(FloatFilter discount) {
        this.discount = discount;
    }

    /**
     * To Get the discountAmount from PointOfSaleDetails table
     * 
     * @return discountAmount
     */
    public FloatFilter getDiscountAmount() {
        return discountAmount;
    }

    /**
     * To set the discountAmount values for the PointOfSaleDetails table.
     * 
     * @param discountAmount discountAmount of the PointOfSaleDetails
     */
    public void setDiscountAmount(FloatFilter discountAmount) {
        this.discountAmount = discountAmount;
    }

    /**
     * To Get the collectedAmount from PointOfSaleDetails table
     * 
     * @return collectedAmount
     */
    public FloatFilter getCollectedAmount() {
        return collectedAmount;
    }

    /**
     * To set the collectedAmount values for the PointOfSaleDetails table.
     * 
     * @param collectedAmount collectedAmount of the PointOfSaleDetails
     */
    public void setCollectedAmount(FloatFilter collectedAmount) {
        this.collectedAmount = collectedAmount;
    }

    /**
     * To Get the date from PointOfSaleDetails table
     * 
     * @return date
     */
    public LocalDateFilter getDate() {
        return date;
    }

    /**
     * To set the date values for the PointOfSaleDetails table.
     * 
     * @param date date of the PointOfSaleDetails
     */
    public void setDate(LocalDateFilter date) {
        this.date = date;
    }

    /**
     * To Get the status from PointOfSaleDetails table
     * 
     * @return status
     */
    public IntegerFilter getStatus() {
        return status;
    }

    /**
     * To set the status values for the PointOfSaleDetails table.
     * 
     * @param status status of the PointOfSaleDetails
     */
    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    /**
     * To Get the pickListVarietyId from PointOfSaleDetails table
     * 
     * @return pickListVarietyId
     */
    public LongFilter getPickListVarietyId() {
        return pickListVarietyId;
    }

    /**
     * To set the pickListVarietyId values for the PointOfSaleDetails table.
     * 
     * @param pickListVarietyId pickListVarietyId of the PointOfSaleDetails
     */
    public void setPickListVarietyId(LongFilter pickListVarietyId) {
        this.pickListVarietyId = pickListVarietyId;
    }

    /**
     * To Get the pickListCategoryId from PointOfSaleDetails table
     * 
     * @return pickListCategoryId
     */
    public LongFilter getPickListCategoryId() {
        return pickListCategoryId;
    }

    /**
     * To set the pickListCategoryId values for the PointOfSaleDetails table.
     * 
     * @param pickListCategoryId pickListCategoryId of the PointOfSaleDetails
     */
    public void setPickListCategoryId(LongFilter pickListCategoryId) {
        this.pickListCategoryId = pickListCategoryId;
    }

    /**
     * To Get the nurseryStockId from PointOfSaleDetails table
     * 
     * @return nurseryStockId
     */
    public LongFilter getNurseryStockId() {
        return nurseryStockId;
    }

    /**
     * To set the nurseryStockId values for the PointOfSaleDetails table.
     * 
     * @param nurseryStockId nurseryStockId of the PointOfSaleDetails
     */
    public void setNurseryStockId(LongFilter nurseryStockId) {
        this.nurseryStockId = nurseryStockId;
    }

    /**
     * toString to get the values
     * 
     * @return values
     */
    @Override
    public String toString() {
        return "PointOfSaleDetailsCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (quantity != null ? "quantity=" + quantity + ", " : "") +
                (purposeOfTaking != null ? "purposeOfTaking=" + purposeOfTaking + ", " : "") +
                (donorName != null ? "donorName=" + donorName + ", " : "") +
                (donorAddress != null ? "donorAddress=" + donorAddress + ", " : "") +
                (contactNo != null ? "contactNo=" + contactNo + ", " : "") +
                (discount != null ? "discount=" + discount + ", " : "") +
                (discountAmount != null ? "discountAmount=" + discountAmount + ", " : "") +
                (collectedAmount != null ? "collectedAmount=" + collectedAmount + ", " : "") +
                (date != null ? "date=" + date + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (pickListVarietyId != null ? "pickListVarietyId=" + pickListVarietyId + ", " : "") +
                (pickListCategoryId != null ? "pickListCategoryId=" + pickListCategoryId + ", " : "") +
                (nurseryStockId != null ? "nurseryStockId=" + nurseryStockId + ", " : "") +
            "}";
    }

}
