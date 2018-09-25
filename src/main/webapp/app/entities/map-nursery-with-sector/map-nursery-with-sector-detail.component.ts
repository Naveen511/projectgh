import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMapNurseryWithSector } from 'app/shared/model/map-nursery-with-sector.model';

@Component({
    selector: 'jhi-map-nursery-with-sector-detail',
    templateUrl: './map-nursery-with-sector-detail.component.html'
})
export class MapNurseryWithSectorDetailComponent implements OnInit {
    mapNurseryWithSector: IMapNurseryWithSector;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ mapNurseryWithSector }) => {
            this.mapNurseryWithSector = mapNurseryWithSector;
        });
    }

    previousState() {
        window.history.back();
    }
}
