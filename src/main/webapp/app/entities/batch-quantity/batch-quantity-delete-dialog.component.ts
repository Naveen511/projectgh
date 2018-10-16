import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBatchQuantity } from 'app/shared/model/batch-quantity.model';
import { BatchQuantityService } from './batch-quantity.service';

@Component({
    selector: 'jhi-batch-quantity-delete-dialog',
    templateUrl: './batch-quantity-delete-dialog.component.html'
})
export class BatchQuantityDeleteDialogComponent {
    batchQuantity: IBatchQuantity;

    constructor(
        private batchQuantityService: BatchQuantityService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.batchQuantityService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'batchQuantityListModification',
                content: 'Deleted an batchQuantity'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-batch-quantity-delete-popup',
    template: ''
})
export class BatchQuantityDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ batchQuantity }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(BatchQuantityDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.batchQuantity = batchQuantity;
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
