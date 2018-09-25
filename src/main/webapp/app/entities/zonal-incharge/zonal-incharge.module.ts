import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProjectghSharedModule } from 'app/shared';
import {
    ZonalInchargeComponent,
    ZonalInchargeDetailComponent,
    ZonalInchargeUpdateComponent,
    ZonalInchargeDeletePopupComponent,
    ZonalInchargeDeleteDialogComponent,
    zonalInchargeRoute,
    zonalInchargePopupRoute
} from './';

const ENTITY_STATES = [...zonalInchargeRoute, ...zonalInchargePopupRoute];

@NgModule({
    imports: [ProjectghSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ZonalInchargeComponent,
        ZonalInchargeDetailComponent,
        ZonalInchargeUpdateComponent,
        ZonalInchargeDeleteDialogComponent,
        ZonalInchargeDeletePopupComponent
    ],
    entryComponents: [
        ZonalInchargeComponent,
        ZonalInchargeUpdateComponent,
        ZonalInchargeDeleteDialogComponent,
        ZonalInchargeDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ProjectghZonalInchargeModule {}
