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
 * Criteria class for the CommonSettings entity. This class is used in CommonSettingsResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /common-settings?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CommonSettingsCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private IntegerFilter daysToCloseBatch;

    private IntegerFilter status;

    public CommonSettingsCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getDaysToCloseBatch() {
        return daysToCloseBatch;
    }

    public void setDaysToCloseBatch(IntegerFilter daysToCloseBatch) {
        this.daysToCloseBatch = daysToCloseBatch;
    }

    public IntegerFilter getStatus() {
        return status;
    }

    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CommonSettingsCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (daysToCloseBatch != null ? "daysToCloseBatch=" + daysToCloseBatch + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
            "}";
    }

}
