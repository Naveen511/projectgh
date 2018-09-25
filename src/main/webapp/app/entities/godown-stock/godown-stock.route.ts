import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { GodownStock } from 'app/shared/model/godown-stock.model';
import { GodownStockService } from './godown-stock.service';
import { GodownStockComponent } from './godown-stock.component';
import { GodownStockDetailComponent } from './godown-stock-detail.component';
import { GodownStockUpdateComponent } from './godown-stock-update.component';
import { GodownStockDeletePopupComponent } from './godown-stock-delete-dialog.component';
import { IGodownStock } from 'app/shared/model/godown-stock.model';

@Injectable({ providedIn: 'root' })
export class GodownStockResolve implements Resolve<IGodownStock> {
    constructor(private service: GodownStockService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((godownStock: HttpResponse<GodownStock>) => godownStock.body));
        }
        return of(new GodownStock());
    }
}

export const godownStockRoute: Routes = [
    {
        path: 'godown-stock',
        component: GodownStockComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'projectghApp.godownStock.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'godown-stock/:id/view',
        component: GodownStockDetailComponent,
        resolve: {
            godownStock: GodownStockResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.godownStock.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'godown-stock/new',
        component: GodownStockUpdateComponent,
        resolve: {
            godownStock: GodownStockResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.godownStock.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'godown-stock/:id/edit',
        component: GodownStockUpdateComponent,
        resolve: {
            godownStock: GodownStockResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.godownStock.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const godownStockPopupRoute: Routes = [
    {
        path: 'godown-stock/:id/delete',
        component: GodownStockDeletePopupComponent,
        resolve: {
            godownStock: GodownStockResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.godownStock.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
