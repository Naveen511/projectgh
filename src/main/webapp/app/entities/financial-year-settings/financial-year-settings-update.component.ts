import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IFinancialYearSettings } from 'app/shared/model/financial-year-settings.model';
import { FinancialYearSettingsService } from './financial-year-settings.service';
import { IPickListValue } from 'app/shared/model/pick-list-value.model';
import { PickListValueService } from 'app/entities/pick-list-value';

@Component({
    selector: 'jhi-financial-year-settings-update',
    templateUrl: './financial-year-settings-update.component.html'
})
export class FinancialYearSettingsUpdateComponent implements OnInit {
    private _financialYearSettings: IFinancialYearSettings;
    isSaving: boolean;

    picklistvalues: IPickListValue[];
    startDateDp: any;
    endDateDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private financialYearSettingsService: FinancialYearSettingsService,
        private pickListValueService: PickListValueService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ financialYearSettings }) => {
            this.financialYearSettings = financialYearSettings;
        });
        this.pickListValueService.query().subscribe(
            (res: HttpResponse<IPickListValue[]>) => {
                this.picklistvalues = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.financialYearSettings.id !== undefined) {
            this.subscribeToSaveResponse(this.financialYearSettingsService.update(this.financialYearSettings));
        } else {
            this.subscribeToSaveResponse(this.financialYearSettingsService.create(this.financialYearSettings));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IFinancialYearSettings>>) {
        result.subscribe(
            (res: HttpResponse<IFinancialYearSettings>) => this.onSaveSuccess(),
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

    trackPickListValueById(index: number, item: IPickListValue) {
        return item.id;
    }
    get financialYearSettings() {
        return this._financialYearSettings;
    }

    set financialYearSettings(financialYearSettings: IFinancialYearSettings) {
        this._financialYearSettings = financialYearSettings;
    }
}
