import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBatchQuantity } from 'app/shared/model/batch-quantity.model';

@Component({
    selector: 'jhi-batch-quantity-detail',
    templateUrl: './batch-quantity-detail.component.html'
})
export class BatchQuantityDetailComponent implements OnInit {
    batchQuantity: IBatchQuantity;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ batchQuantity }) => {
            this.batchQuantity = batchQuantity;
        });
    }

    previousState() {
        window.history.back();
    }
}
