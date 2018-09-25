import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DamagemgntComponent } from 'app/entities/damage-management/damagemgnt.component';
import { JhiResolvePagingParams } from 'ng-jhipster';

const routes: Routes = [
    {
        path: '',
        component: DamagemgntComponent,
        // resolve: {
        //     pagingParams: JhiResolvePagingParams
        // },
        data: {
            // defaultSort: 'id,asc',
            pageTitle: 'Damage Reporting'
        }
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class DamagemgntRoutingModule {}
