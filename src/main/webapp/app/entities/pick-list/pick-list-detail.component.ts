import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPickList } from 'app/shared/model/pick-list.model';

@Component({
    selector: 'jhi-pick-list-detail',
    templateUrl: './pick-list-detail.component.html'
})
export class PickListDetailComponent implements OnInit {
    pickList: IPickList;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ pickList }) => {
            this.pickList = pickList;
        });
    }

    previousState() {
        window.history.back();
    }
}
