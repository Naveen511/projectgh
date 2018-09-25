/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ProjectghTestModule } from '../../../test.module';
import { GodownPurchaseDetailsDeleteDialogComponent } from 'app/entities/godown-purchase-details/godown-purchase-details-delete-dialog.component';
import { GodownPurchaseDetailsService } from 'app/entities/godown-purchase-details/godown-purchase-details.service';

describe('Component Tests', () => {
    describe('GodownPurchaseDetails Management Delete Component', () => {
        let comp: GodownPurchaseDetailsDeleteDialogComponent;
        let fixture: ComponentFixture<GodownPurchaseDetailsDeleteDialogComponent>;
        let service: GodownPurchaseDetailsService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [GodownPurchaseDetailsDeleteDialogComponent]
            })
                .overrideTemplate(GodownPurchaseDetailsDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(GodownPurchaseDetailsDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GodownPurchaseDetailsService);
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
