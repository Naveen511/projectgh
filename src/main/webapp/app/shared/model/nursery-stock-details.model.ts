import { Moment } from 'moment';

export interface INurseryStockDetails {
    id?: number;
    date?: Moment;
    quantity?: number;
    description?: string;
    status?: number;
    batchBatchName?: string;
    batchId?: number;
    nurseryStockId?: number;
    itNurseryNurseryName?: string;
    itNurseryId?: number;
    saplingDamageAreaPickListValue?: string;
    saplingDamageAreaId?: number;
    financialYearStockDetailsBatchName?: string;
    financialYearStockDetailsId?: number;
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
        public batchBatchName?: string,
        public batchId?: number,
        public nurseryStockId?: number,
        public itNurseryNurseryName?: string,
        public itNurseryId?: number,
        public saplingDamageAreaPickListValue?: string,
        public saplingDamageAreaId?: number,
        public financialYearStockDetailsBatchName?: string,
        public financialYearStockDetailsId?: number,
        public stockVariety?: string,
        public stockcategory?: string,
        public stockVarietyId?: number,
        public stockCategoryId?: number
    ) {}
}

export class NurseryStockDetailsModel {
    id?: number;
    date?: Moment;
    quantity?: number;
    description?: string;
    status?: number;
    batchBatchName?: string;
    batchId?: number;
    nurseryStockId?: number;
    itNurseryNurseryName?: string;
    itNurseryId?: number;
    saplingDamageAreaPickListValue?: string;
    saplingDamageAreaId?: number;
    financialYearStockDetailsBatchName?: string;
    financialYearStockDetailsId?: number;
    stockVariety?: string;
    stockcategory?: string;
    stockVarietyId?: number;
    stockCategoryId?: number;
}

export const STATUS_ADD = 1;
export const STATUS_CONSUME = 2;
export const STATUS_SAPLING_DAMAGE = 3;
export const STATUS_SALE_POS = 4;
