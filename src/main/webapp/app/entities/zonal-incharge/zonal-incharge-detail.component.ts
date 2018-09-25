import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IZonalIncharge } from 'app/shared/model/zonal-incharge.model';

@Component({
    selector: 'jhi-zonal-incharge-detail',
    templateUrl: './zonal-incharge-detail.component.html'
})
export class ZonalInchargeDetailComponent implements OnInit {
    zonalIncharge: IZonalIncharge;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ zonalIncharge }) => {
            this.zonalIncharge = zonalIncharge;
        });
    }

    previousState() {
        window.history.back();
    }
}
