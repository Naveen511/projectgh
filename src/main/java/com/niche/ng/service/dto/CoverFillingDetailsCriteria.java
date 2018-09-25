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
 * Criteria class for the CoverFillingDetails entity. This class is used in CoverFillingDetailsResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /cover-filling-details?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CoverFillingDetailsCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private IntegerFilter quantity;

    private LocalDateFilter date;

    private IntegerFilter status;

    private StringFilter description;

    private LongFilter coverFillingId;

    private LongFilter damageTypeId;

    private LongFilter coverFillingDamageDescriptionId;

    public CoverFillingDetailsCriteria() {
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

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public LongFilter getCoverFillingId() {
        return coverFillingId;
    }

    public void setCoverFillingId(LongFilter coverFillingId) {
        this.coverFillingId = coverFillingId;
    }

    public LongFilter getDamageTypeId() {
        return damageTypeId;
    }

    public void setDamageTypeId(LongFilter damageTypeId) {
        this.damageTypeId = damageTypeId;
    }

    public LongFilter getCoverFillingDamageDescriptionId() {
        return coverFillingDamageDescriptionId;
    }

    public void setCoverFillingDamageDescriptionId(LongFilter coverFillingDamageDescriptionId) {
        this.coverFillingDamageDescriptionId = coverFillingDamageDescriptionId;
    }

    @Override
    public String toString() {
        return "CoverFillingDetailsCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (quantity != null ? "quantity=" + quantity + ", " : "") +
                (date != null ? "date=" + date + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (coverFillingId != null ? "coverFillingId=" + coverFillingId + ", " : "") +
                (damageTypeId != null ? "damageTypeId=" + damageTypeId + ", " : "") +
                (coverFillingDamageDescriptionId != null ? "coverFillingDamageDescriptionId=" + coverFillingDamageDescriptionId + ", " : "") +
            "}";
    }

}
