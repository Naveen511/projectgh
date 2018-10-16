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

export interface IShadeArea {
    id?: number;
    noOfSeedlings?: number;
    date?: Moment;
    status?: number;
    damage?: number;
    saplings?: number;
    round?: number;
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
        public round?: number,
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
    round?: number;
    batchBatchName?: string;
    batchId?: number;
    financialYearShadeAreaBatchName?: string;
    financialYearShadeAreaId?: number;
}
