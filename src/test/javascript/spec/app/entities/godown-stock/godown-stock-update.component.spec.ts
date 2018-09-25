/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { GodownStockUpdateComponent } from 'app/entities/godown-stock/godown-stock-update.component';
import { GodownStockService } from 'app/entities/godown-stock/godown-stock.service';
import { GodownStock } from 'app/shared/model/godown-stock.model';

describe('Component Tests', () => {
    describe('GodownStock Management Update Component', () => {
        let comp: GodownStockUpdateComponent;
        let fixture: ComponentFixture<GodownStockUpdateComponent>;
        let service: GodownStockService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [GodownStockUpdateComponent]
            })
                .overrideTemplate(GodownStockUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(GodownStockUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GodownStockService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new GodownStock(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.godownStock = entity;
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
                    const entity = new GodownStock();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.godownStock = entity;
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
