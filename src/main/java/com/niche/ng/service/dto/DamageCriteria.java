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
 * Criteria class for the Damage entity. This class is used in DamageResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /damages?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DamageCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LongFilter noOfQuantity;

    private LocalDateFilter date;

    private IntegerFilter status;

    private LongFilter batchId;

    private LongFilter descriptionId;

    private LongFilter damageAreaId;

    private LongFilter financialYearDamageId;

    public DamageCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getNoOfQuantity() {
        return noOfQuantity;
    }

    public void setNoOfQuantity(LongFilter noOfQuantity) {
        this.noOfQuantity = noOfQuantity;
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

    public LongFilter getBatchId() {
        return batchId;
    }

    public void setBatchId(LongFilter batchId) {
        this.batchId = batchId;
    }

    public LongFilter getDescriptionId() {
        return descriptionId;
    }

    public void setDescriptionId(LongFilter descriptionId) {
        this.descriptionId = descriptionId;
    }

    public LongFilter getDamageAreaId() {
        return damageAreaId;
    }

    public void setDamageAreaId(LongFilter damageAreaId) {
        this.damageAreaId = damageAreaId;
    }

    public LongFilter getFinancialYearDamageId() {
        return financialYearDamageId;
    }

    public void setFinancialYearDamageId(LongFilter financialYearDamageId) {
        this.financialYearDamageId = financialYearDamageId;
    }

    @Override
    public String toString() {
        return "DamageCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (noOfQuantity != null ? "noOfQuantity=" + noOfQuantity + ", " : "") +
                (date != null ? "date=" + date + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (batchId != null ? "batchId=" + batchId + ", " : "") +
                (descriptionId != null ? "descriptionId=" + descriptionId + ", " : "") +
                (damageAreaId != null ? "damageAreaId=" + damageAreaId + ", " : "") +
                (financialYearDamageId != null ? "financialYearDamageId=" + financialYearDamageId + ", " : "") +
            "}";
    }

}
