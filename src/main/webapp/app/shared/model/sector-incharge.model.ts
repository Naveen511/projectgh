import { Moment } from 'moment';

export interface ISectorIncharge {
    id?: number;
    fromDate?: Moment;
    toDate?: Moment;
    description?: string;
    status?: number;
    sectorSectorName?: string;
    sectorId?: number;
}

export class SectorIncharge implements ISectorIncharge {
    constructor(
        public id?: number,
        public fromDate?: Moment,
        public toDate?: Moment,
        public description?: string,
        public status?: number,
        public sectorSectorName?: string,
        public sectorId?: number
    ) {}
}

export class SectorInchargeModel {
    id?: number;
    fromDate?: Moment;
    toDate?: Moment;
    description?: string;
    status?: number;
    sectorSectorName?: string;
    sectorId?: number;
}
