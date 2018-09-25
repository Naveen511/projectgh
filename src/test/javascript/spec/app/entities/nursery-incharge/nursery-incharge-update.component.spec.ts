/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { NurseryInchargeUpdateComponent } from 'app/entities/nursery-incharge/nursery-incharge-update.component';
import { NurseryInchargeService } from 'app/entities/nursery-incharge/nursery-incharge.service';
import { NurseryIncharge } from 'app/shared/model/nursery-incharge.model';

describe('Component Tests', () => {
    describe('NurseryIncharge Management Update Component', () => {
        let comp: NurseryInchargeUpdateComponent;
        let fixture: ComponentFixture<NurseryInchargeUpdateComponent>;
        let service: NurseryInchargeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [NurseryInchargeUpdateComponent]
            })
                .overrideTemplate(NurseryInchargeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(NurseryInchargeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NurseryInchargeService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new NurseryIncharge(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.nurseryIncharge = entity;
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
                    const entity = new NurseryIncharge();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.nurseryIncharge = entity;
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
