import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGodownStockDetails } from 'app/shared/model/godown-stock-details.model';
import { GodownStockDetailsService } from './godown-stock-details.service';

@Component({
    selector: 'jhi-godown-stock-details-delete-dialog',
    templateUrl: './godown-stock-details-delete-dialog.component.html'
})
export class GodownStockDetailsDeleteDialogComponent {
    godownStockDetails: IGodownStockDetails;

    constructor(
        private godownStockDetailsService: GodownStockDetailsService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.godownStockDetailsService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'godownStockDetailsListModification',
                content: 'Deleted an godownStockDetails'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-godown-stock-details-delete-popup',
    template: ''
})
export class GodownStockDetailsDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ godownStockDetails }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(GodownStockDetailsDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.godownStockDetails = godownStockDetails;
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
