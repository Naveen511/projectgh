import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IQuantity } from 'app/shared/model/quantity.model';
import { QuantityService } from './quantity.service';
import { IPickListValue } from 'app/shared/model/pick-list-value.model';
import { PickListValueService } from 'app/entities/pick-list-value';

@Component({
    selector: 'jhi-quantity-update',
    templateUrl: './quantity-update.component.html'
})
export class QuantityUpdateComponent implements OnInit {
    private _quantity: IQuantity;
    isSaving: boolean;

    picklistvalues: IPickListValue[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private quantityService: QuantityService,
        private pickListValueService: PickListValueService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ quantity }) => {
            this.quantity = quantity;
        });
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
        if (this.quantity.id !== undefined) {
            this.subscribeToSaveResponse(this.quantityService.update(this.quantity));
        } else {
            this.subscribeToSaveResponse(this.quantityService.create(this.quantity));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IQuantity>>) {
        result.subscribe((res: HttpResponse<IQuantity>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackPickListValueById(index: number, item: IPickListValue) {
        return item.id;
    }
    get quantity() {
        return this._quantity;
    }

    set quantity(quantity: IQuantity) {
        this._quantity = quantity;
    }
}
