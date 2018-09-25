/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { PickListValueDetailComponent } from 'app/entities/pick-list-value/pick-list-value-detail.component';
import { PickListValue } from 'app/shared/model/pick-list-value.model';

describe('Component Tests', () => {
    describe('PickListValue Management Detail Component', () => {
        let comp: PickListValueDetailComponent;
        let fixture: ComponentFixture<PickListValueDetailComponent>;
        const route = ({ data: of({ pickListValue: new PickListValue(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [PickListValueDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PickListValueDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PickListValueDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.pickListValue).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
