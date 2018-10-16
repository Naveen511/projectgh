import { Moment } from 'moment';
import { IPickListValue } from 'app/shared/model//pick-list-value.model';

export interface IPickList {
    id?: number;
    pickListName?: string;
    status?: number;
    pickListValues?: IPickListValue[];
}

export class PickList implements IPickList {
    constructor(
        public id?: number,
        public pickListName?: string,
        public status?: number,
        public pickListValues?: IPickListValue[]
    ) {}
}

export class PickListModel {
    id?: number;
    pickListName?: string;
    status?: number;
    pickListValues?: IPickListValue[];
}
