/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02 11:27:58
 *  Target: yarn
 *******************************************************************************/

export interface IQuantity {
    id?: number;
    approxQuantity?: number;
    pickListVarietyId?: number;
    pickListCategoryId?: number;
    pickListVarietyPickListValue?: string;
    pickListCategoryPickListValue?: string;
}

export class Quantity implements IQuantity {
    constructor(
        public id?: number,
        public approxQuantity?: number,
        public pickListVarietyId?: number,
        public pickListCategoryId?: number,
        public pickListVarietyPickListValue?: string,
        public pickListCategoryPickListValue?: string
    ) {}
}

export class QuantityModel {
    id?: number;
    approxQuantity?: number;
    pickListVarietyId?: number;
    pickListCategoryId?: number;
    pickListId?: number;
    pickListVarietyPickListValue?: string;
    pickListCategoryPickListValue?: string;
}
