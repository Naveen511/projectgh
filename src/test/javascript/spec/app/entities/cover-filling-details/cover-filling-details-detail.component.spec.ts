/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { CoverFillingDetailsDetailComponent } from 'app/entities/cover-filling-details/cover-filling-details-detail.component';
import { CoverFillingDetails } from 'app/shared/model/cover-filling-details.model';

describe('Component Tests', () => {
    describe('CoverFillingDetails Management Detail Component', () => {
        let comp: CoverFillingDetailsDetailComponent;
        let fixture: ComponentFixture<CoverFillingDetailsDetailComponent>;
        const route = ({ data: of({ coverFillingDetails: new CoverFillingDetails(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [CoverFillingDetailsDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CoverFillingDetailsDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CoverFillingDetailsDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.coverFillingDetails).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
