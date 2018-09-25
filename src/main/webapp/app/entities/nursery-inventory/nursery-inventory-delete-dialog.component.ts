import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INurseryInventory } from 'app/shared/model/nursery-inventory.model';
import { NurseryInventoryService } from './nursery-inventory.service';

@Component({
    selector: 'jhi-nursery-inventory-delete-dialog',
    templateUrl: './nursery-inventory-delete-dialog.component.html'
})
export class NurseryInventoryDeleteDialogComponent {
    nurseryInventory: INurseryInventory;

    constructor(
        private nurseryInventoryService: NurseryInventoryService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.nurseryInventoryService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'nurseryInventoryListModification',
                content: 'Deleted an nurseryInventory'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-nursery-inventory-delete-popup',
    template: ''
})
export class NurseryInventoryDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ nurseryInventory }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(NurseryInventoryDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.nurseryInventory = nurseryInventory;
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
