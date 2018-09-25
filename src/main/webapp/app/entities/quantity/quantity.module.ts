import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProjectghSharedModule } from 'app/shared';
import {
    QuantityComponent,
    QuantityDetailComponent,
    QuantityUpdateComponent,
    QuantityDeletePopupComponent,
    QuantityDeleteDialogComponent,
    quantityRoute,
    quantityPopupRoute
} from './';

const ENTITY_STATES = [...quantityRoute, ...quantityPopupRoute];

@NgModule({
    imports: [ProjectghSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        QuantityComponent,
        QuantityDetailComponent,
        QuantityUpdateComponent,
        QuantityDeleteDialogComponent,
        QuantityDeletePopupComponent
    ],
    entryComponents: [QuantityComponent, QuantityUpdateComponent, QuantityDeleteDialogComponent, QuantityDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ProjectghQuantityModule {}
