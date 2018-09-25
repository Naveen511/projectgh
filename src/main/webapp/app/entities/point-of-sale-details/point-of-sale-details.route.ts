import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { PointOfSaleDetails } from 'app/shared/model/point-of-sale-details.model';
import { PointOfSaleDetailsService } from './point-of-sale-details.service';
import { PointOfSaleDetailsComponent } from './point-of-sale-details.component';
import { PointOfSaleDetailsDetailComponent } from './point-of-sale-details-detail.component';
import { PointOfSaleDetailsUpdateComponent } from './point-of-sale-details-update.component';
import { PointOfSaleDetailsDeletePopupComponent } from './point-of-sale-details-delete-dialog.component';
import { IPointOfSaleDetails } from 'app/shared/model/point-of-sale-details.model';

@Injectable({ providedIn: 'root' })
export class PointOfSaleDetailsResolve implements Resolve<IPointOfSaleDetails> {
    constructor(private service: PointOfSaleDetailsService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((pointOfSaleDetails: HttpResponse<PointOfSaleDetails>) => pointOfSaleDetails.body));
        }
        return of(new PointOfSaleDetails());
    }
}

export const pointOfSaleDetailsRoute: Routes = [
    {
        path: 'point-of-sale-details',
        component: PointOfSaleDetailsComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'projectghApp.pointOfSaleDetails.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'point-of-sale-details/:id/view',
        component: PointOfSaleDetailsDetailComponent,
        resolve: {
            pointOfSaleDetails: PointOfSaleDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.pointOfSaleDetails.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'point-of-sale-details/new',
        component: PointOfSaleDetailsUpdateComponent,
        resolve: {
            pointOfSaleDetails: PointOfSaleDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.pointOfSaleDetails.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'point-of-sale-details/:id/edit',
        component: PointOfSaleDetailsUpdateComponent,
        resolve: {
            pointOfSaleDetails: PointOfSaleDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.pointOfSaleDetails.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const pointOfSaleDetailsPopupRoute: Routes = [
    {
        path: 'point-of-sale-details/:id/delete',
        component: PointOfSaleDetailsDeletePopupComponent,
        resolve: {
            pointOfSaleDetails: PointOfSaleDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.pointOfSaleDetails.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
