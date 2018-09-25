import { Component, OnInit, ViewChild } from '@angular/core';
import { PickListService } from 'app/entities/service/pick-list.service';
import { PickListValueService } from 'app/entities/service/pick-list-value.service';
import { ActivatedRoute, Router } from '@angular/router';

import { JhiParseLinks } from 'ng-jhipster';

import * as moment from 'moment';
import { DATE_TIME_FORMAT, ITEMS_PER_PAGE, STATUS_ACTIVE, SOFT_DELETE_STATUS } from 'app/shared';
import { HttpResponse, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PickListModel, IPickList } from 'app/shared/model/pick-list.model';
import { PickListValueModel, IPickListValue } from 'app/shared/model/pick-list-value.model';
import { ModalDirective } from 'ngx-bootstrap';
import { QuantityModel, IQuantity } from 'app/shared/model/quantity.model';
import { QuantityService } from 'app/entities/quantity';
import { IMotherBed, MotherBedModel } from 'app/shared/model/mother-bed.model';
import { MotherBedService } from 'app/entities/service/mother-bed.service';
import { ZonalService } from 'app/entities/service/zonal.service';
import { NurseryService } from 'app/entities/service/nursery.service';
import { SectorService } from 'app/entities/service/sector.service';
import { IZonal } from 'app/shared/model/zonal.model';
import { ISector } from 'app/shared/model/sector.model';
import { INursery } from 'app/shared/model/nursery.model';

// Display the alert message of success and error
import { Subject } from 'rxjs';
import { debounceTime } from 'rxjs/operators';

@Component({
    selector: 'jhi-picklist',
    templateUrl: 'pick-list.component.html'
})
export class PickListComponent implements OnInit {
    pickListObject: PickListModel = new PickListModel();
    pickListValueObject: PickListValueModel = new PickListModel();
    quantityObject: QuantityModel = new QuantityModel();
    motherBedObject: MotherBedModel = new MotherBedModel();
    // create empty array for each service
    pickLists: IPickList[];
    pickListValues: IPickListValue[];
    motherBedList: IMotherBed[];
    zonals: IZonal[];
    sectors: ISector[];
    nurserys: INursery[];

    // Quanity Table values
    variety: IPickListValue[];
    categorys: IPickListValue[];
    quantity: IQuantity[];

    // Title and alertTitle declation as String
    title: String;
    alertTitle: String;

    // To display the success message
    private success = new Subject<string>();
    successMessage: string;

    // To display the error message
    private error = new Subject<string>();
    errorMessage: string;

    // By default close the alert with statc time
    staticAlertClosed = false;

    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;
    filter: any;

    // View Modal pop up for create and update
    @ViewChild('pickListModal') public pickListModal: ModalDirective;
    @ViewChild('pickListValueModal') public pickListValueModal: ModalDirective;
    @ViewChild('subPickListModal') public subPickListModal: ModalDirective;
    @ViewChild('motherBedModal') public motherBedModal: ModalDirective;
    @ViewChild('quantityModal') public quantityModal: ModalDirective;

    constructor(
        private pickListService: PickListService,
        private pickListValueService: PickListValueService,
        private quantityService: QuantityService,
        private motherBedService: MotherBedService,
        private zonalService: ZonalService,
        private nurseryService: NurseryService,
        private sectorService: SectorService,
        private parseLinks: JhiParseLinks,
        private router: Router,
        private activatedRoute: ActivatedRoute
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            // console.log(data);
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
    }

    ngOnInit() {
        this.getPickList();
        this.getAllPickListValue();
        this.getMotherBedList();
        this.getZonalList();

        // To set the time for automatic alert close
        setTimeout(() => this.staticAlertClosed = true, 20000);

        // Set the success message with debounce time
        this.success.subscribe(message => this.successMessage = message);
        this.success.pipe(
            debounceTime(5000)
        ).subscribe(() => this.successMessage = null);

        // To set the error message with debounce time
        this.error.subscribe(message => this.errorMessage = message);
        this.error.pipe(
            debounceTime(5000)
        ).subscribe(() => this.errorMessage = null);
    }

    // Get picklist value from the picklist table
    getPickList(): void {
        // Get the list of pickList
        this.pickListService.query({
            filter: {'status.equals': STATUS_ACTIVE}
        }).subscribe((res: HttpResponse<IPickList[]>) => {
            this.pickLists = res.body;
        });
    }

