/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02 11:27:58
 *  Target: yarn
 *******************************************************************************/
// Import angular dependency
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';

// Import the component
import { GodownComponent } from 'app/entities/godown-management/godown.component';
import { GodownPurchaseComponent } from 'app/entities/godown-management/godown-purchase.component';
import { GodownStockComponent } from 'app/entities/godown-management/godown-stock.component';

// Constant value to set sidebar link for godown, purchase and godownStock details
// with default params to list data.
const routes: Routes = [
    {
        path: '',
        data: {
            title: 'Godown Management'
        },
        children: [
            {
                path: 'godownlist',
                component: GodownComponent,
                resolve: {
                    pagingParams: JhiResolvePagingParams
                },
                data: {
                    defaultSort: 'id,asc',
                    pageTitle: 'Godown Details'
                }
            },
            {
                path: 'purchase',
                component: GodownPurchaseComponent,
                resolve: {
                    pagingParams: JhiResolvePagingParams
                },
                data: {
                    defaultSort: 'id,asc',
                    pageTitle: 'Purchase Details'
                }
            },
            {
                path: 'stock',
                component: GodownStockComponent,
                resolve: {
                    pagingParams: JhiResolvePagingParams
                },
                data: {
                    defaultSort: 'id,asc',
                    pageTitle: 'Stock List'
                }
            }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class GodownRoutingModule {}
