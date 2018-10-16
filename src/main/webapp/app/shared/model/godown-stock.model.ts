/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/27
 *  Target: yarn
 *******************************************************************************/

// Import needed model
import { IGodownStockDetails } from 'app/shared/model//godown-stock-details.model';

export interface IGodownStock {
    id?: number;
    currentQuantity?: number;
    addedQuantity?: number;
    consumedQuantity?: number;
    description?: string;
    status?: number;
    godownStockDetails?: IGodownStockDetails[];
    pickListVarietyPickListValue?: string;
    pickListVarietyId?: number;
    pickListCategoryPickListValue?: string;
    pickListCategoryId?: number;
    pickListQuantityTypePickListValue?: string;
    pickListQuantityTypeId?: number;
    godownName?: string;
    godownId?: number;
    financialYearGodownStockBatchName?: string;
    financialYearGodownStockId?: number;
}

export class GodownStock implements IGodownStock {
    constructor(
        public id?: number,
        public currentQuantity?: number,
        public addedQuantity?: number,
        public consumedQuantity?: number,
        public description?: string,
        public status?: number,
        public godownStockDetails?: IGodownStockDetails[],
        public pickListVarietyPickListValue?: string,
        public pickListVarietyId?: number,
        public pickListCategoryPickListValue?: string,
        public pickListCategoryId?: number,
        public pickListQuantityTypePickListValue?: string,
        public pickListQuantityTypeId?: number,
        public godownName?: string,
        public godownId?: number,
        public financialYearGodownStockBatchName?: string,
        public financialYearGodownStockId?: number
    ) {}
}

export class GodownStockModel {
    id?: number;
    currentQuantity?: number;
    addedQuantity?: number;
    consumedQuantity?: number;
    description?: string;
    status?: number;
    godownStockDetails?: IGodownStockDetails[];
    pickListVarietyPickListValue?: string;
    pickListVarietyId?: number;
    pickListCategoryPickListValue?: string;
    pickListCategoryId?: number;
    pickListQuantityTypePickListValue?: string;
    pickListQuantityTypeId?: number;
    godownName?: string;
    godownId?: number;
    financialYearGodownStockBatchName?: string;
    financialYearGodownStockId?: number;
    pickListQuantityId?: number;
    pickListId?: number;
    length?: number;
}

// Constant variable
export const STATUS_ACTIVE = 1;
export const DISPLAY_NAME_VARIETY = 'VARIETY';
export const DISPLAY_NAME_QUANTITY_TYPE = 'QUANTITY TYPE';
