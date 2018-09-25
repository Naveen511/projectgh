import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { NurseryIncharge } from 'app/shared/model/nursery-incharge.model';
import { NurseryInchargeService } from './nursery-incharge.service';
import { NurseryInchargeComponent } from './nursery-incharge.component';
import { NurseryInchargeDetailComponent } from './nursery-incharge-detail.component';
import { NurseryInchargeUpdateComponent } from './nursery-incharge-update.component';
import { NurseryInchargeDeletePopupComponent } from './nursery-incharge-delete-dialog.component';
import { INurseryIncharge } from 'app/shared/model/nursery-incharge.model';

@Injectable({ providedIn: 'root' })
export class NurseryInchargeResolve implements Resolve<INurseryIncharge> {
    constructor(private service: NurseryInchargeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((nurseryIncharge: HttpResponse<NurseryIncharge>) => nurseryIncharge.body));
        }
        return of(new NurseryIncharge());
    }
}

export const nurseryInchargeRoute: Routes = [
    {
        path: 'nursery-incharge',
        component: NurseryInchargeComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'projectghApp.nurseryIncharge.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'nursery-incharge/:id/view',
        component: NurseryInchargeDetailComponent,
        resolve: {
            nurseryIncharge: NurseryInchargeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.nurseryIncharge.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'nursery-incharge/new',
        component: NurseryInchargeUpdateComponent,
        resolve: {
            nurseryIncharge: NurseryInchargeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.nurseryIncharge.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'nursery-incharge/:id/edit',
        component: NurseryInchargeUpdateComponent,
        resolve: {
            nurseryIncharge: NurseryInchargeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.nurseryIncharge.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const nurseryInchargePopupRoute: Routes = [
    {
        path: 'nursery-incharge/:id/delete',
        component: NurseryInchargeDeletePopupComponent,
        resolve: {
            nurseryIncharge: NurseryInchargeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.nurseryIncharge.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
