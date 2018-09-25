import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IZonalIncharge } from 'app/shared/model/zonal-incharge.model';
import { ZonalInchargeService } from './zonal-incharge.service';

@Component({
    selector: 'jhi-zonal-incharge-delete-dialog',
    templateUrl: './zonal-incharge-delete-dialog.component.html'
})
export class ZonalInchargeDeleteDialogComponent {
    zonalIncharge: IZonalIncharge;

    constructor(
        private zonalInchargeService: ZonalInchargeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.zonalInchargeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'zonalInchargeListModification',
                content: 'Deleted an zonalIncharge'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-zonal-incharge-delete-popup',
    template: ''
})
export class ZonalInchargeDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ zonalIncharge }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ZonalInchargeDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.zonalIncharge = zonalIncharge;
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
