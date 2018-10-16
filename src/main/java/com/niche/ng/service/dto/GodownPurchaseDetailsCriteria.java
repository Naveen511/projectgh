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

    private LongFilter price;

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

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getQuantity() {
        return quantity;
    }

    public void setQuantity(LongFilter quantity) {
        this.quantity = quantity;
    }

    public LocalDateFilter getDate() {
        return date;
    }

    public void setDate(LocalDateFilter date) {
        this.date = date;
    }

    public LongFilter getPrice() {
        return price;
    }

    public void setPrice(LongFilter price) {
        this.price = price;
    }

    public StringFilter getOwnedBy() {
        return ownedBy;
    }

    public void setOwnedBy(StringFilter ownedBy) {
        this.ownedBy = ownedBy;
    }

    public StringFilter getVendorName() {
        return vendorName;
    }

    public void setVendorName(StringFilter vendorName) {
        this.vendorName = vendorName;
    }

    public StringFilter getVendorAddress() {
        return vendorAddress;
    }

    public void setVendorAddress(StringFilter vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

    public LongFilter getVendorPhone() {
        return vendorPhone;
    }

    public void setVendorPhone(LongFilter vendorPhone) {
        this.vendorPhone = vendorPhone;
    }

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
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

    public LongFilter getPickListQuantityTypeId() {
        return pickListQuantityTypeId;
    }

    public void setPickListQuantityTypeId(LongFilter pickListQuantityTypeId) {
        this.pickListQuantityTypeId = pickListQuantityTypeId;
    }

    public LongFilter getGodownId() {
        return godownId;
    }

    public void setGodownId(LongFilter godownId) {
        this.godownId = godownId;
    }

    public LongFilter getFinancialYearGodownPurchaseId() {
        return financialYearGodownPurchaseId;
    }

    public void setFinancialYearGodownPurchaseId(LongFilter financialYearGodownPurchaseId) {
        this.financialYearGodownPurchaseId = financialYearGodownPurchaseId;
    }

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
