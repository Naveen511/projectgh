import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IGodown } from 'app/shared/model/godown.model';
import { GodownService } from './godown.service';
import { IFinancialYearSettings } from 'app/shared/model/financial-year-settings.model';
import { FinancialYearSettingsService } from 'app/entities/financial-year-settings';

@Component({
    selector: 'jhi-godown-update',
    templateUrl: './godown-update.component.html'
})
export class GodownUpdateComponent implements OnInit {
    private _godown: IGodown;
    isSaving: boolean;

    financialyearsettings: IFinancialYearSettings[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private godownService: GodownService,
        private financialYearSettingsService: FinancialYearSettingsService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ godown }) => {
            this.godown = godown;
        });
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
        if (this.godown.id !== undefined) {
            this.subscribeToSaveResponse(this.godownService.update(this.godown));
        } else {
            this.subscribeToSaveResponse(this.godownService.create(this.godown));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IGodown>>) {
        result.subscribe((res: HttpResponse<IGodown>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    get godown() {
        return this._godown;
    }

    set godown(godown: IGodown) {
        this._godown = godown;
    }
}
