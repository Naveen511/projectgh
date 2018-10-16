/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { BatchQuantityUpdateComponent } from 'app/entities/batch-quantity/batch-quantity-update.component';
import { BatchQuantityService } from 'app/entities/batch-quantity/batch-quantity.service';
import { BatchQuantity } from 'app/shared/model/batch-quantity.model';

describe('Component Tests', () => {
    describe('BatchQuantity Management Update Component', () => {
        let comp: BatchQuantityUpdateComponent;
        let fixture: ComponentFixture<BatchQuantityUpdateComponent>;
        let service: BatchQuantityService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [BatchQuantityUpdateComponent]
            })
                .overrideTemplate(BatchQuantityUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(BatchQuantityUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BatchQuantityService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new BatchQuantity(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.batchQuantity = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new BatchQuantity();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.batchQuantity = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
