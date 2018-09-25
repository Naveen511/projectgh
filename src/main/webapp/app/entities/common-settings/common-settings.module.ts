import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProjectghSharedModule } from 'app/shared';
import {
    CommonSettingsComponent,
    CommonSettingsDetailComponent,
    CommonSettingsUpdateComponent,
    CommonSettingsDeletePopupComponent,
    CommonSettingsDeleteDialogComponent,
    commonSettingsRoute,
    commonSettingsPopupRoute
} from './';

const ENTITY_STATES = [...commonSettingsRoute, ...commonSettingsPopupRoute];

@NgModule({
    imports: [ProjectghSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CommonSettingsComponent,
        CommonSettingsDetailComponent,
        CommonSettingsUpdateComponent,
        CommonSettingsDeleteDialogComponent,
        CommonSettingsDeletePopupComponent
    ],
    entryComponents: [
        CommonSettingsComponent,
        CommonSettingsUpdateComponent,
        CommonSettingsDeleteDialogComponent,
        CommonSettingsDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ProjectghCommonSettingsModule {}
