import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IZonalIncharge } from 'app/shared/model/zonal-incharge.model';
import { ZonalInchargeService } from './zonal-incharge.service';
import { IZonal } from 'app/shared/model/zonal.model';
import { ZonalService } from 'app/entities/zonal';

@Component({
    selector: 'jhi-zonal-incharge-update',
    templateUrl: './zonal-incharge-update.component.html'
})
export class ZonalInchargeUpdateComponent implements OnInit {
    private _zonalIncharge: IZonalIncharge;
    isSaving: boolean;

    zonals: IZonal[];
    fromDateDp: any;
    toDateDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private zonalInchargeService: ZonalInchargeService,
        private zonalService: ZonalService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ zonalIncharge }) => {
            this.zonalIncharge = zonalIncharge;
        });
        this.zonalService.query().subscribe(
            (res: HttpResponse<IZonal[]>) => {
                this.zonals = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.zonalIncharge.id !== undefined) {
            this.subscribeToSaveResponse(this.zonalInchargeService.update(this.zonalIncharge));
        } else {
            this.subscribeToSaveResponse(this.zonalInchargeService.create(this.zonalIncharge));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IZonalIncharge>>) {
        result.subscribe((res: HttpResponse<IZonalIncharge>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    get zonalIncharge() {
        return this._zonalIncharge;
    }

    set zonalIncharge(zonalIncharge: IZonalIncharge) {
        this._zonalIncharge = zonalIncharge;
    }
}
