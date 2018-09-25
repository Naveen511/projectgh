package com.niche.ng.service.dto;

import java.io.Serializable;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;


import io.github.jhipster.service.filter.LocalDateFilter;



/**
 * Criteria class for the NurseryInventoryDetails entity. This class is used in NurseryInventoryDetailsResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /nursery-inventory-details?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class NurseryInventoryDetailsCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LocalDateFilter date;

    private IntegerFilter quantity;

    private IntegerFilter status;

    private StringFilter description;

    private LongFilter nurseryInventoryId;

    private LongFilter damageTypeId;

    private LongFilter inventoryDamageDescriptionId;

    public NurseryInventoryDetailsCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LocalDateFilter getDate() {
        return date;
    }

    public void setDate(LocalDateFilter date) {
        this.date = date;
    }

    public IntegerFilter getQuantity() {
        return quantity;
    }

    public void setQuantity(IntegerFilter quantity) {
        this.quantity = quantity;
    }

    public IntegerFilter getStatus() {
        return status;
    }

    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public LongFilter getNurseryInventoryId() {
        return nurseryInventoryId;
    }

    public void setNurseryInventoryId(LongFilter nurseryInventoryId) {
        this.nurseryInventoryId = nurseryInventoryId;
    }

    public LongFilter getDamageTypeId() {
        return damageTypeId;
    }

    public void setDamageTypeId(LongFilter damageTypeId) {
        this.damageTypeId = damageTypeId;
    }

    public LongFilter getInventoryDamageDescriptionId() {
        return inventoryDamageDescriptionId;
    }

    public void setInventoryDamageDescriptionId(LongFilter inventoryDamageDescriptionId) {
        this.inventoryDamageDescriptionId = inventoryDamageDescriptionId;
    }


    @Override
    public String toString() {
        return "NurseryInventoryDetailsCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (date != null ? "date=" + date + ", " : "") +
                (quantity != null ? "quantity=" + quantity + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (nurseryInventoryId != null ? "nurseryInventoryId=" + nurseryInventoryId + ", " : "") +
                (damageTypeId != null ? "damageTypeId=" + damageTypeId + ", " : "") +
                (inventoryDamageDescriptionId != null ? "inventoryDamageDescriptionId=" + inventoryDamageDescriptionId + ", " : "") +
            "}";
    }

}
