import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { CommonSettings } from 'app/shared/model/common-settings.model';
import { CommonSettingsService } from './common-settings.service';
import { CommonSettingsComponent } from './common-settings.component';
import { CommonSettingsDetailComponent } from './common-settings-detail.component';
import { CommonSettingsUpdateComponent } from './common-settings-update.component';
import { CommonSettingsDeletePopupComponent } from './common-settings-delete-dialog.component';
import { ICommonSettings } from 'app/shared/model/common-settings.model';

@Injectable({ providedIn: 'root' })
export class CommonSettingsResolve implements Resolve<ICommonSettings> {
    constructor(private service: CommonSettingsService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((commonSettings: HttpResponse<CommonSettings>) => commonSettings.body));
        }
        return of(new CommonSettings());
    }
}

export const commonSettingsRoute: Routes = [
    {
        path: 'common-settings',
        component: CommonSettingsComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'projectghApp.commonSettings.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'common-settings/:id/view',
        component: CommonSettingsDetailComponent,
        resolve: {
            commonSettings: CommonSettingsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.commonSettings.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'common-settings/new',
        component: CommonSettingsUpdateComponent,
        resolve: {
            commonSettings: CommonSettingsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.commonSettings.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'common-settings/:id/edit',
        component: CommonSettingsUpdateComponent,
        resolve: {
            commonSettings: CommonSettingsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.commonSettings.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const commonSettingsPopupRoute: Routes = [
    {
        path: 'common-settings/:id/delete',
        component: CommonSettingsDeletePopupComponent,
        resolve: {
            commonSettings: CommonSettingsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.commonSettings.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
