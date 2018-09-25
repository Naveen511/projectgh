/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { DamageDetailComponent } from 'app/entities/damage/damage-detail.component';
import { Damage } from 'app/shared/model/damage.model';

describe('Component Tests', () => {
    describe('Damage Management Detail Component', () => {
        let comp: DamageDetailComponent;
        let fixture: ComponentFixture<DamageDetailComponent>;
        const route = ({ data: of({ damage: new Damage(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [DamageDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(DamageDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DamageDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.damage).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
