/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { NurseryStockDetailsUpdateComponent } from 'app/entities/nursery-stock-details/nursery-stock-details-update.component';
import { NurseryStockDetailsService } from 'app/entities/nursery-stock-details/nursery-stock-details.service';
import { NurseryStockDetails } from 'app/shared/model/nursery-stock-details.model';

describe('Component Tests', () => {
    describe('NurseryStockDetails Management Update Component', () => {
        let comp: NurseryStockDetailsUpdateComponent;
        let fixture: ComponentFixture<NurseryStockDetailsUpdateComponent>;
        let service: NurseryStockDetailsService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [NurseryStockDetailsUpdateComponent]
            })
                .overrideTemplate(NurseryStockDetailsUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(NurseryStockDetailsUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NurseryStockDetailsService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new NurseryStockDetails(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.nurseryStockDetails = entity;
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
                    const entity = new NurseryStockDetails();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.nurseryStockDetails = entity;
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
