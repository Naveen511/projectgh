/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs NurseryCriteria and
                            declared the table fields, data types for Nursery table.
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
 * Criteria class for the Nursery entity. This class is used in NurseryResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /nurseries?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class NurseryCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter nurseryName;

    private StringFilter nurseryAddress;

    private IntegerFilter status;

    private StringFilter code;

    private LongFilter batchsId;

    private LongFilter nurseryStocksId;

    private LongFilter sectorId;

    private LongFilter nurseryTypeId;

    private LongFilter motherBedsId;

    private LongFilter nurseryInventorysId;

    private LongFilter nurseryStockDetailsId;

    private LongFilter financialYearNurseryId;

    private LongFilter inchargeId;

    private LongFilter mapNurseryWithSectorId;

    private LongFilter fromNurseryStockDetailsId;

    /**
     * NurseryCriteria from Nursery table
     */
    public NurseryCriteria() {
    }

    /**
     * To Get the Id from Nursery table
     * 
     * @return id
     */
    public LongFilter getId() {
        return id;
    }

    /**
     * To set the id values for the Nursery table.
     * 
     * @param id id value of the Nursery
     */
    public void setId(LongFilter id) {
        this.id = id;
    }

    /**
     * To Get the nurseryName from Nursery table
     * 
     * @return nurseryName
     */
    public StringFilter getNurseryName() {
        return nurseryName;
    }

    /**
     * To set the nurseryName for the Nursery table.
     * 
     * @param nurseryName nurseryName of the Nursery
     */
    public void setNurseryName(StringFilter nurseryName) {
        this.nurseryName = nurseryName;
    }

    /**
     * To Get the nurseryAddress from Nursery table
     * 
     * @return nurseryAddress
     */
    public StringFilter getNurseryAddress() {
        return nurseryAddress;
    }

    /**
     * To set the nurseryAddress for the Nursery table.
     * 
     * @param nurseryAddress nurseryAddress of the Nursery
     */
    public void setNurseryAddress(StringFilter nurseryAddress) {
        this.nurseryAddress = nurseryAddress;
    }

    /**
     * To Get the status from Nursery table
     * 
     * @return status
     */
    public IntegerFilter getStatus() {
        return status;
    }

    /**
     * To set the status for the Nursery table.
     * 
     * @param status status of the Nursery
     */
    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    /**
     * To Get the code from Nursery table
     * 
     * @return code
     */
    public StringFilter getCode() {
        return code;
    }

    /**
     * To set the code for the Nursery table.
     * 
     * @param code code of the Nursery
     */
    public void setCode(StringFilter code) {
        this.code = code;
    }

    /**
     * To Get the batchsId from Nursery table
     * 
     * @return batchsId
     */
    public LongFilter getBatchsId() {
        return batchsId;
    }

    /**
     * To set the batchsId for the Nursery table.
     * 
     * @param batchsId batchsId of the Nursery
     */
    public void setBatchsId(LongFilter batchsId) {
        this.batchsId = batchsId;
    }

    /**
     * To Get the nurseryStocksId from Nursery table
     * 
     * @return nurseryStocksId
     */
    public LongFilter getNurseryStocksId() {
        return nurseryStocksId;
    }

    /**
     * To set the nurseryStocksId for the Nursery table.
     * 
     * @param nurseryStocksId nurseryStocksId of the Nursery
     */
    public void setNurseryStocksId(LongFilter nurseryStocksId) {
        this.nurseryStocksId = nurseryStocksId;
    }

    /**
     * To Get the sectorId from Nursery table
     * 
     * @return sectorId
     */
    public LongFilter getSectorId() {
        return sectorId;
    }

    /**
     * To set the sectorId for the Nursery table.
     * 
     * @param sectorId sectorId of the Nursery
     */
    public void setSectorId(LongFilter sectorId) {
        this.sectorId = sectorId;
    }

    /**
     * To Get the nurseryTypeId from Nursery table
     * 
     * @return nurseryTypeId
     */
    public LongFilter getNurseryTypeId() {
        return nurseryTypeId;
    }

    /**
     * To set the nurseryTypeId for the Nursery table.
     * 
     * @param nurseryTypeId nurseryTypeId of the Nursery
     */
    public void setNurseryTypeId(LongFilter nurseryTypeId) {
        this.nurseryTypeId = nurseryTypeId;
    }
        
    /**
     * To Get the motherBedsId from Nursery table
     * 
     * @return motherBedsId
     */
    public LongFilter getMotherBedsId() {
        return motherBedsId;
    }

    /**
     * To set the motherBedsId for the Nursery table.
     * 
     * @param motherBedsId motherBedsId of the Nursery
     */
    public void setMotherBedsId(LongFilter motherBedsId) {
        this.motherBedsId = motherBedsId;
    }

    /**
     * To Get the nurseryInventorysId from Nursery table
     * 
     * @return nurseryInventorysId
     */
    public LongFilter getNurseryInventorysId() {
        return nurseryInventorysId;
    }

    /**
     * To set the nurseryInventorysId for the Nursery table.
     * 
     * @param nurseryInventorysId nurseryInventorysId of the Nursery
     */
    public void setNurseryInventorysId(LongFilter nurseryInventorysId) {
        this.nurseryInventorysId = nurseryInventorysId;
    }

    /**
     * To Get the nurseryStockDetailsId from Nursery table
     * 
     * @return nurseryStockDetailsId
     */
    public LongFilter getNurseryStockDetailsId() {
        return nurseryStockDetailsId;
    }

    /**
     * To set the nurseryStockDetailsId for the Nursery table.
     * 
     * @param nurseryStockDetailsId nurseryStockDetailsId of the Nursery
     */
    public void setNurseryStockDetailsId(LongFilter nurseryStockDetailsId) {
        this.nurseryStockDetailsId = nurseryStockDetailsId;
    }

    /**
     * To Get the financialYearNurseryId from Nursery table
     * 
     * @return financialYearNurseryId
     */
    public LongFilter getFinancialYearNurseryId() {
        return financialYearNurseryId;
    }

    /**
     * To set the financialYearNurseryId for the Nursery table.
     * 
     * @param financialYearNurseryId financialYearNurseryId of the Nursery
     */
    public void setFinancialYearNurseryId(LongFilter financialYearNurseryId) {
        this.financialYearNurseryId = financialYearNurseryId;
    }

    /**
     * To Get the inchargeId from Nursery table
     * 
     * @return inchargeId
     */
    public LongFilter getInchargeId() {
        return inchargeId;
    }

    /**
     * To set the inchargeId for the Nursery table.
     * 
     * @param inchargeId inchargeId of the Nursery
     */
    public void setInchargeId(LongFilter inchargeId) {
        this.inchargeId = inchargeId;
    }

    /**
     * To Get the mapNurseryWithSectorId from Nursery table
     * 
     * @return mapNurseryWithSectorId
     */
    public LongFilter getMapNurseryWithSectorId() {
        return mapNurseryWithSectorId;
    }

    /**
     * To set the mapNurseryWithSectorId for the Nursery table.
     * 
     * @param mapNurseryWithSectorId mapNurseryWithSectorId of the Nursery
     */
    public void setMapNurseryWithSectorId(LongFilter mapNurseryWithSectorId) {
        this.mapNurseryWithSectorId = mapNurseryWithSectorId;
    }

    /**
     * To Get the fromNurseryStockDetailsId from Nursery table
     * 
     * @return fromNurseryStockDetailsId
     */
    public LongFilter getFromNurseryStockDetailsId() {
        return fromNurseryStockDetailsId;
    }

    /**
     * To set the fromNurseryStockDetailsId for the Nursery table.
     * 
     * @param fromNurseryStockDetailsId fromNurseryStockDetailsId of the Nursery
     */
    public void setFromNurseryStockDetailsId(LongFilter fromNurseryStockDetailsId) {
        this.fromNurseryStockDetailsId = fromNurseryStockDetailsId;
    }


    /**
     * toString to get the values
     * 
     * @return values
     */
    @Override
    public String toString() {
        return "NurseryCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (nurseryName != null ? "nurseryName=" + nurseryName + ", " : "") +
                (nurseryAddress != null ? "nurseryAddress=" + nurseryAddress + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (batchsId != null ? "batchsId=" + batchsId + ", " : "") +
                (nurseryStocksId != null ? "nurseryStocksId=" + nurseryStocksId + ", " : "") +
                (sectorId != null ? "sectorId=" + sectorId + ", " : "") +
                (nurseryTypeId != null ? "nurseryTypeId=" + nurseryTypeId + ", " : "") +
                (motherBedsId != null ? "motherBedsId=" + motherBedsId + ", " : "") +
                (nurseryInventorysId != null ? "nurseryInventorysId=" + nurseryInventorysId + ", " : "") +
                (nurseryStockDetailsId != null ? "nurseryStockDetailsId=" + nurseryStockDetailsId + ", " : "") +
                (financialYearNurseryId != null ? "financialYearNurseryId=" + financialYearNurseryId + ", " : "") +
                (inchargeId != null ? "inchargeId=" + inchargeId + ", " : "") +
                (mapNurseryWithSectorId != null ? "mapNurseryWithSectorId=" + mapNurseryWithSectorId + ", " : "") +
                (fromNurseryStockDetailsId != null ? "fromNurseryStockDetailsId=" + fromNurseryStockDetailsId + ", " : "") +
            "}";
    }

}