    // Get quantity list from the picklist table
    getQuantityList(): void {
        this.quantityService.query().subscribe((res: HttpResponse<IQuantity[]>) => {
            this.quantity = res.body;
        });
    }

    // Get all the picklist value from the picklist table
    getAllPickListValue(): void {
        // Get the list of pickListValue
        this.pickListValueService.query({
            page: this.page - 1,
            size: this.itemsPerPage,
            sort: this.sort(),
            filter: {'status.equals': STATUS_ACTIVE}
        })
        .subscribe((res: HttpResponse<IPickListValue[]>) => this.paginatePickValues(res.body, res.headers));
    }

    // Get the Motherbed list from the picklist table
    getMotherBedList(): void {
        // Get the list of motherBeds
        this.motherBedService.query({
            filter: {'status.equals': STATUS_ACTIVE}
        }).subscribe((res: HttpResponse<IMotherBed[]>) => {
            this.motherBedList = res.body;
        });
    }

    // Call a service function to get list of zonals
    getZonalList(): void {
        // Get the list of zone
        this.zonalService.query({
            filter: {'status.equals': STATUS_ACTIVE}
        }).subscribe((res: HttpResponse<IZonal[]>) => {
            this.zonals = res.body;
        });
    }

    // Get the sector value based on zonal Id
    getSector(zoneId): void {
        // Get the list of sector
        this.sectorService.getSectors(zoneId).subscribe((res: HttpResponse<ISector[]>) => {
            this.sectors = res.body;
        });
    }

    // Get the nursery based on the sector Id
    getNursery(sectorId): void {
        // Get the list of nursery
        this.nurseryService.getNurserys(sectorId).subscribe((res: HttpResponse<INursery[]>) => {
            // console.log(res.body);
            this.nurserys = res.body;
        });
    }

