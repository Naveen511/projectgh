/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { NurseryStockDetailComponent } from 'app/entities/nursery-stock/nursery-stock-detail.component';
import { NurseryStock } from 'app/shared/model/nursery-stock.model';

describe('Component Tests', () => {
    describe('NurseryStock Management Detail Component', () => {
        let comp: NurseryStockDetailComponent;
        let fixture: ComponentFixture<NurseryStockDetailComponent>;
        const route = ({ data: of({ nurseryStock: new NurseryStock(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [NurseryStockDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(NurseryStockDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(NurseryStockDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.nurseryStock).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
