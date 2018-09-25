import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ICommonSettings } from 'app/shared/model/common-settings.model';
import { CommonSettingsService } from './common-settings.service';

@Component({
    selector: 'jhi-common-settings-update',
    templateUrl: './common-settings-update.component.html'
})
export class CommonSettingsUpdateComponent implements OnInit {
    private _commonSettings: ICommonSettings;
    isSaving: boolean;

    constructor(private commonSettingsService: CommonSettingsService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ commonSettings }) => {
            this.commonSettings = commonSettings;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.commonSettings.id !== undefined) {
            this.subscribeToSaveResponse(this.commonSettingsService.update(this.commonSettings));
        } else {
            this.subscribeToSaveResponse(this.commonSettingsService.create(this.commonSettings));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICommonSettings>>) {
        result.subscribe((res: HttpResponse<ICommonSettings>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get commonSettings() {
        return this._commonSettings;
    }

    set commonSettings(commonSettings: ICommonSettings) {
        this._commonSettings = commonSettings;
    }
}
