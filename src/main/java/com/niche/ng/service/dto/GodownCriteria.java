/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs GodownCriteria and
                            declared the table fields, data types for Godown table.
 *
 *******************************************************************************/
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

    /**
     * To Get the Id from Godown table
     * 
     * @return id
     */
    public LongFilter getId() {
        return id;
    }

    /**
     * To set the id values for the Godown table.
     * 
     * @param id id value
     */
    public void setId(LongFilter id) {
        this.id = id;
    }

    /**
     * To Get the name from Godown table
     * 
     * @return name
     */
    public StringFilter getName() {
        return name;
    }

    /**
     * To set the name values for the Godown table.
     * 
     * @param name name of the Godown
     */
    public void setName(StringFilter name) {
        this.name = name;
    }

    /**
     * To Get the address from Godown table
     * 
     * @return address
     */
    public StringFilter getAddress() {
        return address;
    }

    /**
     * To set the address values for the Godown table.
     * 
     * @param address address of the Godown
     */
    public void setAddress(StringFilter address) {
        this.address = address;
    }

    /**
     * To Get the incharge from Godown table
     * 
     * @return incharge
     */
    public StringFilter getIncharge() {
        return incharge;
    }

    /**
     * To set the incharge values for the Godown table.
     * 
     * @param incharge incharge of the Godown
     */
    public void setIncharge(StringFilter incharge) {
        this.incharge = incharge;
    }

    /**
     * To Get the status from Godown table
     * 
     * @return status
     */
    public IntegerFilter getStatus() {
        return status;
    }

    /**
     * To set the status values for the Godown table.
     * 
     * @param status status of the godown
     */
    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    /**
     * To Get the godownPurchaseDetailsId from Godown table
     * 
     * @return godownPurchaseDetailsId
     */
    public LongFilter getGodownPurchaseDetailsId() {
        return godownPurchaseDetailsId;
    }

    /**
     * To set the godownPurchaseDetailsId values for the Godown table.
     * 
     * @param godownPurchaseDetailsId godownPurchaseDetailsId of the Godown
     */
    public void setGodownPurchaseDetailsId(LongFilter godownPurchaseDetailsId) {
        this.godownPurchaseDetailsId = godownPurchaseDetailsId;
    }

    /**
     * To Get the godownStocksId from Godown table
     * 
     * @return godownStocksId
     */
    public LongFilter getGodownStocksId() {
        return godownStocksId;
    }

    /**
     * To set the godownStocksId values for the Godown table.
     * 
     * @param godownStocksId godownStocksId of the Godown
     */
    public void setGodownStocksId(LongFilter godownStocksId) {
        this.godownStocksId = godownStocksId;
    }

    /**
     * To Get the financialYearGodownId from Godown table
     * 
     * @return financialYearGodownId
     */
    public LongFilter getFinancialYearGodownId() {
        return financialYearGodownId;
    }

    /**
     * To set the financialYearGodownId values for the Godown table.
     * 
     * @param financialYearGodownId financialYearGodownId of the Godown
     */
    public void setFinancialYearGodownId(LongFilter financialYearGodownId) {
        this.financialYearGodownId = financialYearGodownId;
    }

    /**
     * toString to get the values
     * 
     * @return values
     */
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
