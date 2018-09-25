import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NurseryInventoryMgntComponent } from 'app/entities/nursery-inventory-management/nursery-inventory.component';

const routes: Routes = [
    {
        path: '',
        component: NurseryInventoryMgntComponent,
        data: {
            title: 'Seeds & Cover Management'
        }
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class NurseryInventoryMgntRoutingModule {}
