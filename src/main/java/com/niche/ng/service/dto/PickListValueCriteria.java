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

    public PickListValueCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getPickListValue() {
        return pickListValue;
    }

    public void setPickListValue(StringFilter pickListValue) {
        this.pickListValue = pickListValue;
    }

    public IntegerFilter getStatus() {
        return status;
    }

    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    public LongFilter getSelfIdsId() {
        return selfIdsId;
    }

    public void setSelfIdsId(LongFilter selfIdsId) {
        this.selfIdsId = selfIdsId;
    }

    public LongFilter getVarietysId() {
        return varietysId;
    }

    public void setVarietysId(LongFilter varietysId) {
        this.varietysId = varietysId;
    }

    public LongFilter getCategorysId() {
        return categorysId;
    }

    public void setCategorysId(LongFilter categorysId) {
        this.categorysId = categorysId;
    }

    public LongFilter getNurseryStockVarietysId() {
        return nurseryStockVarietysId;
    }

    public void setNurseryStockVarietysId(LongFilter nurseryStockVarietysId) {
        this.nurseryStockVarietysId = nurseryStockVarietysId;
    }

    public LongFilter getNurseryStockCategorysId() {
        return nurseryStockCategorysId;
    }

    public void setNurseryStockCategorysId(LongFilter nurseryStockCategorysId) {
        this.nurseryStockCategorysId = nurseryStockCategorysId;
    }

    public LongFilter getGodownPurchaseVarietysId() {
        return godownPurchaseVarietysId;
    }

    public void setGodownPurchaseVarietysId(LongFilter godownPurchaseVarietysId) {
        this.godownPurchaseVarietysId = godownPurchaseVarietysId;
    }

    public LongFilter getGodownPurchaseCategorysId() {
        return godownPurchaseCategorysId;
    }

    public void setGodownPurchaseCategorysId(LongFilter godownPurchaseCategorysId) {
        this.godownPurchaseCategorysId = godownPurchaseCategorysId;
    }

    public LongFilter getGodownPurchaseQuantityTypeId() {
        return godownPurchaseQuantityTypeId;
    }

    public void setGodownPurchaseQuantityTypeId(LongFilter godownPurchaseQuantityTypeId) {
        this.godownPurchaseQuantityTypeId = godownPurchaseQuantityTypeId;
    }

    public LongFilter getGodownStockVarietysId() {
        return godownStockVarietysId;
    }

    public void setGodownStockVarietysId(LongFilter godownStockVarietysId) {
        this.godownStockVarietysId = godownStockVarietysId;
    }

    public LongFilter getGodownStockCategorysId() {
        return godownStockCategorysId;
    }

    public void setGodownStockCategorysId(LongFilter godownStockCategorysId) {
        this.godownStockCategorysId = godownStockCategorysId;
    }

    public LongFilter getGodownStockQuantityTypesId() {
        return godownStockQuantityTypesId;
    }

    public void setGodownStockQuantityTypesId(LongFilter godownStockQuantityTypesId) {
        this.godownStockQuantityTypesId = godownStockQuantityTypesId;
    }

    public LongFilter getPickListId() {
        return pickListId;
    }

    public void setPickListId(LongFilter pickListId) {
        this.pickListId = pickListId;
    }

    public LongFilter getPickValueId() {
        return pickValueId;
    }

    public void setPickValueId(LongFilter pickValueId) {
        this.pickValueId = pickValueId;
    }

    public LongFilter getNurserysId() {
        return nurserysId;
    }

    public void setNurserysId(LongFilter nurserysId) {
        this.nurserysId = nurserysId;
    }

    public LongFilter getBatchQuantityTypesId() {
        return batchQuantityTypesId;
    }

    public void setBatchQuantityTypesId(LongFilter batchQuantityTypesId) {
        this.batchQuantityTypesId = batchQuantityTypesId;
    }

    public LongFilter getNurseryInventoryVarietysId() {
        return nurseryInventoryVarietysId;
    }

    public void setNurseryInventoryVarietysId(LongFilter nurseryInventoryVarietysId) {
        this.nurseryInventoryVarietysId = nurseryInventoryVarietysId;
    }

    public LongFilter getNurseryInventoryCategorysId() {
        return nurseryInventoryCategorysId;
    }

    public void setNurseryInventoryCategorysId(LongFilter nurseryInventoryCategorysId) {
        this.nurseryInventoryCategorysId = nurseryInventoryCategorysId;
    }

    public LongFilter getNurseryInventoryQuantityTypesId() {
        return nurseryInventoryQuantityTypesId;
    }

    public void setNurseryInventoryQuantityTypesId(LongFilter nurseryInventoryQuantityTypesId) {
        this.nurseryInventoryQuantityTypesId = nurseryInventoryQuantityTypesId;
    }

    public LongFilter getNurseryInventoryDamageTypesId() {
        return nurseryInventoryDamageTypesId;
    }

    public void setNurseryInventoryDamageTypesId(LongFilter nurseryInventoryDamageTypesId) {
        this.nurseryInventoryDamageTypesId = nurseryInventoryDamageTypesId;
    }

    public LongFilter getPickListValueDamageAreaId() {
        return pickListValueDamageAreaId;
    }

    public void setPickListValueDamageAreaId(LongFilter pickListValueDamageAreaId) {
        this.pickListValueDamageAreaId = pickListValueDamageAreaId;
    }

    public LongFilter getNurseryStockDamageAreaId() {
        return nurseryStockDamageAreaId;
    }

    public void setNurseryStockDamageAreaId(LongFilter nurseryStockDamageAreaId) {
        this.nurseryStockDamageAreaId = nurseryStockDamageAreaId;
    }

    public LongFilter getFinancialYearNameId() {
        return financialYearNameId;
    }

    public void setFinancialYearNameId(LongFilter financialYearNameId) {
        this.financialYearNameId = financialYearNameId;
    }

    public LongFilter getDamageDescriptionId() {
        return damageDescriptionId;
    }

    public void setDamageDescriptionId(LongFilter damageDescriptionId) {
        this.damageDescriptionId = damageDescriptionId;
    }

    public LongFilter getPointOfSaleVarietysId() {
        return pointOfSaleVarietysId;
    }

    public void setPointOfSaleVarietysId(LongFilter pointOfSaleVarietysId) {
        this.pointOfSaleVarietysId = pointOfSaleVarietysId;
    }

    public LongFilter getPointOfSaleCategorysId() {
        return pointOfSaleCategorysId;
    }

    public void setPointOfSaleCategorysId(LongFilter pointOfSaleCategorysId) {
        this.pointOfSaleCategorysId = pointOfSaleCategorysId;
    }

    public LongFilter getCoverFillingDetailsId() {
        return coverFillingDetailsId;
    }

    public void setCoverFillingDetailsId(LongFilter coverFillingDetailsId) {
        this.coverFillingDetailsId = coverFillingDetailsId;
    }

    public LongFilter getNurseryInventoryDamageDescId() {
        return nurseryInventoryDamageDescId;
    }

    public void setNurseryInventoryDamageDescId(LongFilter nurseryInventoryDamageDescId) {
        this.nurseryInventoryDamageDescId = nurseryInventoryDamageDescId;
    }

    public LongFilter getCoverFillingDamageDescId() {
        return coverFillingDamageDescId;
    }

    public void setCoverFillingDamageDescId(LongFilter coverFillingDamageDescId) {
        this.coverFillingDamageDescId = coverFillingDamageDescId;
    }

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
