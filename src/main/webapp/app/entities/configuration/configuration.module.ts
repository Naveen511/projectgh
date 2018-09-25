/******************************************************************************
*  Property of Nichehands
*  Nichehands Confidential Proprietary
*  Nichehands Copyright (C) 2018 All rights reserved
*  ----------------------------------------------------------------------------
*  Date: 2018/08/02 11:27:58
*  Target: yarn
*******************************************************************************/
// Angular
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

// Components Routing
import { ConfigurationRoutingModule } from 'app/entities/configuration/configuration.routing.module';
import { OperationalHeadComponent } from 'app/entities/configuration/operational-head.component';
import { ZonalComponent } from 'app/entities/configuration/zonal.component';
import { SectorComponent } from 'app/entities/configuration/sector.component';
import { NurseryComponent } from 'app/entities/configuration/nursery.component';
import { PickListComponent } from 'app/entities/configuration/pick-list.component';

import { ModalModule } from 'ngx-bootstrap/modal';
import { TabsModule } from 'ngx-bootstrap/tabs';
// import { AlertModule } from 'ngx-bootstrap';
import { FinancialYearSettingsComponent } from 'app/entities/configuration/financial-year-settings.component';
// Collapse Component
import { CollapseModule } from 'ngx-bootstrap/collapse';
// Display the tooltip, Added the NgbModel from bootsrap
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ProjectghSharedModule } from 'app/shared';

// For Date picker
import { NgbDateAdapter } from '@ng-bootstrap/ng-bootstrap';
import { NgbDateMomentAdapter } from '../../shared/util/datepicker-adapter';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        ModalModule.forRoot(),
        // AlertModule.forRoot(),
        TabsModule.forRoot(),
        ConfigurationRoutingModule,
        FontAwesomeModule,
        NgbModule.forRoot(),
        CollapseModule.forRoot(),
        ProjectghSharedModule
    ],
    declarations: [
        OperationalHeadComponent,
        ZonalComponent,
        SectorComponent,
        NurseryComponent,
        PickListComponent,
        FinancialYearSettingsComponent
    ],
    providers: [
        { provide: NgbDateAdapter, useClass: NgbDateMomentAdapter }
    ],
    exports: [NgbModule],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ConfigurationModule {}
