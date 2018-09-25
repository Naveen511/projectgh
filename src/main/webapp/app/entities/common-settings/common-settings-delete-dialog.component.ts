import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICommonSettings } from 'app/shared/model/common-settings.model';
import { CommonSettingsService } from './common-settings.service';

@Component({
    selector: 'jhi-common-settings-delete-dialog',
    templateUrl: './common-settings-delete-dialog.component.html'
})
export class CommonSettingsDeleteDialogComponent {
    commonSettings: ICommonSettings;

    constructor(
        private commonSettingsService: CommonSettingsService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.commonSettingsService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'commonSettingsListModification',
                content: 'Deleted an commonSettings'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-common-settings-delete-popup',
    template: ''
})
export class CommonSettingsDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ commonSettings }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CommonSettingsDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.commonSettings = commonSettings;
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
