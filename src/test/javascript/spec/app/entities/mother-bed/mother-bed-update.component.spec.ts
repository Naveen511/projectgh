/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { MotherBedUpdateComponent } from 'app/entities/mother-bed/mother-bed-update.component';
import { MotherBedService } from 'app/entities/mother-bed/mother-bed.service';
import { MotherBed } from 'app/shared/model/mother-bed.model';

describe('Component Tests', () => {
    describe('MotherBed Management Update Component', () => {
        let comp: MotherBedUpdateComponent;
        let fixture: ComponentFixture<MotherBedUpdateComponent>;
        let service: MotherBedService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [MotherBedUpdateComponent]
            })
                .overrideTemplate(MotherBedUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MotherBedUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MotherBedService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new MotherBed(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.motherBed = entity;
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
                    const entity = new MotherBed();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.motherBed = entity;
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
