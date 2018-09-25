import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProjectghSharedModule } from 'app/shared';
import {
    GodownComponent,
    GodownDetailComponent,
    GodownUpdateComponent,
    GodownDeletePopupComponent,
    GodownDeleteDialogComponent,
    godownRoute,
    godownPopupRoute
} from './';

const ENTITY_STATES = [...godownRoute, ...godownPopupRoute];

@NgModule({
    imports: [ProjectghSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [GodownComponent, GodownDetailComponent, GodownUpdateComponent, GodownDeleteDialogComponent, GodownDeletePopupComponent],
    entryComponents: [GodownComponent, GodownUpdateComponent, GodownDeleteDialogComponent, GodownDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ProjectghGodownModule {}
