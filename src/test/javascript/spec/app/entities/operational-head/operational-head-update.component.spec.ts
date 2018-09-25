/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { OperationalHeadUpdateComponent } from 'app/entities/operational-head/operational-head-update.component';
import { OperationalHeadService } from 'app/entities/operational-head/operational-head.service';
import { OperationalHead } from 'app/shared/model/operational-head.model';

describe('Component Tests', () => {
    describe('OperationalHead Management Update Component', () => {
        let comp: OperationalHeadUpdateComponent;
        let fixture: ComponentFixture<OperationalHeadUpdateComponent>;
        let service: OperationalHeadService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [OperationalHeadUpdateComponent]
            })
                .overrideTemplate(OperationalHeadUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(OperationalHeadUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OperationalHeadService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new OperationalHead(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.operationalHead = entity;
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
                    const entity = new OperationalHead();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.operationalHead = entity;
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
