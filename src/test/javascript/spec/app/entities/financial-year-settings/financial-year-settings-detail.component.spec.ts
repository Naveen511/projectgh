/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { FinancialYearSettingsDetailComponent } from 'app/entities/financial-year-settings/financial-year-settings-detail.component';
import { FinancialYearSettings } from 'app/shared/model/financial-year-settings.model';

describe('Component Tests', () => {
    describe('FinancialYearSettings Management Detail Component', () => {
        let comp: FinancialYearSettingsDetailComponent;
        let fixture: ComponentFixture<FinancialYearSettingsDetailComponent>;
        const route = ({ data: of({ financialYearSettings: new FinancialYearSettings(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [FinancialYearSettingsDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(FinancialYearSettingsDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(FinancialYearSettingsDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.financialYearSettings).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
