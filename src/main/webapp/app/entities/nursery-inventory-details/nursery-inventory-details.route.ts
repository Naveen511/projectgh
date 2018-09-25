import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { NurseryInventoryDetails } from 'app/shared/model/nursery-inventory-details.model';
import { NurseryInventoryDetailsService } from './nursery-inventory-details.service';
import { NurseryInventoryDetailsComponent } from './nursery-inventory-details.component';
import { NurseryInventoryDetailsDetailComponent } from './nursery-inventory-details-detail.component';
import { NurseryInventoryDetailsUpdateComponent } from './nursery-inventory-details-update.component';
import { NurseryInventoryDetailsDeletePopupComponent } from './nursery-inventory-details-delete-dialog.component';
import { INurseryInventoryDetails } from 'app/shared/model/nursery-inventory-details.model';

@Injectable({ providedIn: 'root' })
export class NurseryInventoryDetailsResolve implements Resolve<INurseryInventoryDetails> {
    constructor(private service: NurseryInventoryDetailsService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(map((nurseryInventoryDetails: HttpResponse<NurseryInventoryDetails>) => nurseryInventoryDetails.body));
        }
        return of(new NurseryInventoryDetails());
    }
}

export const nurseryInventoryDetailsRoute: Routes = [
    {
        path: 'nursery-inventory-details',
        component: NurseryInventoryDetailsComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'projectghApp.nurseryInventoryDetails.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'nursery-inventory-details/:id/view',
        component: NurseryInventoryDetailsDetailComponent,
        resolve: {
            nurseryInventoryDetails: NurseryInventoryDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.nurseryInventoryDetails.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'nursery-inventory-details/new',
        component: NurseryInventoryDetailsUpdateComponent,
        resolve: {
            nurseryInventoryDetails: NurseryInventoryDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.nurseryInventoryDetails.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'nursery-inventory-details/:id/edit',
        component: NurseryInventoryDetailsUpdateComponent,
        resolve: {
            nurseryInventoryDetails: NurseryInventoryDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.nurseryInventoryDetails.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const nurseryInventoryDetailsPopupRoute: Routes = [
    {
        path: 'nursery-inventory-details/:id/delete',
        component: NurseryInventoryDetailsDeletePopupComponent,
        resolve: {
            nurseryInventoryDetails: NurseryInventoryDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'projectghApp.nurseryInventoryDetails.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
