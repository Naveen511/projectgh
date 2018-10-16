/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02 11:27:58
 *  Target: yarn
 *******************************************************************************/

// Import needed dependency
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

export class NurseryInchargeModel {
    id?: number;
    fromDate?: Moment;
    toDate?: Moment;
    description?: string;
    status?: number;
    nurseryNurseryName?: string;
    nurseryId?: number;
}
