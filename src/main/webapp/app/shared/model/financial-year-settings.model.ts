/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/20
 *  Target: yarn
 *******************************************************************************/

// Import needed model and dependency
import { Moment } from 'moment';
import { IZonal } from 'app/shared/model//zonal.model';
import { ISector } from 'app/shared/model//sector.model';
import { INursery } from 'app/shared/model//nursery.model';
import { IBatch } from 'app/shared/model//batch.model';
import { IDamage } from 'app/shared/model//damage.model';
import { IShadeArea } from 'app/shared/model//shade-area.model';
import { INurseryStock } from 'app/shared/model//nursery-stock.model';
import { INurseryStockDetails } from 'app/shared/model//nursery-stock-details.model';
import { IGodown } from 'app/shared/model//godown.model';
import { IGodownStock } from 'app/shared/model//godown-stock.model';
import { IGodownStockDetails } from 'app/shared/model//godown-stock-details.model';
import { IGodownPurchaseDetails } from 'app/shared/model//godown-purchase-details.model';

export interface IFinancialYearSettings {
    id?: number;
    batchName?: string;
    startDate?: Moment;
    endDate?: Moment;
    status?: number;
    financialYearPickListValue?: string;
    financialYearId?: number;
    zonals?: IZonal[];
    sectors?: ISector[];
    nurseries?: INursery[];
    batches?: IBatch[];
    damages?: IDamage[];
    shadeAreas?: IShadeArea[];
    nurseryStocks?: INurseryStock[];
    nurseryStockDetails?: INurseryStockDetails[];
    godowns?: IGodown[];
    godownStocks?: IGodownStock[];
    godownStockDetails?: IGodownStockDetails[];
    godownPurchaseDetails?: IGodownPurchaseDetails[];
    pickListId?: number;
}

export class FinancialYearSettings implements IFinancialYearSettings {
    constructor(
        public id?: number,
        public batchName?: string,
        public startDate?: Moment,
        public endDate?: Moment,
        public status?: number,
        public financialYearPickListValue?: string,
        public financialYearId?: number,
        public zonals?: IZonal[],
        public sectors?: ISector[],
        public nurseries?: INursery[],
        public batches?: IBatch[],
        public damages?: IDamage[],
        public shadeAreas?: IShadeArea[],
        public nurseryStocks?: INurseryStock[],
        public nurseryStockDetails?: INurseryStockDetails[],
        public godowns?: IGodown[],
        public godownStocks?: IGodownStock[],
        public godownStockDetails?: IGodownStockDetails[],
        public godownPurchaseDetails?: IGodownPurchaseDetails[],
        public pickListId?: number
    ) {}
}

export class FinancialYearSettingsModel {
    id?: number;
    batchName?: string;
    startDate?: Moment;
    endDate?: Moment;
    status?: number;
    financialYearPickListValue?: string;
    financialYearId?: number;
    zonals?: IZonal[];
    sectors?: ISector[];
    nurseries?: INursery[];
    batches?: IBatch[];
    damages?: IDamage[];
    shadeAreas?: IShadeArea[];
    nurseryStocks?: INurseryStock[];
    nurseryStockDetails?: INurseryStockDetails[];
    godowns?: IGodown[];
    godownStocks?: IGodownStock[];
    godownStockDetails?: IGodownStockDetails[];
    godownPurchaseDetails?: IGodownPurchaseDetails[];
    pickListId?: number;
}

// Constant variable
export const STATUS_ACTIVE = 1;
export const STATUS_INACTIVE = 2;
export const DISPLAY_NAME_YEAR = 'YEAR';
