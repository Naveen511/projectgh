import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMapNurseryWithSector } from 'app/shared/model/map-nursery-with-sector.model';
import { MapNurseryWithSectorService } from './map-nursery-with-sector.service';

@Component({
    selector: 'jhi-map-nursery-with-sector-delete-dialog',
    templateUrl: './map-nursery-with-sector-delete-dialog.component.html'
})
export class MapNurseryWithSectorDeleteDialogComponent {
    mapNurseryWithSector: IMapNurseryWithSector;

    constructor(
        private mapNurseryWithSectorService: MapNurseryWithSectorService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.mapNurseryWithSectorService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'mapNurseryWithSectorListModification',
                content: 'Deleted an mapNurseryWithSector'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-map-nursery-with-sector-delete-popup',
    template: ''
})
export class MapNurseryWithSectorDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ mapNurseryWithSector }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MapNurseryWithSectorDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.mapNurseryWithSector = mapNurseryWithSector;
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
