/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { MapZonalWithOhUpdateComponent } from 'app/entities/map-zonal-with-oh/map-zonal-with-oh-update.component';
import { MapZonalWithOhService } from 'app/entities/map-zonal-with-oh/map-zonal-with-oh.service';
import { MapZonalWithOh } from 'app/shared/model/map-zonal-with-oh.model';

describe('Component Tests', () => {
    describe('MapZonalWithOh Management Update Component', () => {
        let comp: MapZonalWithOhUpdateComponent;
        let fixture: ComponentFixture<MapZonalWithOhUpdateComponent>;
        let service: MapZonalWithOhService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [MapZonalWithOhUpdateComponent]
            })
                .overrideTemplate(MapZonalWithOhUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MapZonalWithOhUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MapZonalWithOhService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new MapZonalWithOh(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.mapZonalWithOh = entity;
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
                    const entity = new MapZonalWithOh();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.mapZonalWithOh = entity;
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
