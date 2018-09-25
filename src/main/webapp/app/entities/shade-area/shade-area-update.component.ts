import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IShadeArea } from 'app/shared/model/shade-area.model';
import { ShadeAreaService } from './shade-area.service';
import { IBatch } from 'app/shared/model/batch.model';
import { BatchService } from 'app/entities/batch';
import { IFinancialYearSettings } from 'app/shared/model/financial-year-settings.model';
import { FinancialYearSettingsService } from 'app/entities/financial-year-settings';

@Component({
    selector: 'jhi-shade-area-update',
    templateUrl: './shade-area-update.component.html'
})
export class ShadeAreaUpdateComponent implements OnInit {
    private _shadeArea: IShadeArea;
    isSaving: boolean;

    batches: IBatch[];

    financialyearsettings: IFinancialYearSettings[];
    dateDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private shadeAreaService: ShadeAreaService,
        private batchService: BatchService,
        private financialYearSettingsService: FinancialYearSettingsService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ shadeArea }) => {
            this.shadeArea = shadeArea;
        });
        this.batchService.query().subscribe(
            (res: HttpResponse<IBatch[]>) => {
                this.batches = res.body;
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
        if (this.shadeArea.id !== undefined) {
            this.subscribeToSaveResponse(this.shadeAreaService.update(this.shadeArea));
        } else {
            this.subscribeToSaveResponse(this.shadeAreaService.create(this.shadeArea));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IShadeArea>>) {
        result.subscribe((res: HttpResponse<IShadeArea>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackFinancialYearSettingsById(index: number, item: IFinancialYearSettings) {
        return item.id;
    }
    get shadeArea() {
        return this._shadeArea;
    }

    set shadeArea(shadeArea: IShadeArea) {
        this._shadeArea = shadeArea;
    }
}
