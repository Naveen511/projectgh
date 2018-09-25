/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ProjectghTestModule } from '../../../test.module';
import { MapZonalWithOhDeleteDialogComponent } from 'app/entities/map-zonal-with-oh/map-zonal-with-oh-delete-dialog.component';
import { MapZonalWithOhService } from 'app/entities/map-zonal-with-oh/map-zonal-with-oh.service';

describe('Component Tests', () => {
    describe('MapZonalWithOh Management Delete Component', () => {
        let comp: MapZonalWithOhDeleteDialogComponent;
        let fixture: ComponentFixture<MapZonalWithOhDeleteDialogComponent>;
        let service: MapZonalWithOhService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [MapZonalWithOhDeleteDialogComponent]
            })
                .overrideTemplate(MapZonalWithOhDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MapZonalWithOhDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MapZonalWithOhService);
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
