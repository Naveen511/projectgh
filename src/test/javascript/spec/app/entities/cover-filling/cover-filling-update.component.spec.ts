/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { CoverFillingUpdateComponent } from 'app/entities/cover-filling/cover-filling-update.component';
import { CoverFillingService } from 'app/entities/cover-filling/cover-filling.service';
import { CoverFilling } from 'app/shared/model/cover-filling.model';

describe('Component Tests', () => {
    describe('CoverFilling Management Update Component', () => {
        let comp: CoverFillingUpdateComponent;
        let fixture: ComponentFixture<CoverFillingUpdateComponent>;
        let service: CoverFillingService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [CoverFillingUpdateComponent]
            })
                .overrideTemplate(CoverFillingUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CoverFillingUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CoverFillingService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new CoverFilling(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.coverFilling = entity;
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
                    const entity = new CoverFilling();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.coverFilling = entity;
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
