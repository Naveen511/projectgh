import { Moment } from 'moment';

export interface IPointOfSaleDetails {
    id?: number;
    quantity?: number;
    purposeOfTaking?: string;
    donorName?: string;
    donorAddress?: string;
    contactNo?: string;
    discount?: number;
    discountAmount?: number;
    collectedAmount?: number;
    date?: Moment;
    status?: number;
    pickListVarietyPickListValue?: string;
    pickListVarietyId?: number;
    pickListCategoryPickListValue?: string;
    pickListCategoryId?: number;
    nurseryStockId?: number;
}

export class PointOfSaleDetails implements IPointOfSaleDetails {
    constructor(
        public id?: number,
        public quantity?: number,
        public purposeOfTaking?: string,
        public donorName?: string,
        public donorAddress?: string,
        public contactNo?: string,
        public discount?: number,
        public discountAmount?: number,
        public collectedAmount?: number,
        public date?: Moment,
        public status?: number,
        public pickListVarietyPickListValue?: string,
        public pickListVarietyId?: number,
        public pickListCategoryPickListValue?: string,
        public pickListCategoryId?: number,
        public nurseryStockId?: number

    ) {}
}

export class PointOfSaleDetailsModel {
    id?: number;
    quantity?: number;
    purposeOfTaking?: string;
    donorName?: string;
    donorAddress?: string;
    contactNo?: string;
    discount?: number;
    discountAmount?: number;
    collectedAmount?: number;
    date?: Moment;
    status?: number;
    pickListVarietyPickListValue?: string;
    pickListVarietyId?: number;
    pickListCategoryPickListValue?: string;
    pickListCategoryId?: number;
    nurseryStockId?: number;
}
