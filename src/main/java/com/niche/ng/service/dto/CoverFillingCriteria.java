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
 * Criteria class for the CoverFilling entity. This class is used in CoverFillingResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /cover-fillings?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CoverFillingCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private IntegerFilter noOfCover;

    private LocalDateFilter date;

    private StringFilter description;

    private IntegerFilter status;

    private IntegerFilter damageQuantity;

    private LongFilter coverFillingDetailsId;

    public CoverFillingCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getNoOfCover() {
        return noOfCover;
    }

    public void setNoOfCover(IntegerFilter noOfCover) {
        this.noOfCover = noOfCover;
    }

    public LocalDateFilter getDate() {
        return date;
    }

    public void setDate(LocalDateFilter date) {
        this.date = date;
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

    public IntegerFilter getDamageQuantity() {
        return damageQuantity;
    }

    public void setDamageQuantity(IntegerFilter damageQuantity) {
        this.damageQuantity = damageQuantity;
    }

    public LongFilter getCoverFillingDetailsId() {
        return coverFillingDetailsId;
    }

    public void setCoverFillingDetailsId(LongFilter coverFillingDetailsId) {
        this.coverFillingDetailsId = coverFillingDetailsId;
    }

    @Override
    public String toString() {
        return "CoverFillingCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (noOfCover != null ? "noOfCover=" + noOfCover + ", " : "") +
                (date != null ? "date=" + date + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (damageQuantity != null ? "damageQuantity=" + damageQuantity + ", " : "") +
                (coverFillingDetailsId != null ? "coverFillingDetailsId=" + coverFillingDetailsId + ", " : "") +
            "}";
    }

}
