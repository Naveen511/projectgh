import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IShadeArea } from 'app/shared/model/shade-area.model';
import { ShadeAreaService } from './shade-area.service';

@Component({
    selector: 'jhi-shade-area-delete-dialog',
    templateUrl: './shade-area-delete-dialog.component.html'
})
export class ShadeAreaDeleteDialogComponent {
    shadeArea: IShadeArea;

    constructor(private shadeAreaService: ShadeAreaService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.shadeAreaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'shadeAreaListModification',
                content: 'Deleted an shadeArea'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-shade-area-delete-popup',
    template: ''
})
export class ShadeAreaDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ shadeArea }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ShadeAreaDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.shadeArea = shadeArea;
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
