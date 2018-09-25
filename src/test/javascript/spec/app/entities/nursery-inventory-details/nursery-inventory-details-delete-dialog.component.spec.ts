/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ProjectghTestModule } from '../../../test.module';
import { NurseryInventoryDetailsDeleteDialogComponent } from 'app/entities/nursery-inventory-details/nursery-inventory-details-delete-dialog.component';
import { NurseryInventoryDetailsService } from 'app/entities/nursery-inventory-details/nursery-inventory-details.service';

describe('Component Tests', () => {
    describe('NurseryInventoryDetails Management Delete Component', () => {
        let comp: NurseryInventoryDetailsDeleteDialogComponent;
        let fixture: ComponentFixture<NurseryInventoryDetailsDeleteDialogComponent>;
        let service: NurseryInventoryDetailsService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [NurseryInventoryDetailsDeleteDialogComponent]
            })
                .overrideTemplate(NurseryInventoryDetailsDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(NurseryInventoryDetailsDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NurseryInventoryDetailsService);
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
