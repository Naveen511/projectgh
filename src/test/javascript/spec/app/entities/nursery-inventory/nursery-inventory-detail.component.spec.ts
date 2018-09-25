/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { NurseryInventoryDetailComponent } from 'app/entities/nursery-inventory/nursery-inventory-detail.component';
import { NurseryInventory } from 'app/shared/model/nursery-inventory.model';

describe('Component Tests', () => {
    describe('NurseryInventory Management Detail Component', () => {
        let comp: NurseryInventoryDetailComponent;
        let fixture: ComponentFixture<NurseryInventoryDetailComponent>;
        const route = ({ data: of({ nurseryInventory: new NurseryInventory(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [NurseryInventoryDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(NurseryInventoryDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(NurseryInventoryDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.nurseryInventory).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
