import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOperationalHead } from 'app/shared/model/operational-head.model';
import { OperationalHeadService } from './operational-head.service';

@Component({
    selector: 'jhi-operational-head-delete-dialog',
    templateUrl: './operational-head-delete-dialog.component.html'
})
export class OperationalHeadDeleteDialogComponent {
    operationalHead: IOperationalHead;

    constructor(
        private operationalHeadService: OperationalHeadService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.operationalHeadService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'operationalHeadListModification',
                content: 'Deleted an operationalHead'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-operational-head-delete-popup',
    template: ''
})
export class OperationalHeadDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ operationalHead }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(OperationalHeadDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.operationalHead = operationalHead;
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
