/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ProjectghTestModule } from '../../../test.module';
import { SectorInchargeDeleteDialogComponent } from 'app/entities/sector-incharge/sector-incharge-delete-dialog.component';
import { SectorInchargeService } from 'app/entities/sector-incharge/sector-incharge.service';

describe('Component Tests', () => {
    describe('SectorIncharge Management Delete Component', () => {
        let comp: SectorInchargeDeleteDialogComponent;
        let fixture: ComponentFixture<SectorInchargeDeleteDialogComponent>;
        let service: SectorInchargeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [SectorInchargeDeleteDialogComponent]
            })
                .overrideTemplate(SectorInchargeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SectorInchargeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SectorInchargeService);
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
