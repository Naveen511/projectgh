/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ProjectghTestModule } from '../../../test.module';
import { FinancialYearSettingsDeleteDialogComponent } from 'app/entities/financial-year-settings/financial-year-settings-delete-dialog.component';
import { FinancialYearSettingsService } from 'app/entities/financial-year-settings/financial-year-settings.service';

describe('Component Tests', () => {
    describe('FinancialYearSettings Management Delete Component', () => {
        let comp: FinancialYearSettingsDeleteDialogComponent;
        let fixture: ComponentFixture<FinancialYearSettingsDeleteDialogComponent>;
        let service: FinancialYearSettingsService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [FinancialYearSettingsDeleteDialogComponent]
            })
                .overrideTemplate(FinancialYearSettingsDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(FinancialYearSettingsDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FinancialYearSettingsService);
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
