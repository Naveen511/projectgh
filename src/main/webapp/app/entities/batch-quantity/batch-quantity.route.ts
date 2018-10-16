import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { BatchQuantity } from 'app/shared/model/batch-quantity.model';
import { BatchQuantityService } from './batch-quantity.service';
import { BatchQuantityComponent } from './batch-quantity.component';
import { BatchQuantityDetailComponent } from './batch-quantity-detail.component';
import { BatchQuantityUpdateComponent } from './batch-quantity-update.component';
import { BatchQuantityDeletePopupComponent } from './batch-quantity-delete-dialog.component';
import { IBatchQuantity } from 'app/shared/model/batch-quantity.model';

@Injectable({ providedIn: 'root' })
export class BatchQuantityResolve implements Resolve<IBatchQuantity> {
    constructor(private service: BatchQuantityService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((batchQuantity: HttpResponse<BatchQuantity>) => batchQuantity.body));
        }
        return of(new BatchQuantity());
    }
}

export const batchQuantityRoute: Routes = [
    {
        path: 'batch-quantity',
        component: BatchQuantityComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'projectghApp.batchQuantity.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'batch-quantity/:id/view',
        component: BatchQuantityDetailComponent,
        resolve: {
            batchQuantity: BatchQuantityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.batchQuantity.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'batch-quantity/new',
        component: BatchQuantityUpdateComponent,
        resolve: {
            batchQuantity: BatchQuantityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.batchQuantity.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'batch-quantity/:id/edit',
        component: BatchQuantityUpdateComponent,
        resolve: {
            batchQuantity: BatchQuantityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.batchQuantity.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const batchQuantityPopupRoute: Routes = [
    {
        path: 'batch-quantity/:id/delete',
        component: BatchQuantityDeletePopupComponent,
        resolve: {
            batchQuantity: BatchQuantityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.batchQuantity.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
