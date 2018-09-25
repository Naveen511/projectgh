import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPointOfSaleDetails } from 'app/shared/model/point-of-sale-details.model';
import { PointOfSaleDetailsService } from './point-of-sale-details.service';

@Component({
    selector: 'jhi-point-of-sale-details-delete-dialog',
    templateUrl: './point-of-sale-details-delete-dialog.component.html'
})
export class PointOfSaleDetailsDeleteDialogComponent {
    pointOfSaleDetails: IPointOfSaleDetails;

    constructor(
        private pointOfSaleDetailsService: PointOfSaleDetailsService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.pointOfSaleDetailsService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'pointOfSaleDetailsListModification',
                content: 'Deleted an pointOfSaleDetails'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-point-of-sale-details-delete-popup',
    template: ''
})
export class PointOfSaleDetailsDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ pointOfSaleDetails }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PointOfSaleDetailsDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.pointOfSaleDetails = pointOfSaleDetails;
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
