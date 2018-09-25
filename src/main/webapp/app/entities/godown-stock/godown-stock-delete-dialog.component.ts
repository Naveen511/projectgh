import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGodownStock } from 'app/shared/model/godown-stock.model';
import { GodownStockService } from './godown-stock.service';

@Component({
    selector: 'jhi-godown-stock-delete-dialog',
    templateUrl: './godown-stock-delete-dialog.component.html'
})
export class GodownStockDeleteDialogComponent {
    godownStock: IGodownStock;

    constructor(
        private godownStockService: GodownStockService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.godownStockService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'godownStockListModification',
                content: 'Deleted an godownStock'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-godown-stock-delete-popup',
    template: ''
})
export class GodownStockDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ godownStock }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(GodownStockDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.godownStock = godownStock;
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
