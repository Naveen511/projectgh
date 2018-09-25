import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { PickListValue } from 'app/shared/model/pick-list-value.model';
import { PickListValueService } from './pick-list-value.service';
import { PickListValueComponent } from './pick-list-value.component';
import { PickListValueDetailComponent } from './pick-list-value-detail.component';
import { PickListValueUpdateComponent } from './pick-list-value-update.component';
import { PickListValueDeletePopupComponent } from './pick-list-value-delete-dialog.component';
import { IPickListValue } from 'app/shared/model/pick-list-value.model';

@Injectable({ providedIn: 'root' })
export class PickListValueResolve implements Resolve<IPickListValue> {
    constructor(private service: PickListValueService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((pickListValue: HttpResponse<PickListValue>) => pickListValue.body));
        }
        return of(new PickListValue());
    }
}

export const pickListValueRoute: Routes = [
    {
        path: 'pick-list-value',
        component: PickListValueComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'projectghApp.pickListValue.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'pick-list-value/:id/view',
        component: PickListValueDetailComponent,
        resolve: {
            pickListValue: PickListValueResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.pickListValue.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'pick-list-value/new',
        component: PickListValueUpdateComponent,
        resolve: {
            pickListValue: PickListValueResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.pickListValue.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'pick-list-value/:id/edit',
        component: PickListValueUpdateComponent,
        resolve: {
            pickListValue: PickListValueResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.pickListValue.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const pickListValuePopupRoute: Routes = [
    {
        path: 'pick-list-value/:id/delete',
        component: PickListValueDeletePopupComponent,
        resolve: {
            pickListValue: PickListValueResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.pickListValue.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
