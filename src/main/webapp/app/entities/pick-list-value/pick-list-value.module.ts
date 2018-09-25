import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProjectghSharedModule } from 'app/shared';
import {
    PickListValueComponent,
    PickListValueDetailComponent,
    PickListValueUpdateComponent,
    PickListValueDeletePopupComponent,
    PickListValueDeleteDialogComponent,
    pickListValueRoute,
    pickListValuePopupRoute
} from './';

const ENTITY_STATES = [...pickListValueRoute, ...pickListValuePopupRoute];

@NgModule({
    imports: [ProjectghSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PickListValueComponent,
        PickListValueDetailComponent,
        PickListValueUpdateComponent,
        PickListValueDeleteDialogComponent,
        PickListValueDeletePopupComponent
    ],
    entryComponents: [
        PickListValueComponent,
        PickListValueUpdateComponent,
        PickListValueDeleteDialogComponent,
        PickListValueDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ProjectghPickListValueModule {}
