/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { CommonSettingsUpdateComponent } from 'app/entities/common-settings/common-settings-update.component';
import { CommonSettingsService } from 'app/entities/common-settings/common-settings.service';
import { CommonSettings } from 'app/shared/model/common-settings.model';

describe('Component Tests', () => {
    describe('CommonSettings Management Update Component', () => {
        let comp: CommonSettingsUpdateComponent;
        let fixture: ComponentFixture<CommonSettingsUpdateComponent>;
        let service: CommonSettingsService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [CommonSettingsUpdateComponent]
            })
                .overrideTemplate(CommonSettingsUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CommonSettingsUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CommonSettingsService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new CommonSettings(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.commonSettings = entity;
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
                    const entity = new CommonSettings();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.commonSettings = entity;
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
