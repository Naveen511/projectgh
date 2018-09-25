import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { INurseryStock } from 'app/shared/model/nursery-stock.model';
import { NurseryStockService } from './nursery-stock.service';
import { INursery } from 'app/shared/model/nursery.model';
import { NurseryService } from 'app/entities/nursery';
import { IPickListValue } from 'app/shared/model/pick-list-value.model';
import { PickListValueService } from 'app/entities/pick-list-value';
import { IFinancialYearSettings } from 'app/shared/model/financial-year-settings.model';
import { FinancialYearSettingsService } from 'app/entities/financial-year-settings';

@Component({
    selector: 'jhi-nursery-stock-update',
    templateUrl: './nursery-stock-update.component.html'
})
export class NurseryStockUpdateComponent implements OnInit {
    private _nurseryStock: INurseryStock;
    isSaving: boolean;

    nurseries: INursery[];

    picklistvalues: IPickListValue[];

    financialyearsettings: IFinancialYearSettings[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private nurseryStockService: NurseryStockService,
        private nurseryService: NurseryService,
        private pickListValueService: PickListValueService,
        private financialYearSettingsService: FinancialYearSettingsService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ nurseryStock }) => {
            this.nurseryStock = nurseryStock;
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
        if (this.nurseryStock.id !== undefined) {
            this.subscribeToSaveResponse(this.nurseryStockService.update(this.nurseryStock));
        } else {
            this.subscribeToSaveResponse(this.nurseryStockService.create(this.nurseryStock));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<INurseryStock>>) {
        result.subscribe((res: HttpResponse<INurseryStock>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackFinancialYearSettingsById(index: number, item: IFinancialYearSettings) {
        return item.id;
    }
    get nurseryStock() {
        return this._nurseryStock;
    }

    set nurseryStock(nurseryStock: INurseryStock) {
        this._nurseryStock = nurseryStock;
    }
}
