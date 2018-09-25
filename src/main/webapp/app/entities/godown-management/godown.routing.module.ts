import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';

import { GodownComponent } from 'app/entities/godown-management/godown.component';
import { GodownPurchaseComponent } from 'app/entities/godown-management/godown-purchase.component';
import { GodownStockComponent } from 'app/entities/godown-management/godown-stock.component';

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
