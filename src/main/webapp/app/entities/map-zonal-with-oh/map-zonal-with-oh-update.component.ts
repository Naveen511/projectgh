import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IMapZonalWithOh } from 'app/shared/model/map-zonal-with-oh.model';
import { MapZonalWithOhService } from './map-zonal-with-oh.service';
import { IZonal } from 'app/shared/model/zonal.model';
import { ZonalService } from 'app/entities/zonal';
import { IOperationalHead } from 'app/shared/model/operational-head.model';
import { OperationalHeadService } from 'app/entities/operational-head';

@Component({
    selector: 'jhi-map-zonal-with-oh-update',
    templateUrl: './map-zonal-with-oh-update.component.html'
})
export class MapZonalWithOhUpdateComponent implements OnInit {
    private _mapZonalWithOh: IMapZonalWithOh;
    isSaving: boolean;

    zonals: IZonal[];

    operationalheads: IOperationalHead[];
    fromDateDp: any;
    toDateDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private mapZonalWithOhService: MapZonalWithOhService,
        private zonalService: ZonalService,
        private operationalHeadService: OperationalHeadService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ mapZonalWithOh }) => {
            this.mapZonalWithOh = mapZonalWithOh;
        });
        this.zonalService.query().subscribe(
            (res: HttpResponse<IZonal[]>) => {
                this.zonals = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.operationalHeadService.query().subscribe(
            (res: HttpResponse<IOperationalHead[]>) => {
                this.operationalheads = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.mapZonalWithOh.id !== undefined) {
            this.subscribeToSaveResponse(this.mapZonalWithOhService.update(this.mapZonalWithOh));
        } else {
            this.subscribeToSaveResponse(this.mapZonalWithOhService.create(this.mapZonalWithOh));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IMapZonalWithOh>>) {
        result.subscribe((res: HttpResponse<IMapZonalWithOh>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackOperationalHeadById(index: number, item: IOperationalHead) {
        return item.id;
    }
    get mapZonalWithOh() {
        return this._mapZonalWithOh;
    }

    set mapZonalWithOh(mapZonalWithOh: IMapZonalWithOh) {
        this._mapZonalWithOh = mapZonalWithOh;
    }
}
