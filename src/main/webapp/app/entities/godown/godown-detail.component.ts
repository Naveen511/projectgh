import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGodown } from 'app/shared/model/godown.model';

@Component({
    selector: 'jhi-godown-detail',
    templateUrl: './godown-detail.component.html'
})
export class GodownDetailComponent implements OnInit {
    godown: IGodown;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ godown }) => {
            this.godown = godown;
        });
    }

    previousState() {
        window.history.back();
    }
}
