import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ICoverFilling } from 'app/shared/model/cover-filling.model';
import { CoverFillingService } from './cover-filling.service';

@Component({
    selector: 'jhi-cover-filling-update',
    templateUrl: './cover-filling-update.component.html'
})
export class CoverFillingUpdateComponent implements OnInit {
    private _coverFilling: ICoverFilling;
    isSaving: boolean;
    dateDp: any;

    constructor(private coverFillingService: CoverFillingService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ coverFilling }) => {
            this.coverFilling = coverFilling;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.coverFilling.id !== undefined) {
            this.subscribeToSaveResponse(this.coverFillingService.update(this.coverFilling));
        } else {
            this.subscribeToSaveResponse(this.coverFillingService.create(this.coverFilling));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICoverFilling>>) {
        result.subscribe((res: HttpResponse<ICoverFilling>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get coverFilling() {
        return this._coverFilling;
    }

    set coverFilling(coverFilling: ICoverFilling) {
        this._coverFilling = coverFilling;
    }
}
