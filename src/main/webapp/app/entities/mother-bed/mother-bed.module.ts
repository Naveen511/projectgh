import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProjectghSharedModule } from 'app/shared';
import {
    MotherBedComponent,
    MotherBedDetailComponent,
    MotherBedUpdateComponent,
    MotherBedDeletePopupComponent,
    MotherBedDeleteDialogComponent,
    motherBedRoute,
    motherBedPopupRoute
} from './';

const ENTITY_STATES = [...motherBedRoute, ...motherBedPopupRoute];

@NgModule({
    imports: [ProjectghSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MotherBedComponent,
        MotherBedDetailComponent,
        MotherBedUpdateComponent,
        MotherBedDeleteDialogComponent,
        MotherBedDeletePopupComponent
    ],
    entryComponents: [MotherBedComponent, MotherBedUpdateComponent, MotherBedDeleteDialogComponent, MotherBedDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ProjectghMotherBedModule {}
