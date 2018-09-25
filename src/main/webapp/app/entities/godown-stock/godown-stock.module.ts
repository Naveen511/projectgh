import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProjectghSharedModule } from 'app/shared';
import {
    GodownStockComponent,
    GodownStockDetailComponent,
    GodownStockUpdateComponent,
    GodownStockDeletePopupComponent,
    GodownStockDeleteDialogComponent,
    godownStockRoute,
    godownStockPopupRoute
} from './';

const ENTITY_STATES = [...godownStockRoute, ...godownStockPopupRoute];

@NgModule({
    imports: [ProjectghSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        GodownStockComponent,
        GodownStockDetailComponent,
        GodownStockUpdateComponent,
        GodownStockDeleteDialogComponent,
        GodownStockDeletePopupComponent
    ],
    entryComponents: [GodownStockComponent, GodownStockUpdateComponent, GodownStockDeleteDialogComponent, GodownStockDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ProjectghGodownStockModule {}
