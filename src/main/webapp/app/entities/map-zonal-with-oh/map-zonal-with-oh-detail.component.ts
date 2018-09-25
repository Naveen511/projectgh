import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMapZonalWithOh } from 'app/shared/model/map-zonal-with-oh.model';

@Component({
    selector: 'jhi-map-zonal-with-oh-detail',
    templateUrl: './map-zonal-with-oh-detail.component.html'
})
export class MapZonalWithOhDetailComponent implements OnInit {
    mapZonalWithOh: IMapZonalWithOh;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ mapZonalWithOh }) => {
            this.mapZonalWithOh = mapZonalWithOh;
        });
    }

    previousState() {
        window.history.back();
    }
}
