import { Moment } from 'moment';
import { INurseryStockDetails } from 'app/shared/model//nursery-stock-details.model';
import { IPointOfSaleDetails } from 'app/shared/model//point-of-sale-details.model';

export interface INurseryStock {
    id?: number;
    currentQuantity?: number;
    addedQuantity?: number;
    consumedQuantity?: number;
    description?: string;
    status?: number;
    posQuantity?: number;
    nurseryStockDetails?: INurseryStockDetails[];
    nurseryNurseryName?: string;
    nurseryId?: number;
    pickListVarietyPickListValue?: string;
    pickListVarietyId?: number;
    pickListCategoryPickListValue?: string;
    pickListCategoryId?: number;
    financialYearNurseryStockBatchName?: string;
    financialYearNurseryStockId?: number;
    pointOfSaleDetails?: IPointOfSaleDetails[];
}

export class NurseryStock implements INurseryStock {
    constructor(
        public id?: number,
        public currentQuantity?: number,
        public addedQuantity?: number,
        public consumedQuantity?: number,
        public description?: string,
        public status?: number,
        public posQuantity?: number,
        public nurseryStockDetails?: INurseryStockDetails[],
        public nurseryNurseryName?: string,
        public nurseryId?: number,
        public pickListVarietyPickListValue?: string,
        public pickListVarietyId?: number,
        public pickListCategoryPickListValue?: string,
        public pickListCategoryId?: number,
        public financialYearNurseryStockBatchName?: string,
        public financialYearNurseryStockId?: number,
        public pointOfSaleDetails?: IPointOfSaleDetails[]
    ) {}
}

export class NurseryStockModel {
    id?: number;
    currentQuantity?: number;
    addedQuantity?: number;
    consumedQuantity?: number;
    description?: string;
    status?: number;
    posQuantity?: number;
    nurseryStockDetails?: INurseryStockDetails[];
    nurseryNurseryName?: string;
    nurseryId?: number;
    pickListVarietyPickListValue?: string;
    pickListVarietyId?: number;
    pickListCategoryPickListValue?: string;
    pickListCategoryId?: number;
    financialYearNurseryStockBatchName?: string;
    financialYearNurseryStockId?: number;
    pointOfSaleDetails?: IPointOfSaleDetails[];
}

export const STATUS_FROM_BATCH = 1;
export const STATUS_DIRECT = 2;
