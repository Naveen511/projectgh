import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { INursery } from 'app/shared/model/nursery.model';
import { NurseryService } from './nursery.service';
import { ISector } from 'app/shared/model/sector.model';
import { SectorService } from 'app/entities/sector';
import { IPickListValue } from 'app/shared/model/pick-list-value.model';
import { PickListValueService } from 'app/entities/pick-list-value';
import { IFinancialYearSettings } from 'app/shared/model/financial-year-settings.model';
import { FinancialYearSettingsService } from 'app/entities/financial-year-settings';

@Component({
    selector: 'jhi-nursery-update',
    templateUrl: './nursery-update.component.html'
})
export class NurseryUpdateComponent implements OnInit {
    private _nursery: INursery;
    isSaving: boolean;

    sectors: ISector[];

    picklistvalues: IPickListValue[];

    financialyearsettings: IFinancialYearSettings[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private nurseryService: NurseryService,
        private sectorService: SectorService,
        private pickListValueService: PickListValueService,
        private financialYearSettingsService: FinancialYearSettingsService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ nursery }) => {
            this.nursery = nursery;
        });
        this.sectorService.query().subscribe(
            (res: HttpResponse<ISector[]>) => {
                this.sectors = res.body;
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
        if (this.nursery.id !== undefined) {
            this.subscribeToSaveResponse(this.nurseryService.update(this.nursery));
        } else {
            this.subscribeToSaveResponse(this.nurseryService.create(this.nursery));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<INursery>>) {
        result.subscribe((res: HttpResponse<INursery>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackPickListValueById(index: number, item: IPickListValue) {
        return item.id;
    }

    trackFinancialYearSettingsById(index: number, item: IFinancialYearSettings) {
        return item.id;
    }
    get nursery() {
        return this._nursery;
    }

    set nursery(nursery: INursery) {
        this._nursery = nursery;
    }
}
