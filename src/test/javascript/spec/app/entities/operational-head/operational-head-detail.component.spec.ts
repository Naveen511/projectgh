/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { OperationalHeadDetailComponent } from 'app/entities/operational-head/operational-head-detail.component';
import { OperationalHead } from 'app/shared/model/operational-head.model';

describe('Component Tests', () => {
    describe('OperationalHead Management Detail Component', () => {
        let comp: OperationalHeadDetailComponent;
        let fixture: ComponentFixture<OperationalHeadDetailComponent>;
        const route = ({ data: of({ operationalHead: new OperationalHead(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [OperationalHeadDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(OperationalHeadDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(OperationalHeadDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.operationalHead).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
