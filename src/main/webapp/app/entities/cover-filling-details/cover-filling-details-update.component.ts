import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ICoverFillingDetails } from 'app/shared/model/cover-filling-details.model';
import { CoverFillingDetailsService } from './cover-filling-details.service';
import { ICoverFilling } from 'app/shared/model/cover-filling.model';
import { CoverFillingService } from 'app/entities/cover-filling';
import { IPickListValue } from 'app/shared/model/pick-list-value.model';
import { PickListValueService } from 'app/entities/pick-list-value';

@Component({
    selector: 'jhi-cover-filling-details-update',
    templateUrl: './cover-filling-details-update.component.html'
})
export class CoverFillingDetailsUpdateComponent implements OnInit {
    private _coverFillingDetails: ICoverFillingDetails;
    isSaving: boolean;

    coverfillings: ICoverFilling[];

    picklistvalues: IPickListValue[];
    dateDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private coverFillingDetailsService: CoverFillingDetailsService,
        private coverFillingService: CoverFillingService,
        private pickListValueService: PickListValueService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ coverFillingDetails }) => {
            this.coverFillingDetails = coverFillingDetails;
        });
        this.coverFillingService.query().subscribe(
            (res: HttpResponse<ICoverFilling[]>) => {
                this.coverfillings = res.body;
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
        if (this.coverFillingDetails.id !== undefined) {
            this.subscribeToSaveResponse(this.coverFillingDetailsService.update(this.coverFillingDetails));
        } else {
            this.subscribeToSaveResponse(this.coverFillingDetailsService.create(this.coverFillingDetails));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICoverFillingDetails>>) {
        result.subscribe((res: HttpResponse<ICoverFillingDetails>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackCoverFillingById(index: number, item: ICoverFilling) {
        return item.id;
    }

    trackPickListValueById(index: number, item: IPickListValue) {
        return item.id;
    }
    get coverFillingDetails() {
        return this._coverFillingDetails;
    }

    set coverFillingDetails(coverFillingDetails: ICoverFillingDetails) {
        this._coverFillingDetails = coverFillingDetails;
    }
}
