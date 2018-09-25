import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IPointOfSaleDetails } from 'app/shared/model/point-of-sale-details.model';
import { PointOfSaleDetailsService } from './point-of-sale-details.service';
import { IPickListValue } from 'app/shared/model/pick-list-value.model';
import { PickListValueService } from 'app/entities/pick-list-value';
import { INurseryStock } from 'app/shared/model/nursery-stock.model';
import { NurseryStockService } from 'app/entities/nursery-stock';

@Component({
    selector: 'jhi-point-of-sale-details-update',
    templateUrl: './point-of-sale-details-update.component.html'
})
export class PointOfSaleDetailsUpdateComponent implements OnInit {
    private _pointOfSaleDetails: IPointOfSaleDetails;
    isSaving: boolean;

    picklistvalues: IPickListValue[];

    nurserystocks: INurseryStock[];
    dateDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private pointOfSaleDetailsService: PointOfSaleDetailsService,
        private pickListValueService: PickListValueService,
        private nurseryStockService: NurseryStockService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ pointOfSaleDetails }) => {
            this.pointOfSaleDetails = pointOfSaleDetails;
        });
        this.pickListValueService.query().subscribe(
            (res: HttpResponse<IPickListValue[]>) => {
                this.picklistvalues = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.nurseryStockService.query().subscribe(
            (res: HttpResponse<INurseryStock[]>) => {
                this.nurserystocks = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.pointOfSaleDetails.id !== undefined) {
            this.subscribeToSaveResponse(this.pointOfSaleDetailsService.update(this.pointOfSaleDetails));
        } else {
            this.subscribeToSaveResponse(this.pointOfSaleDetailsService.create(this.pointOfSaleDetails));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPointOfSaleDetails>>) {
        result.subscribe((res: HttpResponse<IPointOfSaleDetails>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackNurseryStockById(index: number, item: INurseryStock) {
        return item.id;
    }
    get pointOfSaleDetails() {
        return this._pointOfSaleDetails;
    }

    set pointOfSaleDetails(pointOfSaleDetails: IPointOfSaleDetails) {
        this._pointOfSaleDetails = pointOfSaleDetails;
    }
}
