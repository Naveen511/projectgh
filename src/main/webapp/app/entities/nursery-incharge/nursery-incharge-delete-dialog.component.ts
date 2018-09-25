import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INurseryIncharge } from 'app/shared/model/nursery-incharge.model';
import { NurseryInchargeService } from './nursery-incharge.service';

@Component({
    selector: 'jhi-nursery-incharge-delete-dialog',
    templateUrl: './nursery-incharge-delete-dialog.component.html'
})
export class NurseryInchargeDeleteDialogComponent {
    nurseryIncharge: INurseryIncharge;

    constructor(
        private nurseryInchargeService: NurseryInchargeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.nurseryInchargeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'nurseryInchargeListModification',
                content: 'Deleted an nurseryIncharge'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-nursery-incharge-delete-popup',
    template: ''
})
export class NurseryInchargeDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ nurseryIncharge }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(NurseryInchargeDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.nurseryIncharge = nurseryIncharge;
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
