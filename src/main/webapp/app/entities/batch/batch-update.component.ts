import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IBatch } from 'app/shared/model/batch.model';
import { BatchService } from './batch.service';
import { INursery } from 'app/shared/model/nursery.model';
import { NurseryService } from 'app/entities/nursery';
import { IPickListValue } from 'app/shared/model/pick-list-value.model';
import { PickListValueService } from 'app/entities/pick-list-value';
import { IMotherBed } from 'app/shared/model/mother-bed.model';
import { MotherBedService } from 'app/entities/mother-bed';
import { IFinancialYearSettings } from 'app/shared/model/financial-year-settings.model';
import { FinancialYearSettingsService } from 'app/entities/financial-year-settings';

@Component({
    selector: 'jhi-batch-update',
    templateUrl: './batch-update.component.html'
})
export class BatchUpdateComponent implements OnInit {
    private _batch: IBatch;
    isSaving: boolean;

    nurseries: INursery[];

    picklistvalues: IPickListValue[];

    motherbeds: IMotherBed[];

    financialyearsettings: IFinancialYearSettings[];
    sowingDateDp: any;
    closedDateDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private batchService: BatchService,
        private nurseryService: NurseryService,
        private pickListValueService: PickListValueService,
        private motherBedService: MotherBedService,
        private financialYearSettingsService: FinancialYearSettingsService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ batch }) => {
            this.batch = batch;
        });
        this.nurseryService.query().subscribe(
            (res: HttpResponse<INursery[]>) => {
                this.nurseries = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.pickListValueService.query().subscribe(
            (res: HttpResponse<IPickListValue[]>) => {
                this.picklistvalues = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.motherBedService.query().subscribe(
            (res: HttpResponse<IMotherBed[]>) => {
                this.motherbeds = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.financialYearSettingsService.query().subscribe(
            (res: HttpResponse<IFinancialYearSettings[]>) => {
                this.financialyearsettings = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.batch.id !== undefined) {
            this.subscribeToSaveResponse(this.batchService.update(this.batch));
        } else {
            this.subscribeToSaveResponse(this.batchService.create(this.batch));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IBatch>>) {
        result.subscribe((res: HttpResponse<IBatch>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackPickListValueById(index: number, item: IPickListValue) {
        return item.id;
    }

    trackMotherBedById(index: number, item: IMotherBed) {
        return item.id;
    }

    trackFinancialYearSettingsById(index: number, item: IFinancialYearSettings) {
        return item.id;
    }
    get batch() {
        return this._batch;
    }

    set batch(batch: IBatch) {
        this._batch = batch;
    }
}
