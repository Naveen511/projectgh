import { Moment } from 'moment';

export interface IShadeArea {
    id?: number;
    noOfSeedlings?: number;
    date?: Moment;
    status?: number;
    damage?: number;
    saplings?: number;
    batchBatchName?: string;
    batchId?: number;
    financialYearShadeAreaBatchName?: string;
    financialYearShadeAreaId?: number;
}

export class ShadeArea implements IShadeArea {
    constructor(
        public id?: number,
        public noOfSeedlings?: number,
        public date?: Moment,
        public status?: number,
        public damage?: number,
        public saplings?: number,
        public batchBatchName?: string,
        public batchId?: number,
        public financialYearShadeAreaBatchName?: string,
        public financialYearShadeAreaId?: number
    ) {}
}

export class ShadeAreaModel {
    id?: number;
    noOfSeedlings?: number;
    date?: Moment;
    status?: number;
    damage?: number;
    saplings?: number;
    batchBatchName?: string;
    batchId?: number;
    financialYearShadeAreaBatchName?: string;
    financialYearShadeAreaId?: number;
}
