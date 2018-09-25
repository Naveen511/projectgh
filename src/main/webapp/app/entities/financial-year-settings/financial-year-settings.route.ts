import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { FinancialYearSettings } from 'app/shared/model/financial-year-settings.model';
import { FinancialYearSettingsService } from './financial-year-settings.service';
import { FinancialYearSettingsComponent } from './financial-year-settings.component';
import { FinancialYearSettingsDetailComponent } from './financial-year-settings-detail.component';
import { FinancialYearSettingsUpdateComponent } from './financial-year-settings-update.component';
import { FinancialYearSettingsDeletePopupComponent } from './financial-year-settings-delete-dialog.component';
import { IFinancialYearSettings } from 'app/shared/model/financial-year-settings.model';

@Injectable({ providedIn: 'root' })
export class FinancialYearSettingsResolve implements Resolve<IFinancialYearSettings> {
    constructor(private service: FinancialYearSettingsService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(map((financialYearSettings: HttpResponse<FinancialYearSettings>) => financialYearSettings.body));
        }
        return of(new FinancialYearSettings());
    }
}

export const financialYearSettingsRoute: Routes = [
    {
        path: 'financial-year-settings',
        component: FinancialYearSettingsComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'projectghApp.financialYearSettings.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'financial-year-settings/:id/view',
        component: FinancialYearSettingsDetailComponent,
        resolve: {
            financialYearSettings: FinancialYearSettingsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.financialYearSettings.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'financial-year-settings/new',
        component: FinancialYearSettingsUpdateComponent,
        resolve: {
            financialYearSettings: FinancialYearSettingsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.financialYearSettings.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'financial-year-settings/:id/edit',
        component: FinancialYearSettingsUpdateComponent,
        resolve: {
            financialYearSettings: FinancialYearSettingsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.financialYearSettings.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const financialYearSettingsPopupRoute: Routes = [
    {
        path: 'financial-year-settings/:id/delete',
        component: FinancialYearSettingsDeletePopupComponent,
        resolve: {
            financialYearSettings: FinancialYearSettingsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.financialYearSettings.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
