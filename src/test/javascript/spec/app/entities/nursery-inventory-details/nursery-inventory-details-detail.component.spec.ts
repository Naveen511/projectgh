/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { NurseryInventoryDetailsDetailComponent } from 'app/entities/nursery-inventory-details/nursery-inventory-details-detail.component';
import { NurseryInventoryDetails } from 'app/shared/model/nursery-inventory-details.model';

describe('Component Tests', () => {
    describe('NurseryInventoryDetails Management Detail Component', () => {
        let comp: NurseryInventoryDetailsDetailComponent;
        let fixture: ComponentFixture<NurseryInventoryDetailsDetailComponent>;
        const route = ({ data: of({ nurseryInventoryDetails: new NurseryInventoryDetails(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [NurseryInventoryDetailsDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(NurseryInventoryDetailsDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(NurseryInventoryDetailsDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.nurseryInventoryDetails).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
