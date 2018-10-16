import { Moment } from 'moment';

export interface ICoverFillingDetails {
    id?: number;
    quantity?: number;
    date?: Moment;
    status?: number;
    description?: string;
    coverFillingId?: number;
    damageTypePickListValue?: string;
    damageTypeId?: number;
    coverFillingDamageDescriptionPickListValue?: string;
    coverFillingDamageDescriptionId?: number;
}

export class CoverFillingDetails implements ICoverFillingDetails {
    constructor(
        public id?: number,
        public quantity?: number,
        public date?: Moment,
        public status?: number,
        public description?: string,
        public coverFillingId?: number,
        public damageTypePickListValue?: string,
        public damageTypeId?: number,
        public coverFillingDamageDescriptionPickListValue?: string,
        public coverFillingDamageDescriptionId?: number

    ) {}
}

export interface ICoverFillingDetailsModel {
    id?: number;
    quantity?: number;
    date?: Moment;
    status?: number;
    description?: string;
    coverFillingId?: number;
    damageTypePickListValue?: string;
    damageTypeId?: number;
    coverFillingDamageDescriptionPickListValue?: string;
    coverFillingDamageDescriptionId?: number;

}

export const STATUS_ADD = 1;
export const STATUS_CONSUME = 2;
export const STATUS_DAMAGE = 3;
