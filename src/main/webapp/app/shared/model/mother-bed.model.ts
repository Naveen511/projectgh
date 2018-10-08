import { IBatch } from 'app/shared/model//batch.model';

export interface IMotherBed {
    id?: number;
    value?: string;
    status?: number;
    nurseryNurseryName?: string;
    nurseryId?: number;
    batchMotherBeds?: IBatch[];
}

export class MotherBed implements IMotherBed {
    constructor(
        public id?: number,
        public value?: string,
        public status?: number,
        public nurseryNurseryName?: string,
        public nurseryId?: number,
        public batchMotherBeds?: IBatch[]
    ) {}
}

export class MotherBedModel {
    id?: number;
    value?: string;
    status?: number;
    nurseryNurseryName?: string;
    nurseryId?: number;
    batchMotherBeds?: IBatch[];
}

export const STATUS_OCCUPIED = 3;
export const STATUS_AVAILABLE = 1;
