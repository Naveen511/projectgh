import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { MapNurseryWithSector } from 'app/shared/model/map-nursery-with-sector.model';
import { MapNurseryWithSectorService } from './map-nursery-with-sector.service';
import { MapNurseryWithSectorComponent } from './map-nursery-with-sector.component';
import { MapNurseryWithSectorDetailComponent } from './map-nursery-with-sector-detail.component';
import { MapNurseryWithSectorUpdateComponent } from './map-nursery-with-sector-update.component';
import { MapNurseryWithSectorDeletePopupComponent } from './map-nursery-with-sector-delete-dialog.component';
import { IMapNurseryWithSector } from 'app/shared/model/map-nursery-with-sector.model';

@Injectable({ providedIn: 'root' })
export class MapNurseryWithSectorResolve implements Resolve<IMapNurseryWithSector> {
    constructor(private service: MapNurseryWithSectorService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((mapNurseryWithSector: HttpResponse<MapNurseryWithSector>) => mapNurseryWithSector.body));
        }
        return of(new MapNurseryWithSector());
    }
}

export const mapNurseryWithSectorRoute: Routes = [
    {
        path: 'map-nursery-with-sector',
        component: MapNurseryWithSectorComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'projectghApp.mapNurseryWithSector.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'map-nursery-with-sector/:id/view',
        component: MapNurseryWithSectorDetailComponent,
        resolve: {
            mapNurseryWithSector: MapNurseryWithSectorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.mapNurseryWithSector.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'map-nursery-with-sector/new',
        component: MapNurseryWithSectorUpdateComponent,
        resolve: {
            mapNurseryWithSector: MapNurseryWithSectorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.mapNurseryWithSector.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'map-nursery-with-sector/:id/edit',
        component: MapNurseryWithSectorUpdateComponent,
        resolve: {
            mapNurseryWithSector: MapNurseryWithSectorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.mapNurseryWithSector.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const mapNurseryWithSectorPopupRoute: Routes = [
    {
        path: 'map-nursery-with-sector/:id/delete',
        component: MapNurseryWithSectorDeletePopupComponent,
        resolve: {
            mapNurseryWithSector: MapNurseryWithSectorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.mapNurseryWithSector.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
