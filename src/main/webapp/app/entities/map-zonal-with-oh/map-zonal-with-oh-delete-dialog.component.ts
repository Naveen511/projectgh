import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMapZonalWithOh } from 'app/shared/model/map-zonal-with-oh.model';
import { MapZonalWithOhService } from './map-zonal-with-oh.service';

@Component({
    selector: 'jhi-map-zonal-with-oh-delete-dialog',
    templateUrl: './map-zonal-with-oh-delete-dialog.component.html'
})
export class MapZonalWithOhDeleteDialogComponent {
    mapZonalWithOh: IMapZonalWithOh;

    constructor(
        private mapZonalWithOhService: MapZonalWithOhService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.mapZonalWithOhService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'mapZonalWithOhListModification',
                content: 'Deleted an mapZonalWithOh'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-map-zonal-with-oh-delete-popup',
    template: ''
})
export class MapZonalWithOhDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ mapZonalWithOh }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MapZonalWithOhDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.mapZonalWithOh = mapZonalWithOh;
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
