import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { SectorIncharge } from 'app/shared/model/sector-incharge.model';
import { SectorInchargeService } from './sector-incharge.service';
import { SectorInchargeComponent } from './sector-incharge.component';
import { SectorInchargeDetailComponent } from './sector-incharge-detail.component';
import { SectorInchargeUpdateComponent } from './sector-incharge-update.component';
import { SectorInchargeDeletePopupComponent } from './sector-incharge-delete-dialog.component';
import { ISectorIncharge } from 'app/shared/model/sector-incharge.model';

@Injectable({ providedIn: 'root' })
export class SectorInchargeResolve implements Resolve<ISectorIncharge> {
    constructor(private service: SectorInchargeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((sectorIncharge: HttpResponse<SectorIncharge>) => sectorIncharge.body));
        }
        return of(new SectorIncharge());
    }
}

export const sectorInchargeRoute: Routes = [
    {
        path: 'sector-incharge',
        component: SectorInchargeComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'projectghApp.sectorIncharge.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sector-incharge/:id/view',
        component: SectorInchargeDetailComponent,
        resolve: {
            sectorIncharge: SectorInchargeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.sectorIncharge.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sector-incharge/new',
        component: SectorInchargeUpdateComponent,
        resolve: {
            sectorIncharge: SectorInchargeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.sectorIncharge.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sector-incharge/:id/edit',
        component: SectorInchargeUpdateComponent,
        resolve: {
            sectorIncharge: SectorInchargeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.sectorIncharge.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sectorInchargePopupRoute: Routes = [
    {
        path: 'sector-incharge/:id/delete',
        component: SectorInchargeDeletePopupComponent,
        resolve: {
            sectorIncharge: SectorInchargeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.sectorIncharge.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
