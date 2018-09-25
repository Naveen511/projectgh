/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { FinancialYearSettingsUpdateComponent } from 'app/entities/financial-year-settings/financial-year-settings-update.component';
import { FinancialYearSettingsService } from 'app/entities/financial-year-settings/financial-year-settings.service';
import { FinancialYearSettings } from 'app/shared/model/financial-year-settings.model';

describe('Component Tests', () => {
    describe('FinancialYearSettings Management Update Component', () => {
        let comp: FinancialYearSettingsUpdateComponent;
        let fixture: ComponentFixture<FinancialYearSettingsUpdateComponent>;
        let service: FinancialYearSettingsService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [FinancialYearSettingsUpdateComponent]
            })
                .overrideTemplate(FinancialYearSettingsUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(FinancialYearSettingsUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FinancialYearSettingsService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new FinancialYearSettings(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.financialYearSettings = entity;
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
                    const entity = new FinancialYearSettings();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.financialYearSettings = entity;
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
