import { Moment } from 'moment';

export interface IDamage {
    id?: number;
    noOfQuantity?: number;
    date?: Moment;
    status?: number;
    batchBatchName?: string;
    batchId?: number;
    descriptionPickListValue?: string;
    descriptionId?: number;
    damageAreaPickListValue?: string;
    damageAreaId?: number;
    financialYearDamageBatchName?: string;
    financialYearDamageId?: number;
}

export class Damage implements IDamage {
    constructor(
        public id?: number,
        public noOfQuantity?: number,
        public date?: Moment,
        public status?: number,
        public batchBatchName?: string,
        public batchId?: number,
        public descriptionPickListValue?: string,
        public descriptionId?: number,
        public damageAreaPickListValue?: string,
        public damageAreaId?: number,
        public financialYearDamageBatchName?: string,
        public financialYearDamageId?: number
    ) {}
}

export class DamageModel {
    id?: number;
    noOfQuantity?: number;
    date?: Moment;
    status?: number;
    batchBatchName?: string;
    batchId?: number;
    descriptionPickListValue?: string;
    descriptionId?: number;
    pickListDescriptionId?: number;
    damageAreaPickListValue?: string;
    damageAreaId?: number;
    financialYearDamageBatchName?: string;
    financialYearDamageId?: number;
}

export const STATUS_SEEDS = 1;
export const STATUS_SEEDLING = 2;
export const STATUS_SAPLING = 3;
export const STATUS_DISTRIBUTION = 4;
export const INVENTORY_STATUS_SEEDS = 101;
export const INVENTORY_STATUS_COVER = 102;
