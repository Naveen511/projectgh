/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ProjectghTestModule } from '../../../test.module';
import { GodownStockDeleteDialogComponent } from 'app/entities/godown-stock/godown-stock-delete-dialog.component';
import { GodownStockService } from 'app/entities/godown-stock/godown-stock.service';

describe('Component Tests', () => {
    describe('GodownStock Management Delete Component', () => {
        let comp: GodownStockDeleteDialogComponent;
        let fixture: ComponentFixture<GodownStockDeleteDialogComponent>;
        let service: GodownStockService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [GodownStockDeleteDialogComponent]
            })
                .overrideTemplate(GodownStockDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(GodownStockDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GodownStockService);
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
