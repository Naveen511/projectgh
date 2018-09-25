/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { GodownUpdateComponent } from 'app/entities/godown/godown-update.component';
import { GodownService } from 'app/entities/godown/godown.service';
import { Godown } from 'app/shared/model/godown.model';

describe('Component Tests', () => {
    describe('Godown Management Update Component', () => {
        let comp: GodownUpdateComponent;
        let fixture: ComponentFixture<GodownUpdateComponent>;
        let service: GodownService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [GodownUpdateComponent]
            })
                .overrideTemplate(GodownUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(GodownUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GodownService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Godown(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.godown = entity;
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
                    const entity = new Godown();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.godown = entity;
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
