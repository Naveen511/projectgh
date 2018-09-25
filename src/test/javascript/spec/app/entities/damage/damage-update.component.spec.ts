/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { DamageUpdateComponent } from 'app/entities/damage/damage-update.component';
import { DamageService } from 'app/entities/damage/damage.service';
import { Damage } from 'app/shared/model/damage.model';

describe('Component Tests', () => {
    describe('Damage Management Update Component', () => {
        let comp: DamageUpdateComponent;
        let fixture: ComponentFixture<DamageUpdateComponent>;
        let service: DamageService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [DamageUpdateComponent]
            })
                .overrideTemplate(DamageUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DamageUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DamageService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Damage(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.damage = entity;
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
                    const entity = new Damage();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.damage = entity;
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