    // Save the pick list value
    savePickList() {
        this.pickListObject.status = STATUS_ACTIVE;
        // If the id is not undefined, to update the old records of the picklist
        if (this.pickListObject.id !== undefined) {
            this.alertTitle = 'updated';
            this.subscribeToSaveResponse(
                this.pickListService.update(this.pickListObject), this.alertTitle
        );
        } else {
            this.alertTitle = 'created';
            this.subscribeToSaveResponse(
                this.pickListService.create(this.pickListObject), this.alertTitle
            );
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPickList>>, alertTitle) {
        result.subscribe(
            (res: HttpResponse<IPickList>) => {
                this.pickListModal.hide();
                this.pickListObject = new PickListModel();
                // alert('Successfully updated the record.');
                this.success.next(`Pick list ${alertTitle} successfully`);
                this.getPickList();
            },
            (res: HttpErrorResponse) => {
                // alert('Not Saved.');
                // alert(res.error.fieldErrors[0].message);
                this.error.next(res.error.fieldErrors[0].message);
            }
        );
    }

    // Save the pick value
    savePickValue() {
        this.pickListValueObject.status = STATUS_ACTIVE;
        // If the picklist object id is not undefined, to update the old records
        if (this.pickListValueObject.id !== undefined) {
            this.alertTitle = 'updated';
            this.subscribeToSaveSubPickListResponse(
                this.pickListValueService.update(this.pickListValueObject), this.alertTitle
            );
        } else {
            this.alertTitle = 'created';
            this.subscribeToSaveSubPickListResponse(
                this.pickListValueService.create(this.pickListValueObject), this.alertTitle
            );
        }
    }

    private subscribeToSaveSubPickListResponse(result: Observable<HttpResponse<IPickListValue>>, alertTitle) {
        result.subscribe(
            (res: HttpResponse<IPickListValue>) => {
                this.pickListValueModal.hide();
                this.pickListValueObject = new PickListValueModel();
                // alert('Successfully updated the record.');
                this.success.next(`Pick list value ${alertTitle} successfully`);
                this.getAllPickListValue();
            },
            (res: HttpErrorResponse) => {
                // alert('Not Saved.');
                // alert(res.error.fieldErrors[0].message);
                this.error.next(res.error.fieldErrors[0].message);
            }
        );
    }

    // show model popup to create picklist value
    createPickList(): void {
        this.pickListObject = new PickListModel();
        this.pickListModal.show();
        this.title = 'Create Pick List:';
    }

    // show model popup to create pickListValue
    createPickListValue(): void {
        this.pickListValueObject = new PickListValueModel();
        this.pickListValueModal.show();
        // console.log(this.pickListValueObject);
        this.title = 'Create Pick List Value:';
    }

    // show model popup to create MotherBed
    showMotherBedCreateForm(): void {
        this.motherBedObject = new MotherBedModel();
        this.motherBedModal.show();
    }

    // show model popup to update pickList value
    getPickListValue(value: PickListModel): void {
        this.pickListModal.show();
        this.pickListObject = value;
        this.title = `Update Pick List: ${value.pickListName}`;
    }

    // show model popup to update value
    getPickValue(value: PickListValueModel): void {
        // console.log('pick list value');
        // console.log(value);
        this.pickListValueModal.show();
        this.pickListValueObject = value;
        this.title = `Update Pick List: ${value.pickListValue}`;
    }

    // delete picklist value
    deletePickList(value: PickListModel): void {
        this.pickListService.delete(value.id).subscribe(data => {
            // alert('PickList delete Successfully.');
            this.success.next(`Pick list deleted successfully`);
            this.pickLists = this.pickLists.filter(u => u !== value);
        });
    }

    // delete picklist value
    deletePickListValue(value: PickListValueModel): void {
        this.pickListValueService.delete(value.id).subscribe(data => {
            // alert('PickListValue deleted Successfully.');
            this.success.next(`Pick list value deleted successfully`);
            this.pickListValues = this.pickListValues.filter(u => u !== value);
        });
    }

    // To show the pick list model and set the value of the picklist
    openChildModel(value: PickListValueModel): void {
        this.subPickListModal.show();
        this.pickListValueObject = value;
    }

    // Add sub pick list value
    addChild(val: PickListValueModel): void {
        this.pickListValueObject = new PickListValueModel();
        this.pickListValueObject.pickValueId = val.id;
        this.pickListValueObject.pickListValue = val.subChildValue;
        this.pickListValueObject.status = STATUS_ACTIVE;
        this.pickListValueService.create(this.pickListValueObject)
            .subscribe(data => {
                this.subPickListModal.hide();
                // alert('Sub PickListValue Created Successfully.');
                this.success.next(`Sub PickListValue Created Successfully.`);
                this.pickListValueObject = new PickListValueModel();
                this.getAllPickListValue();
            },
            (res: HttpErrorResponse) => {
                // alert(res.error.fieldErrors[0].message);
                this.error.next(res.error.fieldErrors[0].message);
            }
        );
    }

    // Save the quantity value
    saveQuantity() {
        // this.quantityObject.updatedAt = moment(this.updatedAt, DATE_TIME_FORMAT);
        // console.log('save ', this.quantityObject);
        if (this.quantityObject.id !== undefined) {
            this.subscribeToSaveQuantity(this.quantityService.update(this.quantityObject));
        } else {
            // this.quantityObject.createdAt = moment(this.createdAt, DATE_TIME_FORMAT);
            this.subscribeToSaveQuantity(this.quantityService.create(this.quantityObject));
        }
    }

    // To save the quantity with the response
    private subscribeToSaveQuantity(result: Observable<HttpResponse<IQuantity>>) {
        result.subscribe(
            (res: HttpResponse<IQuantity>) => {
                this.quantityModal.hide();
                this.quantityObject = new QuantityModel();
                alert('Successfully updated the record.');
                this.getQuantityList();
            },
            (res: HttpErrorResponse) => {
                // alert('Not Saved.');
                // alert(res.error.fieldErrors[0].message);
                this.error.next(res.error.fieldErrors[0].message);
            }
        );
    }

    // Save Motherbed
    saveMotherBed(): void {
        // If the motherbed id is not undefined, to update the old records
        this.motherBedObject.status = STATUS_ACTIVE;
        if (this.motherBedObject.id !== undefined) {
            this.alertTitle = 'updated';
            this.subscribeToSaveMotherBedResponse(
                this.motherBedService.update(this.motherBedObject), this.alertTitle);
        } else {
            this.alertTitle = 'created';
            this.subscribeToSaveMotherBedResponse(
                this.motherBedService.create(this.motherBedObject), this.alertTitle);
        }
    }

    private subscribeToSaveMotherBedResponse(result: Observable<HttpResponse<IMotherBed>>, alertTitle) {
        result.subscribe(
            (res: HttpResponse<IMotherBed>) => {
                this.motherBedModal.hide();
                this.motherBedObject = new MotherBedModel();
                // alert('MotherBed Created/Updated Successfully.');
                this.success.next(`Motherbed ${alertTitle} successfully`);
                this.getMotherBedList();
            },
            (res: HttpErrorResponse) => {
                // alert('Nursery Not Saved.');
                // alert(res.error.fieldErrors[0].message);
                this.error.next(res.error.fieldErrors[0].message);
            }
        );
    }

    // To display the Create quantity list from the quantity table
    createQuantityList(): void {
        this.quantityObject = new QuantityModel();
        this.quantityModal.show();
    }

    // commented for making the delete as soft one which means not to delete from db
    // deletePickList(value: PickListModel): void {
    //     this.pickListService.delete(value.id).subscribe(data => {
    //         // alert('PickList delete Successfully.');
    //         this.success.next(`Pick list deleted successfully`);
    //         this.pickLists = this.pickLists.filter(u => u !== value);
    //     });
    // }

    // // delete picklist value
    // deletePickListValue(value: PickListValueModel): void {
    //     this.pickListValueService.delete(value.id).subscribe(data => {
    //         // alert('PickListValue deleted Successfully.');
    //         this.success.next(`Pick list value deleted successfully`);
    //         this.pickListValues = this.pickListValues.filter(u => u !== value);
    //     });
    // }

    // // delete picklist value
    // deleteQuantity(value: QuantityModel): void {
    //     this.quantityService.delete(value.id).subscribe(data => {
    //         alert('PickList delete Successfully.');
    //         this.quantity = this.quantity.filter(u => u !== value);
    //     });
    // }

    // soft delete
    softDeletePickList(value: PickListModel): void {
       this.pickListObject = value;
       this.pickListObject.status = SOFT_DELETE_STATUS;
       this.pickListService.update(this.pickListObject)
       .subscribe(
           data => {
               this.success.next(`PickList deleted successfully`);
               this.getPickList();
           },
           (res: HttpErrorResponse) => {
               alert(res.error.fieldErrors[0].message);
           }
       );
    }

    // soft delete
    softDeletePickListValue(value: PickListValueModel): void {
        this.pickListValueObject = value;
        this.pickListValueObject.status = SOFT_DELETE_STATUS;
        this.pickListValueService.update(this.pickListValueObject)
        .subscribe(
            data => {
                this.success.next(`PickListValue deleted successfully`);
                this.getAllPickListValue();
            },
            (res: HttpErrorResponse) => {
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    softDeleteQuantity(value: QuantityModel): void {
    }

    // soft delete
    // softDeleteQuantity(value: QuantityModel): void {
    //     this.updatedAt = moment(this.quantityObject.updatedAt).format(DATE_TIME_FORMAT);
    //     this.quantityObject = value;
    //     this.quantityObject.status = SOFT_DELETE_STATUS;
    //     this.quantityObject.updatedAt = moment(this.updatedAt, DATE_TIME_FORMAT);
    //     this.quantityService.update(this.quantityObject)
    //     .subscribe(
    //         data => {
    //             this.success.next(`Quantity deleted successfully`);
    //         },
    //         (res: HttpErrorResponse) => {
    //             alert(res.error.fieldErrors[0].message);
    //         }
    //     );
    // }

    // soft delete
    softDeleteMothedBed(value: MotherBedModel): void {
        this.motherBedObject = value;
        this.motherBedObject.status = SOFT_DELETE_STATUS;
        this.motherBedService.update(this.motherBedObject)
        .subscribe(
            data => {
                this.success.next(`Motherbed deleted successfully`);
                this.getMotherBedList();
            },
            (res: HttpErrorResponse) => {
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    // Show model popup to update value of quantity
    getQuantity(value: QuantityModel): void {
        this.quantityModal.show();
        this.quantityObject = value;
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        this.router.navigate(['/pick-list-value'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.getAllPickListValue();
    }

    clear() {
        this.page = 0;
        this.router.navigate([
            '/pick-list-value',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.getAllPickListValue();
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    trackId(index: number, item: IPickListValue) {
        return item.id;
    }

    private paginatePickValues(data: IPickListValue[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.pickListValues = data;
    }

    // To get the picklist variety
    getVariety(id): void {
        this.pickListValueService.getVariety(id)
        .subscribe((res: HttpResponse<IPickListValue[]>) => {
            // console.log(res.body);
            this.variety = res.body;
        });
    }

    // To get the pick list category
    getCategory(id): void {
        this.pickListValueService.getCategory(id)
        .subscribe((res: HttpResponse<IPickListValue[]>) => {
            // console.log(res.body);
            this.categorys = res.body;
        });
    }
}
