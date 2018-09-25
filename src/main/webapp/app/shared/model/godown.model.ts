import { Moment } from 'moment';
import { IGodownPurchaseDetails } from 'app/shared/model//godown-purchase-details.model';
import { IGodownStock } from 'app/shared/model//godown-stock.model';

export interface IGodown {
    id?: number;
    name?: string;
    address?: string;
    incharge?: string;
    status?: number;
    godownPurchaseDetails?: IGodownPurchaseDetails[];
    godownStocks?: IGodownStock[];
    financialYearGodownBatchName?: string;
    financialYearGodownId?: number;
}

export class Godown implements IGodown {
    constructor(
        public id?: number,
        public name?: string,
        public address?: string,
        public incharge?: string,
        public status?: number,
        public godownPurchaseDetails?: IGodownPurchaseDetails[],
        public godownStocks?: IGodownStock[],
        public financialYearGodownBatchName?: string,
        public financialYearGodownId?: number
    ) {}
}

export class GodownModel {
    id?: number;
    name?: string;
    address?: string;
    incharge?: string;
    status?: number;
    godownPurchaseDetails?: IGodownPurchaseDetails[];
    godownStocks?: IGodownStock[];
    financialYearGodownBatchName?: string;
    financialYearGodownId?: number;
}
