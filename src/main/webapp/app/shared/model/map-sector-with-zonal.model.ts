import { Moment } from 'moment';

export interface IMapSectorWithZonal {
    id?: number;
    fromDate?: Moment;
    toDate?: Moment;
    description?: string;
    status?: number;
    zonalZoneName?: string;
    zonalId?: number;
    sectorSectorName?: string;
    sectorId?: number;
}

export class MapSectorWithZonal implements IMapSectorWithZonal {
    constructor(
        public id?: number,
        public fromDate?: Moment,
        public toDate?: Moment,
        public description?: string,
        public status?: number,
        public zonalZoneName?: string,
        public zonalId?: number,
        public sectorSectorName?: string,
        public sectorId?: number
    ) {}
}

export class MapSectorWithZonalModel {
    id?: number;
    fromDate?: Moment;
    toDate?: Moment;
    description?: string;
    status?: number;
    zonalZoneName?: string;
    zonalId?: number;
    sectorSectorName?: string;
    sectorId?: number;
}

export const STATUS_ACTIVE = 1;
export const STATUS_INACTIVE = 2;
