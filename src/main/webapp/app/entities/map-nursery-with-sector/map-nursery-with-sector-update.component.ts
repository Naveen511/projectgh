import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IMapNurseryWithSector } from 'app/shared/model/map-nursery-with-sector.model';
import { MapNurseryWithSectorService } from './map-nursery-with-sector.service';
import { INursery } from 'app/shared/model/nursery.model';
import { NurseryService } from 'app/entities/nursery';
import { ISector } from 'app/shared/model/sector.model';
import { SectorService } from 'app/entities/sector';

@Component({
    selector: 'jhi-map-nursery-with-sector-update',
    templateUrl: './map-nursery-with-sector-update.component.html'
})
export class MapNurseryWithSectorUpdateComponent implements OnInit {
    private _mapNurseryWithSector: IMapNurseryWithSector;
    isSaving: boolean;

    nurseries: INursery[];

    sectors: ISector[];
    fromDateDp: any;
    toDateDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private mapNurseryWithSectorService: MapNurseryWithSectorService,
        private nurseryService: NurseryService,
        private sectorService: SectorService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ mapNurseryWithSector }) => {
            this.mapNurseryWithSector = mapNurseryWithSector;
        });
        this.nurseryService.query().subscribe(
            (res: HttpResponse<INursery[]>) => {
                this.nurseries = res.body;
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
        if (this.mapNurseryWithSector.id !== undefined) {
            this.subscribeToSaveResponse(this.mapNurseryWithSectorService.update(this.mapNurseryWithSector));
        } else {
            this.subscribeToSaveResponse(this.mapNurseryWithSectorService.create(this.mapNurseryWithSector));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IMapNurseryWithSector>>) {
        result.subscribe(
            (res: HttpResponse<IMapNurseryWithSector>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
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

    trackNurseryById(index: number, item: INursery) {
        return item.id;
    }

    trackSectorById(index: number, item: ISector) {
        return item.id;
    }
    get mapNurseryWithSector() {
        return this._mapNurseryWithSector;
    }

    set mapNurseryWithSector(mapNurseryWithSector: IMapNurseryWithSector) {
        this._mapNurseryWithSector = mapNurseryWithSector;
    }
}
