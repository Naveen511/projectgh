import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGodownStockDetails } from 'app/shared/model/godown-stock-details.model';

@Component({
    selector: 'jhi-godown-stock-details-detail',
    templateUrl: './godown-stock-details-detail.component.html'
})
export class GodownStockDetailsDetailComponent implements OnInit {
    godownStockDetails: IGodownStockDetails;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ godownStockDetails }) => {
            this.godownStockDetails = godownStockDetails;
        });
    }

    previousState() {
        window.history.back();
    }
}
