import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { PickList } from 'app/shared/model/pick-list.model';
import { PickListService } from './pick-list.service';
import { PickListComponent } from './pick-list.component';
import { PickListDetailComponent } from './pick-list-detail.component';
import { PickListUpdateComponent } from './pick-list-update.component';
import { PickListDeletePopupComponent } from './pick-list-delete-dialog.component';
import { IPickList } from 'app/shared/model/pick-list.model';

@Injectable({ providedIn: 'root' })
export class PickListResolve implements Resolve<IPickList> {
    constructor(private service: PickListService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((pickList: HttpResponse<PickList>) => pickList.body));
        }
        return of(new PickList());
    }
}

export const pickListRoute: Routes = [
    {
        path: 'pick-list',
        component: PickListComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'projectghApp.pickList.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'pick-list/:id/view',
        component: PickListDetailComponent,
        resolve: {
            pickList: PickListResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.pickList.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'pick-list/new',
        component: PickListUpdateComponent,
        resolve: {
            pickList: PickListResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.pickList.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'pick-list/:id/edit',
        component: PickListUpdateComponent,
        resolve: {
            pickList: PickListResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.pickList.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const pickListPopupRoute: Routes = [
    {
        path: 'pick-list/:id/delete',
        component: PickListDeletePopupComponent,
        resolve: {
            pickList: PickListResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.pickList.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
