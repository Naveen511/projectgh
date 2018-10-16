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
import { JhiResolvePagingParams } from 'ng-jhipster';
import { NurseryInventoryMgntComponent } from 'app/entities/nursery-inventory-management/nursery-inventory.component';

// Constant value to set sidebar link for nursery inventory details
// with default params to list data.
const routes: Routes = [
    {
        path: '',
        component: NurseryInventoryMgntComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            defaultSort: 'id,asc',
            pageTitle: 'Seeds & Cover Management'
        }
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class NurseryInventoryMgntRoutingModule {}
