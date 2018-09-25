/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { GodownDetailComponent } from 'app/entities/godown/godown-detail.component';
import { Godown } from 'app/shared/model/godown.model';

describe('Component Tests', () => {
    describe('Godown Management Detail Component', () => {
        let comp: GodownDetailComponent;
        let fixture: ComponentFixture<GodownDetailComponent>;
        const route = ({ data: of({ godown: new Godown(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [GodownDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(GodownDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(GodownDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.godown).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
