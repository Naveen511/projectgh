/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02 11:27:58
 *  Target: yarn
 *******************************************************************************/

// Import needed model and dependency
import { INurseryInventoryDetails } from 'app/shared/model//nursery-inventory-details.model';

export interface INurseryInventory {
    id?: number;
    currentQuantity?: number;
    addedQuantity?: number;
    consumedQuantity?: number;
    description?: string;
    status?: number;
    damageQuantity?: number;
    nurserysNurseryName?: string;
    nurserysId?: number;
    pickListVarietyPickListValue?: string;
    pickListVarietyId?: number;
    pickListCategoryPickListValue?: string;
    pickListCategoryId?: number;
    nurseryInventoryDetails?: INurseryInventoryDetails[];
    quantityTypePickListValue?: string;
    quantityTypeId?: number;
}

export class NurseryInventory implements INurseryInventory {
    constructor(
        public id?: number,
        public currentQuantity?: number,
        public addedQuantity?: number,
        public consumedQuantity?: number,
        public description?: string,
        public status?: number,
        public damageQuantity?: number,
        public nurserysNurseryName?: string,
        public nurserysId?: number,
        public pickListVarietyPickListValue?: string,
        public pickListVarietyId?: number,
        public pickListCategoryPickListValue?: string,
        public pickListCategoryId?: number,
        public nurseryInventoryDetails?: INurseryInventoryDetails[],
        public quantityTypePickListValue?: string,
        public quantityTypeId?: number,
        public zoneId?: number,
        public sectorId?: number,
        public pickListId?: number,
        public pickListQuantityId?: number
    ) {}
}

export class NurseryInventoryModel {
    id?: number;
    currentQuantity?: number;
    addedQuantity?: number;
    consumedQuantity?: number;
    description?: string;
    status?: number;
    damageQuantity?: number;
    nurseryStockDetails?: INurseryInventoryDetails[];
    nurseryNurseryName?: string;
    nurseryId?: number;
    pickListVarietyPickListValue?: string;
    pickListVarietyId?: number;
    pickListCategoryPickListValue?: string;
    pickListCategoryId?: number;
    quantityTypePickListValue?: string;
    quantityTypeId?: number;
    zoneId?: number;
    sectorId?: number;
    pickListId?: number;
    pickListQuantityId?: number;
}

// Constant variables
export const STATUS_SEEDS = 101;
export const STATUS_COVER = 102;
