import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProjectghSharedModule } from 'app/shared';
import {
    NurseryInchargeComponent,
    NurseryInchargeDetailComponent,
    NurseryInchargeUpdateComponent,
    NurseryInchargeDeletePopupComponent,
    NurseryInchargeDeleteDialogComponent,
    nurseryInchargeRoute,
    nurseryInchargePopupRoute
} from './';

const ENTITY_STATES = [...nurseryInchargeRoute, ...nurseryInchargePopupRoute];

@NgModule({
    imports: [ProjectghSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        NurseryInchargeComponent,
        NurseryInchargeDetailComponent,
        NurseryInchargeUpdateComponent,
        NurseryInchargeDeleteDialogComponent,
        NurseryInchargeDeletePopupComponent
    ],
    entryComponents: [
        NurseryInchargeComponent,
        NurseryInchargeUpdateComponent,
        NurseryInchargeDeleteDialogComponent,
        NurseryInchargeDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ProjectghNurseryInchargeModule {}
