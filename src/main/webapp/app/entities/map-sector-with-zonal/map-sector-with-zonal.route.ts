import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { MapSectorWithZonal } from 'app/shared/model/map-sector-with-zonal.model';
import { MapSectorWithZonalService } from './map-sector-with-zonal.service';
import { MapSectorWithZonalComponent } from './map-sector-with-zonal.component';
import { MapSectorWithZonalDetailComponent } from './map-sector-with-zonal-detail.component';
import { MapSectorWithZonalUpdateComponent } from './map-sector-with-zonal-update.component';
import { MapSectorWithZonalDeletePopupComponent } from './map-sector-with-zonal-delete-dialog.component';
import { IMapSectorWithZonal } from 'app/shared/model/map-sector-with-zonal.model';

@Injectable({ providedIn: 'root' })
export class MapSectorWithZonalResolve implements Resolve<IMapSectorWithZonal> {
    constructor(private service: MapSectorWithZonalService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((mapSectorWithZonal: HttpResponse<MapSectorWithZonal>) => mapSectorWithZonal.body));
        }
        return of(new MapSectorWithZonal());
    }
}

export const mapSectorWithZonalRoute: Routes = [
    {
        path: 'map-sector-with-zonal',
        component: MapSectorWithZonalComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'projectghApp.mapSectorWithZonal.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'map-sector-with-zonal/:id/view',
        component: MapSectorWithZonalDetailComponent,
        resolve: {
            mapSectorWithZonal: MapSectorWithZonalResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.mapSectorWithZonal.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'map-sector-with-zonal/new',
        component: MapSectorWithZonalUpdateComponent,
        resolve: {
            mapSectorWithZonal: MapSectorWithZonalResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.mapSectorWithZonal.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'map-sector-with-zonal/:id/edit',
        component: MapSectorWithZonalUpdateComponent,
        resolve: {
            mapSectorWithZonal: MapSectorWithZonalResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.mapSectorWithZonal.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const mapSectorWithZonalPopupRoute: Routes = [
    {
        path: 'map-sector-with-zonal/:id/delete',
        component: MapSectorWithZonalDeletePopupComponent,
        resolve: {
            mapSectorWithZonal: MapSectorWithZonalResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.mapSectorWithZonal.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
