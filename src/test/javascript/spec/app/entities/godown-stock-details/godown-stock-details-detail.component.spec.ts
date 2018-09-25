/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { GodownStockDetailsDetailComponent } from 'app/entities/godown-stock-details/godown-stock-details-detail.component';
import { GodownStockDetails } from 'app/shared/model/godown-stock-details.model';

describe('Component Tests', () => {
    describe('GodownStockDetails Management Detail Component', () => {
        let comp: GodownStockDetailsDetailComponent;
        let fixture: ComponentFixture<GodownStockDetailsDetailComponent>;
        const route = ({ data: of({ godownStockDetails: new GodownStockDetails(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [GodownStockDetailsDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(GodownStockDetailsDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(GodownStockDetailsDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.godownStockDetails).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
