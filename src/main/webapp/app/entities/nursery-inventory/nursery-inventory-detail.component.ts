import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INurseryInventory } from 'app/shared/model/nursery-inventory.model';

@Component({
    selector: 'jhi-nursery-inventory-detail',
    templateUrl: './nursery-inventory-detail.component.html'
})
export class NurseryInventoryDetailComponent implements OnInit {
    nurseryInventory: INurseryInventory;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ nurseryInventory }) => {
            this.nurseryInventory = nurseryInventory;
        });
    }

    previousState() {
        window.history.back();
    }
}
