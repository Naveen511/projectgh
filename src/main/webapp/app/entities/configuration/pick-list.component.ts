/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02 11:27:58
 *  Target: yarn
 *******************************************************************************/

import { Component, OnInit, ViewChild } from '@angular/core';
import { PickListService } from 'app/entities/service/pick-list.service';
import { PickListValueService } from 'app/entities/service/pick-list-value.service';
import { ActivatedRoute, Router } from '@angular/router';

import { JhiParseLinks } from 'ng-jhipster';

import { ITEMS_PER_PAGE, STATUS_ACTIVE, SOFT_DELETE_STATUS, ALERT_TIME_OUT_5000 } from 'app/shared';
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

    // To set value for url params
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;
    // filter: any;

    // View Modal pop up for create and update
    @ViewChild('pickListModal') public pickListModal: ModalDirective;
    @ViewChild('pickListValueModal') public pickListValueModal: ModalDirective;
    @ViewChild('subPickListModal') public subPickListModal: ModalDirective;
    @ViewChild('motherBedModal') public motherBedModal: ModalDirective;

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
        // Declare a value to url params
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
    }

    ngOnInit() {
        // Call a function to get list of active records
        this.getPickList();
        this.getAllPickListValue();
        this.getMotherBedList();
        this.getZonalList();

        // To set the time for automatic alert close
        setTimeout(() => (this.staticAlertClosed = true), ALERT_TIME_OUT_5000);

        // Set the success message with debounce time
        this.success.subscribe(message => (this.successMessage = message));
        this.success.pipe(debounceTime(ALERT_TIME_OUT_5000)).subscribe(() => (this.successMessage = null));

        // To set the error message with debounce time
        this.error.subscribe(message => (this.errorMessage = message));
        this.error.pipe(debounceTime(ALERT_TIME_OUT_5000)).subscribe(() => (this.errorMessage = null));
    }

    /**
     * Get the active pick list value from the variety dropdown
     */
    getPickList(): void {
        // Get the list of pickList
        this.pickListService
            .query({
                filter: { 'status.equals': STATUS_ACTIVE }
            })
            .subscribe((res: HttpResponse<IPickList[]>) => {
                this.pickLists = res.body;
            });
    }

    /**
     * Get quantity list from the picklist table
     */
    getQuantityList(): void {
        this.quantityService.query().subscribe((res: HttpResponse<IQuantity[]>) => {
            this.quantity = res.body;
        });
    }

    /**
     * Get all the picklist value from the picklist table
     * by using status as active
     */
    getAllPickListValue(): void {
        // Get the list of pickListValue
        this.pickListValueService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort(),
                filter: { 'status.equals': STATUS_ACTIVE }
            })
            .subscribe((res: HttpResponse<IPickListValue[]>) => {
                this.paginatePickValues(res.body, res.headers);
            });
    }

    /**
     * Get the Motherbed list from the picklist table
     */
    getMotherBedList(): void {
        // Get the list of motherBeds
        this.motherBedService
            .query({
                filter: { 'status.equals': STATUS_ACTIVE }
            })
            .subscribe((res: HttpResponse<IMotherBed[]>) => {
                this.motherBedList = res.body;
            });
    }

    /**
     * Get the zonal list using the active status
     */
    getZonalList(): void {
        // Get the list of zone
        this.zonalService
            .query({
                filter: { 'status.equals': STATUS_ACTIVE }
            })
            .subscribe((res: HttpResponse<IZonal[]>) => {
                this.zonals = res.body;
            });
    }

    /**
     * Get the sector value based on zonal Id
     * @param zoneId number
     */
    getSector(zoneId): void {
        // Get the list of sector
        // this.sectorService.getSectors(zoneId)
        this.sectorService
            .query({
                filter: {
                    'status.equals': STATUS_ACTIVE,
                    'zonalId.equals': zoneId
                }
            })
            .subscribe((res: HttpResponse<ISector[]>) => {
                this.sectors = res.body;
            });
    }

    /**
     * Get the nursery based on the sector Id
     * @param sectorId number
     */
    getNursery(sectorId): void {
        // Get the list of nursery
        // this.nurseryService.getNurserys(sectorId)
        this.nurseryService
            .query({
                filter: {
                    'status.equals': STATUS_ACTIVE,
                    'sectorId.equals': sectorId
                }
            })
            .subscribe((res: HttpResponse<INursery[]>) => {
                // console.log(res.body);
                this.nurserys = res.body;
            });
    }

    /**
     * Save the pick list value by default set the status as active
     */
    savePickList() {
        this.pickListObject.status = STATUS_ACTIVE;
        // If the id is not undefined, to update the old records of the picklist
        if (this.pickListObject.id !== undefined) {
            // Set the title for the alert
            this.alertTitle = 'updated';
            // update the picklist using the picklist object
            this.subscribeToSaveResponse(this.pickListService.update(this.pickListObject), this.alertTitle);
        } else {
            // Set the title for the alert
            this.alertTitle = 'created';
            // Create the new pick list
            this.subscribeToSaveResponse(this.pickListService.create(this.pickListObject), this.alertTitle);
        }
    }

    /**
     * return the saved response of success and
     * error from the nursery table
     * @param result IPickList
     * @param alertTitle string
     */
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

    /**
     * Save the pick value by default save the status as active
     */
    savePickValue() {
        this.pickListValueObject.status = STATUS_ACTIVE;
        // If the picklist object id is not undefined,
        // to update the old records
        if (this.pickListValueObject.id !== undefined) {
            // Set the alert message
            this.alertTitle = 'updated';
            // Update the old pick value using the picklistObject
            this.subscribeToSaveSubPickListResponse(this.pickListValueService.update(this.pickListValueObject), this.alertTitle);
        } else {
            // Set the alert message
            this.alertTitle = 'created';
            // Create a new pick value
            this.subscribeToSaveSubPickListResponse(this.pickListValueService.create(this.pickListValueObject), this.alertTitle);
        }
    }

    /**
     * return the saved response of success and
     * error from the nursery table
     * @param result IPickListValue
     * @param alertTitle string
     */
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

    /**
     * Show model popup to create picklist
     */
    createPickList(): void {
        this.pickListObject = new PickListModel();
        this.pickListModal.show();
        // Set the title for the pick list creation
        this.title = 'Create Pick List:';
    }

    /**
     * Show model popup to create pickListValue
     */
    createPickListValue(): void {
        this.pickListValueObject = new PickListValueModel();
        this.pickListValueModal.show();
        // Set the title for the pick list creation
        this.title = 'Create Pick List Value:';
    }

    // show model popup to create MotherBed
    /**
     * Show model popup to create MotherBed
     */
    showMotherBedCreateForm(): void {
        this.motherBedObject = new MotherBedModel();
        this.motherBedModal.show();
    }

    /**
     * Get the pick list value to update the PickListValue model
     * Show model popup to update pickList value
     * @param value PickListModel
     */
    getPickListValue(value: PickListModel): void {
        this.pickListModal.show();
        this.pickListObject = value;
        // Set the title for update
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

    /**
     * Add the sub pick list value
     *
     * @param val picklist values
     */
    addChild(val: PickListValueModel): void {
        this.pickListValueObject = new PickListValueModel();
        this.pickListValueObject.pickValueId = val.id;
        this.pickListValueObject.pickListValue = val.subChildValue;
        this.pickListValueObject.status = STATUS_ACTIVE;
        this.pickListValueService.create(this.pickListValueObject).subscribe(
            data => {
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

    /**
     * Save the Motherbed and updated the status
     */
    saveMotherBed(): void {
        // If the motherbed id is not undefined, to update the old records
        this.motherBedObject.status = STATUS_ACTIVE;
        if (this.motherBedObject.id !== undefined) {
            this.alertTitle = 'updated';
            this.subscribeToSaveMotherBedResponse(this.motherBedService.update(this.motherBedObject), this.alertTitle);
        } else {
            this.alertTitle = 'created';
            this.subscribeToSaveMotherBedResponse(this.motherBedService.create(this.motherBedObject), this.alertTitle);
        }
    }

    /**
     * To save the response and hide the model
     *
     * @param result result values
     * @param alertTitle title
     */
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

    /**
     * Soft delete - update the status as deleted
     *
     * @param value picklist values
     */
    softDeletePickList(value: PickListModel): void {
        // Confirmation Pop-up for the Delete
        if (window.confirm('Are sure you want to delete?')) {
            this.pickListObject = value;
            this.pickListObject.status = SOFT_DELETE_STATUS;
            this.pickListService.update(this.pickListObject).subscribe(
                data => {
                    this.success.next(`PickList deleted successfully`);
                    this.getPickList();
                },
                (res: HttpErrorResponse) => {
                    alert(res.error.fieldErrors[0].message);
                }
            );
        }
    }

    /**
     * Soft delete - update the status as deleted
     *
     * @param value picklistvalues values
     */
    softDeletePickListValue(value: PickListValueModel): void {
        // Confirmation Pop-up for the Delete
        if (window.confirm('Are sure you want to delete?')) {
            this.pickListValueObject = value;
            this.pickListValueObject.status = SOFT_DELETE_STATUS;
            this.pickListValueService.update(this.pickListValueObject).subscribe(
                data => {
                    this.success.next(`PickListValue deleted successfully`);
                    this.getAllPickListValue();
                },
                (res: HttpErrorResponse) => {
                    alert(res.error.fieldErrors[0].message);
                }
            );
        }
    }

    /**
     * Delete the calendar settings by updated the status as delete
     * @param value MotherBedModel
     */
    softDeleteMothedBed(value: MotherBedModel): void {
        // Confirmation Pop-up for the Delete
        if (window.confirm('Are sure you want to delete?')) {
            this.motherBedObject = value;
            this.motherBedObject.status = SOFT_DELETE_STATUS;
            this.motherBedService.update(this.motherBedObject).subscribe(
                data => {
                    this.success.next(`Motherbed deleted successfully`);
                    this.getMotherBedList();
                },
                (res: HttpErrorResponse) => {
                    alert(res.error.fieldErrors[0].message);
                }
            );
        }
    }

    /**
     * If the PickListModal pop up closed
     * call the getPickList function
     * to diplay the original records
     */
    closePickListModal(): void {
        // Hide the pickListModal pop-up
        this.pickListModal.hide();
        // Call the getPickList function
        this.getPickList();
    }

    /**
     * If the PickListValueModal pop up closed,
     * call the getAllPickListValue function,
     * to diplay the original records
     */
    closePickListValueModal(): void {
        // Hide the pickListValueModal pop-up
        this.pickListValueModal.hide();
        // Call the getAllPickListValue function
        this.getAllPickListValue();
    }

    /**
     * If the subPickListModal model pop up closed,
     * call the getAllPickListValue function,
     * to diplay the original records
     */
    closeSubPickListModal(): void {
        // Hide the subPickListModal pop-up
        this.subPickListModal.hide();
        // Call the getAllPickListValue function
        this.getAllPickListValue();
    }

    /**
     * If the subPickListModal model pop up closed,
     * call the getMotherBedList function,
     * to diplay original records
     */
    closeMotherBedModal(): void {
        // Hide the motherBedModal pop-up
        this.motherBedModal.hide();
        // Call the getMotherBedList function
        this.getMotherBedList();
    }

    /**
     * To get the picklist variety
     * @param id number
     */
    getVariety(id): void {
        // this.pickListValueService.getVariety(id)
        this.pickListValueService
            .query({
                filter: {
                    'status.equals': STATUS_ACTIVE,
                    'pickListId.equals': id
                }
            })
            .subscribe((res: HttpResponse<IPickListValue[]>) => {
                // console.log(res.body);
                this.variety = res.body;
            });
    }

    /**
     * To get the pick list category using the pick list id
     * @param id number
     */
    getCategory(id): void {
        // this.pickListValueService.getCategory(id)
        this.pickListValueService
            .query({
                filter: {
                    'status.equals': STATUS_ACTIVE,
                    'pickValueId.equals': id
                }
            })
            .subscribe((res: HttpResponse<IPickListValue[]>) => {
                // console.log(res.body);
                this.categorys = res.body;
            });
    }

    /**
     * Get the page number
     *
     * @param page number
     */
    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    /**
     * Based on sort load the data
     */
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

    /**
     * Clear the filter
     */
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

    /**
     * Get the sorting type
     */
    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    /**
     * Get the row index of data
     *
     * @param index number
     * @param item sector vlaue
     */
    trackId(index: number, item: IPickListValue) {
        return item.id;
    }

    /**
     * Set the page size, total record count in header
     *
     * @param data values
     * @param headers header values
     */
    private paginatePickValues(data: IPickListValue[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.pickListValues = data;
    }

    /**
     * softDeleteQuantity(value: QuantityModel): void { }
    // Soft delete
    softDeleteQuantity(value: QuantityModel): void {
        this.updatedAt = moment(this.quantityObject.updatedAt).format(DATE_TIME_FORMAT);
        this.quantityObject = value;
        this.quantityObject.status = SOFT_DELETE_STATUS;
        this.quantityObject.updatedAt = moment(this.updatedAt, DATE_TIME_FORMAT);
        this.quantityService.update(this.quantityObject)
        .subscribe(
            data => {
                this.success.next(`Quantity deleted successfully`);
            },
            (res: HttpErrorResponse) => {
                alert(res.error.fieldErrors[0].message);
            }
        );
    } */
}
