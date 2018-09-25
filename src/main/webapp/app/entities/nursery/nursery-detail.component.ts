import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INursery } from 'app/shared/model/nursery.model';

@Component({
    selector: 'jhi-nursery-detail',
    templateUrl: './nursery-detail.component.html'
})
export class NurseryDetailComponent implements OnInit {
    nursery: INursery;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ nursery }) => {
            this.nursery = nursery;
        });
    }

    previousState() {
        window.history.back();
    }
}
