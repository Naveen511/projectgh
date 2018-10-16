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
        public inventoryDamageDescriptionId?: number

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
}

export const STATUS_ADD = 1;
export const STATUS_CONSUME = 2;
export const STATUS_DAMAGE = 3;
