/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { CoverFillingDetailsUpdateComponent } from 'app/entities/cover-filling-details/cover-filling-details-update.component';
import { CoverFillingDetailsService } from 'app/entities/cover-filling-details/cover-filling-details.service';
import { CoverFillingDetails } from 'app/shared/model/cover-filling-details.model';

describe('Component Tests', () => {
    describe('CoverFillingDetails Management Update Component', () => {
        let comp: CoverFillingDetailsUpdateComponent;
        let fixture: ComponentFixture<CoverFillingDetailsUpdateComponent>;
        let service: CoverFillingDetailsService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [CoverFillingDetailsUpdateComponent]
            })
                .overrideTemplate(CoverFillingDetailsUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CoverFillingDetailsUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CoverFillingDetailsService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new CoverFillingDetails(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.coverFillingDetails = entity;
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
                    const entity = new CoverFillingDetails();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.coverFillingDetails = entity;
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
