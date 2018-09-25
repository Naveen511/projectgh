import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProjectghSharedModule } from 'app/shared';
import {
    OperationalHeadComponent,
    OperationalHeadDetailComponent,
    OperationalHeadUpdateComponent,
    OperationalHeadDeletePopupComponent,
    OperationalHeadDeleteDialogComponent,
    operationalHeadRoute,
    operationalHeadPopupRoute
} from './';

const ENTITY_STATES = [...operationalHeadRoute, ...operationalHeadPopupRoute];

@NgModule({
    imports: [ProjectghSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        OperationalHeadComponent,
        OperationalHeadDetailComponent,
        OperationalHeadUpdateComponent,
        OperationalHeadDeleteDialogComponent,
        OperationalHeadDeletePopupComponent
    ],
    entryComponents: [
        OperationalHeadComponent,
        OperationalHeadUpdateComponent,
        OperationalHeadDeleteDialogComponent,
        OperationalHeadDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ProjectghOperationalHeadModule {}
