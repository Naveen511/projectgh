/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ProjectghTestModule } from '../../../test.module';
import { PickListValueDeleteDialogComponent } from 'app/entities/pick-list-value/pick-list-value-delete-dialog.component';
import { PickListValueService } from 'app/entities/pick-list-value/pick-list-value.service';

describe('Component Tests', () => {
    describe('PickListValue Management Delete Component', () => {
        let comp: PickListValueDeleteDialogComponent;
        let fixture: ComponentFixture<PickListValueDeleteDialogComponent>;
        let service: PickListValueService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [PickListValueDeleteDialogComponent]
            })
                .overrideTemplate(PickListValueDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PickListValueDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PickListValueService);
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
