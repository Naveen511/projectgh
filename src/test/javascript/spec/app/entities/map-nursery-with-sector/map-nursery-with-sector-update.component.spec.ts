/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { MapNurseryWithSectorUpdateComponent } from 'app/entities/map-nursery-with-sector/map-nursery-with-sector-update.component';
import { MapNurseryWithSectorService } from 'app/entities/map-nursery-with-sector/map-nursery-with-sector.service';
import { MapNurseryWithSector } from 'app/shared/model/map-nursery-with-sector.model';

describe('Component Tests', () => {
    describe('MapNurseryWithSector Management Update Component', () => {
        let comp: MapNurseryWithSectorUpdateComponent;
        let fixture: ComponentFixture<MapNurseryWithSectorUpdateComponent>;
        let service: MapNurseryWithSectorService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [MapNurseryWithSectorUpdateComponent]
            })
                .overrideTemplate(MapNurseryWithSectorUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MapNurseryWithSectorUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MapNurseryWithSectorService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new MapNurseryWithSector(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.mapNurseryWithSector = entity;
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
                    const entity = new MapNurseryWithSector();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.mapNurseryWithSector = entity;
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
