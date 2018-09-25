/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ProjectghTestModule } from '../../../test.module';
import { MapNurseryWithSectorDeleteDialogComponent } from 'app/entities/map-nursery-with-sector/map-nursery-with-sector-delete-dialog.component';
import { MapNurseryWithSectorService } from 'app/entities/map-nursery-with-sector/map-nursery-with-sector.service';

describe('Component Tests', () => {
    describe('MapNurseryWithSector Management Delete Component', () => {
        let comp: MapNurseryWithSectorDeleteDialogComponent;
        let fixture: ComponentFixture<MapNurseryWithSectorDeleteDialogComponent>;
        let service: MapNurseryWithSectorService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [MapNurseryWithSectorDeleteDialogComponent]
            })
                .overrideTemplate(MapNurseryWithSectorDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MapNurseryWithSectorDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MapNurseryWithSectorService);
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
