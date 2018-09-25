import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPickListValue } from 'app/shared/model/pick-list-value.model';

@Component({
    selector: 'jhi-pick-list-value-detail',
    templateUrl: './pick-list-value-detail.component.html'
})
export class PickListValueDetailComponent implements OnInit {
    pickListValue: IPickListValue;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ pickListValue }) => {
            this.pickListValue = pickListValue;
        });
    }

    previousState() {
        window.history.back();
    }
}
