/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ProjectghTestModule } from '../../../test.module';
import { NurseryStockDetailsDeleteDialogComponent } from 'app/entities/nursery-stock-details/nursery-stock-details-delete-dialog.component';
import { NurseryStockDetailsService } from 'app/entities/nursery-stock-details/nursery-stock-details.service';

describe('Component Tests', () => {
    describe('NurseryStockDetails Management Delete Component', () => {
        let comp: NurseryStockDetailsDeleteDialogComponent;
        let fixture: ComponentFixture<NurseryStockDetailsDeleteDialogComponent>;
        let service: NurseryStockDetailsService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [NurseryStockDetailsDeleteDialogComponent]
            })
                .overrideTemplate(NurseryStockDetailsDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(NurseryStockDetailsDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NurseryStockDetailsService);
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
