import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Nursery } from 'app/shared/model/nursery.model';
import { NurseryService } from './nursery.service';
import { NurseryComponent } from './nursery.component';
import { NurseryDetailComponent } from './nursery-detail.component';
import { NurseryUpdateComponent } from './nursery-update.component';
import { NurseryDeletePopupComponent } from './nursery-delete-dialog.component';
import { INursery } from 'app/shared/model/nursery.model';

@Injectable({ providedIn: 'root' })
export class NurseryResolve implements Resolve<INursery> {
    constructor(private service: NurseryService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((nursery: HttpResponse<Nursery>) => nursery.body));
        }
        return of(new Nursery());
    }
}

export const nurseryRoute: Routes = [
    {
        path: 'nursery',
        component: NurseryComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'projectghApp.nursery.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'nursery/:id/view',
        component: NurseryDetailComponent,
        resolve: {
            nursery: NurseryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.nursery.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'nursery/new',
        component: NurseryUpdateComponent,
        resolve: {
            nursery: NurseryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.nursery.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'nursery/:id/edit',
        component: NurseryUpdateComponent,
        resolve: {
            nursery: NurseryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.nursery.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const nurseryPopupRoute: Routes = [
    {
        path: 'nursery/:id/delete',
        component: NurseryDeletePopupComponent,
        resolve: {
            nursery: NurseryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.nursery.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
