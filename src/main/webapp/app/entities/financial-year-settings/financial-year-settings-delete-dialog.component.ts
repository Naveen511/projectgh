import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFinancialYearSettings } from 'app/shared/model/financial-year-settings.model';
import { FinancialYearSettingsService } from './financial-year-settings.service';

@Component({
    selector: 'jhi-financial-year-settings-delete-dialog',
    templateUrl: './financial-year-settings-delete-dialog.component.html'
})
export class FinancialYearSettingsDeleteDialogComponent {
    financialYearSettings: IFinancialYearSettings;

    constructor(
        private financialYearSettingsService: FinancialYearSettingsService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.financialYearSettingsService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'financialYearSettingsListModification',
                content: 'Deleted an financialYearSettings'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-financial-year-settings-delete-popup',
    template: ''
})
export class FinancialYearSettingsDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ financialYearSettings }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(FinancialYearSettingsDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.financialYearSettings = financialYearSettings;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
