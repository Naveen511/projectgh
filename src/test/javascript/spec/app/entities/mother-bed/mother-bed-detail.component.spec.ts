/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { MotherBedDetailComponent } from 'app/entities/mother-bed/mother-bed-detail.component';
import { MotherBed } from 'app/shared/model/mother-bed.model';

describe('Component Tests', () => {
    describe('MotherBed Management Detail Component', () => {
        let comp: MotherBedDetailComponent;
        let fixture: ComponentFixture<MotherBedDetailComponent>;
        const route = ({ data: of({ motherBed: new MotherBed(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [MotherBedDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(MotherBedDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MotherBedDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.motherBed).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
