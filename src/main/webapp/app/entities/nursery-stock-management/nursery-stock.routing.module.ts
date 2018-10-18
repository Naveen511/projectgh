/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/09/02
 *  Target: yarn
 *******************************************************************************/
// Import angular dependency and component
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {
    NurseryStockMgntComponent
} from 'app/entities/nursery-stock-management/nursery-stock.component';

// Constant value to set sidebar link for nursery stock to list data of saplings details
const routes: Routes = [
    {
        path: '',
        component: NurseryStockMgntComponent,
        data: {
            title: 'Nursery Stock'
        }
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class NurseryStockMgntRoutingModule {}
