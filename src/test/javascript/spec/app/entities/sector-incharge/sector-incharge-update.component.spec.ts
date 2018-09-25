/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { SectorInchargeUpdateComponent } from 'app/entities/sector-incharge/sector-incharge-update.component';
import { SectorInchargeService } from 'app/entities/sector-incharge/sector-incharge.service';
import { SectorIncharge } from 'app/shared/model/sector-incharge.model';

describe('Component Tests', () => {
    describe('SectorIncharge Management Update Component', () => {
        let comp: SectorInchargeUpdateComponent;
        let fixture: ComponentFixture<SectorInchargeUpdateComponent>;
        let service: SectorInchargeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [SectorInchargeUpdateComponent]
            })
                .overrideTemplate(SectorInchargeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SectorInchargeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SectorInchargeService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new SectorIncharge(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.sectorIncharge = entity;
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
                    const entity = new SectorIncharge();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.sectorIncharge = entity;
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
