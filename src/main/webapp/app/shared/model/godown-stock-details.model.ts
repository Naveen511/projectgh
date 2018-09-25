import { Moment } from 'moment';

export interface IGodownStockDetails {
    id?: number;
    date?: Moment;
    quantity?: number;
    description?: string;
    status?: number;
    price?: number;
    godownStockId?: number;
    financialYearGodownStockDetailsBatchName?: string;
    financialYearGodownStockDetailsId?: number;
}

export class GodownStockDetails implements IGodownStockDetails {
    constructor(
        public id?: number,
        public date?: Moment,
        public quantity?: number,
        public description?: string,
        public status?: number,
        public price?: number,
        public godownStockId?: number,
        public financialYearGodownStockDetailsBatchName?: string,
        public financialYearGodownStockDetailsId?: number
    ) {}
}

export class GodownStockDetailsModel {
    id?: number;
    date?: Moment;
    quantity?: number;
    description?: string;
    status?: number;
    price?: number;
    godownStockId?: number;
    financialYearGodownStockDetailsBatchName?: string;
    financialYearGodownStockDetailsId?: number;
}

export const STATUS_ADD = 1;
export const STATUS_CONSUME = 2;
export const STATUS_DAMAGE = 3;
