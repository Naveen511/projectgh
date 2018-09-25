import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INurseryInventoryDetails } from 'app/shared/model/nursery-inventory-details.model';
import { NurseryInventoryDetailsService } from './nursery-inventory-details.service';

@Component({
    selector: 'jhi-nursery-inventory-details-delete-dialog',
    templateUrl: './nursery-inventory-details-delete-dialog.component.html'
})
export class NurseryInventoryDetailsDeleteDialogComponent {
    nurseryInventoryDetails: INurseryInventoryDetails;

    constructor(
        private nurseryInventoryDetailsService: NurseryInventoryDetailsService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.nurseryInventoryDetailsService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'nurseryInventoryDetailsListModification',
                content: 'Deleted an nurseryInventoryDetails'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-nursery-inventory-details-delete-popup',
    template: ''
})
export class NurseryInventoryDetailsDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ nurseryInventoryDetails }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(NurseryInventoryDetailsDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.nurseryInventoryDetails = nurseryInventoryDetails;
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
