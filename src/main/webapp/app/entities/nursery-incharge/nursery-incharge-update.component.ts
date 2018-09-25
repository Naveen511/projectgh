import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { INurseryIncharge } from 'app/shared/model/nursery-incharge.model';
import { NurseryInchargeService } from './nursery-incharge.service';
import { INursery } from 'app/shared/model/nursery.model';
import { NurseryService } from 'app/entities/nursery';

@Component({
    selector: 'jhi-nursery-incharge-update',
    templateUrl: './nursery-incharge-update.component.html'
})
export class NurseryInchargeUpdateComponent implements OnInit {
    private _nurseryIncharge: INurseryIncharge;
    isSaving: boolean;

    nurseries: INursery[];
    fromDateDp: any;
    toDateDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private nurseryInchargeService: NurseryInchargeService,
        private nurseryService: NurseryService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ nurseryIncharge }) => {
            this.nurseryIncharge = nurseryIncharge;
        });
        this.nurseryService.query().subscribe(
            (res: HttpResponse<INursery[]>) => {
                this.nurseries = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.nurseryIncharge.id !== undefined) {
            this.subscribeToSaveResponse(this.nurseryInchargeService.update(this.nurseryIncharge));
        } else {
            this.subscribeToSaveResponse(this.nurseryInchargeService.create(this.nurseryIncharge));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<INurseryIncharge>>) {
        result.subscribe((res: HttpResponse<INurseryIncharge>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    get nurseryIncharge() {
        return this._nurseryIncharge;
    }

    set nurseryIncharge(nurseryIncharge: INurseryIncharge) {
        this._nurseryIncharge = nurseryIncharge;
    }
}
