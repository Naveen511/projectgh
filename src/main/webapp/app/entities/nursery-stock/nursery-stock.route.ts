import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { NurseryStock } from 'app/shared/model/nursery-stock.model';
import { NurseryStockService } from './nursery-stock.service';
import { NurseryStockComponent } from './nursery-stock.component';
import { NurseryStockDetailComponent } from './nursery-stock-detail.component';
import { NurseryStockUpdateComponent } from './nursery-stock-update.component';
import { NurseryStockDeletePopupComponent } from './nursery-stock-delete-dialog.component';
import { INurseryStock } from 'app/shared/model/nursery-stock.model';

@Injectable({ providedIn: 'root' })
export class NurseryStockResolve implements Resolve<INurseryStock> {
    constructor(private service: NurseryStockService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((nurseryStock: HttpResponse<NurseryStock>) => nurseryStock.body));
        }
        return of(new NurseryStock());
    }
}

export const nurseryStockRoute: Routes = [
    {
        path: 'nursery-stock',
        component: NurseryStockComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'projectghApp.nurseryStock.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'nursery-stock/:id/view',
        component: NurseryStockDetailComponent,
        resolve: {
            nurseryStock: NurseryStockResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.nurseryStock.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'nursery-stock/new',
        component: NurseryStockUpdateComponent,
        resolve: {
            nurseryStock: NurseryStockResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.nurseryStock.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'nursery-stock/:id/edit',
        component: NurseryStockUpdateComponent,
        resolve: {
            nurseryStock: NurseryStockResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.nurseryStock.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const nurseryStockPopupRoute: Routes = [
    {
        path: 'nursery-stock/:id/delete',
        component: NurseryStockDeletePopupComponent,
        resolve: {
            nurseryStock: NurseryStockResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.nurseryStock.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
