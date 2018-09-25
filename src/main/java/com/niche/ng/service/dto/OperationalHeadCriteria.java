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
 * Criteria class for the OperationalHead entity. This class is used in OperationalHeadResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /operational-heads?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class OperationalHeadCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter name;

    private StringFilter description;

    private IntegerFilter status;

    private LongFilter zonalId;

    private LongFilter mapZonalWithOhId;

    public OperationalHeadCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
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

    public LongFilter getZonalId() {
        return zonalId;
    }

    public void setZonalId(LongFilter zonalId) {
        this.zonalId = zonalId;
    }

    public LongFilter getMapZonalWithOhId() {
        return mapZonalWithOhId;
    }

    public void setMapZonalWithOhId(LongFilter mapZonalWithOhId) {
        this.mapZonalWithOhId = mapZonalWithOhId;
    }

    @Override
    public String toString() {
        return "OperationalHeadCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (zonalId != null ? "zonalId=" + zonalId + ", " : "") +
                (mapZonalWithOhId != null ? "mapZonalWithOhId=" + mapZonalWithOhId + ", " : "") +
            "}";
    }

}
