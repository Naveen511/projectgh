/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { NurseryDetailComponent } from 'app/entities/nursery/nursery-detail.component';
import { Nursery } from 'app/shared/model/nursery.model';

describe('Component Tests', () => {
    describe('Nursery Management Detail Component', () => {
        let comp: NurseryDetailComponent;
        let fixture: ComponentFixture<NurseryDetailComponent>;
        const route = ({ data: of({ nursery: new Nursery(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [NurseryDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(NurseryDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(NurseryDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.nursery).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
