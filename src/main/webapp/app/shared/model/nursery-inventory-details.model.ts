/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02 11:27:58
 *  Target: yarn
 *******************************************************************************/

// Import needed dependency
import { Moment } from 'moment';

export interface INurseryInventoryDetails {
    id?: number;
    date?: Moment;
    quantity?: number;
    status?: number;
    description?: string;
    nurseryInventoryId?: number;
    damageTypePickListValue?: string;
    damageTypeId?: number;
    inventoryDamageDescriptionPickListValue?: string;
    inventoryDamageDescriptionId?: number;
    damageDescriptionId?: number;
}

export class NurseryInventoryDetails implements INurseryInventoryDetails {
    constructor(
        public id?: number,
        public date?: Moment,
        public quantity?: number,
        public status?: number,
        public description?: string,
        public nurseryInventoryId?: number,
        public damageTypePickListValue?: string,
        public damageTypeId?: number,
        public inventoryDamageDescriptionPickListValue?: string,
        public inventoryDamageDescriptionId?: number,
        public pickListQuantityId?: number,
        public damageDescriptionId?: number
    ) {}
}

export class NurseryInventoryDetailsModel {
    id?: number;
    date?: Moment;
    quantity?: number;
    status?: number;
    description?: string;
    nurseryInventoryId?: number;
    damageTypePickListValue?: string;
    damageTypeId?: number;
    inventoryDamageDescriptionPickListValue?: string;
    inventoryDamageDescriptionId?: number;
    damageDescriptionId?: number;
}

// Constant variables
export const STATUS_ADD = 1;
export const STATUS_CONSUME = 2;
export const STATUS_DAMAGE = 3;
export const DISPLAY_NAME_VARIETY = 'VARIETY';
export const DISPLAY_NAME_QUANTITY_TYPE = 'QUANTITY TYPE';
export const DISPLAY_NAME_DAMAGE_TYPE = 'DAMAGE TYPE';
export const DISPLAY_NAME_DAMAGE_REASON = 'DAMAGE REASON';
export const DISPLAY_NAME_DAMAGE_AREA = 'DAMAGE AREA';
