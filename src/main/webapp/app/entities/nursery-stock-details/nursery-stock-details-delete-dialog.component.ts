import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INurseryStockDetails } from 'app/shared/model/nursery-stock-details.model';
import { NurseryStockDetailsService } from './nursery-stock-details.service';

@Component({
    selector: 'jhi-nursery-stock-details-delete-dialog',
    templateUrl: './nursery-stock-details-delete-dialog.component.html'
})
export class NurseryStockDetailsDeleteDialogComponent {
    nurseryStockDetails: INurseryStockDetails;

    constructor(
        private nurseryStockDetailsService: NurseryStockDetailsService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.nurseryStockDetailsService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'nurseryStockDetailsListModification',
                content: 'Deleted an nurseryStockDetails'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-nursery-stock-details-delete-popup',
    template: ''
})
export class NurseryStockDetailsDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ nurseryStockDetails }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(NurseryStockDetailsDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.nurseryStockDetails = nurseryStockDetails;
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
