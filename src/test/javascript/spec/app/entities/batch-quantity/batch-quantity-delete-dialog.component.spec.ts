/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ProjectghTestModule } from '../../../test.module';
import { BatchQuantityDeleteDialogComponent } from 'app/entities/batch-quantity/batch-quantity-delete-dialog.component';
import { BatchQuantityService } from 'app/entities/batch-quantity/batch-quantity.service';

describe('Component Tests', () => {
    describe('BatchQuantity Management Delete Component', () => {
        let comp: BatchQuantityDeleteDialogComponent;
        let fixture: ComponentFixture<BatchQuantityDeleteDialogComponent>;
        let service: BatchQuantityService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [BatchQuantityDeleteDialogComponent]
            })
                .overrideTemplate(BatchQuantityDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BatchQuantityDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BatchQuantityService);
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
