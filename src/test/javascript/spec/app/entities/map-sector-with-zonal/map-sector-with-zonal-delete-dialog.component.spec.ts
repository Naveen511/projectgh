/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ProjectghTestModule } from '../../../test.module';
import { MapSectorWithZonalDeleteDialogComponent } from 'app/entities/map-sector-with-zonal/map-sector-with-zonal-delete-dialog.component';
import { MapSectorWithZonalService } from 'app/entities/map-sector-with-zonal/map-sector-with-zonal.service';

describe('Component Tests', () => {
    describe('MapSectorWithZonal Management Delete Component', () => {
        let comp: MapSectorWithZonalDeleteDialogComponent;
        let fixture: ComponentFixture<MapSectorWithZonalDeleteDialogComponent>;
        let service: MapSectorWithZonalService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [MapSectorWithZonalDeleteDialogComponent]
            })
                .overrideTemplate(MapSectorWithZonalDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MapSectorWithZonalDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MapSectorWithZonalService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
