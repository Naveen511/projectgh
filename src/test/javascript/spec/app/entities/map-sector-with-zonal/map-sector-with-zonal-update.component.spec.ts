/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { MapSectorWithZonalUpdateComponent } from 'app/entities/map-sector-with-zonal/map-sector-with-zonal-update.component';
import { MapSectorWithZonalService } from 'app/entities/map-sector-with-zonal/map-sector-with-zonal.service';
import { MapSectorWithZonal } from 'app/shared/model/map-sector-with-zonal.model';

describe('Component Tests', () => {
    describe('MapSectorWithZonal Management Update Component', () => {
        let comp: MapSectorWithZonalUpdateComponent;
        let fixture: ComponentFixture<MapSectorWithZonalUpdateComponent>;
        let service: MapSectorWithZonalService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [MapSectorWithZonalUpdateComponent]
            })
                .overrideTemplate(MapSectorWithZonalUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MapSectorWithZonalUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MapSectorWithZonalService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new MapSectorWithZonal(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.mapSectorWithZonal = entity;
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
                    const entity = new MapSectorWithZonal();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.mapSectorWithZonal = entity;
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
