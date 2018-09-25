import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NurseryStockMgntComponent } from 'app/entities/nursery-stock-management/nursery-stock.component';

const routes: Routes = [
    {
        path: '',
        component: NurseryStockMgntComponent,
        data: {
            title: 'Nursery Stock'
        }
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class NurseryStockMgntRoutingModule {}
