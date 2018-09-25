/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { PickListUpdateComponent } from 'app/entities/pick-list/pick-list-update.component';
import { PickListService } from 'app/entities/pick-list/pick-list.service';
import { PickList } from 'app/shared/model/pick-list.model';

describe('Component Tests', () => {
    describe('PickList Management Update Component', () => {
        let comp: PickListUpdateComponent;
        let fixture: ComponentFixture<PickListUpdateComponent>;
        let service: PickListService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [PickListUpdateComponent]
            })
                .overrideTemplate(PickListUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PickListUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PickListService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new PickList(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.pickList = entity;
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
                    const entity = new PickList();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.pickList = entity;
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
