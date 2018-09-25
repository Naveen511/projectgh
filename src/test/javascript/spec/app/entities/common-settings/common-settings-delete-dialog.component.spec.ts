/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ProjectghTestModule } from '../../../test.module';
import { CommonSettingsDeleteDialogComponent } from 'app/entities/common-settings/common-settings-delete-dialog.component';
import { CommonSettingsService } from 'app/entities/common-settings/common-settings.service';

describe('Component Tests', () => {
    describe('CommonSettings Management Delete Component', () => {
        let comp: CommonSettingsDeleteDialogComponent;
        let fixture: ComponentFixture<CommonSettingsDeleteDialogComponent>;
        let service: CommonSettingsService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [CommonSettingsDeleteDialogComponent]
            })
                .overrideTemplate(CommonSettingsDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CommonSettingsDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CommonSettingsService);
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
