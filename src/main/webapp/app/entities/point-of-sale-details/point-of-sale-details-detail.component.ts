import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPointOfSaleDetails } from 'app/shared/model/point-of-sale-details.model';

@Component({
    selector: 'jhi-point-of-sale-details-detail',
    templateUrl: './point-of-sale-details-detail.component.html'
})
export class PointOfSaleDetailsDetailComponent implements OnInit {
    pointOfSaleDetails: IPointOfSaleDetails;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ pointOfSaleDetails }) => {
            this.pointOfSaleDetails = pointOfSaleDetails;
        });
    }

    previousState() {
        window.history.back();
    }
}
