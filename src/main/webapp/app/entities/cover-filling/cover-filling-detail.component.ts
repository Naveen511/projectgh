import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICoverFilling } from 'app/shared/model/cover-filling.model';

@Component({
    selector: 'jhi-cover-filling-detail',
    templateUrl: './cover-filling-detail.component.html'
})
export class CoverFillingDetailComponent implements OnInit {
    coverFilling: ICoverFilling;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ coverFilling }) => {
            this.coverFilling = coverFilling;
        });
    }

    previousState() {
        window.history.back();
    }
}
