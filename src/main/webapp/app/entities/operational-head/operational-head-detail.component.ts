import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOperationalHead } from 'app/shared/model/operational-head.model';

@Component({
    selector: 'jhi-operational-head-detail',
    templateUrl: './operational-head-detail.component.html'
})
export class OperationalHeadDetailComponent implements OnInit {
    operationalHead: IOperationalHead;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ operationalHead }) => {
            this.operationalHead = operationalHead;
        });
    }

    previousState() {
        window.history.back();
    }
}
