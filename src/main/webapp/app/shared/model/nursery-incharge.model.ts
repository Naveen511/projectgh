import { Moment } from 'moment';

export interface INurseryIncharge {
    id?: number;
    fromDate?: Moment;
    toDate?: Moment;
    description?: string;
    status?: number;
    nurseryNurseryName?: string;
    nurseryId?: number;
}

export class NurseryIncharge implements INurseryIncharge {
    constructor(
        public id?: number,
        public fromDate?: Moment,
        public toDate?: Moment,
        public description?: string,
        public status?: number,
        public nurseryNurseryName?: string,
        public nurseryId?: number
    ) {}
}
