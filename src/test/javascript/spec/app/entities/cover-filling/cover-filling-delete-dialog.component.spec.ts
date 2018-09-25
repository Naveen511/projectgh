/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ProjectghTestModule } from '../../../test.module';
import { CoverFillingDeleteDialogComponent } from 'app/entities/cover-filling/cover-filling-delete-dialog.component';
import { CoverFillingService } from 'app/entities/cover-filling/cover-filling.service';

describe('Component Tests', () => {
    describe('CoverFilling Management Delete Component', () => {
        let comp: CoverFillingDeleteDialogComponent;
        let fixture: ComponentFixture<CoverFillingDeleteDialogComponent>;
        let service: CoverFillingService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [CoverFillingDeleteDialogComponent]
            })
                .overrideTemplate(CoverFillingDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CoverFillingDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CoverFillingService);
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
