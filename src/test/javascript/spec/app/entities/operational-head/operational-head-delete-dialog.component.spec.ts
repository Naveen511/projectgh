/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ProjectghTestModule } from '../../../test.module';
import { OperationalHeadDeleteDialogComponent } from 'app/entities/operational-head/operational-head-delete-dialog.component';
import { OperationalHeadService } from 'app/entities/operational-head/operational-head.service';

describe('Component Tests', () => {
    describe('OperationalHead Management Delete Component', () => {
        let comp: OperationalHeadDeleteDialogComponent;
        let fixture: ComponentFixture<OperationalHeadDeleteDialogComponent>;
        let service: OperationalHeadService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [OperationalHeadDeleteDialogComponent]
            })
                .overrideTemplate(OperationalHeadDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(OperationalHeadDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OperationalHeadService);
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
