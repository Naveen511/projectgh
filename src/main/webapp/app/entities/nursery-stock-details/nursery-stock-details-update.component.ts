import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { INurseryStockDetails } from 'app/shared/model/nursery-stock-details.model';
import { NurseryStockDetailsService } from './nursery-stock-details.service';
import { IBatch } from 'app/shared/model/batch.model';
import { BatchService } from 'app/entities/batch';
import { INurseryStock } from 'app/shared/model/nursery-stock.model';
import { NurseryStockService } from 'app/entities/nursery-stock';
import { INursery } from 'app/shared/model/nursery.model';
import { NurseryService } from 'app/entities/nursery';
import { IPickListValue } from 'app/shared/model/pick-list-value.model';
import { PickListValueService } from 'app/entities/pick-list-value';
import { IFinancialYearSettings } from 'app/shared/model/financial-year-settings.model';
import { FinancialYearSettingsService } from 'app/entities/financial-year-settings';

@Component({
    selector: 'jhi-nursery-stock-details-update',
    templateUrl: './nursery-stock-details-update.component.html'
})
export class NurseryStockDetailsUpdateComponent implements OnInit {
    private _nurseryStockDetails: INurseryStockDetails;
    isSaving: boolean;

    batches: IBatch[];

    nurserystocks: INurseryStock[];

    nurseries: INursery[];

    picklistvalues: IPickListValue[];

    financialyearsettings: IFinancialYearSettings[];
    dateDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private nurseryStockDetailsService: NurseryStockDetailsService,
        private batchService: BatchService,
        private nurseryStockService: NurseryStockService,
        private nurseryService: NurseryService,
        private pickListValueService: PickListValueService,
        private financialYearSettingsService: FinancialYearSettingsService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ nurseryStockDetails }) => {
            this.nurseryStockDetails = nurseryStockDetails;
        });
        this.batchService.query().subscribe(
            (res: HttpResponse<IBatch[]>) => {
                this.batches = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.nurseryStockService.query().subscribe(
            (res: HttpResponse<INurseryStock[]>) => {
                this.nurserystocks = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
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
        if (this.nurseryStockDetails.id !== undefined) {
            this.subscribeToSaveResponse(this.nurseryStockDetailsService.update(this.nurseryStockDetails));
        } else {
            this.subscribeToSaveResponse(this.nurseryStockDetailsService.create(this.nurseryStockDetails));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<INurseryStockDetails>>) {
        result.subscribe((res: HttpResponse<INurseryStockDetails>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackBatchById(index: number, item: IBatch) {
        return item.id;
    }

    trackNurseryStockById(index: number, item: INurseryStock) {
        return item.id;
    }

    trackNurseryById(index: number, item: INursery) {
        return item.id;
    }

    trackPickListValueById(index: number, item: IPickListValue) {
        return item.id;
    }

    trackFinancialYearSettingsById(index: number, item: IFinancialYearSettings) {
        return item.id;
    }
    get nurseryStockDetails() {
        return this._nurseryStockDetails;
    }

    set nurseryStockDetails(nurseryStockDetails: INurseryStockDetails) {
        this._nurseryStockDetails = nurseryStockDetails;
    }
}
