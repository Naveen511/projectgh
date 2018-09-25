import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INurseryInventoryDetails } from 'app/shared/model/nursery-inventory-details.model';

@Component({
    selector: 'jhi-nursery-inventory-details-detail',
    templateUrl: './nursery-inventory-details-detail.component.html'
})
export class NurseryInventoryDetailsDetailComponent implements OnInit {
    nurseryInventoryDetails: INurseryInventoryDetails;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ nurseryInventoryDetails }) => {
            this.nurseryInventoryDetails = nurseryInventoryDetails;
        });
    }

    previousState() {
        window.history.back();
    }
}
