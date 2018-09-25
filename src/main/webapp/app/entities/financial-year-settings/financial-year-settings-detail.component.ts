import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFinancialYearSettings } from 'app/shared/model/financial-year-settings.model';

@Component({
    selector: 'jhi-financial-year-settings-detail',
    templateUrl: './financial-year-settings-detail.component.html'
})
export class FinancialYearSettingsDetailComponent implements OnInit {
    financialYearSettings: IFinancialYearSettings;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ financialYearSettings }) => {
            this.financialYearSettings = financialYearSettings;
        });
    }

    previousState() {
        window.history.back();
    }
}
