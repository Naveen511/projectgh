import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { INurseryInventoryDetails } from 'app/shared/model/nursery-inventory-details.model';
import { NurseryInventoryDetailsService } from './nursery-inventory-details.service';
import { INurseryInventory } from 'app/shared/model/nursery-inventory.model';
import { NurseryInventoryService } from 'app/entities/nursery-inventory';
import { IPickListValue } from 'app/shared/model/pick-list-value.model';
import { PickListValueService } from 'app/entities/pick-list-value';

@Component({
    selector: 'jhi-nursery-inventory-details-update',
    templateUrl: './nursery-inventory-details-update.component.html'
})
export class NurseryInventoryDetailsUpdateComponent implements OnInit {
    private _nurseryInventoryDetails: INurseryInventoryDetails;
    isSaving: boolean;

    nurseryinventories: INurseryInventory[];

    picklistvalues: IPickListValue[];
    dateDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private nurseryInventoryDetailsService: NurseryInventoryDetailsService,
        private nurseryInventoryService: NurseryInventoryService,
        private pickListValueService: PickListValueService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ nurseryInventoryDetails }) => {
            this.nurseryInventoryDetails = nurseryInventoryDetails;
        });
        this.nurseryInventoryService.query().subscribe(
            (res: HttpResponse<INurseryInventory[]>) => {
                this.nurseryinventories = res.body;
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
        if (this.nurseryInventoryDetails.id !== undefined) {
            this.subscribeToSaveResponse(this.nurseryInventoryDetailsService.update(this.nurseryInventoryDetails));
        } else {
            this.subscribeToSaveResponse(this.nurseryInventoryDetailsService.create(this.nurseryInventoryDetails));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<INurseryInventoryDetails>>) {
        result.subscribe(
            (res: HttpResponse<INurseryInventoryDetails>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
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

    trackNurseryInventoryById(index: number, item: INurseryInventory) {
        return item.id;
    }

    trackPickListValueById(index: number, item: IPickListValue) {
        return item.id;
    }
    get nurseryInventoryDetails() {
        return this._nurseryInventoryDetails;
    }

    set nurseryInventoryDetails(nurseryInventoryDetails: INurseryInventoryDetails) {
        this._nurseryInventoryDetails = nurseryInventoryDetails;
    }
}
