import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProjectghSharedModule } from 'app/shared';
import {
    SectorInchargeComponent,
    SectorInchargeDetailComponent,
    SectorInchargeUpdateComponent,
    SectorInchargeDeletePopupComponent,
    SectorInchargeDeleteDialogComponent,
    sectorInchargeRoute,
    sectorInchargePopupRoute
} from './';

const ENTITY_STATES = [...sectorInchargeRoute, ...sectorInchargePopupRoute];

@NgModule({
    imports: [ProjectghSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SectorInchargeComponent,
        SectorInchargeDetailComponent,
        SectorInchargeUpdateComponent,
        SectorInchargeDeleteDialogComponent,
        SectorInchargeDeletePopupComponent
    ],
    entryComponents: [
        SectorInchargeComponent,
        SectorInchargeUpdateComponent,
        SectorInchargeDeleteDialogComponent,
        SectorInchargeDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ProjectghSectorInchargeModule {}
