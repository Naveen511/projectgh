/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { MapSectorWithZonalDetailComponent } from 'app/entities/map-sector-with-zonal/map-sector-with-zonal-detail.component';
import { MapSectorWithZonal } from 'app/shared/model/map-sector-with-zonal.model';

describe('Component Tests', () => {
    describe('MapSectorWithZonal Management Detail Component', () => {
        let comp: MapSectorWithZonalDetailComponent;
        let fixture: ComponentFixture<MapSectorWithZonalDetailComponent>;
        const route = ({ data: of({ mapSectorWithZonal: new MapSectorWithZonal(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [MapSectorWithZonalDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(MapSectorWithZonalDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MapSectorWithZonalDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.mapSectorWithZonal).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
