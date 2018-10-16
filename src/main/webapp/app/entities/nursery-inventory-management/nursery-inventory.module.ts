/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/09/02
 *  Target: yarn
 *******************************************************************************/
// Angular
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
// Collapse Component
import { CollapseModule } from 'ngx-bootstrap/collapse';
// Module Component
import { ModalModule } from 'ngx-bootstrap/modal';
import { TabsModule } from 'ngx-bootstrap/tabs';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NurseryInventoryMgntRoutingModule } from 'app/entities/nursery-inventory-management/nursery-inventory.routing.module';
import { NurseryInventoryMgntComponent } from 'app/entities/nursery-inventory-management/nursery-inventory.component';

// Date picker from bootstrap
import { NgbDateAdapter } from '@ng-bootstrap/ng-bootstrap';
import { NgbDateMomentAdapter } from '../../shared/util/datepicker-adapter';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        ModalModule.forRoot(),
        TabsModule.forRoot(),
        FontAwesomeModule,
        NgbModule.forRoot(),
        CollapseModule.forRoot(),
        NurseryInventoryMgntRoutingModule
    ],
    declarations: [NurseryInventoryMgntComponent],
    providers: [{ provide: NgbDateAdapter, useClass: NgbDateMomentAdapter }],
    exports: [NgbModule]
})
export class NurseryInventoryMgntModule {}
