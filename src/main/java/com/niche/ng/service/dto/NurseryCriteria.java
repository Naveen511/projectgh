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

    public NurseryCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getNurseryName() {
        return nurseryName;
    }

    public void setNurseryName(StringFilter nurseryName) {
        this.nurseryName = nurseryName;
    }

    public StringFilter getNurseryAddress() {
        return nurseryAddress;
    }

    public void setNurseryAddress(StringFilter nurseryAddress) {
        this.nurseryAddress = nurseryAddress;
    }

    public IntegerFilter getStatus() {
        return status;
    }

    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    public StringFilter getCode() {
        return code;
    }

    public void setCode(StringFilter code) {
        this.code = code;
    }

    public LongFilter getBatchsId() {
        return batchsId;
    }

    public void setBatchsId(LongFilter batchsId) {
        this.batchsId = batchsId;
    }

    public LongFilter getNurseryStocksId() {
        return nurseryStocksId;
    }

    public void setNurseryStocksId(LongFilter nurseryStocksId) {
        this.nurseryStocksId = nurseryStocksId;
    }

    public LongFilter getSectorId() {
        return sectorId;
    }

    public void setSectorId(LongFilter sectorId) {
        this.sectorId = sectorId;
    }

    public LongFilter getNurseryTypeId() {
        return nurseryTypeId;
    }

    public void setNurseryTypeId(LongFilter nurseryTypeId) {
        this.nurseryTypeId = nurseryTypeId;
    }

    public LongFilter getMotherBedsId() {
        return motherBedsId;
    }

    public void setMotherBedsId(LongFilter motherBedsId) {
        this.motherBedsId = motherBedsId;
    }

    public LongFilter getNurseryInventorysId() {
        return nurseryInventorysId;
    }

    public void setNurseryInventorysId(LongFilter nurseryInventorysId) {
        this.nurseryInventorysId = nurseryInventorysId;
    }

    public LongFilter getNurseryStockDetailsId() {
        return nurseryStockDetailsId;
    }

    public void setNurseryStockDetailsId(LongFilter nurseryStockDetailsId) {
        this.nurseryStockDetailsId = nurseryStockDetailsId;
    }

    public LongFilter getFinancialYearNurseryId() {
        return financialYearNurseryId;
    }

    public void setFinancialYearNurseryId(LongFilter financialYearNurseryId) {
        this.financialYearNurseryId = financialYearNurseryId;
    }

    public LongFilter getInchargeId() {
        return inchargeId;
    }

    public void setInchargeId(LongFilter inchargeId) {
        this.inchargeId = inchargeId;
    }

    public LongFilter getMapNurseryWithSectorId() {
        return mapNurseryWithSectorId;
    }

    public void setMapNurseryWithSectorId(LongFilter mapNurseryWithSectorId) {
        this.mapNurseryWithSectorId = mapNurseryWithSectorId;
    }

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
            "}";
    }

}
