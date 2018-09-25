import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { CoverFilling } from 'app/shared/model/cover-filling.model';
import { CoverFillingService } from './cover-filling.service';
import { CoverFillingComponent } from './cover-filling.component';
import { CoverFillingDetailComponent } from './cover-filling-detail.component';
import { CoverFillingUpdateComponent } from './cover-filling-update.component';
import { CoverFillingDeletePopupComponent } from './cover-filling-delete-dialog.component';
import { ICoverFilling } from 'app/shared/model/cover-filling.model';

@Injectable({ providedIn: 'root' })
export class CoverFillingResolve implements Resolve<ICoverFilling> {
    constructor(private service: CoverFillingService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((coverFilling: HttpResponse<CoverFilling>) => coverFilling.body));
        }
        return of(new CoverFilling());
    }
}

export const coverFillingRoute: Routes = [
    {
        path: 'cover-filling',
        component: CoverFillingComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'projectghApp.coverFilling.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cover-filling/:id/view',
        component: CoverFillingDetailComponent,
        resolve: {
            coverFilling: CoverFillingResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.coverFilling.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cover-filling/new',
        component: CoverFillingUpdateComponent,
        resolve: {
            coverFilling: CoverFillingResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.coverFilling.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cover-filling/:id/edit',
        component: CoverFillingUpdateComponent,
        resolve: {
            coverFilling: CoverFillingResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.coverFilling.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const coverFillingPopupRoute: Routes = [
    {
        path: 'cover-filling/:id/delete',
        component: CoverFillingDeletePopupComponent,
        resolve: {
            coverFilling: CoverFillingResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.coverFilling.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
