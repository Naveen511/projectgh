/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { NurseryStockUpdateComponent } from 'app/entities/nursery-stock/nursery-stock-update.component';
import { NurseryStockService } from 'app/entities/nursery-stock/nursery-stock.service';
import { NurseryStock } from 'app/shared/model/nursery-stock.model';

describe('Component Tests', () => {
    describe('NurseryStock Management Update Component', () => {
        let comp: NurseryStockUpdateComponent;
        let fixture: ComponentFixture<NurseryStockUpdateComponent>;
        let service: NurseryStockService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [NurseryStockUpdateComponent]
            })
                .overrideTemplate(NurseryStockUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(NurseryStockUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NurseryStockService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new NurseryStock(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.nurseryStock = entity;
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
                    const entity = new NurseryStock();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.nurseryStock = entity;
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
