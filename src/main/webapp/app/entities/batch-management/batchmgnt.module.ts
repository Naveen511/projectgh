/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 *******************************************************************************/

// Import Angular, Collapse Component, Popover, Module and datepicker
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { CollapseModule } from 'ngx-bootstrap/collapse';
import { PopoverModule } from 'ngx-bootstrap/popover';
import { ModalModule } from 'ngx-bootstrap/modal';
import { TabsModule } from 'ngx-bootstrap/tabs';
import { NgbDateMomentAdapter } from '../../shared/util/datepicker-adapter';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ProjectghSharedModule } from 'app/shared';

import {
    BatchmgntRoutingModule
} from 'app/entities/batch-management/batchmgnt.routing.module';
import { BatchmgntComponent } from 'app/entities/batch-management/batchmgnt.component';

// import { ZonalService } from 'app/entities/service/zonal.service';
// import { SectorService } from 'app/entities/service/sector.service';
import { NurseryService } from 'app/entities/service/nursery.service';
import { BatchService } from 'app/entities/service/batch.service';
import { DamageService } from 'app/entities/service/damage.service';
import { PickListService } from 'app/entities/service/pick-list.service';
import { PickListValueService } from 'app/entities/service/pick-list-value.service';
import { ShadeAreaService } from 'app/entities/service/shade-area.service';
import { NgbDateAdapter } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        ModalModule.forRoot(),
        TabsModule.forRoot(),
        CollapseModule.forRoot(),
        PopoverModule.forRoot(),
        BatchmgntRoutingModule,
        FontAwesomeModule,
        NgbModule.forRoot(),
        ProjectghSharedModule
    ],
    declarations: [BatchmgntComponent],
    providers: [
        // ZonalService,
        // SectorService,
        NurseryService,
        BatchService,
        DamageService,
        PickListService,
        PickListValueService,
        ShadeAreaService,
        { provide: NgbDateAdapter, useClass: NgbDateMomentAdapter }
    ],
    exports: [NgbModule],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BatchmgntModule {}
