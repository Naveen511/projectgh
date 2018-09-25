import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IMapSectorWithZonal } from 'app/shared/model/map-sector-with-zonal.model';
import { MapSectorWithZonalService } from './map-sector-with-zonal.service';
import { IZonal } from 'app/shared/model/zonal.model';
import { ZonalService } from 'app/entities/zonal';
import { ISector } from 'app/shared/model/sector.model';
import { SectorService } from 'app/entities/sector';

@Component({
    selector: 'jhi-map-sector-with-zonal-update',
    templateUrl: './map-sector-with-zonal-update.component.html'
})
export class MapSectorWithZonalUpdateComponent implements OnInit {
    private _mapSectorWithZonal: IMapSectorWithZonal;
    isSaving: boolean;

    zonals: IZonal[];

    sectors: ISector[];
    fromDateDp: any;
    toDateDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private mapSectorWithZonalService: MapSectorWithZonalService,
        private zonalService: ZonalService,
        private sectorService: SectorService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ mapSectorWithZonal }) => {
            this.mapSectorWithZonal = mapSectorWithZonal;
        });
        this.zonalService.query().subscribe(
            (res: HttpResponse<IZonal[]>) => {
                this.zonals = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.sectorService.query().subscribe(
            (res: HttpResponse<ISector[]>) => {
                this.sectors = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.mapSectorWithZonal.id !== undefined) {
            this.subscribeToSaveResponse(this.mapSectorWithZonalService.update(this.mapSectorWithZonal));
        } else {
            this.subscribeToSaveResponse(this.mapSectorWithZonalService.create(this.mapSectorWithZonal));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IMapSectorWithZonal>>) {
        result.subscribe((res: HttpResponse<IMapSectorWithZonal>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackZonalById(index: number, item: IZonal) {
        return item.id;
    }

    trackSectorById(index: number, item: ISector) {
        return item.id;
    }
    get mapSectorWithZonal() {
        return this._mapSectorWithZonal;
    }

    set mapSectorWithZonal(mapSectorWithZonal: IMapSectorWithZonal) {
        this._mapSectorWithZonal = mapSectorWithZonal;
    }
}
