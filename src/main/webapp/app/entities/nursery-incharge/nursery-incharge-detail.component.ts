import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INurseryIncharge } from 'app/shared/model/nursery-incharge.model';

@Component({
    selector: 'jhi-nursery-incharge-detail',
    templateUrl: './nursery-incharge-detail.component.html'
})
export class NurseryInchargeDetailComponent implements OnInit {
    nurseryIncharge: INurseryIncharge;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ nurseryIncharge }) => {
            this.nurseryIncharge = nurseryIncharge;
        });
    }

    previousState() {
        window.history.back();
    }
}
