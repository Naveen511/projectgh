/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { NurseryInventoryDetailsUpdateComponent } from 'app/entities/nursery-inventory-details/nursery-inventory-details-update.component';
import { NurseryInventoryDetailsService } from 'app/entities/nursery-inventory-details/nursery-inventory-details.service';
import { NurseryInventoryDetails } from 'app/shared/model/nursery-inventory-details.model';

describe('Component Tests', () => {
    describe('NurseryInventoryDetails Management Update Component', () => {
        let comp: NurseryInventoryDetailsUpdateComponent;
        let fixture: ComponentFixture<NurseryInventoryDetailsUpdateComponent>;
        let service: NurseryInventoryDetailsService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [NurseryInventoryDetailsUpdateComponent]
            })
                .overrideTemplate(NurseryInventoryDetailsUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(NurseryInventoryDetailsUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NurseryInventoryDetailsService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new NurseryInventoryDetails(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.nurseryInventoryDetails = entity;
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
                    const entity = new NurseryInventoryDetails();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.nurseryInventoryDetails = entity;
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
