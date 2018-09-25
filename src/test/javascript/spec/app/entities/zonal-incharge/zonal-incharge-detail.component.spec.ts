/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { ZonalInchargeDetailComponent } from 'app/entities/zonal-incharge/zonal-incharge-detail.component';
import { ZonalIncharge } from 'app/shared/model/zonal-incharge.model';

describe('Component Tests', () => {
    describe('ZonalIncharge Management Detail Component', () => {
        let comp: ZonalInchargeDetailComponent;
        let fixture: ComponentFixture<ZonalInchargeDetailComponent>;
        const route = ({ data: of({ zonalIncharge: new ZonalIncharge(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [ZonalInchargeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ZonalInchargeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ZonalInchargeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.zonalIncharge).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
