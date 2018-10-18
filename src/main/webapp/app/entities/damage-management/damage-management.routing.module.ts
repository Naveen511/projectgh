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
    DamagemgntComponent
} from 'app/entities/damage-management/damagemgnt.component';
// import { JhiResolvePagingParams } from 'ng-jhipster';

// Constant value to set sidebar link for damage to list data of seed, seedling, saplings,
// cover and seed inventory damage details.
const routes: Routes = [
    {
        path: '',
        component: DamagemgntComponent,
        // resolve: {
        //     pagingParams: JhiResolvePagingParams
        // },
        data: {
            // defaultSort: 'id,asc',
            pageTitle: 'Damage Reporting'
        }
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class DamagemgntRoutingModule {}
