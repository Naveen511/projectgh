import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICoverFillingDetails } from 'app/shared/model/cover-filling-details.model';
import { CoverFillingDetailsService } from './cover-filling-details.service';

@Component({
    selector: 'jhi-cover-filling-details-delete-dialog',
    templateUrl: './cover-filling-details-delete-dialog.component.html'
})
export class CoverFillingDetailsDeleteDialogComponent {
    coverFillingDetails: ICoverFillingDetails;

    constructor(
        private coverFillingDetailsService: CoverFillingDetailsService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.coverFillingDetailsService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'coverFillingDetailsListModification',
                content: 'Deleted an coverFillingDetails'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-cover-filling-details-delete-popup',
    template: ''
})
export class CoverFillingDetailsDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ coverFillingDetails }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CoverFillingDetailsDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.coverFillingDetails = coverFillingDetails;
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
