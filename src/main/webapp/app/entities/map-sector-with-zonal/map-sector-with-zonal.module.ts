import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProjectghSharedModule } from 'app/shared';
import {
    MapSectorWithZonalComponent,
    MapSectorWithZonalDetailComponent,
    MapSectorWithZonalUpdateComponent,
    MapSectorWithZonalDeletePopupComponent,
    MapSectorWithZonalDeleteDialogComponent,
    mapSectorWithZonalRoute,
    mapSectorWithZonalPopupRoute
} from './';

const ENTITY_STATES = [...mapSectorWithZonalRoute, ...mapSectorWithZonalPopupRoute];

@NgModule({
    imports: [ProjectghSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MapSectorWithZonalComponent,
        MapSectorWithZonalDetailComponent,
        MapSectorWithZonalUpdateComponent,
        MapSectorWithZonalDeleteDialogComponent,
        MapSectorWithZonalDeletePopupComponent
    ],
    entryComponents: [
        MapSectorWithZonalComponent,
        MapSectorWithZonalUpdateComponent,
        MapSectorWithZonalDeleteDialogComponent,
        MapSectorWithZonalDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ProjectghMapSectorWithZonalModule {}
