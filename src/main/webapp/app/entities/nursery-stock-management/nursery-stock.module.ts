/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/09/02
 *  Target: yarn
 *******************************************************************************/

// Import angular dependency, component, module, routing
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { CollapseModule } from 'ngx-bootstrap/collapse';
import { ModalModule } from 'ngx-bootstrap/modal';
import { TabsModule } from 'ngx-bootstrap/tabs';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgbDateAdapter } from '@ng-bootstrap/ng-bootstrap';
import { NgbDateMomentAdapter } from '../../shared/util/datepicker-adapter';
import {
    NurseryStockMgntRoutingModule
} from 'app/entities/nursery-stock-management/nursery-stock.routing.module';
import {
    NurseryStockMgntComponent
} from 'app/entities/nursery-stock-management/nursery-stock.component';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        ModalModule.forRoot(),
        TabsModule.forRoot(),
        FontAwesomeModule,
        NgbModule.forRoot(),
        CollapseModule.forRoot(),
        NurseryStockMgntRoutingModule
    ],
    declarations: [NurseryStockMgntComponent],
    providers: [{ provide: NgbDateAdapter, useClass: NgbDateMomentAdapter }],
    exports: [NgbModule]
})

export class NurseryStockMgntModule {}
