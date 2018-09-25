/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { GodownPurchaseDetailsDetailComponent } from 'app/entities/godown-purchase-details/godown-purchase-details-detail.component';
import { GodownPurchaseDetails } from 'app/shared/model/godown-purchase-details.model';

describe('Component Tests', () => {
    describe('GodownPurchaseDetails Management Detail Component', () => {
        let comp: GodownPurchaseDetailsDetailComponent;
        let fixture: ComponentFixture<GodownPurchaseDetailsDetailComponent>;
        const route = ({ data: of({ godownPurchaseDetails: new GodownPurchaseDetails(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [GodownPurchaseDetailsDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(GodownPurchaseDetailsDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(GodownPurchaseDetailsDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.godownPurchaseDetails).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
