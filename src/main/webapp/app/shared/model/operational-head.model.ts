/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02 11:27:58
 *  Target: yarn
 *******************************************************************************/

// Import needed model and dependency
import { IZonal } from 'app/shared/model//zonal.model';
import { IMapZonalWithOh } from 'app/shared/model//map-zonal-with-oh.model';

export interface IOperationalHead {
    id?: number;
    name?: string;
    description?: string;
    status?: number;
    zonals?: IZonal[];
    mapZonalWithOhs?: IMapZonalWithOh[];
}

export class OperationalHead implements IOperationalHead {
    constructor(
        public id?: number,
        public name?: string,
        public description?: string,
        public status?: number,
        public zonals?: IZonal[],
        public mapZonalWithOhs?: IMapZonalWithOh[]
    ) {}
}

export class OperationalHeadModel {
    id?: number;
    name?: string;
    description?: string;
    status?: number;
    zonals?: IZonal[];
    mapZonalWithOhs?: IMapZonalWithOh[];
}

// Constant variable
export const STATUS_ACTIVE = 1;
