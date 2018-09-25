import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { MapZonalWithOh } from 'app/shared/model/map-zonal-with-oh.model';
import { MapZonalWithOhService } from './map-zonal-with-oh.service';
import { MapZonalWithOhComponent } from './map-zonal-with-oh.component';
import { MapZonalWithOhDetailComponent } from './map-zonal-with-oh-detail.component';
import { MapZonalWithOhUpdateComponent } from './map-zonal-with-oh-update.component';
import { MapZonalWithOhDeletePopupComponent } from './map-zonal-with-oh-delete-dialog.component';
import { IMapZonalWithOh } from 'app/shared/model/map-zonal-with-oh.model';

@Injectable({ providedIn: 'root' })
export class MapZonalWithOhResolve implements Resolve<IMapZonalWithOh> {
    constructor(private service: MapZonalWithOhService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((mapZonalWithOh: HttpResponse<MapZonalWithOh>) => mapZonalWithOh.body));
        }
        return of(new MapZonalWithOh());
    }
}

export const mapZonalWithOhRoute: Routes = [
    {
        path: 'map-zonal-with-oh',
        component: MapZonalWithOhComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'projectghApp.mapZonalWithOh.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'map-zonal-with-oh/:id/view',
        component: MapZonalWithOhDetailComponent,
        resolve: {
            mapZonalWithOh: MapZonalWithOhResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.mapZonalWithOh.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'map-zonal-with-oh/new',
        component: MapZonalWithOhUpdateComponent,
        resolve: {
            mapZonalWithOh: MapZonalWithOhResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.mapZonalWithOh.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'map-zonal-with-oh/:id/edit',
        component: MapZonalWithOhUpdateComponent,
        resolve: {
            mapZonalWithOh: MapZonalWithOhResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.mapZonalWithOh.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const mapZonalWithOhPopupRoute: Routes = [
    {
        path: 'map-zonal-with-oh/:id/delete',
        component: MapZonalWithOhDeletePopupComponent,
        resolve: {
            mapZonalWithOh: MapZonalWithOhResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.mapZonalWithOh.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
