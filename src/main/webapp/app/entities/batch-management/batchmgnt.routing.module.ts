import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { BatchmgntComponent } from 'app/entities/batch-management/batchmgnt.component';

const routes: Routes = [
    {
        path: '',
        component: BatchmgntComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            defaultSort: 'id,asc',
            pageTitle: 'Batch Formation'
        }
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class BatchmgntRoutingModule {}
