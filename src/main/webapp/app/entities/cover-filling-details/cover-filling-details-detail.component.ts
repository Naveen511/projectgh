import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICoverFillingDetails } from 'app/shared/model/cover-filling-details.model';

@Component({
    selector: 'jhi-cover-filling-details-detail',
    templateUrl: './cover-filling-details-detail.component.html'
})
export class CoverFillingDetailsDetailComponent implements OnInit {
    coverFillingDetails: ICoverFillingDetails;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ coverFillingDetails }) => {
            this.coverFillingDetails = coverFillingDetails;
        });
    }

    previousState() {
        window.history.back();
    }
}
