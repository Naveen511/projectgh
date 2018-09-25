import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Godown } from 'app/shared/model/godown.model';
import { GodownService } from './godown.service';
import { GodownComponent } from './godown.component';
import { GodownDetailComponent } from './godown-detail.component';
import { GodownUpdateComponent } from './godown-update.component';
import { GodownDeletePopupComponent } from './godown-delete-dialog.component';
import { IGodown } from 'app/shared/model/godown.model';

@Injectable({ providedIn: 'root' })
export class GodownResolve implements Resolve<IGodown> {
    constructor(private service: GodownService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((godown: HttpResponse<Godown>) => godown.body));
        }
        return of(new Godown());
    }
}

export const godownRoute: Routes = [
    {
        path: 'godown',
        component: GodownComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'projectghApp.godown.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'godown/:id/view',
        component: GodownDetailComponent,
        resolve: {
            godown: GodownResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.godown.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'godown/new',
        component: GodownUpdateComponent,
        resolve: {
            godown: GodownResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.godown.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'godown/:id/edit',
        component: GodownUpdateComponent,
        resolve: {
            godown: GodownResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.godown.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const godownPopupRoute: Routes = [
    {
        path: 'godown/:id/delete',
        component: GodownDeletePopupComponent,
        resolve: {
            godown: GodownResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.godown.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
