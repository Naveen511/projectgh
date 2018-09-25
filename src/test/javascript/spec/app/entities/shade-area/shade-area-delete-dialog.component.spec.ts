/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ProjectghTestModule } from '../../../test.module';
import { ShadeAreaDeleteDialogComponent } from 'app/entities/shade-area/shade-area-delete-dialog.component';
import { ShadeAreaService } from 'app/entities/shade-area/shade-area.service';

describe('Component Tests', () => {
    describe('ShadeArea Management Delete Component', () => {
        let comp: ShadeAreaDeleteDialogComponent;
        let fixture: ComponentFixture<ShadeAreaDeleteDialogComponent>;
        let service: ShadeAreaService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [ShadeAreaDeleteDialogComponent]
            })
                .overrideTemplate(ShadeAreaDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ShadeAreaDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ShadeAreaService);
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
