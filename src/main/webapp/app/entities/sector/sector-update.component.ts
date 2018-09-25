import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ISector } from 'app/shared/model/sector.model';
import { SectorService } from './sector.service';
import { IZonal } from 'app/shared/model/zonal.model';
import { ZonalService } from 'app/entities/zonal';
import { IFinancialYearSettings } from 'app/shared/model/financial-year-settings.model';
import { FinancialYearSettingsService } from 'app/entities/financial-year-settings';

@Component({
    selector: 'jhi-sector-update',
    templateUrl: './sector-update.component.html'
})
export class SectorUpdateComponent implements OnInit {
    private _sector: ISector;
    isSaving: boolean;

    zonals: IZonal[];

    financialyearsettings: IFinancialYearSettings[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private sectorService: SectorService,
        private zonalService: ZonalService,
        private financialYearSettingsService: FinancialYearSettingsService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sector }) => {
            this.sector = sector;
        });
        this.zonalService.query().subscribe(
            (res: HttpResponse<IZonal[]>) => {
                this.zonals = res.body;
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
        if (this.sector.id !== undefined) {
            this.subscribeToSaveResponse(this.sectorService.update(this.sector));
        } else {
            this.subscribeToSaveResponse(this.sectorService.create(this.sector));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ISector>>) {
        result.subscribe((res: HttpResponse<ISector>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackZonalById(index: number, item: IZonal) {
        return item.id;
    }

    trackFinancialYearSettingsById(index: number, item: IFinancialYearSettings) {
        return item.id;
    }
    get sector() {
        return this._sector;
    }

    set sector(sector: ISector) {
        this._sector = sector;
    }
}
