import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { MotherBed } from 'app/shared/model/mother-bed.model';
import { MotherBedService } from './mother-bed.service';
import { MotherBedComponent } from './mother-bed.component';
import { MotherBedDetailComponent } from './mother-bed-detail.component';
import { MotherBedUpdateComponent } from './mother-bed-update.component';
import { MotherBedDeletePopupComponent } from './mother-bed-delete-dialog.component';
import { IMotherBed } from 'app/shared/model/mother-bed.model';

@Injectable({ providedIn: 'root' })
export class MotherBedResolve implements Resolve<IMotherBed> {
    constructor(private service: MotherBedService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((motherBed: HttpResponse<MotherBed>) => motherBed.body));
        }
        return of(new MotherBed());
    }
}

export const motherBedRoute: Routes = [
    {
        path: 'mother-bed',
        component: MotherBedComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'projectghApp.motherBed.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'mother-bed/:id/view',
        component: MotherBedDetailComponent,
        resolve: {
            motherBed: MotherBedResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.motherBed.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'mother-bed/new',
        component: MotherBedUpdateComponent,
        resolve: {
            motherBed: MotherBedResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.motherBed.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'mother-bed/:id/edit',
        component: MotherBedUpdateComponent,
        resolve: {
            motherBed: MotherBedResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.motherBed.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const motherBedPopupRoute: Routes = [
    {
        path: 'mother-bed/:id/delete',
        component: MotherBedDeletePopupComponent,
        resolve: {
            motherBed: MotherBedResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.motherBed.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
