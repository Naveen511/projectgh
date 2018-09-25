import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';

import { OperationalHeadComponent } from 'app/entities/configuration/operational-head.component';
import { ZonalComponent } from 'app/entities/configuration/zonal.component';
import { SectorComponent } from 'app/entities/configuration/sector.component';
import { NurseryComponent } from 'app/entities/configuration/nursery.component';
import { PickListComponent } from 'app/entities/configuration/pick-list.component';
import { FinancialYearSettingsComponent } from 'app/entities/configuration/financial-year-settings.component';

const routes: Routes = [
    {
        path: '',
        data: {
            title: 'Configuration'
        },
        children: [
            {
                path: 'head-office',
                component: OperationalHeadComponent,
                resolve: {
                    pagingParams: JhiResolvePagingParams
                },
                data: {
                    defaultSort: 'id,asc',
                    pageTitle: 'Head Office'
                }
            },
            {
                path: 'zonal',
                component: ZonalComponent,
                resolve: {
                    pagingParams: JhiResolvePagingParams
                },
                data: {
                    defaultSort: 'id,asc',
                    pageTitle: 'projectghApp.zonal.home.title'
                }
                // ,
                // data: {
                //     title: 'Zonal'
                // }
            },
            {
                path: 'sector',
                component: SectorComponent,
                resolve: {
                    pagingParams: JhiResolvePagingParams
                },
                data: {
                    defaultSort: 'id,asc',
                    pageTitle: 'Sector'
                }
                // data: {
                //     title: 'Sector'
                // }
            },
            {
                path: 'nursery',
                component: NurseryComponent,
                resolve: {
                    pagingParams: JhiResolvePagingParams
                },
                data: {
                    defaultSort: 'id,asc',
                    pageTitle: 'Nursery'
                }
                // data: {
                //     title: 'Nursery'
                // }
            },
            {
                path: 'picklist',
                component: PickListComponent,
                resolve: {
                    pagingParams: JhiResolvePagingParams
                },
                data: {
                    defaultSort: 'id,asc',
                    pageTitle: 'Pick List'
                }
                // data: {
                //     title: 'Pick List'
                // }
            },
            {
                path: 'financialYearSettings',
                component: FinancialYearSettingsComponent,
                resolve: {
                    pagingParams: JhiResolvePagingParams
                },
                data: {
                    defaultSort: 'id,asc',
                    pageTitle: 'Financial Year Settings'
                }
                // data: {
                //     title: 'Financial Year Settings'
                // }
            }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ConfigurationRoutingModule {}
