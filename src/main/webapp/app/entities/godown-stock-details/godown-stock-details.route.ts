import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { GodownStockDetails } from 'app/shared/model/godown-stock-details.model';
import { GodownStockDetailsService } from './godown-stock-details.service';
import { GodownStockDetailsComponent } from './godown-stock-details.component';
import { GodownStockDetailsDetailComponent } from './godown-stock-details-detail.component';
import { GodownStockDetailsUpdateComponent } from './godown-stock-details-update.component';
import { GodownStockDetailsDeletePopupComponent } from './godown-stock-details-delete-dialog.component';
import { IGodownStockDetails } from 'app/shared/model/godown-stock-details.model';

@Injectable({ providedIn: 'root' })
export class GodownStockDetailsResolve implements Resolve<IGodownStockDetails> {
    constructor(private service: GodownStockDetailsService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((godownStockDetails: HttpResponse<GodownStockDetails>) => godownStockDetails.body));
        }
        return of(new GodownStockDetails());
    }
}

export const godownStockDetailsRoute: Routes = [
    {
        path: 'godown-stock-details',
        component: GodownStockDetailsComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'projectghApp.godownStockDetails.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'godown-stock-details/:id/view',
        component: GodownStockDetailsDetailComponent,
        resolve: {
            godownStockDetails: GodownStockDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.godownStockDetails.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'godown-stock-details/new',
        component: GodownStockDetailsUpdateComponent,
        resolve: {
            godownStockDetails: GodownStockDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.godownStockDetails.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'godown-stock-details/:id/edit',
        component: GodownStockDetailsUpdateComponent,
        resolve: {
            godownStockDetails: GodownStockDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.godownStockDetails.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const godownStockDetailsPopupRoute: Routes = [
    {
        path: 'godown-stock-details/:id/delete',
        component: GodownStockDetailsDeletePopupComponent,
        resolve: {
            godownStockDetails: GodownStockDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.godownStockDetails.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
