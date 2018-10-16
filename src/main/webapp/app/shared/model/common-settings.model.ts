/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/25
 *  Target: yarn
 *******************************************************************************/

export interface ICommonSettings {
    id?: number;
    daysToCloseBatch?: number;
    status?: number;
}

export class CommonSettings implements ICommonSettings {
    constructor(public id?: number, public daysToCloseBatch?: number, public status?: number) {}
}
