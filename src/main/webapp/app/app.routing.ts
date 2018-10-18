/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02
 *  Target: yarn
 * -----------------------------------------------------------------------------
 * File Description : Declared an array variable to set module to a sidebar option
 *                    with path to display in url, component name to access html,
 *                    css and methods, data for an option to set page title,
 *                    loadChildren to mension folder path of module file and
 *                    class name as an object.
 *
 *******************************************************************************/
// Import an angular dependency
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

// Import Component
import { DefaultLayoutComponent } from 'app/containers';
import { P404Component } from './views/error/404.component';
import { P500Component } from './views/error/500.component';
import { LoginComponent } from './views/login/login.component';
import { RegisterComponent } from './views/register/register.component';

// Declare a variable with array of values
export const routes: Routes = [
    {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full'
    },
    {
        path: '404',
        component: P404Component,
        data: {
            title: 'Page 404'
        }
    },
    {
        path: '500',
        component: P500Component,
        data: {
            title: 'Page 500'
        }
    },
    {
        path: 'login',
        component: LoginComponent,
        data: {
            title: 'Login Page'
        }
    },
    {
        path: 'register',
        component: RegisterComponent,
        data: {
            title: 'Register Page'
        }
    },
    {
        path: '',
        component: DefaultLayoutComponent,
        data: {
            title: 'Home'
        },
        children: [
            {
                path: 'configuration',
                loadChildren: './entities/configuration/configuration.module#ConfigurationModule'
            },
            {
                path: 'batch',
                loadChildren: './entities/batch-management/batchmgnt.module#BatchmgntModule'
            },
            {
                path: 'nurserystock',
                loadChildren: './entities/nursery-stock-management/nursery-stock.module#NurseryStockMgntModule'
            },
            {
                path: 'nursery-inventory',
                loadChildren: './entities/nursery-inventory-management/nursery-inventory.module#NurseryInventoryMgntModule'
            },
            {
                path: 'damage',
                loadChildren: './entities/damage-management/damage-management.module#DamagemgntModule'
            },
            {
                path: 'godown',
                loadChildren: './entities/godown-management/godown.module#GodownModule'
            },
            {
                path: 'dashboard',
                loadChildren: './views/dashboard/dashboard.module#DashboardModule'
            },
            {
                path: 'entity-audit',
                // loadChildren: './admin/entity-audit/entity-audit.module#EntityAuditModule'
                loadChildren: './admin/admin.module#ProjectghAdminModule'
            }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {}
