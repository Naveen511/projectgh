import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMapSectorWithZonal } from 'app/shared/model/map-sector-with-zonal.model';

@Component({
    selector: 'jhi-map-sector-with-zonal-detail',
    templateUrl: './map-sector-with-zonal-detail.component.html'
})
export class MapSectorWithZonalDetailComponent implements OnInit {
    mapSectorWithZonal: IMapSectorWithZonal;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ mapSectorWithZonal }) => {
            this.mapSectorWithZonal = mapSectorWithZonal;
        });
    }

    previousState() {
        window.history.back();
    }
}
