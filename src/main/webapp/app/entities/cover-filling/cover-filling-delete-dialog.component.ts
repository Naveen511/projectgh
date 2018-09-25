import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICoverFilling } from 'app/shared/model/cover-filling.model';
import { CoverFillingService } from './cover-filling.service';

@Component({
    selector: 'jhi-cover-filling-delete-dialog',
    templateUrl: './cover-filling-delete-dialog.component.html'
})
export class CoverFillingDeleteDialogComponent {
    coverFilling: ICoverFilling;

    constructor(
        private coverFillingService: CoverFillingService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.coverFillingService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'coverFillingListModification',
                content: 'Deleted an coverFilling'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-cover-filling-delete-popup',
    template: ''
})
export class CoverFillingDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ coverFilling }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CoverFillingDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.coverFilling = coverFilling;
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
