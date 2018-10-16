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

    public PointOfSaleDetailsCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getQuantity() {
        return quantity;
    }

    public void setQuantity(IntegerFilter quantity) {
        this.quantity = quantity;
    }

    public StringFilter getPurposeOfTaking() {
        return purposeOfTaking;
    }

    public void setPurposeOfTaking(StringFilter purposeOfTaking) {
        this.purposeOfTaking = purposeOfTaking;
    }

    public StringFilter getDonorName() {
        return donorName;
    }

    public void setDonorName(StringFilter donorName) {
        this.donorName = donorName;
    }

    public StringFilter getDonorAddress() {
        return donorAddress;
    }

    public void setDonorAddress(StringFilter donorAddress) {
        this.donorAddress = donorAddress;
    }

    public StringFilter getContactNo() {
        return contactNo;
    }

    public void setContactNo(StringFilter contactNo) {
        this.contactNo = contactNo;
    }

    public FloatFilter getDiscount() {
        return discount;
    }

    public void setDiscount(FloatFilter discount) {
        this.discount = discount;
    }

    public FloatFilter getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(FloatFilter discountAmount) {
        this.discountAmount = discountAmount;
    }

    public FloatFilter getCollectedAmount() {
        return collectedAmount;
    }

    public void setCollectedAmount(FloatFilter collectedAmount) {
        this.collectedAmount = collectedAmount;
    }

    public LocalDateFilter getDate() {
        return date;
    }

    public void setDate(LocalDateFilter date) {
        this.date = date;
    }

    public IntegerFilter getStatus() {
        return status;
    }

    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    public LongFilter getPickListVarietyId() {
        return pickListVarietyId;
    }

    public void setPickListVarietyId(LongFilter pickListVarietyId) {
        this.pickListVarietyId = pickListVarietyId;
    }

    public LongFilter getPickListCategoryId() {
        return pickListCategoryId;
    }

    public void setPickListCategoryId(LongFilter pickListCategoryId) {
        this.pickListCategoryId = pickListCategoryId;
    }

    public LongFilter getNurseryStockId() {
        return nurseryStockId;
    }

    public void setNurseryStockId(LongFilter nurseryStockId) {
        this.nurseryStockId = nurseryStockId;
    }

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
