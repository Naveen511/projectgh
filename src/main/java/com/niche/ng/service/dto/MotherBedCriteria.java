package com.niche.ng.service.dto;

import java.io.Serializable;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;






/**
 * Criteria class for the MotherBed entity. This class is used in MotherBedResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /mother-beds?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MotherBedCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private IntegerFilter value;

    private IntegerFilter status;

    private LongFilter nurseryId;

    private LongFilter batchMotherBedId;

    public MotherBedCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getValue() {
        return value;
    }

    public void setValue(IntegerFilter value) {
        this.value = value;
    }

    public IntegerFilter getStatus() {
        return status;
    }

    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    public LongFilter getNurseryId() {
        return nurseryId;
    }

    public void setNurseryId(LongFilter nurseryId) {
        this.nurseryId = nurseryId;
    }

    public LongFilter getBatchMotherBedId() {
        return batchMotherBedId;
    }

    public void setBatchMotherBedId(LongFilter batchMotherBedId) {
        this.batchMotherBedId = batchMotherBedId;
    }

    @Override
    public String toString() {
        return "MotherBedCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (value != null ? "value=" + value + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (nurseryId != null ? "nurseryId=" + nurseryId + ", " : "") +
                (batchMotherBedId != null ? "batchMotherBedId=" + batchMotherBedId + ", " : "") +
            "}";
    }

}
