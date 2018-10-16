/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/09/22 11:27:58
 *  Target: yarn
 *******************************************************************************/

// Import needed dependency
import { Moment } from 'moment';

export interface IBatchQuantity {
    id?: number;
    quantity?: number;
    date?: Moment;
    remarks?: string;
    status?: number;
    batchBatchName?: string;
    batchId?: number;
}

export class BatchQuantity implements IBatchQuantity {
    constructor(
        public id?: number,
        public quantity?: number,
        public date?: Moment,
        public remarks?: string,
        public status?: number,
        public batchBatchName?: string,
        public batchId?: number
    ) {}
}

export class BatchQuantityModel {
    id?: number;
    quantity?: number;
    date?: Moment;
    remarks?: string;
    status?: number;
    batchBatchName?: string;
    batchId?: number;
}

// Constant variable
export const STATUS_ADDED = 1;
