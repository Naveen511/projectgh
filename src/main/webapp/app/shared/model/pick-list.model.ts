/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02 11:27:58
 *  Target: yarn
 *******************************************************************************/

// Import needed model and dependency
import { IPickListValue } from 'app/shared/model//pick-list-value.model';

export interface IPickList {
    id?: number;
    pickListName?: string;
    status?: number;
    displayLabelName?: string;
    pickListValues?: IPickListValue[];
}

export class PickList implements IPickList {
    constructor(
        public id?: number,
        public pickListName?: string,
        public status?: number,
        public displayLabelName?: string,
        public pickListValues?: IPickListValue[]
    ) {}
}

export class PickListModel {
    id?: number;
    pickListName?: string;
    status?: number;
    displayLabelName?: string;
    pickListValues?: IPickListValue[];
}
