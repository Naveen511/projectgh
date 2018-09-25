/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ProjectghTestModule } from '../../../test.module';
import { NurseryInventoryDeleteDialogComponent } from 'app/entities/nursery-inventory/nursery-inventory-delete-dialog.component';
import { NurseryInventoryService } from 'app/entities/nursery-inventory/nursery-inventory.service';

describe('Component Tests', () => {
    describe('NurseryInventory Management Delete Component', () => {
        let comp: NurseryInventoryDeleteDialogComponent;
        let fixture: ComponentFixture<NurseryInventoryDeleteDialogComponent>;
        let service: NurseryInventoryService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [NurseryInventoryDeleteDialogComponent]
            })
                .overrideTemplate(NurseryInventoryDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(NurseryInventoryDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NurseryInventoryService);
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
