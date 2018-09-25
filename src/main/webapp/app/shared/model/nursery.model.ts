import { Moment } from 'moment';
import { IBatch } from 'app/shared/model//batch.model';
import { INurseryStock } from 'app/shared/model//nursery-stock.model';
import { IMotherBed } from 'app/shared/model//mother-bed.model';
import { INurseryInventory } from 'app/shared/model//nursery-inventory.model';
import { INurseryStockDetails } from 'app/shared/model//nursery-stock-details.model';
import { INurseryIncharge } from 'app/shared/model//nursery-incharge.model';
import { IMapNurseryWithSector } from 'app/shared/model//map-nursery-with-sector.model';

export interface INursery {
    id?: number;
    nurseryName?: string;
    nurseryAddress?: string;
    status?: number;
    code?: string;
    batchs?: IBatch[];
    nurseryStocks?: INurseryStock[];
    sectorSectorName?: string;
    sectorId?: number;
    nurseryTypePickListValue?: string;
    nurseryTypeId?: number;
    motherBeds?: IMotherBed[];
    nurseryInventorys?: INurseryInventory[];
    nurseryStockDetails?: INurseryStockDetails[];
    financialYearNurseryBatchName?: string;
    financialYearNurseryId?: number;
    zonalName?: string;
    incharges?: INurseryIncharge[];
    mapNurseryWithSectors?: IMapNurseryWithSector[];
}

export class Nursery implements INursery {
    constructor(
        public id?: number,
        public nurseryName?: string,
        public nurseryAddress?: string,
        public status?: number,
        public code?: string,
        public batchs?: IBatch[],
        public nurseryStocks?: INurseryStock[],
        public sectorSectorName?: string,
        public sectorId?: number,
        public nurseryTypePickListValue?: string,
        public nurseryTypeId?: number,
        public motherBeds?: IMotherBed[],
        public nurseryInventorys?: INurseryInventory[],
        public nurseryStockDetails?: INurseryStockDetails[],
        public financialYearNurseryBatchName?: string,
        public financialYearNurseryId?: number,
        public zonalName?: string,
        public incharges?: INurseryIncharge[],
        public mapNurseryWithSectors?: IMapNurseryWithSector[]
    ) {}
}

export class NurseryModel {
    id?: number;
    nurseryName?: string;
    nurseryAddress?: string;
    status?: number;
    code?: string;
    batchs?: IBatch[];
    nurseryStocks?: INurseryStock[];
    sectorSectorName?: string;
    sectorId?: number;
    nurseryTypePickListValue?: string;
    nurseryTypeId?: number;
    motherBeds?: IMotherBed[];
    zoneId?: number;
    pickListId?: number;
    nurseryInventorys?: INurseryInventory[];
    nurseryStockDetails?: INurseryStockDetails[];
    financialYearNurseryBatchName?: string;
    financialYearNurseryId?: number;
    zonalName?: string;
    incharges?: INurseryIncharge[];
    mapNurseryWithSectors?: IMapNurseryWithSector[];
}
