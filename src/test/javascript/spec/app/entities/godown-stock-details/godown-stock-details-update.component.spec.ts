/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { GodownStockDetailsUpdateComponent } from 'app/entities/godown-stock-details/godown-stock-details-update.component';
import { GodownStockDetailsService } from 'app/entities/godown-stock-details/godown-stock-details.service';
import { GodownStockDetails } from 'app/shared/model/godown-stock-details.model';

describe('Component Tests', () => {
    describe('GodownStockDetails Management Update Component', () => {
        let comp: GodownStockDetailsUpdateComponent;
        let fixture: ComponentFixture<GodownStockDetailsUpdateComponent>;
        let service: GodownStockDetailsService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [GodownStockDetailsUpdateComponent]
            })
                .overrideTemplate(GodownStockDetailsUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(GodownStockDetailsUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GodownStockDetailsService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new GodownStockDetails(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.godownStockDetails = entity;
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
                    const entity = new GodownStockDetails();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.godownStockDetails = entity;
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
