import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IPickListValue } from 'app/shared/model/pick-list-value.model';
import { PickListValueService } from './pick-list-value.service';
import { IPickList } from 'app/shared/model/pick-list.model';
import { PickListService } from 'app/entities/pick-list';

@Component({
    selector: 'jhi-pick-list-value-update',
    templateUrl: './pick-list-value-update.component.html'
})
export class PickListValueUpdateComponent implements OnInit {
    private _pickListValue: IPickListValue;
    isSaving: boolean;

    picklists: IPickList[];

    picklistvalues: IPickListValue[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private pickListValueService: PickListValueService,
        private pickListService: PickListService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ pickListValue }) => {
            this.pickListValue = pickListValue;
        });
        this.pickListService.query().subscribe(
            (res: HttpResponse<IPickList[]>) => {
                this.picklists = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.pickListValueService.query().subscribe(
            (res: HttpResponse<IPickListValue[]>) => {
                this.picklistvalues = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.pickListValue.id !== undefined) {
            this.subscribeToSaveResponse(this.pickListValueService.update(this.pickListValue));
        } else {
            this.subscribeToSaveResponse(this.pickListValueService.create(this.pickListValue));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPickListValue>>) {
        result.subscribe((res: HttpResponse<IPickListValue>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackPickListById(index: number, item: IPickList) {
        return item.id;
    }

    trackPickListValueById(index: number, item: IPickListValue) {
        return item.id;
    }
    get pickListValue() {
        return this._pickListValue;
    }

    set pickListValue(pickListValue: IPickListValue) {
        this._pickListValue = pickListValue;
    }
}
