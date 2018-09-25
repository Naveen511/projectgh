/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { MapZonalWithOhDetailComponent } from 'app/entities/map-zonal-with-oh/map-zonal-with-oh-detail.component';
import { MapZonalWithOh } from 'app/shared/model/map-zonal-with-oh.model';

describe('Component Tests', () => {
    describe('MapZonalWithOh Management Detail Component', () => {
        let comp: MapZonalWithOhDetailComponent;
        let fixture: ComponentFixture<MapZonalWithOhDetailComponent>;
        const route = ({ data: of({ mapZonalWithOh: new MapZonalWithOh(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [MapZonalWithOhDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(MapZonalWithOhDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MapZonalWithOhDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.mapZonalWithOh).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
