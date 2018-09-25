/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { SectorInchargeDetailComponent } from 'app/entities/sector-incharge/sector-incharge-detail.component';
import { SectorIncharge } from 'app/shared/model/sector-incharge.model';

describe('Component Tests', () => {
    describe('SectorIncharge Management Detail Component', () => {
        let comp: SectorInchargeDetailComponent;
        let fixture: ComponentFixture<SectorInchargeDetailComponent>;
        const route = ({ data: of({ sectorIncharge: new SectorIncharge(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [SectorInchargeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SectorInchargeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SectorInchargeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sectorIncharge).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
