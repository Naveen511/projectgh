import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProjectghSharedModule } from 'app/shared';
import {
    MapZonalWithOhComponent,
    MapZonalWithOhDetailComponent,
    MapZonalWithOhUpdateComponent,
    MapZonalWithOhDeletePopupComponent,
    MapZonalWithOhDeleteDialogComponent,
    mapZonalWithOhRoute,
    mapZonalWithOhPopupRoute
} from './';

const ENTITY_STATES = [...mapZonalWithOhRoute, ...mapZonalWithOhPopupRoute];

@NgModule({
    imports: [ProjectghSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MapZonalWithOhComponent,
        MapZonalWithOhDetailComponent,
        MapZonalWithOhUpdateComponent,
        MapZonalWithOhDeleteDialogComponent,
        MapZonalWithOhDeletePopupComponent
    ],
    entryComponents: [
        MapZonalWithOhComponent,
        MapZonalWithOhUpdateComponent,
        MapZonalWithOhDeleteDialogComponent,
        MapZonalWithOhDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ProjectghMapZonalWithOhModule {}
