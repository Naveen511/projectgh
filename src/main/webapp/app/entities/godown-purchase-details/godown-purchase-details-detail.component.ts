import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGodownPurchaseDetails } from 'app/shared/model/godown-purchase-details.model';

@Component({
    selector: 'jhi-godown-purchase-details-detail',
    templateUrl: './godown-purchase-details-detail.component.html'
})
export class GodownPurchaseDetailsDetailComponent implements OnInit {
    godownPurchaseDetails: IGodownPurchaseDetails;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ godownPurchaseDetails }) => {
            this.godownPurchaseDetails = godownPurchaseDetails;
        });
    }

    previousState() {
        window.history.back();
    }
}
