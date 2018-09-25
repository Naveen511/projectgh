import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProjectghSharedModule } from 'app/shared';
import {
    GodownPurchaseDetailsComponent,
    GodownPurchaseDetailsDetailComponent,
    GodownPurchaseDetailsUpdateComponent,
    GodownPurchaseDetailsDeletePopupComponent,
    GodownPurchaseDetailsDeleteDialogComponent,
    godownPurchaseDetailsRoute,
    godownPurchaseDetailsPopupRoute
} from './';

const ENTITY_STATES = [...godownPurchaseDetailsRoute, ...godownPurchaseDetailsPopupRoute];

@NgModule({
    imports: [ProjectghSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        GodownPurchaseDetailsComponent,
        GodownPurchaseDetailsDetailComponent,
        GodownPurchaseDetailsUpdateComponent,
        GodownPurchaseDetailsDeleteDialogComponent,
        GodownPurchaseDetailsDeletePopupComponent
    ],
    entryComponents: [
        GodownPurchaseDetailsComponent,
        GodownPurchaseDetailsUpdateComponent,
        GodownPurchaseDetailsDeleteDialogComponent,
        GodownPurchaseDetailsDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ProjectghGodownPurchaseDetailsModule {}
