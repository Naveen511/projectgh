import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IZonal } from 'app/shared/model/zonal.model';
import { ZonalService } from './zonal.service';
import { IFinancialYearSettings } from 'app/shared/model/financial-year-settings.model';
import { FinancialYearSettingsService } from 'app/entities/financial-year-settings';
import { IOperationalHead } from 'app/shared/model/operational-head.model';
import { OperationalHeadService } from 'app/entities/operational-head';

@Component({
    selector: 'jhi-zonal-update',
    templateUrl: './zonal-update.component.html'
})
export class ZonalUpdateComponent implements OnInit {
    private _zonal: IZonal;
    isSaving: boolean;

    financialyearsettings: IFinancialYearSettings[];

    operationalheads: IOperationalHead[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private zonalService: ZonalService,
        private financialYearSettingsService: FinancialYearSettingsService,
        private operationalHeadService: OperationalHeadService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ zonal }) => {
            this.zonal = zonal;
        });
        this.financialYearSettingsService.query().subscribe(
            (res: HttpResponse<IFinancialYearSettings[]>) => {
                this.financialyearsettings = res.body;
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
        if (this.zonal.id !== undefined) {
            this.subscribeToSaveResponse(this.zonalService.update(this.zonal));
        } else {
            this.subscribeToSaveResponse(this.zonalService.create(this.zonal));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IZonal>>) {
        result.subscribe((res: HttpResponse<IZonal>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackFinancialYearSettingsById(index: number, item: IFinancialYearSettings) {
        return item.id;
    }

    trackOperationalHeadById(index: number, item: IOperationalHead) {
        return item.id;
    }
    get zonal() {
        return this._zonal;
    }

    set zonal(zonal: IZonal) {
        this._zonal = zonal;
    }
}
