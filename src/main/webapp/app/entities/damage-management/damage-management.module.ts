/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/09/02
 *  Target: yarn
 *******************************************************************************/

// Import angular dependency, model, module, component, routing
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { ProjectghSharedModule } from 'app/shared';
import { CollapseModule } from 'ngx-bootstrap/collapse';
import { TabsModule } from 'ngx-bootstrap/tabs';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import {
    DamagemgntRoutingModule
} from 'app/entities/damage-management/damage-management.routing.module';

import { DamageService } from 'app/entities/service/damage.service';
import { DamagemgntComponent } from 'app/entities/damage-management/damagemgnt.component';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        TabsModule.forRoot(),
        DamagemgntRoutingModule,
        FontAwesomeModule,
        NgbModule.forRoot(),
        CollapseModule.forRoot(),
        ProjectghSharedModule
    ],
    declarations: [DamagemgntComponent],
    providers: [DamageService],
    exports: [NgbModule],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DamagemgntModule {}
