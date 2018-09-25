import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Damage } from 'app/shared/model/damage.model';
import { DamageService } from './damage.service';
import { DamageComponent } from './damage.component';
import { DamageDetailComponent } from './damage-detail.component';
import { DamageUpdateComponent } from './damage-update.component';
import { DamageDeletePopupComponent } from './damage-delete-dialog.component';
import { IDamage } from 'app/shared/model/damage.model';

@Injectable({ providedIn: 'root' })
export class DamageResolve implements Resolve<IDamage> {
    constructor(private service: DamageService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((damage: HttpResponse<Damage>) => damage.body));
        }
        return of(new Damage());
    }
}

export const damageRoute: Routes = [
    {
        path: 'damage',
        component: DamageComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'projectghApp.damage.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'damage/:id/view',
        component: DamageDetailComponent,
        resolve: {
            damage: DamageResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.damage.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'damage/new',
        component: DamageUpdateComponent,
        resolve: {
            damage: DamageResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.damage.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'damage/:id/edit',
        component: DamageUpdateComponent,
        resolve: {
            damage: DamageResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.damage.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const damagePopupRoute: Routes = [
    {
        path: 'damage/:id/delete',
        component: DamageDeletePopupComponent,
        resolve: {
            damage: DamageResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.damage.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
