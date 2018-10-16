/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/31
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs PickListValueCriteria and
                            declared the table fields, data types for PickListValue table.
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
 * Criteria class for the PickListValue entity. This class is used in PickListValueResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /pick-list-values?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PickListValueCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter pickListValue;

    private IntegerFilter status;

    private LongFilter selfIdsId;

    private LongFilter varietysId;

    private LongFilter categorysId;

    private LongFilter nurseryStockVarietysId;

    private LongFilter nurseryStockCategorysId;

    private LongFilter godownPurchaseVarietysId;

    private LongFilter godownPurchaseCategorysId;

    private LongFilter godownPurchaseQuantityTypeId;

    private LongFilter godownStockVarietysId;

    private LongFilter godownStockCategorysId;

    private LongFilter godownStockQuantityTypesId;

    private LongFilter pickListId;

    private LongFilter pickValueId;

    private LongFilter nurserysId;

    private LongFilter batchQuantityTypesId;

    private LongFilter nurseryInventoryVarietysId;

    private LongFilter nurseryInventoryCategorysId;

    private LongFilter nurseryInventoryQuantityTypesId;

    private LongFilter nurseryInventoryDamageTypesId;

    private LongFilter pickListValueDamageAreaId;

    private LongFilter nurseryStockDamageAreaId;

    private LongFilter financialYearNameId;

    private LongFilter damageDescriptionId;

    private LongFilter pointOfSaleVarietysId;

    private LongFilter pointOfSaleCategorysId;

    private LongFilter coverFillingDetailsId;

    private LongFilter nurseryInventoryDamageDescId;

    private LongFilter coverFillingDamageDescId;

    /**
     * PickListValueCriteria from PickListValue table
     */
    public PickListValueCriteria() {
    }

    /**
     * To Get the Id from PickListValue table
     * 
     * @return id
     */
    public LongFilter getId() {
        return id;
    }

    /**
     * To set the id values for the PickListValue table.
     * 
     * @param id id of the PickListValue
     */
    public void setId(LongFilter id) {
        this.id = id;
    }

    /**
     * To Get the pickListValue from PickListValue table
     * 
     * @return pickListValue
     */
    public StringFilter getPickListValue() {
        return pickListValue;
    }

    /**
     * To set the pickListValue values for the PickListValue table.
     * 
     * @param pickListValue pickListValue of the PickListValue
     */
    public void setPickListValue(StringFilter pickListValue) {
        this.pickListValue = pickListValue;
    }

    /**
     * To Get the status from PickListValue table
     * 
     * @return status
     */
    public IntegerFilter getStatus() {
        return status;
    }

    /**
     * To set the status values for the PickListValue table.
     * 
     * @param status status of the PickListValue
     */
    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    /**
     * To Get the selfIdsId from PickListValue table
     * 
     * @return selfIdsId
     */
    public LongFilter getSelfIdsId() {
        return selfIdsId;
    }

    /**
     * To set the selfIdsId values for the PickListValue table.
     * 
     * @param selfIdsId selfIdsId of the PickListValue
     */
    public void setSelfIdsId(LongFilter selfIdsId) {
        this.selfIdsId = selfIdsId;
    }

    /**
     * To Get the varietysId from PickListValue table
     * 
     * @return varietysId
     */
    public LongFilter getVarietysId() {
        return varietysId;
    }

    /**
     * To set the varietysId values for the PickListValue table.
     * 
     * @param varietysId varietysId of the PickListValue
     */
    public void setVarietysId(LongFilter varietysId) {
        this.varietysId = varietysId;
    }

    /**
     * To Get the categorysId from PickListValue table
     * 
     * @return categorysId
     */
    public LongFilter getCategorysId() {
        return categorysId;
    }
    
    /**
     * To set the categorysId values for the PickListValue table.
     * 
     * @param categorysId categorysId of the PickListValue
     */
    public void setCategorysId(LongFilter categorysId) {
        this.categorysId = categorysId;
    }

    /**
     * To Get the nurseryStockVarietysId from PickListValue table
     * 
     * @return nurseryStockVarietysId
     */
    public LongFilter getNurseryStockVarietysId() {
        return nurseryStockVarietysId;
    }

    /**
     * To set the nurseryStockVarietysId values for the PickListValue table.
     * 
     * @param nurseryStockVarietysId nurseryStockVarietysId of the PickListValue
     */
    public void setNurseryStockVarietysId(LongFilter nurseryStockVarietysId) {
        this.nurseryStockVarietysId = nurseryStockVarietysId;
    }

    /**
     * To Get the nurseryStockCategorysId from PickListValue table
     * 
     * @return nurseryStockCategorysId
     */
    public LongFilter getNurseryStockCategorysId() {
        return nurseryStockCategorysId;
    }

    /**
     * To set the nurseryStockCategorysId values for the PickListValue table.
     * 
     * @param nurseryStockCategorysId nurseryStockCategorysId of the PickListValue
     */
    public void setNurseryStockCategorysId(LongFilter nurseryStockCategorysId) {
        this.nurseryStockCategorysId = nurseryStockCategorysId;
    }

    /**
     * To Get the godownPurchaseVarietysId from PickListValue table
     * 
     * @return godownPurchaseVarietysId
     */
    public LongFilter getGodownPurchaseVarietysId() {
        return godownPurchaseVarietysId;
    }

    /**
     * To set the godownPurchaseVarietysId values for the PickListValue table.
     * 
     * @param godownPurchaseVarietysId godownPurchaseVarietysId of the PickListValue
     */
    public void setGodownPurchaseVarietysId(LongFilter godownPurchaseVarietysId) {
        this.godownPurchaseVarietysId = godownPurchaseVarietysId;
    }

    /**
     * To Get the godownPurchaseCategorysId from PickListValue table
     * 
     * @return godownPurchaseCategorysId
     */
    public LongFilter getGodownPurchaseCategorysId() {
        return godownPurchaseCategorysId;
    }
    
    /**
     * To set the godownPurchaseCategorysId values for the PickListValue table.
     * 
     * @param godownPurchaseCategorysId godownPurchaseCategorysId of the PickListValue
     */
    public void setGodownPurchaseCategorysId(LongFilter godownPurchaseCategorysId) {
        this.godownPurchaseCategorysId = godownPurchaseCategorysId;
    }

    /**
     * To Get the godownPurchaseQuantityTypeId from PickListValue table
     * 
     * @return godownPurchaseQuantityTypeId
     */
    public LongFilter getGodownPurchaseQuantityTypeId() {
        return godownPurchaseQuantityTypeId;
    }

    /**
     * To set the godownPurchaseQuantityTypeId values for the PickListValue table.
     * 
     * @param godownPurchaseQuantityTypeId godownPurchaseQuantityTypeId of the PickListValue
     */
    public void setGodownPurchaseQuantityTypeId(LongFilter godownPurchaseQuantityTypeId) {
        this.godownPurchaseQuantityTypeId = godownPurchaseQuantityTypeId;
    }

    /**
     * To Get the godownStockVarietysId from PickListValue table
     * 
     * @return godownStockVarietysId
     */
    public LongFilter getGodownStockVarietysId() {
        return godownStockVarietysId;
    }

    /**
     * To set the godownStockVarietysId values for the PickListValue table.
     * 
     * @param godownStockVarietysId godownStockVarietysId of the PickListValue
     */
    public void setGodownStockVarietysId(LongFilter godownStockVarietysId) {
        this.godownStockVarietysId = godownStockVarietysId;
    }

    /**
     * To Get the godownStockCategorysId from PickListValue table
     * 
     * @return godownStockCategorysId
     */
    public LongFilter getGodownStockCategorysId() {
        return godownStockCategorysId;
    }

    /**
     * To set the godownStockCategorysId values for the PickListValue table.
     * 
     * @param godownStockCategorysId godownStockCategorysId of the PickListValue
     */
    public void setGodownStockCategorysId(LongFilter godownStockCategorysId) {
        this.godownStockCategorysId = godownStockCategorysId;
    }

    /**
     * To Get the godownStockQuantityTypesId from PickListValue table
     * 
     * @return godownStockQuantityTypesId
     */
    public LongFilter getGodownStockQuantityTypesId() {
        return godownStockQuantityTypesId;
    }

    /**
     * To set the godownStockQuantityTypesId values for the PickListValue table.
     * 
     * @param godownStockQuantityTypesId godownStockQuantityTypesId of the PickListValue
     */
    public void setGodownStockQuantityTypesId(LongFilter godownStockQuantityTypesId) {
        this.godownStockQuantityTypesId = godownStockQuantityTypesId;
    }

    /**
     * To Get the pickListId from PickListValue table
     * 
     * @return pickListId
     */
    public LongFilter getPickListId() {
        return pickListId;
    }

    /**
     * To set the pickListId values for the PickListValue table.
     * 
     * @param pickListId pickListId of the PickListValue
     */
    public void setPickListId(LongFilter pickListId) {
        this.pickListId = pickListId;
    }

    /**
     * To Get the pickValueId from PickListValue table
     * 
     * @return pickValueId
     */
    public LongFilter getPickValueId() {
        return pickValueId;
    }

    /**
     * To set the pickValueId values for the PickListValue table.
     * 
     * @param pickValueId pickValueId of the PickListValue
     */
    public void setPickValueId(LongFilter pickValueId) {
        this.pickValueId = pickValueId;
    }

    /**
     * To Get the nurserysId from PickListValue table
     * 
     * @return nurserysId
     */
    public LongFilter getNurserysId() {
        return nurserysId;
    }

    /**
     * To set the nurserysId values for the PickListValue table.
     * 
     * @param nurserysId nurserysId of the PickListValue
     */
    public void setNurserysId(LongFilter nurserysId) {
        this.nurserysId = nurserysId;
    }

    /**
     * To Get the batchQuantityTypesId from PickListValue table
     * 
     * @return batchQuantityTypesId
     */
    public LongFilter getBatchQuantityTypesId() {
        return batchQuantityTypesId;
    }

    /**
     * To set the batchQuantityTypesId values for the PickListValue table.
     * 
     * @param batchQuantityTypesId batchQuantityTypesId of the PickListValue
     */
    public void setBatchQuantityTypesId(LongFilter batchQuantityTypesId) {
        this.batchQuantityTypesId = batchQuantityTypesId;
    }

    /**
     * To Get the nurseryInventoryVarietysId from PickListValue table
     * 
     * @return nurseryInventoryVarietysId
     */
    public LongFilter getNurseryInventoryVarietysId() {
        return nurseryInventoryVarietysId;
    }

    /**
     * To Get the nurseryInventoryVarietysId from PickListValue table
     * 
     * @return nurseryInventoryVarietysId
     */
    public void setNurseryInventoryVarietysId(LongFilter nurseryInventoryVarietysId) {
        this.nurseryInventoryVarietysId = nurseryInventoryVarietysId;
    }

    /**
     * To Get the nurseryInventoryCategorysId from PickListValue table
     * 
     * @return nurseryInventoryCategorysId
     */
    public LongFilter getNurseryInventoryCategorysId() {
        return nurseryInventoryCategorysId;
    }

    /**
     * To set the nurseryInventoryCategorysId values for the PickListValue table.
     * 
     * @param nurseryInventoryCategorysId nurseryInventoryCategorysId of the PickListValue
     */
    public void setNurseryInventoryCategorysId(LongFilter nurseryInventoryCategorysId) {
        this.nurseryInventoryCategorysId = nurseryInventoryCategorysId;
    }

    /**
     * To Get the nurseryInventoryQuantityTypesId from PickListValue table
     * 
     * @return nurseryInventoryQuantityTypesId
     */
    public LongFilter getNurseryInventoryQuantityTypesId() {
        return nurseryInventoryQuantityTypesId;
    }

    /**
     * To set the nurseryInventoryQuantityTypesId values for the PickListValue table.
     * 
     * @param nurseryInventoryQuantityTypesId nurseryInventoryQuantityTypesId of the PickListValue
     */
    public void setNurseryInventoryQuantityTypesId(LongFilter nurseryInventoryQuantityTypesId) {
        this.nurseryInventoryQuantityTypesId = nurseryInventoryQuantityTypesId;
    }

    /**
     * To Get the nurseryInventoryDamageTypesId from PickListValue table
     * 
     * @return nurseryInventoryDamageTypesId
     */
    public LongFilter getNurseryInventoryDamageTypesId() {
        return nurseryInventoryDamageTypesId;
    }

    /**
     * To set the nurseryInventoryDamageTypesId values for the PickListValue table.
     * 
     * @param nurseryInventoryDamageTypesId nurseryInventoryDamageTypesId of the PickListValue
     */
    public void setNurseryInventoryDamageTypesId(LongFilter nurseryInventoryDamageTypesId) {
        this.nurseryInventoryDamageTypesId = nurseryInventoryDamageTypesId;
    }

    /**
     * To Get the pickListValueDamageAreaId from PickListValue table
     * 
     * @return pickListValueDamageAreaId
     */
    public LongFilter getPickListValueDamageAreaId() {
        return pickListValueDamageAreaId;
    }

    /**
     * To set the pickListValueDamageAreaId values for the PickListValue table.
     * 
     * @param pickListValueDamageAreaId pickListValueDamageAreaId of the PickListValue
     */
    public void setPickListValueDamageAreaId(LongFilter pickListValueDamageAreaId) {
        this.pickListValueDamageAreaId = pickListValueDamageAreaId;
    }

    /**
     * To Get the nurseryStockDamageAreaId from PickListValue table
     * 
     * @return nurseryStockDamageAreaId
     */
    public LongFilter getNurseryStockDamageAreaId() {
        return nurseryStockDamageAreaId;
    }

    /**
     * To set the nurseryStockDamageAreaId values for the PickListValue table.
     * 
     * @param nurseryStockDamageAreaId nurseryStockDamageAreaId of the PickListValue
     */
    public void setNurseryStockDamageAreaId(LongFilter nurseryStockDamageAreaId) {
        this.nurseryStockDamageAreaId = nurseryStockDamageAreaId;
    }

    /**
     * To Get the financialYearNameId from PickListValue table
     * 
     * @return financialYearNameId
     */
    public LongFilter getFinancialYearNameId() {
        return financialYearNameId;
    }

    /**
     * To set the financialYearNameId values for the PickListValue table.
     * 
     * @param financialYearNameId financialYearNameId of the PickListValue
     */
    public void setFinancialYearNameId(LongFilter financialYearNameId) {
        this.financialYearNameId = financialYearNameId;
    }

    /**
     * To Get the damageDescriptionId from PickListValue table
     * 
     * @return damageDescriptionId
     */
    public LongFilter getDamageDescriptionId() {
        return damageDescriptionId;
    }

    /**
     * To set the damageDescriptionId values for the PickListValue table.
     * 
     * @param damageDescriptionId damageDescriptionId of the PickListValue
     */
    public void setDamageDescriptionId(LongFilter damageDescriptionId) {
        this.damageDescriptionId = damageDescriptionId;
    }

    /**
     * To Get the pointOfSaleVarietysId from PickListValue table
     * 
     * @return pointOfSaleVarietysId
     */
    public LongFilter getPointOfSaleVarietysId() {
        return pointOfSaleVarietysId;
    }

    /**
     * To set the pointOfSaleVarietysId values for the PickListValue table.
     * 
     * @param pointOfSaleVarietysId pointOfSaleVarietysId of the PickListValue
     */
    public void setPointOfSaleVarietysId(LongFilter pointOfSaleVarietysId) {
        this.pointOfSaleVarietysId = pointOfSaleVarietysId;
    }

    /**
     * To Get the pointOfSaleCategorysId from PickListValue table
     * 
     * @return pointOfSaleCategorysId
     */
    public LongFilter getPointOfSaleCategorysId() {
        return pointOfSaleCategorysId;
    }

    /**
     * To set the pointOfSaleCategorysId values for the PickListValue table.
     * 
     * @param pointOfSaleCategorysId pointOfSaleCategorysId of the PickListValue
     */
    public void setPointOfSaleCategorysId(LongFilter pointOfSaleCategorysId) {
        this.pointOfSaleCategorysId = pointOfSaleCategorysId;
    }

    /**
     * To Get the coverFillingDetailsId from PickListValue table
     * 
     * @return coverFillingDetailsId
     */
    public LongFilter getCoverFillingDetailsId() {
        return coverFillingDetailsId;
    }

    /**
     * To set the coverFillingDetailsId values for the PickListValue table.
     * 
     * @param coverFillingDetailsId coverFillingDetailsId of the PickListValue
     */
    public void setCoverFillingDetailsId(LongFilter coverFillingDetailsId) {
        this.coverFillingDetailsId = coverFillingDetailsId;
    }

    /**
     * To Get the nurseryInventoryDamageDescId from PickListValue table
     * 
     * @return nurseryInventoryDamageDescId
     */
    public LongFilter getNurseryInventoryDamageDescId() {
        return nurseryInventoryDamageDescId;
    }

    /**
     * To set the nurseryInventoryDamageDescId values for the PickListValue table.
     * 
     * @param nurseryInventoryDamageDescId nurseryInventoryDamageDescId of the PickListValue
     */
    public void setNurseryInventoryDamageDescId(LongFilter nurseryInventoryDamageDescId) {
        this.nurseryInventoryDamageDescId = nurseryInventoryDamageDescId;
    }

    /**
     * To Get the coverFillingDamageDescId from PickListValue table
     * 
     * @return coverFillingDamageDescId
     */
    public LongFilter getCoverFillingDamageDescId() {
        return coverFillingDamageDescId;
    }

    /**
     * To set the coverFillingDamageDescId values for the PickListValue table.
     * 
     * @param coverFillingDamageDescId coverFillingDamageDescId of the PickListValue
     */
    public void setCoverFillingDamageDescId(LongFilter coverFillingDamageDescId) {
        this.coverFillingDamageDescId = coverFillingDamageDescId;
    }

    /**
     * toString to get the values
     * 
     * @return values
     */
    @Override
    public String toString() {
        return "PickListValueCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (pickListValue != null ? "pickListValue=" + pickListValue + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (selfIdsId != null ? "selfIdsId=" + selfIdsId + ", " : "") +
                (varietysId != null ? "varietysId=" + varietysId + ", " : "") +
                (categorysId != null ? "categorysId=" + categorysId + ", " : "") +
                (nurseryStockVarietysId != null ? "nurseryStockVarietysId=" + nurseryStockVarietysId + ", " : "") +
                (nurseryStockCategorysId != null ? "nurseryStockCategorysId=" + nurseryStockCategorysId + ", " : "") +
                (godownPurchaseVarietysId != null ? "godownPurchaseVarietysId=" + godownPurchaseVarietysId + ", " : "") +
                (godownPurchaseCategorysId != null ? "godownPurchaseCategorysId=" + godownPurchaseCategorysId + ", " : "") +
                (godownPurchaseQuantityTypeId != null ? "godownPurchaseQuantityTypeId=" + godownPurchaseQuantityTypeId + ", " : "") +
                (godownStockVarietysId != null ? "godownStockVarietysId=" + godownStockVarietysId + ", " : "") +
                (godownStockCategorysId != null ? "godownStockCategorysId=" + godownStockCategorysId + ", " : "") +
                (godownStockQuantityTypesId != null ? "godownStockQuantityTypesId=" + godownStockQuantityTypesId + ", " : "") +
                (pickListId != null ? "pickListId=" + pickListId + ", " : "") +
                (pickValueId != null ? "pickValueId=" + pickValueId + ", " : "") +
                (nurserysId != null ? "nurserysId=" + nurserysId + ", " : "") +
                (batchQuantityTypesId != null ? "batchQuantityTypesId=" + batchQuantityTypesId + ", " : "") +
                (nurseryInventoryVarietysId != null ? "nurseryInventoryVarietysId=" + nurseryInventoryVarietysId + ", " : "") +
                (nurseryInventoryCategorysId != null ? "nurseryInventoryCategorysId=" + nurseryInventoryCategorysId + ", " : "") +
                (nurseryInventoryQuantityTypesId != null ? "nurseryInventoryQuantityTypesId=" + nurseryInventoryQuantityTypesId + ", " : "") +
                (nurseryInventoryDamageTypesId != null ? "nurseryInventoryDamageTypesId=" + nurseryInventoryDamageTypesId + ", " : "") +
                (pickListValueDamageAreaId != null ? "pickListValueDamageAreaId=" + pickListValueDamageAreaId + ", " : "") +
                (nurseryStockDamageAreaId != null ? "nurseryStockDamageAreaId=" + nurseryStockDamageAreaId + ", " : "") +
                (financialYearNameId != null ? "financialYearNameId=" + financialYearNameId + ", " : "") +
                (damageDescriptionId != null ? "damageDescriptionId=" + damageDescriptionId + ", " : "") +
                (pointOfSaleVarietysId != null ? "pointOfSaleVarietysId=" + pointOfSaleVarietysId + ", " : "") +
                (pointOfSaleCategorysId != null ? "pointOfSaleCategorysId=" + pointOfSaleCategorysId + ", " : "") +
                (coverFillingDetailsId != null ? "coverFillingDetailsId=" + coverFillingDetailsId + ", " : "") +
                (nurseryInventoryDamageDescId != null ? "nurseryInventoryDamageDescId=" + nurseryInventoryDamageDescId + ", " : "") +
                (coverFillingDamageDescId != null ? "coverFillingDamageDescId=" + coverFillingDamageDescId + ", " : "") +
            "}";
    }

}
