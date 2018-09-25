import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INurseryStock } from 'app/shared/model/nursery-stock.model';
import { NurseryStockService } from './nursery-stock.service';

@Component({
    selector: 'jhi-nursery-stock-delete-dialog',
    templateUrl: './nursery-stock-delete-dialog.component.html'
})
export class NurseryStockDeleteDialogComponent {
    nurseryStock: INurseryStock;

    constructor(
        private nurseryStockService: NurseryStockService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.nurseryStockService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'nurseryStockListModification',
                content: 'Deleted an nurseryStock'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-nursery-stock-delete-popup',
    template: ''
})
export class NurseryStockDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ nurseryStock }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(NurseryStockDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.nurseryStock = nurseryStock;
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
