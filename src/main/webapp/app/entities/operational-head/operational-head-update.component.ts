import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IOperationalHead } from 'app/shared/model/operational-head.model';
import { OperationalHeadService } from './operational-head.service';

@Component({
    selector: 'jhi-operational-head-update',
    templateUrl: './operational-head-update.component.html'
})
export class OperationalHeadUpdateComponent implements OnInit {
    private _operationalHead: IOperationalHead;
    isSaving: boolean;

    constructor(private operationalHeadService: OperationalHeadService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ operationalHead }) => {
            this.operationalHead = operationalHead;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.operationalHead.id !== undefined) {
            this.subscribeToSaveResponse(this.operationalHeadService.update(this.operationalHead));
        } else {
            this.subscribeToSaveResponse(this.operationalHeadService.create(this.operationalHead));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IOperationalHead>>) {
        result.subscribe((res: HttpResponse<IOperationalHead>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get operationalHead() {
        return this._operationalHead;
    }

    set operationalHead(operationalHead: IOperationalHead) {
        this._operationalHead = operationalHead;
    }
}
