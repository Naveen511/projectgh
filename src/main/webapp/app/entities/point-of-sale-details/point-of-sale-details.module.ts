import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProjectghSharedModule } from 'app/shared';
import {
    PointOfSaleDetailsComponent,
    PointOfSaleDetailsDetailComponent,
    PointOfSaleDetailsUpdateComponent,
    PointOfSaleDetailsDeletePopupComponent,
    PointOfSaleDetailsDeleteDialogComponent,
    pointOfSaleDetailsRoute,
    pointOfSaleDetailsPopupRoute
} from './';

const ENTITY_STATES = [...pointOfSaleDetailsRoute, ...pointOfSaleDetailsPopupRoute];

@NgModule({
    imports: [ProjectghSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PointOfSaleDetailsComponent,
        PointOfSaleDetailsDetailComponent,
        PointOfSaleDetailsUpdateComponent,
        PointOfSaleDetailsDeleteDialogComponent,
        PointOfSaleDetailsDeletePopupComponent
    ],
    entryComponents: [
        PointOfSaleDetailsComponent,
        PointOfSaleDetailsUpdateComponent,
        PointOfSaleDetailsDeleteDialogComponent,
        PointOfSaleDetailsDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ProjectghPointOfSaleDetailsModule {}
