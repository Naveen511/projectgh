/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ProjectghTestModule } from '../../../test.module';
import { MotherBedDeleteDialogComponent } from 'app/entities/mother-bed/mother-bed-delete-dialog.component';
import { MotherBedService } from 'app/entities/mother-bed/mother-bed.service';

describe('Component Tests', () => {
    describe('MotherBed Management Delete Component', () => {
        let comp: MotherBedDeleteDialogComponent;
        let fixture: ComponentFixture<MotherBedDeleteDialogComponent>;
        let service: MotherBedService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [MotherBedDeleteDialogComponent]
            })
                .overrideTemplate(MotherBedDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MotherBedDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MotherBedService);
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
