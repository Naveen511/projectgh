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
 * Criteria class for the PickList entity. This class is used in PickListResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /pick-lists?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PickListCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter pickListName;

    private IntegerFilter status;

    private LongFilter pickListValuesId;

    public PickListCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getPickListName() {
        return pickListName;
    }

    public void setPickListName(StringFilter pickListName) {
        this.pickListName = pickListName;
    }

    public IntegerFilter getStatus() {
        return status;
    }

    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    public LongFilter getPickListValuesId() {
        return pickListValuesId;
    }

    public void setPickListValuesId(LongFilter pickListValuesId) {
        this.pickListValuesId = pickListValuesId;
    }

    @Override
    public String toString() {
        return "PickListCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (pickListName != null ? "pickListName=" + pickListName + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (pickListValuesId != null ? "pickListValuesId=" + pickListValuesId + ", " : "") +
            "}";
    }

}
