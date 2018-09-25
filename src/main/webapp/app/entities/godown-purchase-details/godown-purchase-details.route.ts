import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { GodownPurchaseDetails } from 'app/shared/model/godown-purchase-details.model';
import { GodownPurchaseDetailsService } from './godown-purchase-details.service';
import { GodownPurchaseDetailsComponent } from './godown-purchase-details.component';
import { GodownPurchaseDetailsDetailComponent } from './godown-purchase-details-detail.component';
import { GodownPurchaseDetailsUpdateComponent } from './godown-purchase-details-update.component';
import { GodownPurchaseDetailsDeletePopupComponent } from './godown-purchase-details-delete-dialog.component';
import { IGodownPurchaseDetails } from 'app/shared/model/godown-purchase-details.model';

@Injectable({ providedIn: 'root' })
export class GodownPurchaseDetailsResolve implements Resolve<IGodownPurchaseDetails> {
    constructor(private service: GodownPurchaseDetailsService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(map((godownPurchaseDetails: HttpResponse<GodownPurchaseDetails>) => godownPurchaseDetails.body));
        }
        return of(new GodownPurchaseDetails());
    }
}

export const godownPurchaseDetailsRoute: Routes = [
    {
        path: 'godown-purchase-details',
        component: GodownPurchaseDetailsComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'projectghApp.godownPurchaseDetails.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'godown-purchase-details/:id/view',
        component: GodownPurchaseDetailsDetailComponent,
        resolve: {
            godownPurchaseDetails: GodownPurchaseDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.godownPurchaseDetails.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'godown-purchase-details/new',
        component: GodownPurchaseDetailsUpdateComponent,
        resolve: {
            godownPurchaseDetails: GodownPurchaseDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.godownPurchaseDetails.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'godown-purchase-details/:id/edit',
        component: GodownPurchaseDetailsUpdateComponent,
        resolve: {
            godownPurchaseDetails: GodownPurchaseDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.godownPurchaseDetails.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const godownPurchaseDetailsPopupRoute: Routes = [
    {
        path: 'godown-purchase-details/:id/delete',
        component: GodownPurchaseDetailsDeletePopupComponent,
        resolve: {
            godownPurchaseDetails: GodownPurchaseDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.godownPurchaseDetails.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
