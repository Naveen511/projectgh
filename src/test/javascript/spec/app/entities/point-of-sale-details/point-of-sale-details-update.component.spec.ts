/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { PointOfSaleDetailsUpdateComponent } from 'app/entities/point-of-sale-details/point-of-sale-details-update.component';
import { PointOfSaleDetailsService } from 'app/entities/point-of-sale-details/point-of-sale-details.service';
import { PointOfSaleDetails } from 'app/shared/model/point-of-sale-details.model';

describe('Component Tests', () => {
    describe('PointOfSaleDetails Management Update Component', () => {
        let comp: PointOfSaleDetailsUpdateComponent;
        let fixture: ComponentFixture<PointOfSaleDetailsUpdateComponent>;
        let service: PointOfSaleDetailsService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [PointOfSaleDetailsUpdateComponent]
            })
                .overrideTemplate(PointOfSaleDetailsUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PointOfSaleDetailsUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PointOfSaleDetailsService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new PointOfSaleDetails(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.pointOfSaleDetails = entity;
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
                    const entity = new PointOfSaleDetails();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.pointOfSaleDetails = entity;
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
