/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/24
 *  Target: yarn
 *******************************************************************************/

// Import needed model and dependency
import { Moment } from 'moment';
import { ICoverFillingDetails } from 'app/shared/model//cover-filling-details.model';

export interface ICoverFilling {
    id?: number;
    noOfCover?: number;
    date?: Moment;
    description?: string;
    status?: number;
    damageQuantity?: number;
    coverFillingDetails?: ICoverFillingDetails[];
}

export class CoverFilling implements ICoverFilling {
    constructor(
        public id?: number,
        public noOfCover?: number,
        public date?: Moment,
        public description?: string,
        public status?: number,
        public damageQuantity?: number,
        public coverFillingDetails?: ICoverFillingDetails[]
    ) {}
}

export class CoverFillingModel {
    id?: number;
    noOfCover?: number;
    date?: Moment;
    description?: string;
    status?: number;
    damageQuantity?: number;
    coverFillingDetails?: ICoverFillingDetails[];
}
