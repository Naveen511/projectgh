import { Moment } from 'moment';

export interface IZonalIncharge {
    id?: number;
    fromDate?: Moment;
    toDate?: Moment;
    description?: string;
    status?: number;
    zonalZoneName?: string;
    zonalId?: number;
}

export class ZonalIncharge implements IZonalIncharge {
    constructor(
        public id?: number,
        public fromDate?: Moment,
        public toDate?: Moment,
        public description?: string,
        public status?: number,
        public zonalZoneName?: string,
        public zonalId?: number
    ) {}
}

export class ZonalInchargeModel {
    id?: number;
    fromDate?: Moment;
    toDate?: Moment;
    description?: string;
    status?: number;
    zonalZoneName?: string;
    zonalId?: number;
}
