/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02 11:27:58
 *  Target: yarn
 *******************************************************************************/

// Import needed model and dependency
import { Moment } from 'moment';

export interface INurseryStockDetails {
    id?: number;
    date?: Moment;
    quantity?: number;
    description?: string;
    status?: number;
    itStatus?: number;
    batchBatchName?: string;
    batchId?: number;
    nurseryStockId?: number;
    itNurseryNurseryName?: string;
    itNurseryId?: number;
    saplingDamageAreaPickListValue?: string;
    saplingDamageAreaId?: number;
    financialYearStockDetailsBatchName?: string;
    financialYearStockDetailsId?: number;
    fromNurseryStockDetailsNurseryName?: string;
    fromNurseryStockDetailsId?: number;
    stockVariety?: string;
    stockcategory?: string;
    stockVarietyId?: number;
    stockCategoryId?: number;
}

export class NurseryStockDetails implements INurseryStockDetails {
    constructor(
        public id?: number,
        public date?: Moment,
        public quantity?: number,
        public description?: string,
        public status?: number,
        public itStatus?: number,
        public batchBatchName?: string,
        public batchId?: number,
        public nurseryStockId?: number,
        public itNurseryNurseryName?: string,
        public itNurseryId?: number,
        public saplingDamageAreaPickListValue?: string,
        public saplingDamageAreaId?: number,
        public financialYearStockDetailsBatchName?: string,
        public financialYearStockDetailsId?: number,
        public fromNurseryStockDetailsNurseryName?: string,
        public stockVariety?: string,
        public stockcategory?: string,
        public stockVarietyId?: number,
        public stockCategoryId?: number,
        public pickListDamageAreaId?: number,
        public pickListDescriptionId?: number,
        public zoneId?: number,
        public sectorId?: number,
        public receivedZoneId?: number,
        public receivedSectorId?: number,
        public fromNurseryStockDetailsId?: number
    ) {}
}

export class NurseryStockDetailsModel {
    id?: number;
    date?: Moment;
    quantity?: number;
    description?: string;
    status?: number;
    itStatus?: number;
    batchBatchName?: string;
    batchId?: number;
    nurseryStockId?: number;
    itNurseryNurseryName?: string;
    itNurseryId?: number;
    saplingDamageAreaPickListValue?: string;
    saplingDamageAreaId?: number;
    financialYearStockDetailsBatchName?: string;
    financialYearStockDetailsId?: number;
    fromNurseryStockDetailsNurseryName?: string;
    fromNurseryStockDetailsId?: number;
    stockVariety?: string;
    stockcategory?: string;
    stockVarietyId?: number;
    stockCategoryId?: number;
    zoneId?: number;
    sectorId?: number;
    receivedZoneId?: number;
    receivedSectorId?: number;
}

// Constant variables
export const STATUS_ADD = 1;
export const STATUS_CONSUME = 2;
export const STATUS_SAPLING_DAMAGE = 3;
export const STATUS_SALE_POS = 4;
export const DISPLAY_NAME_VARIETY = 'VARIETY';
export const DISPLAY_NAME_QUANTITY_TYPE = 'QUANTITY TYPE';

export const IT_STATUS_ADDED = 1;
export const IT_STATUS_DISTRIBUTED = 2;
