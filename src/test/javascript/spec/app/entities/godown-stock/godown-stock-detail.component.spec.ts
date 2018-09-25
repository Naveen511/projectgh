/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { GodownStockDetailComponent } from 'app/entities/godown-stock/godown-stock-detail.component';
import { GodownStock } from 'app/shared/model/godown-stock.model';

describe('Component Tests', () => {
    describe('GodownStock Management Detail Component', () => {
        let comp: GodownStockDetailComponent;
        let fixture: ComponentFixture<GodownStockDetailComponent>;
        const route = ({ data: of({ godownStock: new GodownStock(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [GodownStockDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(GodownStockDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(GodownStockDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.godownStock).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
