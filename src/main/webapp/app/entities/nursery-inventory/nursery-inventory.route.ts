import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { NurseryInventory } from 'app/shared/model/nursery-inventory.model';
import { NurseryInventoryService } from './nursery-inventory.service';
import { NurseryInventoryComponent } from './nursery-inventory.component';
import { NurseryInventoryDetailComponent } from './nursery-inventory-detail.component';
import { NurseryInventoryUpdateComponent } from './nursery-inventory-update.component';
import { NurseryInventoryDeletePopupComponent } from './nursery-inventory-delete-dialog.component';
import { INurseryInventory } from 'app/shared/model/nursery-inventory.model';

@Injectable({ providedIn: 'root' })
export class NurseryInventoryResolve implements Resolve<INurseryInventory> {
    constructor(private service: NurseryInventoryService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((nurseryInventory: HttpResponse<NurseryInventory>) => nurseryInventory.body));
        }
        return of(new NurseryInventory());
    }
}

export const nurseryInventoryRoute: Routes = [
    {
        path: 'nursery-inventory',
        component: NurseryInventoryComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'projectghApp.nurseryInventory.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'nursery-inventory/:id/view',
        component: NurseryInventoryDetailComponent,
        resolve: {
            nurseryInventory: NurseryInventoryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.nurseryInventory.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'nursery-inventory/new',
        component: NurseryInventoryUpdateComponent,
        resolve: {
            nurseryInventory: NurseryInventoryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.nurseryInventory.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'nursery-inventory/:id/edit',
        component: NurseryInventoryUpdateComponent,
        resolve: {
            nurseryInventory: NurseryInventoryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.nurseryInventory.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const nurseryInventoryPopupRoute: Routes = [
    {
        path: 'nursery-inventory/:id/delete',
        component: NurseryInventoryDeletePopupComponent,
        resolve: {
            nurseryInventory: NurseryInventoryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.nurseryInventory.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
