/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { NurseryInchargeDetailComponent } from 'app/entities/nursery-incharge/nursery-incharge-detail.component';
import { NurseryIncharge } from 'app/shared/model/nursery-incharge.model';

describe('Component Tests', () => {
    describe('NurseryIncharge Management Detail Component', () => {
        let comp: NurseryInchargeDetailComponent;
        let fixture: ComponentFixture<NurseryInchargeDetailComponent>;
        const route = ({ data: of({ nurseryIncharge: new NurseryIncharge(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [NurseryInchargeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(NurseryInchargeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(NurseryInchargeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.nurseryIncharge).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
