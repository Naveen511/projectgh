import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISectorIncharge } from 'app/shared/model/sector-incharge.model';

@Component({
    selector: 'jhi-sector-incharge-detail',
    templateUrl: './sector-incharge-detail.component.html'
})
export class SectorInchargeDetailComponent implements OnInit {
    sectorIncharge: ISectorIncharge;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sectorIncharge }) => {
            this.sectorIncharge = sectorIncharge;
        });
    }

    previousState() {
        window.history.back();
    }
}
