import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ShadeArea } from 'app/shared/model/shade-area.model';
import { ShadeAreaService } from './shade-area.service';
import { ShadeAreaComponent } from './shade-area.component';
import { ShadeAreaDetailComponent } from './shade-area-detail.component';
import { ShadeAreaUpdateComponent } from './shade-area-update.component';
import { ShadeAreaDeletePopupComponent } from './shade-area-delete-dialog.component';
import { IShadeArea } from 'app/shared/model/shade-area.model';

@Injectable({ providedIn: 'root' })
export class ShadeAreaResolve implements Resolve<IShadeArea> {
    constructor(private service: ShadeAreaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((shadeArea: HttpResponse<ShadeArea>) => shadeArea.body));
        }
        return of(new ShadeArea());
    }
}

export const shadeAreaRoute: Routes = [
    {
        path: 'shade-area',
        component: ShadeAreaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'projectghApp.shadeArea.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'shade-area/:id/view',
        component: ShadeAreaDetailComponent,
        resolve: {
            shadeArea: ShadeAreaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.shadeArea.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'shade-area/new',
        component: ShadeAreaUpdateComponent,
        resolve: {
            shadeArea: ShadeAreaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.shadeArea.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'shade-area/:id/edit',
        component: ShadeAreaUpdateComponent,
        resolve: {
            shadeArea: ShadeAreaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.shadeArea.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const shadeAreaPopupRoute: Routes = [
    {
        path: 'shade-area/:id/delete',
        component: ShadeAreaDeletePopupComponent,
        resolve: {
            shadeArea: ShadeAreaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.shadeArea.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
