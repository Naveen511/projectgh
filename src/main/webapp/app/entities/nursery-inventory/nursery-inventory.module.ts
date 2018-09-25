import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProjectghSharedModule } from 'app/shared';
import {
    NurseryInventoryComponent,
    NurseryInventoryDetailComponent,
    NurseryInventoryUpdateComponent,
    NurseryInventoryDeletePopupComponent,
    NurseryInventoryDeleteDialogComponent,
    nurseryInventoryRoute,
    nurseryInventoryPopupRoute
} from './';

const ENTITY_STATES = [...nurseryInventoryRoute, ...nurseryInventoryPopupRoute];

@NgModule({
    imports: [ProjectghSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        NurseryInventoryComponent,
        NurseryInventoryDetailComponent,
        NurseryInventoryUpdateComponent,
        NurseryInventoryDeleteDialogComponent,
        NurseryInventoryDeletePopupComponent
    ],
    entryComponents: [
        NurseryInventoryComponent,
        NurseryInventoryUpdateComponent,
        NurseryInventoryDeleteDialogComponent,
        NurseryInventoryDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ProjectghNurseryInventoryModule {}
