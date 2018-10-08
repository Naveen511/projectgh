import { Moment } from 'moment';
import { ISector } from 'app/shared/model//sector.model';
import { IMapZonalWithOh } from 'app/shared/model//map-zonal-with-oh.model';
import { IZonalIncharge } from 'app/shared/model//zonal-incharge.model';
import { IMapSectorWithZonal } from 'app/shared/model//map-sector-with-zonal.model';

export interface IZonal {
    id?: number;
    zoneName?: string;
    status?: number;
    sectors?: ISector[];
    financialYearBatchName?: string;
    financialYearId?: number;
    operationalHeadName?: string;
    operationalHeadId?: number;
    mapZonalWithOhs?: IMapZonalWithOh[];
    zonalIncharges?: IZonalIncharge[];
    mapSectorWithZonals?: IMapSectorWithZonal[];
}

export class Zonal implements IZonal {
    constructor(
        public id?: number,
        public zoneName?: string,
        public status?: number,
        public sectors?: ISector[],
        public financialYearBatchName?: string,
        public financialYearId?: number,
        public operationalHeadName?: string,
        public operationalHeadId?: number,
        public mapZonalWithOhs?: IMapZonalWithOh[],
        public zonalIncharges?: IZonalIncharge[],
        public mapSectorWithZonals?: IMapSectorWithZonal[]
    ) {}
}

export class ZonalModel {
    id?: number;
    zoneName?: string;
    status?: number;
    sectors?: ISector[];
    financialYearBatchName?: string;
    financialYearId?: number;
    operationalHeadName?: string;
    operationalHeadId?: number;
    mapZonalWithOhs?: IMapZonalWithOh[];
    zonalIncharges?: IZonalIncharge[];
    mapSectorWithZonals?: IMapSectorWithZonal[];
}

export const STATUS_ACTIVE = 1;
