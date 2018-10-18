/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02 11:27:58
 *  Target: yarn
 *******************************************************************************/

// Import angular dependency and component
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { BatchmgntComponent } from 'app/entities/batch-management/batchmgnt.component';

// Constant value to set sidebar link for batch formation with default params to list data.
const routes: Routes = [
    {
        path: '',
        component: BatchmgntComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            defaultSort: 'id,asc',
            pageTitle: 'Batch Formation'
        }
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class BatchmgntRoutingModule {}
