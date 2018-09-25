import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGodownStock } from 'app/shared/model/godown-stock.model';

@Component({
    selector: 'jhi-godown-stock-detail',
    templateUrl: './godown-stock-detail.component.html'
})
export class GodownStockDetailComponent implements OnInit {
    godownStock: IGodownStock;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ godownStock }) => {
            this.godownStock = godownStock;
        });
    }

    previousState() {
        window.history.back();
    }
}
