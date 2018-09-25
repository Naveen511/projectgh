import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMapSectorWithZonal } from 'app/shared/model/map-sector-with-zonal.model';
import { MapSectorWithZonalService } from './map-sector-with-zonal.service';

@Component({
    selector: 'jhi-map-sector-with-zonal-delete-dialog',
    templateUrl: './map-sector-with-zonal-delete-dialog.component.html'
})
export class MapSectorWithZonalDeleteDialogComponent {
    mapSectorWithZonal: IMapSectorWithZonal;

    constructor(
        private mapSectorWithZonalService: MapSectorWithZonalService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.mapSectorWithZonalService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'mapSectorWithZonalListModification',
                content: 'Deleted an mapSectorWithZonal'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-map-sector-with-zonal-delete-popup',
    template: ''
})
export class MapSectorWithZonalDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ mapSectorWithZonal }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MapSectorWithZonalDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.mapSectorWithZonal = mapSectorWithZonal;
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
