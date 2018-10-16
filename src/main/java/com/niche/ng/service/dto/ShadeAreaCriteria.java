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
 * Criteria class for the ShadeArea entity. This class is used in ShadeAreaResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /shade-areas?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ShadeAreaCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LongFilter noOfSeedlings;

    private LocalDateFilter date;

    private IntegerFilter status;

    private IntegerFilter damage;

    private IntegerFilter saplings;

    private LongFilter batchId;

    private LongFilter financialYearShadeAreaId;

    public ShadeAreaCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getNoOfSeedlings() {
        return noOfSeedlings;
    }

    public void setNoOfSeedlings(LongFilter noOfSeedlings) {
        this.noOfSeedlings = noOfSeedlings;
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

    public IntegerFilter getDamage() {
        return damage;
    }

    public void setDamage(IntegerFilter damage) {
        this.damage = damage;
    }

    public IntegerFilter getSaplings() {
        return saplings;
    }

    public void setSaplings(IntegerFilter saplings) {
        this.saplings = saplings;
    }

    public LongFilter getBatchId() {
        return batchId;
    }

    public void setBatchId(LongFilter batchId) {
        this.batchId = batchId;
    }

    public LongFilter getFinancialYearShadeAreaId() {
        return financialYearShadeAreaId;
    }

    public void setFinancialYearShadeAreaId(LongFilter financialYearShadeAreaId) {
        this.financialYearShadeAreaId = financialYearShadeAreaId;
    }

    @Override
    public String toString() {
        return "ShadeAreaCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (noOfSeedlings != null ? "noOfSeedlings=" + noOfSeedlings + ", " : "") +
                (date != null ? "date=" + date + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (damage != null ? "damage=" + damage + ", " : "") +
                (saplings != null ? "saplings=" + saplings + ", " : "") +
                (batchId != null ? "batchId=" + batchId + ", " : "") +
                (financialYearShadeAreaId != null ? "financialYearShadeAreaId=" + financialYearShadeAreaId + ", " : "") +
            "}";
    }

}
