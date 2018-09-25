/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { CommonSettingsDetailComponent } from 'app/entities/common-settings/common-settings-detail.component';
import { CommonSettings } from 'app/shared/model/common-settings.model';

describe('Component Tests', () => {
    describe('CommonSettings Management Detail Component', () => {
        let comp: CommonSettingsDetailComponent;
        let fixture: ComponentFixture<CommonSettingsDetailComponent>;
        const route = ({ data: of({ commonSettings: new CommonSettings(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [CommonSettingsDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CommonSettingsDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CommonSettingsDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.commonSettings).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
