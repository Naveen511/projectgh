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
 * Criteria class for the NurseryInventory entity. This class is used in NurseryInventoryResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /nursery-inventories?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class NurseryInventoryCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private IntegerFilter currentQuantity;

    private IntegerFilter addedQuantity;

    private IntegerFilter consumedQuantity;

    private StringFilter description;

    private IntegerFilter status;

    private IntegerFilter damageQuantity;

    private LongFilter nurserysId;

    private LongFilter pickListVarietyId;

    private LongFilter pickListCategoryId;

    private LongFilter nurseryInventoryDetailsId;

    private LongFilter quantityTypeId;

    public NurseryInventoryCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(IntegerFilter currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public IntegerFilter getAddedQuantity() {
        return addedQuantity;
    }

    public void setAddedQuantity(IntegerFilter addedQuantity) {
        this.addedQuantity = addedQuantity;
    }

    public IntegerFilter getConsumedQuantity() {
        return consumedQuantity;
    }

    public void setConsumedQuantity(IntegerFilter consumedQuantity) {
        this.consumedQuantity = consumedQuantity;
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

    public IntegerFilter getDamageQuantity() {
        return damageQuantity;
    }

    public void setDamageQuantity(IntegerFilter damageQuantity) {
        this.damageQuantity = damageQuantity;
    }

    public LongFilter getNurserysId() {
        return nurserysId;
    }

    public void setNurserysId(LongFilter nurserysId) {
        this.nurserysId = nurserysId;
    }

    public LongFilter getPickListVarietyId() {
        return pickListVarietyId;
    }

    public void setPickListVarietyId(LongFilter pickListVarietyId) {
        this.pickListVarietyId = pickListVarietyId;
    }

    public LongFilter getPickListCategoryId() {
        return pickListCategoryId;
    }

    public void setPickListCategoryId(LongFilter pickListCategoryId) {
        this.pickListCategoryId = pickListCategoryId;
    }

    public LongFilter getNurseryInventoryDetailsId() {
        return nurseryInventoryDetailsId;
    }

    public void setNurseryInventoryDetailsId(LongFilter nurseryInventoryDetailsId) {
        this.nurseryInventoryDetailsId = nurseryInventoryDetailsId;
    }

    public LongFilter getQuantityTypeId() {
        return quantityTypeId;
    }

    public void setQuantityTypeId(LongFilter quantityTypeId) {
        this.quantityTypeId = quantityTypeId;
    }

    @Override
    public String toString() {
        return "NurseryInventoryCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (currentQuantity != null ? "currentQuantity=" + currentQuantity + ", " : "") +
                (addedQuantity != null ? "addedQuantity=" + addedQuantity + ", " : "") +
                (consumedQuantity != null ? "consumedQuantity=" + consumedQuantity + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (damageQuantity != null ? "damageQuantity=" + damageQuantity + ", " : "") +
                (nurserysId != null ? "nurserysId=" + nurserysId + ", " : "") +
                (pickListVarietyId != null ? "pickListVarietyId=" + pickListVarietyId + ", " : "") +
                (pickListCategoryId != null ? "pickListCategoryId=" + pickListCategoryId + ", " : "") +
                (nurseryInventoryDetailsId != null ? "nurseryInventoryDetailsId=" + nurseryInventoryDetailsId + ", " : "") +
                (quantityTypeId != null ? "quantityTypeId=" + quantityTypeId + ", " : "") +
            "}";
    }

}
