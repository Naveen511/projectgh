import { IBatch } from 'app/shared/model//batch.model';

export interface IMotherBed {
    id?: number;
    value?: number;
    status?: number;
    nurseryNurseryName?: string;
    nurseryId?: number;
    batchMotherBeds?: IBatch[];
}

export class MotherBed implements IMotherBed {
    constructor(
        public id?: number,
        public value?: number,
        public status?: number,
        public nurseryNurseryName?: string,
        public nurseryId?: number,
        public batchMotherBeds?: IBatch[]
    ) {}
}

export class MotherBedModel {
    id?: number;
    value?: number;
    status?: number;
    nurseryNurseryName?: string;
    nurseryId?: number;
    batchMotherBeds?: IBatch[];
}
