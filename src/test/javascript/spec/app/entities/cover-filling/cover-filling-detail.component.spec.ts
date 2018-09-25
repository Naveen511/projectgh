/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { CoverFillingDetailComponent } from 'app/entities/cover-filling/cover-filling-detail.component';
import { CoverFilling } from 'app/shared/model/cover-filling.model';

describe('Component Tests', () => {
    describe('CoverFilling Management Detail Component', () => {
        let comp: CoverFillingDetailComponent;
        let fixture: ComponentFixture<CoverFillingDetailComponent>;
        const route = ({ data: of({ coverFilling: new CoverFilling(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [CoverFillingDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CoverFillingDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CoverFillingDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.coverFilling).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
