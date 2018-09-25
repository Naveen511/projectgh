import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProjectghSharedModule } from 'app/shared';
import {
    FinancialYearSettingsComponent,
    FinancialYearSettingsDetailComponent,
    FinancialYearSettingsUpdateComponent,
    FinancialYearSettingsDeletePopupComponent,
    FinancialYearSettingsDeleteDialogComponent,
    financialYearSettingsRoute,
    financialYearSettingsPopupRoute
} from './';

const ENTITY_STATES = [...financialYearSettingsRoute, ...financialYearSettingsPopupRoute];

@NgModule({
    imports: [ProjectghSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        FinancialYearSettingsComponent,
        FinancialYearSettingsDetailComponent,
        FinancialYearSettingsUpdateComponent,
        FinancialYearSettingsDeleteDialogComponent,
        FinancialYearSettingsDeletePopupComponent
    ],
    entryComponents: [
        FinancialYearSettingsComponent,
        FinancialYearSettingsUpdateComponent,
        FinancialYearSettingsDeleteDialogComponent,
        FinancialYearSettingsDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ProjectghFinancialYearSettingsModule {}
