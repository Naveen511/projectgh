import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { INurseryInventory } from 'app/shared/model/nursery-inventory.model';
import { NurseryInventoryService } from './nursery-inventory.service';
import { INursery } from 'app/shared/model/nursery.model';
import { NurseryService } from 'app/entities/nursery';
import { IPickListValue } from 'app/shared/model/pick-list-value.model';
import { PickListValueService } from 'app/entities/pick-list-value';

@Component({
    selector: 'jhi-nursery-inventory-update',
    templateUrl: './nursery-inventory-update.component.html'
})
export class NurseryInventoryUpdateComponent implements OnInit {
    private _nurseryInventory: INurseryInventory;
    isSaving: boolean;

    nurseries: INursery[];

    picklistvalues: IPickListValue[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private nurseryInventoryService: NurseryInventoryService,
        private nurseryService: NurseryService,
        private pickListValueService: PickListValueService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ nurseryInventory }) => {
            this.nurseryInventory = nurseryInventory;
        });
        this.nurseryService.query().subscribe(
            (res: HttpResponse<INursery[]>) => {
                this.nurseries = res.body;
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
        if (this.nurseryInventory.id !== undefined) {
            this.subscribeToSaveResponse(this.nurseryInventoryService.update(this.nurseryInventory));
        } else {
            this.subscribeToSaveResponse(this.nurseryInventoryService.create(this.nurseryInventory));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<INurseryInventory>>) {
        result.subscribe((res: HttpResponse<INurseryInventory>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackNurseryById(index: number, item: INursery) {
        return item.id;
    }

    trackPickListValueById(index: number, item: IPickListValue) {
        return item.id;
    }
    get nurseryInventory() {
        return this._nurseryInventory;
    }

    set nurseryInventory(nurseryInventory: INurseryInventory) {
        this._nurseryInventory = nurseryInventory;
    }
}
