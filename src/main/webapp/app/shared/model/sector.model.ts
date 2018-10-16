import { Moment } from 'moment';
import { INursery } from 'app/shared/model//nursery.model';
import { ISectorIncharge } from 'app/shared/model//sector-incharge.model';
import { IMapSectorWithZonal } from 'app/shared/model//map-sector-with-zonal.model';
import { IMapNurseryWithSector } from 'app/shared/model//map-nursery-with-sector.model';

export interface ISector {
    id?: number;
    sectorName?: string;
    status?: number;
    nurserys?: INursery[];
    zonalZoneName?: string;
    zonalId?: number;
    financialYearSectorBatchName?: string;
    financialYearSectorId?: number;
    incharges?: ISectorIncharge[];
    mapSectorWithZonals?: IMapSectorWithZonal[];
    mapNurseryWithSectors?: IMapNurseryWithSector[];
}

export class Sector implements ISector {
    constructor(
        public id?: number,
        public sectorName?: string,
        public status?: number,
        public nurserys?: INursery[],
        public zonalZoneName?: string,
        public zonalId?: number,
        public financialYearSectorBatchName?: string,
        public financialYearSectorId?: number,
        public incharges?: ISectorIncharge[],
        public mapSectorWithZonals?: IMapSectorWithZonal[],
        public mapNurseryWithSectors?: IMapNurseryWithSector[]
    ) {}
}

export class SectorModel {
    id?: number;
    sectorName?: string;
    status?: number;
    zonalZoneName?: string;
    zonalId?: number;
    nursery?: INursery[];
    financialYearSectorBatchName?: string;
    financialYearSectorId?: number;
    incharges?: ISectorIncharge[];
    mapSectorWithZonals?: IMapSectorWithZonal[];
    mapNurseryWithSectors?: IMapNurseryWithSector[];
}
