import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGodown } from 'app/shared/model/godown.model';
import { GodownService } from './godown.service';

@Component({
    selector: 'jhi-godown-delete-dialog',
    templateUrl: './godown-delete-dialog.component.html'
})
export class GodownDeleteDialogComponent {
    godown: IGodown;

    constructor(private godownService: GodownService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.godownService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'godownListModification',
                content: 'Deleted an godown'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-godown-delete-popup',
    template: ''
})
export class GodownDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ godown }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(GodownDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.godown = godown;
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
