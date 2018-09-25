import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IDamage } from 'app/shared/model/damage.model';
import { DamageService } from './damage.service';
import { IBatch } from 'app/shared/model/batch.model';
import { BatchService } from 'app/entities/batch';
import { IPickListValue } from 'app/shared/model/pick-list-value.model';
import { PickListValueService } from 'app/entities/pick-list-value';
import { IFinancialYearSettings } from 'app/shared/model/financial-year-settings.model';
import { FinancialYearSettingsService } from 'app/entities/financial-year-settings';

@Component({
    selector: 'jhi-damage-update',
    templateUrl: './damage-update.component.html'
})
export class DamageUpdateComponent implements OnInit {
    private _damage: IDamage;
    isSaving: boolean;

    batches: IBatch[];

    picklistvalues: IPickListValue[];

    financialyearsettings: IFinancialYearSettings[];
    dateDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private damageService: DamageService,
        private batchService: BatchService,
        private pickListValueService: PickListValueService,
        private financialYearSettingsService: FinancialYearSettingsService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ damage }) => {
            this.damage = damage;
        });
        this.batchService.query().subscribe(
            (res: HttpResponse<IBatch[]>) => {
                this.batches = res.body;
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
        if (this.damage.id !== undefined) {
            this.subscribeToSaveResponse(this.damageService.update(this.damage));
        } else {
            this.subscribeToSaveResponse(this.damageService.create(this.damage));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDamage>>) {
        result.subscribe((res: HttpResponse<IDamage>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackPickListValueById(index: number, item: IPickListValue) {
        return item.id;
    }

    trackFinancialYearSettingsById(index: number, item: IFinancialYearSettings) {
        return item.id;
    }
    get damage() {
        return this._damage;
    }

    set damage(damage: IDamage) {
        this._damage = damage;
    }
}
