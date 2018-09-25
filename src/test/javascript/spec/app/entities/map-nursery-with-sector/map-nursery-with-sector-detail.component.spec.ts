/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { MapNurseryWithSectorDetailComponent } from 'app/entities/map-nursery-with-sector/map-nursery-with-sector-detail.component';
import { MapNurseryWithSector } from 'app/shared/model/map-nursery-with-sector.model';

describe('Component Tests', () => {
    describe('MapNurseryWithSector Management Detail Component', () => {
        let comp: MapNurseryWithSectorDetailComponent;
        let fixture: ComponentFixture<MapNurseryWithSectorDetailComponent>;
        const route = ({ data: of({ mapNurseryWithSector: new MapNurseryWithSector(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [MapNurseryWithSectorDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(MapNurseryWithSectorDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MapNurseryWithSectorDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.mapNurseryWithSector).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
