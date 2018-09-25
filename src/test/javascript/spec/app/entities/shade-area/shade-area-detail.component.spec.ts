/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { ShadeAreaDetailComponent } from 'app/entities/shade-area/shade-area-detail.component';
import { ShadeArea } from 'app/shared/model/shade-area.model';

describe('Component Tests', () => {
    describe('ShadeArea Management Detail Component', () => {
        let comp: ShadeAreaDetailComponent;
        let fixture: ComponentFixture<ShadeAreaDetailComponent>;
        const route = ({ data: of({ shadeArea: new ShadeArea(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [ShadeAreaDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ShadeAreaDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ShadeAreaDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.shadeArea).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
