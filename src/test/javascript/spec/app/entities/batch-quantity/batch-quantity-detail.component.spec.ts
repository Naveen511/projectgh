/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { BatchQuantityDetailComponent } from 'app/entities/batch-quantity/batch-quantity-detail.component';
import { BatchQuantity } from 'app/shared/model/batch-quantity.model';

describe('Component Tests', () => {
    describe('BatchQuantity Management Detail Component', () => {
        let comp: BatchQuantityDetailComponent;
        let fixture: ComponentFixture<BatchQuantityDetailComponent>;
        const route = ({ data: of({ batchQuantity: new BatchQuantity(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [BatchQuantityDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(BatchQuantityDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BatchQuantityDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.batchQuantity).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
