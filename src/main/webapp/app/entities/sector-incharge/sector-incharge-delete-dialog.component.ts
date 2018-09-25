import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISectorIncharge } from 'app/shared/model/sector-incharge.model';
import { SectorInchargeService } from './sector-incharge.service';

@Component({
    selector: 'jhi-sector-incharge-delete-dialog',
    templateUrl: './sector-incharge-delete-dialog.component.html'
})
export class SectorInchargeDeleteDialogComponent {
    sectorIncharge: ISectorIncharge;

    constructor(
        private sectorInchargeService: SectorInchargeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sectorInchargeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sectorInchargeListModification',
                content: 'Deleted an sectorIncharge'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sector-incharge-delete-popup',
    template: ''
})
export class SectorInchargeDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sectorIncharge }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SectorInchargeDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.sectorIncharge = sectorIncharge;
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
