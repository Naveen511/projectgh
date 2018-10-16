/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02 11:27:58
 *  Target: yarn
 *******************************************************************************/

// Import needed model
import { IBatch } from 'app/shared/model//batch.model';

export interface IMotherBed {
    id?: number;
    value?: string;
    status?: number;
    nurseryNurseryName?: string;
    nurseryId?: number;
    batchMotherBeds?: IBatch[];
}

export class MotherBed implements IMotherBed {
    constructor(
        public id?: number,
        public value?: string,
        public status?: number,
        public nurseryNurseryName?: string,
        public nurseryId?: number,
        public batchMotherBeds?: IBatch[]
    ) {}
}

export class MotherBedModel {
    id?: number;
    value?: string;
    status?: number;
    nurseryNurseryName?: string;
    nurseryId?: number;
    batchMotherBeds?: IBatch[];
    zoneId?: number;
    sectorId?: number;
}

// Constant variable
export const STATUS_OCCUPIED = 3;
export const STATUS_AVAILABLE = 1;
