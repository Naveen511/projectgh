import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INurseryStockDetails } from 'app/shared/model/nursery-stock-details.model';

@Component({
    selector: 'jhi-nursery-stock-details-detail',
    templateUrl: './nursery-stock-details-detail.component.html'
})
export class NurseryStockDetailsDetailComponent implements OnInit {
    nurseryStockDetails: INurseryStockDetails;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ nurseryStockDetails }) => {
            this.nurseryStockDetails = nurseryStockDetails;
        });
    }

    previousState() {
        window.history.back();
    }
}
