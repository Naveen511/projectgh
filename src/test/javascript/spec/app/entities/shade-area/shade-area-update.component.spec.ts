/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { ShadeAreaUpdateComponent } from 'app/entities/shade-area/shade-area-update.component';
import { ShadeAreaService } from 'app/entities/shade-area/shade-area.service';
import { ShadeArea } from 'app/shared/model/shade-area.model';

describe('Component Tests', () => {
    describe('ShadeArea Management Update Component', () => {
        let comp: ShadeAreaUpdateComponent;
        let fixture: ComponentFixture<ShadeAreaUpdateComponent>;
        let service: ShadeAreaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [ShadeAreaUpdateComponent]
            })
                .overrideTemplate(ShadeAreaUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ShadeAreaUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ShadeAreaService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ShadeArea(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.shadeArea = entity;
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
                    const entity = new ShadeArea();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.shadeArea = entity;
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
