import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ISectorIncharge } from 'app/shared/model/sector-incharge.model';
import { SectorInchargeService } from './sector-incharge.service';
import { ISector } from 'app/shared/model/sector.model';
import { SectorService } from 'app/entities/sector';

@Component({
    selector: 'jhi-sector-incharge-update',
    templateUrl: './sector-incharge-update.component.html'
})
export class SectorInchargeUpdateComponent implements OnInit {
    private _sectorIncharge: ISectorIncharge;
    isSaving: boolean;

    sectors: ISector[];
    fromDateDp: any;
    toDateDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private sectorInchargeService: SectorInchargeService,
        private sectorService: SectorService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sectorIncharge }) => {
            this.sectorIncharge = sectorIncharge;
        });
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
        if (this.sectorIncharge.id !== undefined) {
            this.subscribeToSaveResponse(this.sectorInchargeService.update(this.sectorIncharge));
        } else {
            this.subscribeToSaveResponse(this.sectorInchargeService.create(this.sectorIncharge));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ISectorIncharge>>) {
        result.subscribe((res: HttpResponse<ISectorIncharge>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackSectorById(index: number, item: ISector) {
        return item.id;
    }
    get sectorIncharge() {
        return this._sectorIncharge;
    }

    set sectorIncharge(sectorIncharge: ISectorIncharge) {
        this._sectorIncharge = sectorIncharge;
    }
}
