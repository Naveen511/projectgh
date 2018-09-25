import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IShadeArea } from 'app/shared/model/shade-area.model';

@Component({
    selector: 'jhi-shade-area-detail',
    templateUrl: './shade-area-detail.component.html'
})
export class ShadeAreaDetailComponent implements OnInit {
    shadeArea: IShadeArea;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ shadeArea }) => {
            this.shadeArea = shadeArea;
        });
    }

    previousState() {
        window.history.back();
    }
}
