import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProjectghSharedModule } from 'app/shared';
import {
    DamageComponent,
    DamageDetailComponent,
    DamageUpdateComponent,
    DamageDeletePopupComponent,
    DamageDeleteDialogComponent,
    damageRoute,
    damagePopupRoute
} from './';

const ENTITY_STATES = [...damageRoute, ...damagePopupRoute];

@NgModule({
    imports: [ProjectghSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [DamageComponent, DamageDetailComponent, DamageUpdateComponent, DamageDeleteDialogComponent, DamageDeletePopupComponent],
    entryComponents: [DamageComponent, DamageUpdateComponent, DamageDeleteDialogComponent, DamageDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ProjectghDamageModule {}
