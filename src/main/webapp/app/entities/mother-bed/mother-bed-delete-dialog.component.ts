import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMotherBed } from 'app/shared/model/mother-bed.model';
import { MotherBedService } from './mother-bed.service';

@Component({
    selector: 'jhi-mother-bed-delete-dialog',
    templateUrl: './mother-bed-delete-dialog.component.html'
})
export class MotherBedDeleteDialogComponent {
    motherBed: IMotherBed;

    constructor(private motherBedService: MotherBedService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.motherBedService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'motherBedListModification',
                content: 'Deleted an motherBed'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-mother-bed-delete-popup',
    template: ''
})
export class MotherBedDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ motherBed }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MotherBedDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.motherBed = motherBed;
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
