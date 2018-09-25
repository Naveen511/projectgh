import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProjectghSharedModule } from 'app/shared';
import {
    GodownStockDetailsComponent,
    GodownStockDetailsDetailComponent,
    GodownStockDetailsUpdateComponent,
    GodownStockDetailsDeletePopupComponent,
    GodownStockDetailsDeleteDialogComponent,
    godownStockDetailsRoute,
    godownStockDetailsPopupRoute
} from './';

const ENTITY_STATES = [...godownStockDetailsRoute, ...godownStockDetailsPopupRoute];

@NgModule({
    imports: [ProjectghSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        GodownStockDetailsComponent,
        GodownStockDetailsDetailComponent,
        GodownStockDetailsUpdateComponent,
        GodownStockDetailsDeleteDialogComponent,
        GodownStockDetailsDeletePopupComponent
    ],
    entryComponents: [
        GodownStockDetailsComponent,
        GodownStockDetailsUpdateComponent,
        GodownStockDetailsDeleteDialogComponent,
        GodownStockDetailsDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ProjectghGodownStockDetailsModule {}
