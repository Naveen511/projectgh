import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IBatchQuantity } from 'app/shared/model/batch-quantity.model';
import { BatchQuantityService } from './batch-quantity.service';
import { IBatch } from 'app/shared/model/batch.model';
import { BatchService } from 'app/entities/batch';

@Component({
    selector: 'jhi-batch-quantity-update',
    templateUrl: './batch-quantity-update.component.html'
})
export class BatchQuantityUpdateComponent implements OnInit {
    private _batchQuantity: IBatchQuantity;
    isSaving: boolean;

    batches: IBatch[];
    dateDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private batchQuantityService: BatchQuantityService,
        private batchService: BatchService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ batchQuantity }) => {
            this.batchQuantity = batchQuantity;
        });
        this.batchService.query().subscribe(
            (res: HttpResponse<IBatch[]>) => {
                this.batches = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.batchQuantity.id !== undefined) {
            this.subscribeToSaveResponse(this.batchQuantityService.update(this.batchQuantity));
        } else {
            this.subscribeToSaveResponse(this.batchQuantityService.create(this.batchQuantity));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IBatchQuantity>>) {
        result.subscribe((res: HttpResponse<IBatchQuantity>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackBatchById(index: number, item: IBatch) {
        return item.id;
    }
    get batchQuantity() {
        return this._batchQuantity;
    }

    set batchQuantity(batchQuantity: IBatchQuantity) {
        this._batchQuantity = batchQuantity;
    }
}
