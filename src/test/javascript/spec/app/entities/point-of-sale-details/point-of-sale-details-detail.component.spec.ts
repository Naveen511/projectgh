/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { PointOfSaleDetailsDetailComponent } from 'app/entities/point-of-sale-details/point-of-sale-details-detail.component';
import { PointOfSaleDetails } from 'app/shared/model/point-of-sale-details.model';

describe('Component Tests', () => {
    describe('PointOfSaleDetails Management Detail Component', () => {
        let comp: PointOfSaleDetailsDetailComponent;
        let fixture: ComponentFixture<PointOfSaleDetailsDetailComponent>;
        const route = ({ data: of({ pointOfSaleDetails: new PointOfSaleDetails(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [PointOfSaleDetailsDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PointOfSaleDetailsDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PointOfSaleDetailsDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.pointOfSaleDetails).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
