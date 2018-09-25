import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDamage } from 'app/shared/model/damage.model';

@Component({
    selector: 'jhi-damage-detail',
    templateUrl: './damage-detail.component.html'
})
export class DamageDetailComponent implements OnInit {
    damage: IDamage;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ damage }) => {
            this.damage = damage;
        });
    }

    previousState() {
        window.history.back();
    }
}
