/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/20
 *  Target: yarn
 *******************************************************************************/

// Import needed dependency
import { Moment } from 'moment';

export interface IGodownPurchaseDetails {
    id?: number;
    quantity?: number;
    date?: Moment;
    price?: number;
    ownedBy?: string;
    vendorName?: string;
    vendorAddress?: string;
    vendorPhone?: number;
    description?: string;
    status?: number;
    pickListVarietyPickListValue?: string;
    pickListVarietyId?: number;
    pickListCategoryPickListValue?: string;
    pickListCategoryId?: number;
    pickListQuantityTypePickListValue?: string;
    pickListQuantityTypeId?: number;
    godownName?: string;
    godownId?: number;
    financialYearGodownPurchaseBatchName?: string;
    financialYearGodownPurchaseId?: number;
}

export class GodownPurchaseDetails implements IGodownPurchaseDetails {
    constructor(
        public id?: number,
        public quantity?: number,
        public date?: Moment,
        public price?: number,
        public ownedBy?: string,
        public vendorName?: string,
        public vendorAddress?: string,
        public vendorPhone?: number,
        public description?: string,
        public status?: number,
        public pickListVarietyPickListValue?: string,
        public pickListVarietyId?: number,
        public pickListCategoryPickListValue?: string,
        public pickListCategoryId?: number,
        public pickListQuantityTypePickListValue?: string,
        public pickListQuantityTypeId?: number,
        public godownName?: string,
        public godownId?: number,
        public financialYearGodownPurchaseBatchName?: string,
        public financialYearGodownPurchaseId?: number
    ) {}
}

export class GodownPurchaseDetailsModel {
    id?: number;
    quantity?: number;
    date?: Moment;
    price?: number;
    ownedBy?: string;
    vendorName?: string;
    vendorAddress?: string;
    vendorPhone?: number;
    description?: string;
    status?: number;
    pickListVarietyPickListValue?: string;
    pickListVarietyId?: number;
    pickListCategoryPickListValue?: string;
    pickListCategoryId?: number;
    pickListQuantityTypePickListValue?: string;
    pickListQuantityTypeId?: number;
    godownName?: string;
    godownId?: number;
    pickList?: String;
    pickListId?: number;
    pickListsId?: number;
    financialYearGodownPurchaseBatchName?: string;
    financialYearGodownPurchaseId?: number;
}

// Constant variable
export const DISPLAY_NAME_VARIETY = 'VARIETY';
export const DISPLAY_NAME_QUANTITY_TYPE = 'QUANTITY TYPE';
export const STATUS_ADDED_TO_STOCK = 2;
