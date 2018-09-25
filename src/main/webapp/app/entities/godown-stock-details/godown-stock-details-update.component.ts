import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IGodownStockDetails } from 'app/shared/model/godown-stock-details.model';
import { GodownStockDetailsService } from './godown-stock-details.service';
import { IGodownStock } from 'app/shared/model/godown-stock.model';
import { GodownStockService } from 'app/entities/godown-stock';
import { IFinancialYearSettings } from 'app/shared/model/financial-year-settings.model';
import { FinancialYearSettingsService } from 'app/entities/financial-year-settings';

@Component({
    selector: 'jhi-godown-stock-details-update',
    templateUrl: './godown-stock-details-update.component.html'
})
export class GodownStockDetailsUpdateComponent implements OnInit {
    private _godownStockDetails: IGodownStockDetails;
    isSaving: boolean;

    godownstocks: IGodownStock[];

    financialyearsettings: IFinancialYearSettings[];
    dateDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private godownStockDetailsService: GodownStockDetailsService,
        private godownStockService: GodownStockService,
        private financialYearSettingsService: FinancialYearSettingsService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ godownStockDetails }) => {
            this.godownStockDetails = godownStockDetails;
        });
        this.godownStockService.query().subscribe(
            (res: HttpResponse<IGodownStock[]>) => {
                this.godownstocks = res.body;
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
        if (this.godownStockDetails.id !== undefined) {
            this.subscribeToSaveResponse(this.godownStockDetailsService.update(this.godownStockDetails));
        } else {
            this.subscribeToSaveResponse(this.godownStockDetailsService.create(this.godownStockDetails));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IGodownStockDetails>>) {
        result.subscribe((res: HttpResponse<IGodownStockDetails>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackGodownStockById(index: number, item: IGodownStock) {
        return item.id;
    }

    trackFinancialYearSettingsById(index: number, item: IFinancialYearSettings) {
        return item.id;
    }
    get godownStockDetails() {
        return this._godownStockDetails;
    }

    set godownStockDetails(godownStockDetails: IGodownStockDetails) {
        this._godownStockDetails = godownStockDetails;
    }
}
