import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProjectghSharedModule } from 'app/shared';
import {
    NurseryInventoryDetailsComponent,
    NurseryInventoryDetailsDetailComponent,
    NurseryInventoryDetailsUpdateComponent,
    NurseryInventoryDetailsDeletePopupComponent,
    NurseryInventoryDetailsDeleteDialogComponent,
    nurseryInventoryDetailsRoute,
    nurseryInventoryDetailsPopupRoute
} from './';

const ENTITY_STATES = [...nurseryInventoryDetailsRoute, ...nurseryInventoryDetailsPopupRoute];

@NgModule({
    imports: [ProjectghSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        NurseryInventoryDetailsComponent,
        NurseryInventoryDetailsDetailComponent,
        NurseryInventoryDetailsUpdateComponent,
        NurseryInventoryDetailsDeleteDialogComponent,
        NurseryInventoryDetailsDeletePopupComponent
    ],
    entryComponents: [
        NurseryInventoryDetailsComponent,
        NurseryInventoryDetailsUpdateComponent,
        NurseryInventoryDetailsDeleteDialogComponent,
        NurseryInventoryDetailsDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ProjectghNurseryInventoryDetailsModule {}
