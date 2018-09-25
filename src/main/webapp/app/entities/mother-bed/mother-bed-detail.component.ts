import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMotherBed } from 'app/shared/model/mother-bed.model';

@Component({
    selector: 'jhi-mother-bed-detail',
    templateUrl: './mother-bed-detail.component.html'
})
export class MotherBedDetailComponent implements OnInit {
    motherBed: IMotherBed;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ motherBed }) => {
            this.motherBed = motherBed;
        });
    }

    previousState() {
        window.history.back();
    }
}
