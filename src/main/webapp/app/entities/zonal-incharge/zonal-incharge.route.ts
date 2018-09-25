import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ZonalIncharge } from 'app/shared/model/zonal-incharge.model';
import { ZonalInchargeService } from './zonal-incharge.service';
import { ZonalInchargeComponent } from './zonal-incharge.component';
import { ZonalInchargeDetailComponent } from './zonal-incharge-detail.component';
import { ZonalInchargeUpdateComponent } from './zonal-incharge-update.component';
import { ZonalInchargeDeletePopupComponent } from './zonal-incharge-delete-dialog.component';
import { IZonalIncharge } from 'app/shared/model/zonal-incharge.model';

@Injectable({ providedIn: 'root' })
export class ZonalInchargeResolve implements Resolve<IZonalIncharge> {
    constructor(private service: ZonalInchargeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((zonalIncharge: HttpResponse<ZonalIncharge>) => zonalIncharge.body));
        }
        return of(new ZonalIncharge());
    }
}

export const zonalInchargeRoute: Routes = [
    {
        path: 'zonal-incharge',
        component: ZonalInchargeComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'projectghApp.zonalIncharge.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'zonal-incharge/:id/view',
        component: ZonalInchargeDetailComponent,
        resolve: {
            zonalIncharge: ZonalInchargeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.zonalIncharge.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'zonal-incharge/new',
        component: ZonalInchargeUpdateComponent,
        resolve: {
            zonalIncharge: ZonalInchargeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.zonalIncharge.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'zonal-incharge/:id/edit',
        component: ZonalInchargeUpdateComponent,
        resolve: {
            zonalIncharge: ZonalInchargeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.zonalIncharge.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const zonalInchargePopupRoute: Routes = [
    {
        path: 'zonal-incharge/:id/delete',
        component: ZonalInchargeDeletePopupComponent,
        resolve: {
            zonalIncharge: ZonalInchargeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.zonalIncharge.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
