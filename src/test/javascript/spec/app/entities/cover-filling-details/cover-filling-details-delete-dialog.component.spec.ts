/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ProjectghTestModule } from '../../../test.module';
import { CoverFillingDetailsDeleteDialogComponent } from 'app/entities/cover-filling-details/cover-filling-details-delete-dialog.component';
import { CoverFillingDetailsService } from 'app/entities/cover-filling-details/cover-filling-details.service';

describe('Component Tests', () => {
    describe('CoverFillingDetails Management Delete Component', () => {
        let comp: CoverFillingDetailsDeleteDialogComponent;
        let fixture: ComponentFixture<CoverFillingDetailsDeleteDialogComponent>;
        let service: CoverFillingDetailsService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [CoverFillingDetailsDeleteDialogComponent]
            })
                .overrideTemplate(CoverFillingDetailsDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CoverFillingDetailsDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CoverFillingDetailsService);
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
