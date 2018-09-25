/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ProjectghTestModule } from '../../../test.module';
import { NurseryInchargeDeleteDialogComponent } from 'app/entities/nursery-incharge/nursery-incharge-delete-dialog.component';
import { NurseryInchargeService } from 'app/entities/nursery-incharge/nursery-incharge.service';

describe('Component Tests', () => {
    describe('NurseryIncharge Management Delete Component', () => {
        let comp: NurseryInchargeDeleteDialogComponent;
        let fixture: ComponentFixture<NurseryInchargeDeleteDialogComponent>;
        let service: NurseryInchargeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [NurseryInchargeDeleteDialogComponent]
            })
                .overrideTemplate(NurseryInchargeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(NurseryInchargeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NurseryInchargeService);
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
