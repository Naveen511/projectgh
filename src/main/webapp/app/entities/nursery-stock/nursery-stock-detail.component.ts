import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INurseryStock } from 'app/shared/model/nursery-stock.model';

@Component({
    selector: 'jhi-nursery-stock-detail',
    templateUrl: './nursery-stock-detail.component.html'
})
export class NurseryStockDetailComponent implements OnInit {
    nurseryStock: INurseryStock;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ nurseryStock }) => {
            this.nurseryStock = nurseryStock;
        });
    }

    previousState() {
        window.history.back();
    }
}
