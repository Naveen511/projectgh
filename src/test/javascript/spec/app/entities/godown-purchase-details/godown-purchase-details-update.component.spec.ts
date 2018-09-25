/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ProjectghTestModule } from '../../../test.module';
import { GodownPurchaseDetailsUpdateComponent } from 'app/entities/godown-purchase-details/godown-purchase-details-update.component';
import { GodownPurchaseDetailsService } from 'app/entities/godown-purchase-details/godown-purchase-details.service';
import { GodownPurchaseDetails } from 'app/shared/model/godown-purchase-details.model';

describe('Component Tests', () => {
    describe('GodownPurchaseDetails Management Update Component', () => {
        let comp: GodownPurchaseDetailsUpdateComponent;
        let fixture: ComponentFixture<GodownPurchaseDetailsUpdateComponent>;
        let service: GodownPurchaseDetailsService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectghTestModule],
                declarations: [GodownPurchaseDetailsUpdateComponent]
            })
                .overrideTemplate(GodownPurchaseDetailsUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(GodownPurchaseDetailsUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GodownPurchaseDetailsService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new GodownPurchaseDetails(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.godownPurchaseDetails = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new GodownPurchaseDetails();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.godownPurchaseDetails = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
