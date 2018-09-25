import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPickListValue } from 'app/shared/model/pick-list-value.model';
import { PickListValueService } from './pick-list-value.service';

@Component({
    selector: 'jhi-pick-list-value-delete-dialog',
    templateUrl: './pick-list-value-delete-dialog.component.html'
})
export class PickListValueDeleteDialogComponent {
    pickListValue: IPickListValue;

    constructor(
        private pickListValueService: PickListValueService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.pickListValueService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'pickListValueListModification',
                content: 'Deleted an pickListValue'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-pick-list-value-delete-popup',
    template: ''
})
export class PickListValueDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ pickListValue }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PickListValueDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.pickListValue = pickListValue;
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
