import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { NurseryStockDetails } from 'app/shared/model/nursery-stock-details.model';
import { NurseryStockDetailsService } from './nursery-stock-details.service';
import { NurseryStockDetailsComponent } from './nursery-stock-details.component';
import { NurseryStockDetailsDetailComponent } from './nursery-stock-details-detail.component';
import { NurseryStockDetailsUpdateComponent } from './nursery-stock-details-update.component';
import { NurseryStockDetailsDeletePopupComponent } from './nursery-stock-details-delete-dialog.component';
import { INurseryStockDetails } from 'app/shared/model/nursery-stock-details.model';

@Injectable({ providedIn: 'root' })
export class NurseryStockDetailsResolve implements Resolve<INurseryStockDetails> {
    constructor(private service: NurseryStockDetailsService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((nurseryStockDetails: HttpResponse<NurseryStockDetails>) => nurseryStockDetails.body));
        }
        return of(new NurseryStockDetails());
    }
}

export const nurseryStockDetailsRoute: Routes = [
    {
        path: 'nursery-stock-details',
        component: NurseryStockDetailsComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'projectghApp.nurseryStockDetails.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'nursery-stock-details/:id/view',
        component: NurseryStockDetailsDetailComponent,
        resolve: {
            nurseryStockDetails: NurseryStockDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.nurseryStockDetails.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'nursery-stock-details/new',
        component: NurseryStockDetailsUpdateComponent,
        resolve: {
            nurseryStockDetails: NurseryStockDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.nurseryStockDetails.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'nursery-stock-details/:id/edit',
        component: NurseryStockDetailsUpdateComponent,
        resolve: {
            nurseryStockDetails: NurseryStockDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.nurseryStockDetails.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const nurseryStockDetailsPopupRoute: Routes = [
    {
        path: 'nursery-stock-details/:id/delete',
        component: NurseryStockDetailsDeletePopupComponent,
        resolve: {
            nurseryStockDetails: NurseryStockDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.nurseryStockDetails.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
