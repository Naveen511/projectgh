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
 * Criteria class for the Godown entity. This class is used in GodownResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /godowns?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class GodownCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter name;

    private StringFilter address;

    private StringFilter incharge;

    private IntegerFilter status;

    private LongFilter godownPurchaseDetailsId;

    private LongFilter godownStocksId;

    private LongFilter financialYearGodownId;

    public GodownCriteria() {
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

    public StringFilter getAddress() {
        return address;
    }

    public void setAddress(StringFilter address) {
        this.address = address;
    }

    public StringFilter getIncharge() {
        return incharge;
    }

    public void setIncharge(StringFilter incharge) {
        this.incharge = incharge;
    }

    public IntegerFilter getStatus() {
        return status;
    }

    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    public LongFilter getGodownPurchaseDetailsId() {
        return godownPurchaseDetailsId;
    }

    public void setGodownPurchaseDetailsId(LongFilter godownPurchaseDetailsId) {
        this.godownPurchaseDetailsId = godownPurchaseDetailsId;
    }

    public LongFilter getGodownStocksId() {
        return godownStocksId;
    }

    public void setGodownStocksId(LongFilter godownStocksId) {
        this.godownStocksId = godownStocksId;
    }

    public LongFilter getFinancialYearGodownId() {
        return financialYearGodownId;
    }

    public void setFinancialYearGodownId(LongFilter financialYearGodownId) {
        this.financialYearGodownId = financialYearGodownId;
    }

    @Override
    public String toString() {
        return "GodownCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (address != null ? "address=" + address + ", " : "") +
                (incharge != null ? "incharge=" + incharge + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (godownPurchaseDetailsId != null ? "godownPurchaseDetailsId=" + godownPurchaseDetailsId + ", " : "") +
                (godownStocksId != null ? "godownStocksId=" + godownStocksId + ", " : "") +
                (financialYearGodownId != null ? "financialYearGodownId=" + financialYearGodownId + ", " : "") +
            "}";
    }

}
