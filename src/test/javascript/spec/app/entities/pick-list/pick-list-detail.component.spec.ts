/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { PickListDetailComponent } from 'app/entities/pick-list/pick-list-detail.component';
import { PickList } from 'app/shared/model/pick-list.model';

describe('Component Tests', () => {
    describe('PickList Management Detail Component', () => {
        let comp: PickListDetailComponent;
        let fixture: ComponentFixture<PickListDetailComponent>;
        const route = ({ data: of({ pickList: new PickList(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [PickListDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PickListDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PickListDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.pickList).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
