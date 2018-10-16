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

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LocalDateFilter getDate() {
        return date;
    }

    public void setDate(LocalDateFilter date) {
        this.date = date;
    }

    public LongFilter getQuantity() {
        return quantity;
    }

    public void setQuantity(LongFilter quantity) {
        this.quantity = quantity;
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

    public FloatFilter getPrice() {
        return price;
    }

    public void setPrice(FloatFilter price) {
        this.price = price;
    }

    public LongFilter getGodownStockId() {
        return godownStockId;
    }

    public void setGodownStockId(LongFilter godownStockId) {
        this.godownStockId = godownStockId;
    }

    public LongFilter getFinancialYearGodownStockDetailsId() {
        return financialYearGodownStockDetailsId;
    }

    public void setFinancialYearGodownStockDetailsId(LongFilter financialYearGodownStockDetailsId) {
        this.financialYearGodownStockDetailsId = financialYearGodownStockDetailsId;
    }

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
