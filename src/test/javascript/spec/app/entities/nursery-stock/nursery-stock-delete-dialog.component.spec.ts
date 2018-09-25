/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ProjectghTestModule } from '../../../test.module';
import { NurseryStockDeleteDialogComponent } from 'app/entities/nursery-stock/nursery-stock-delete-dialog.component';
import { NurseryStockService } from 'app/entities/nursery-stock/nursery-stock.service';

describe('Component Tests', () => {
    describe('NurseryStock Management Delete Component', () => {
        let comp: NurseryStockDeleteDialogComponent;
        let fixture: ComponentFixture<NurseryStockDeleteDialogComponent>;
        let service: NurseryStockService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [NurseryStockDeleteDialogComponent]
            })
                .overrideTemplate(NurseryStockDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(NurseryStockDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NurseryStockService);
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
