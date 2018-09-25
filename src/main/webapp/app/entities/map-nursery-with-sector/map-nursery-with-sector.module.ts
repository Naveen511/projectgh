import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProjectghSharedModule } from 'app/shared';
import {
    MapNurseryWithSectorComponent,
    MapNurseryWithSectorDetailComponent,
    MapNurseryWithSectorUpdateComponent,
    MapNurseryWithSectorDeletePopupComponent,
    MapNurseryWithSectorDeleteDialogComponent,
    mapNurseryWithSectorRoute,
    mapNurseryWithSectorPopupRoute
} from './';

const ENTITY_STATES = [...mapNurseryWithSectorRoute, ...mapNurseryWithSectorPopupRoute];

@NgModule({
    imports: [ProjectghSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MapNurseryWithSectorComponent,
        MapNurseryWithSectorDetailComponent,
        MapNurseryWithSectorUpdateComponent,
        MapNurseryWithSectorDeleteDialogComponent,
        MapNurseryWithSectorDeletePopupComponent
    ],
    entryComponents: [
        MapNurseryWithSectorComponent,
        MapNurseryWithSectorUpdateComponent,
        MapNurseryWithSectorDeleteDialogComponent,
        MapNurseryWithSectorDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ProjectghMapNurseryWithSectorModule {}
