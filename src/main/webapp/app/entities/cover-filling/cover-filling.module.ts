import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProjectghSharedModule } from 'app/shared';
import {
    CoverFillingComponent,
    CoverFillingDetailComponent,
    CoverFillingUpdateComponent,
    CoverFillingDeletePopupComponent,
    CoverFillingDeleteDialogComponent,
    coverFillingRoute,
    coverFillingPopupRoute
} from './';

const ENTITY_STATES = [...coverFillingRoute, ...coverFillingPopupRoute];

@NgModule({
    imports: [ProjectghSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CoverFillingComponent,
        CoverFillingDetailComponent,
        CoverFillingUpdateComponent,
        CoverFillingDeleteDialogComponent,
        CoverFillingDeletePopupComponent
    ],
    entryComponents: [
        CoverFillingComponent,
        CoverFillingUpdateComponent,
        CoverFillingDeleteDialogComponent,
        CoverFillingDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ProjectghCoverFillingModule {}
