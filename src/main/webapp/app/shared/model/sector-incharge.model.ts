/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02 11:27:58
 *  Target: yarn
 *******************************************************************************/

// Import needed model and dependency
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
