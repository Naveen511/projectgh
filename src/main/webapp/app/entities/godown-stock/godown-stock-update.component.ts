import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IGodownStock } from 'app/shared/model/godown-stock.model';
import { GodownStockService } from './godown-stock.service';
import { IPickListValue } from 'app/shared/model/pick-list-value.model';
import { PickListValueService } from 'app/entities/pick-list-value';
import { IGodown } from 'app/shared/model/godown.model';
import { GodownService } from 'app/entities/godown';
import { IFinancialYearSettings } from 'app/shared/model/financial-year-settings.model';
import { FinancialYearSettingsService } from 'app/entities/financial-year-settings';

@Component({
    selector: 'jhi-godown-stock-update',
    templateUrl: './godown-stock-update.component.html'
})
export class GodownStockUpdateComponent implements OnInit {
    private _godownStock: IGodownStock;
    isSaving: boolean;

    picklistvalues: IPickListValue[];

    godowns: IGodown[];

    financialyearsettings: IFinancialYearSettings[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private godownStockService: GodownStockService,
        private pickListValueService: PickListValueService,
        private godownService: GodownService,
        private financialYearSettingsService: FinancialYearSettingsService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ godownStock }) => {
            this.godownStock = godownStock;
        });
        this.pickListValueService.query().subscribe(
            (res: HttpResponse<IPickListValue[]>) => {
                this.picklistvalues = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.godownService.query().subscribe(
            (res: HttpResponse<IGodown[]>) => {
                this.godowns = res.body;
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
        if (this.godownStock.id !== undefined) {
            this.subscribeToSaveResponse(this.godownStockService.update(this.godownStock));
        } else {
            this.subscribeToSaveResponse(this.godownStockService.create(this.godownStock));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IGodownStock>>) {
        result.subscribe((res: HttpResponse<IGodownStock>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackPickListValueById(index: number, item: IPickListValue) {
        return item.id;
    }

    trackGodownById(index: number, item: IGodown) {
        return item.id;
    }

    trackFinancialYearSettingsById(index: number, item: IFinancialYearSettings) {
        return item.id;
    }
    get godownStock() {
        return this._godownStock;
    }

    set godownStock(godownStock: IGodownStock) {
        this._godownStock = godownStock;
    }
}
