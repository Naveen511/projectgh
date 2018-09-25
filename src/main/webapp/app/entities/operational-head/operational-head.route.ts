import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { OperationalHead } from 'app/shared/model/operational-head.model';
import { OperationalHeadService } from './operational-head.service';
import { OperationalHeadComponent } from './operational-head.component';
import { OperationalHeadDetailComponent } from './operational-head-detail.component';
import { OperationalHeadUpdateComponent } from './operational-head-update.component';
import { OperationalHeadDeletePopupComponent } from './operational-head-delete-dialog.component';
import { IOperationalHead } from 'app/shared/model/operational-head.model';

@Injectable({ providedIn: 'root' })
export class OperationalHeadResolve implements Resolve<IOperationalHead> {
    constructor(private service: OperationalHeadService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((operationalHead: HttpResponse<OperationalHead>) => operationalHead.body));
        }
        return of(new OperationalHead());
    }
}

export const operationalHeadRoute: Routes = [
    {
        path: 'operational-head',
        component: OperationalHeadComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'projectghApp.operationalHead.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'operational-head/:id/view',
        component: OperationalHeadDetailComponent,
        resolve: {
            operationalHead: OperationalHeadResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.operationalHead.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'operational-head/new',
        component: OperationalHeadUpdateComponent,
        resolve: {
            operationalHead: OperationalHeadResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.operationalHead.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'operational-head/:id/edit',
        component: OperationalHeadUpdateComponent,
        resolve: {
            operationalHead: OperationalHeadResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.operationalHead.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const operationalHeadPopupRoute: Routes = [
    {
        path: 'operational-head/:id/delete',
        component: OperationalHeadDeletePopupComponent,
        resolve: {
            operationalHead: OperationalHeadResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.operationalHead.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
