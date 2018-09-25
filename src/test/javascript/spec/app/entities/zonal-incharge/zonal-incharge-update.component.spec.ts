/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { ZonalInchargeUpdateComponent } from 'app/entities/zonal-incharge/zonal-incharge-update.component';
import { ZonalInchargeService } from 'app/entities/zonal-incharge/zonal-incharge.service';
import { ZonalIncharge } from 'app/shared/model/zonal-incharge.model';

describe('Component Tests', () => {
    describe('ZonalIncharge Management Update Component', () => {
        let comp: ZonalInchargeUpdateComponent;
        let fixture: ComponentFixture<ZonalInchargeUpdateComponent>;
        let service: ZonalInchargeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [ZonalInchargeUpdateComponent]
            })
                .overrideTemplate(ZonalInchargeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ZonalInchargeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ZonalInchargeService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ZonalIncharge(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.zonalIncharge = entity;
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
                    const entity = new ZonalIncharge();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.zonalIncharge = entity;
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
