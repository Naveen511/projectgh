import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProjectghSharedModule } from 'app/shared';
import {
    CoverFillingDetailsComponent,
    CoverFillingDetailsDetailComponent,
    CoverFillingDetailsUpdateComponent,
    CoverFillingDetailsDeletePopupComponent,
    CoverFillingDetailsDeleteDialogComponent,
    coverFillingDetailsRoute,
    coverFillingDetailsPopupRoute
} from './';

const ENTITY_STATES = [...coverFillingDetailsRoute, ...coverFillingDetailsPopupRoute];

@NgModule({
    imports: [ProjectghSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CoverFillingDetailsComponent,
        CoverFillingDetailsDetailComponent,
        CoverFillingDetailsUpdateComponent,
        CoverFillingDetailsDeleteDialogComponent,
        CoverFillingDetailsDeletePopupComponent
    ],
    entryComponents: [
        CoverFillingDetailsComponent,
        CoverFillingDetailsUpdateComponent,
        CoverFillingDetailsDeleteDialogComponent,
        CoverFillingDetailsDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ProjectghCoverFillingDetailsModule {}
