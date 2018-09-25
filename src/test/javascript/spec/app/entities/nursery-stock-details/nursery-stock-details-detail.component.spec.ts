/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { NurseryStockDetailsDetailComponent } from 'app/entities/nursery-stock-details/nursery-stock-details-detail.component';
import { NurseryStockDetails } from 'app/shared/model/nursery-stock-details.model';

describe('Component Tests', () => {
    describe('NurseryStockDetails Management Detail Component', () => {
        let comp: NurseryStockDetailsDetailComponent;
        let fixture: ComponentFixture<NurseryStockDetailsDetailComponent>;
        const route = ({ data: of({ nurseryStockDetails: new NurseryStockDetails(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [NurseryStockDetailsDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(NurseryStockDetailsDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(NurseryStockDetailsDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.nurseryStockDetails).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
