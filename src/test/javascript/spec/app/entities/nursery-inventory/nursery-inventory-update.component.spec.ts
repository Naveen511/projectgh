/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { NurseryInventoryUpdateComponent } from 'app/entities/nursery-inventory/nursery-inventory-update.component';
import { NurseryInventoryService } from 'app/entities/nursery-inventory/nursery-inventory.service';
import { NurseryInventory } from 'app/shared/model/nursery-inventory.model';

describe('Component Tests', () => {
    describe('NurseryInventory Management Update Component', () => {
        let comp: NurseryInventoryUpdateComponent;
        let fixture: ComponentFixture<NurseryInventoryUpdateComponent>;
        let service: NurseryInventoryService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [NurseryInventoryUpdateComponent]
            })
                .overrideTemplate(NurseryInventoryUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(NurseryInventoryUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NurseryInventoryService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new NurseryInventory(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.nurseryInventory = entity;
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
                    const entity = new NurseryInventory();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.nurseryInventory = entity;
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
