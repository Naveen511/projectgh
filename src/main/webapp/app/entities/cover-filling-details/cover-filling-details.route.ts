import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { CoverFillingDetails } from 'app/shared/model/cover-filling-details.model';
import { CoverFillingDetailsService } from './cover-filling-details.service';
import { CoverFillingDetailsComponent } from './cover-filling-details.component';
import { CoverFillingDetailsDetailComponent } from './cover-filling-details-detail.component';
import { CoverFillingDetailsUpdateComponent } from './cover-filling-details-update.component';
import { CoverFillingDetailsDeletePopupComponent } from './cover-filling-details-delete-dialog.component';
import { ICoverFillingDetails } from 'app/shared/model/cover-filling-details.model';

@Injectable({ providedIn: 'root' })
export class CoverFillingDetailsResolve implements Resolve<ICoverFillingDetails> {
    constructor(private service: CoverFillingDetailsService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((coverFillingDetails: HttpResponse<CoverFillingDetails>) => coverFillingDetails.body));
        }
        return of(new CoverFillingDetails());
    }
}

export const coverFillingDetailsRoute: Routes = [
    {
        path: 'cover-filling-details',
        component: CoverFillingDetailsComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'projectghApp.coverFillingDetails.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cover-filling-details/:id/view',
        component: CoverFillingDetailsDetailComponent,
        resolve: {
            coverFillingDetails: CoverFillingDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.coverFillingDetails.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cover-filling-details/new',
        component: CoverFillingDetailsUpdateComponent,
        resolve: {
            coverFillingDetails: CoverFillingDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.coverFillingDetails.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cover-filling-details/:id/edit',
        component: CoverFillingDetailsUpdateComponent,
        resolve: {
            coverFillingDetails: CoverFillingDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.coverFillingDetails.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const coverFillingDetailsPopupRoute: Routes = [
    {
        path: 'cover-filling-details/:id/delete',
        component: CoverFillingDetailsDeletePopupComponent,
        resolve: {
            coverFillingDetails: CoverFillingDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.coverFillingDetails.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
