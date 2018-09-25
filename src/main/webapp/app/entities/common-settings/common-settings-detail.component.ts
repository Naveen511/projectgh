import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICommonSettings } from 'app/shared/model/common-settings.model';

@Component({
    selector: 'jhi-common-settings-detail',
    templateUrl: './common-settings-detail.component.html'
})
export class CommonSettingsDetailComponent implements OnInit {
    commonSettings: ICommonSettings;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ commonSettings }) => {
            this.commonSettings = commonSettings;
        });
    }

    previousState() {
        window.history.back();
    }
}
