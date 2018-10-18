/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/12
 *  Target: yarn
 *******************************************************************************/

// Import needed component, model and dependency
import { Component, OnInit } from '@angular/core';
import { ViewChild } from '@angular/core';
import * as moment from 'moment';
import { HttpResponse, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiParseLinks } from 'ng-jhipster';
import { Subject } from 'rxjs';
import { debounceTime } from 'rxjs/operators';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbDatepickerConfig } from '@ng-bootstrap/ng-bootstrap';

import {
    IGodownPurchaseDetails, GodownPurchaseDetailsModel,
    DISPLAY_NAME_VARIETY, DISPLAY_NAME_QUANTITY_TYPE,
    STATUS_ADDED_TO_STOCK
} from 'app/shared/model/godown-purchase-details.model';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { IPickList } from 'app/shared/model/pick-list.model';
import { IPickListValue } from 'app/shared/model/pick-list-value.model';
import {
    GodownStockDetailsModel, STATUS_ADDED_FROM_PURCHASE
} from 'app/shared/model/godown-stock-details.model';
import { IGodownStock, GodownStockModel } from 'app/shared/model/godown-stock.model';
import { IGodown } from 'app/shared/model/godown.model';
import { IFinancialYearSettings } from 'app/shared/model/financial-year-settings.model';

import {
    GodownPurchaseDetailsService
} from 'app/entities/service/godown-purchase-details.service';
import { PickListService } from 'app/entities/service/pick-list.service';
import { PickListValueService } from 'app/entities/service/pick-list-value.service';
import {
    GodownStockDetailsService
} from 'app/entities/service/godown-stock-details.service';
import { GodownService } from 'app/entities/service/godown.service';
import { GodownStockService } from 'app/entities/service/godown-stock.service';
import {
    FinancialYearSettingsService
} from 'app/entities/service/financial-year-settings.service';

import {
    DATE_TIME_FORMAT, ITEMS_PER_PAGE, SOFT_DELETE_STATUS,
    STATUS_ACTIVE, ALERT_TIME_OUT_5000
} from 'app/shared';

// Mention the html, css/sass files
@Component({
    selector: 'jhi-godown-purchase',
    templateUrl: 'godown-purchase.component.html'
})

/**
 * Class GodownPurchaseComponent used to create/update a godownPurchase,
 * List all godownPurchases.
 * Declared an GodownPurchase object to create and update.
 * Declared an Array variable to set list of godownPurchases.
 * Using a modal popup directive create and update form is displayed.
 */
export class GodownPurchaseComponent implements OnInit {
    // Create object for model
    godownPurchaseObject: GodownPurchaseDetailsModel = new GodownPurchaseDetailsModel();
    godownStockDetailsObject: GodownStockDetailsModel = new GodownStockDetailsModel();
    godownStockObject: GodownStockModel = new GodownStockModel();
    // create empty array for each service
    godownPurchases: IGodownPurchaseDetails[];
    financialYearId: number;

    // Get the picklist calue and category
    pickLists: IPickList[];
    quantityTypes: IPickListValue[];
    purchaseDateDp: any;
    stockDateDp: any;
    alerts: any;
    statusAddedToStock: number;

    varieties: IPickListValue[];
    categories: IPickListValue[];
    // godown: IGodown[];

    // Title declation as String
    title: String;

    // To display the success message
    private success = new Subject<string>();
    successMessage: string;

    // To display the error message
    private error = new Subject<string>();
    errorMessage: string;

    // By default close the alert with statc time
    staticAlertClosed = false;

    // For pagination we are declared the following variables
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;

    // Declare a modal popup
    @ViewChild('godownPurchaseModal') public godownPurchaseModal: ModalDirective;
    @ViewChild('godownStockModal') public godownStockModal: ModalDirective;
    @ViewChild('vendorDetailsModal') public vendorDetailsModal: ModalDirective;

    constructor(
        private godownPurchaseService: GodownPurchaseDetailsService,
        private pickListService: PickListService,
        private pickListValueService: PickListValueService,
        private godownStockService: GodownStockService,
        private godownStockDetailsService: GodownStockDetailsService,
        private godownService: GodownService,
        private settingsService: FinancialYearSettingsService,
        private parseLinks: JhiParseLinks,
        private router: Router,
        private activatedRoute: ActivatedRoute,
        private config: NgbDatepickerConfig
    ) {
        // this.maxDate.setDate(this.maxDate.getDate() + 7);
        // this.bsRangeValue = [this.bsValue, this.maxDate];
        // Declare a value to url params
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            // console.log(data);
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });

        this.statusAddedToStock = STATUS_ADDED_TO_STOCK;

        // Declare a current date to an variable
        const currentDate = new Date();
        // config.minDate = {};
        // Set max date as today date for date field
        config.maxDate = {
            year: currentDate.getFullYear(),
            month: currentDate.getMonth() + 1,
            day: currentDate.getDate()
        };
        // Disable the future date
        config.outsideDays = 'hidden';
    }

    ngOnInit() {

        // Call a function to get list of godownPurchases
        this.getGodownPurchaseList();

        // Get the list of picklist
        // this.godownService.query({
        //     filter: {'status.equals': STATUS_ACTIVE}
        // }).subscribe((res: HttpResponse<IGodown[]>) => {
        //     // Assign a value to an object
        //     this.godown = res.body;
        // });

        // Call a function to get active batch id
        // this.getActiveRecord();

        // To get the pick list
        // this.getPickList();
        // to get the quantity varieties
        // this.getVariety(id);

        // To set the time for automatic alert close
        setTimeout(() => (this.staticAlertClosed = true), ALERT_TIME_OUT_5000);

        // Set the success message with debounce time
        this.success.subscribe(message => (this.successMessage = message));
        this.success.pipe(debounceTime(ALERT_TIME_OUT_5000))
        .subscribe(() => (this.successMessage = null));

        // To set the error message with debounce time
        this.error.subscribe(message => (this.errorMessage = message));
        this.error.pipe(debounceTime(ALERT_TIME_OUT_5000))
        .subscribe(() => (this.errorMessage = null));
    }

    /**
     * Call a service function to get list of godowns not in delete status
     */
    getGodownPurchaseList(): void {
        // Get the list of godownPurchase
        this.godownPurchaseService.query({
            page: this.page - 1,
            size: this.itemsPerPage,
            sort: this.sort(),
            filter: { 'status.greaterThan': SOFT_DELETE_STATUS }
        })
        .subscribe((res: HttpResponse<IGodownPurchaseDetails[]>) => {
            this.paginate(res.body, res.headers);
        });
    }

    /**
     * Call a service function to get list of active batch
     */
    getActiveRecord(): void {
        // Get the list of active batch record and
        // assign a 0th index array value to an batch id
        this.settingsService.query({ filter: { 'status.equals': STATUS_ACTIVE }
        }).subscribe((res: HttpResponse<IFinancialYearSettings[]>) => {
            // Assign a value to an object
            this.financialYearId = res.body[0].id;
        });
    }

    /**
     * Get the pick list values
     */
    getPickList(): void {
        // To get the active pick list variety Id from Pick List based on label
        this.pickListService.query({
            filter: {
                'status.equals': STATUS_ACTIVE,
                'displayLabelName.equals': DISPLAY_NAME_VARIETY
            }
        }).subscribe((res: HttpResponse<IPickList[]>) => {
            if (res.body.length > 0) {
                // Call a function to get list of variety
                this.getVariety(res.body[0].id);
            }
        });

        // To get the active pick list quantity type Id from pickList based on label
        // this.pickListService.getPickListId(DISPLAY_NAME_QUANTITY_TYPE)
        this.pickListService.query({
            filter: {
                'status.equals': STATUS_ACTIVE,
                'displayLabelName.equals': DISPLAY_NAME_QUANTITY_TYPE
            }
        }).subscribe((res: HttpResponse<IPickList[]>) => {
            if (res.body.length > 0) {
                // Call a function to get list of quantity
                this.getQuantityTypes(res.body[0].id);
            }
        });
    }

    /**
     * Get the active variety from the picklistvalue
     * @param id as auto increment id of pickList model
     */
    getVariety(id): void {
        // this.pickListValueService.getVariety(id)
        this.pickListValueService.query({
            filter: {
                'status.equals': STATUS_ACTIVE,
                'pickListId.equals': id
            }
        }).subscribe((res: HttpResponse<IPickListValue[]>) => {
            // Assign a response value to an object
            this.varieties = res.body;
        });
    }

    /**
     * Get the active quantity type from the picklistvalue
     * @param id as auto increment id of pickList model
     */
    getQuantityTypes(id): void {
        // this.pickListValueService.getVariety(id)
        this.pickListValueService.query({
            filter: {
                'status.equals': STATUS_ACTIVE,
                'pickListId.equals': id
            }
        }).subscribe((res: HttpResponse<IPickListValue[]>) => {
            // Assign a response value to an object
            this.quantityTypes = res.body;
        });
    }

    /**
     * Get category from the picklist value
     * @param id as auto increment id of pickList model
     */
    getCategory(id): void {
        // this.pickListValueService.getCategory(id)
        this.pickListValueService.query({
            filter: {
                'status.equals': STATUS_ACTIVE,
                'pickValueId.equals': id
            }
        }).subscribe((res: HttpResponse<IPickListValue[]>) => {
            // Assign a response value to an object
            this.categories = res.body;
        });
    }

    /**
     * Send a godownPurchase object to a service (create or update)
     */
    save() {
        // Assign a value to a variable
        this.godownPurchaseObject.date = moment(
            this.godownPurchaseObject.date, DATE_TIME_FORMAT
        );
        this.godownPurchaseObject.status = STATUS_ACTIVE;
        if (this.godownPurchaseObject.id !== undefined) {
            // Call a godown purchase update service
            this.subscribeToSaveResponse(
                this.godownPurchaseService.update(this.godownPurchaseObject),
                'updated.'
            );
        } else {
            this.godownPurchaseObject.financialYearGodownPurchaseId = this.financialYearId;
            // Call a godown purchase create service
            this.subscribeToSaveResponse(
                this.godownPurchaseService.create(this.godownPurchaseObject),
                'created.'
            );
        }
    }

    /**
     * To save the godown purchase details
     * @param result object of godown purchase details
     * @param alertTitle message title for create/update
     */
    private subscribeToSaveResponse(result: Observable<HttpResponse<IGodownPurchaseDetails>>, alertTitle) {
        result.subscribe(
            (res: HttpResponse<IGodownPurchaseDetails>) => {
                // Hide a model popup
                this.godownPurchaseModal.hide();
                // Create new object for GodownPurchaseDetailsModel
                this.godownPurchaseObject = new GodownPurchaseDetailsModel();
                // alert('Purchase Details Created/Updated Successfully.');
                this.success.next(`Successfully ${alertTitle}`);
                // Call a method to reload a purchase list
                this.getGodownPurchaseList();
                this.categories = null;
            },
            (res: HttpErrorResponse) => {
                // Display the error message in view
                this.error.next(res.error.fieldErrors[0].message);
            }
        );
    }

    /**
     * show model popup to create godownPurchase value
     */
    createGodownPurchase(): void {
        this.godownService.query({ filter: { 'status.equals': STATUS_ACTIVE }
        }).subscribe((res: HttpResponse<IGodown[]>) => {
            // Assign a value to an object
            // this.godown = res.body;
            // If the length is greater than zero call the active record and pick list
            if (res.body.length > 0) {
                // Call a function to get active batch id
                this.getActiveRecord();
                // to get the pick list
                this.getPickList();
                // Create a new model object
                this.godownPurchaseObject = new GodownPurchaseDetailsModel();
                // Assign a value to an variable
                this.godownPurchaseObject.godownId = res.body[0].id;
                // Display a collapsed div
                this.godownPurchaseModal.show();
                this.title = 'Create Purchase details';
            } else {
                // Display the error message in view
                alert('There is no godown to add stock.');
            }
        });

        /** if (this.godown.length > 0) {
            // Create a new model object
            this.godownPurchaseObject = new GodownPurchaseDetailsModel();
            // Assign a value to an variable
            this.godownPurchaseObject.godownId = this.godown[0].id;
            // Show model popup
            this.godownPurchaseModal.show();
            this.title = 'Create Purchase details';
        } else {
            // Display the error message in view
            alert('There is no godown to add a purchase');
        }  */
    }

    /**
     * show model popup to update godownPurchase value
     * @param value as object of particular GodownPurchaseDetails Model
     */
    getGodownPurchaseValue(value: GodownPurchaseDetailsModel): void {
        this.godownPurchaseModal.show();
        // Assign a value to an godown purchase object
        this.godownPurchaseObject = value;
        this.title = 'Update Purchase details';
    }

    /**
     * Soft delete for the godown purchase details
     * @param godownPurchase as object of particular GodownPurchaseDetails Model
     */
    softDelete(godownPurchase: GodownPurchaseDetailsModel): void {
        // Confirmation Pop-up for the Delete
        if (window.confirm('Are sure you want to delete?')) {
            // Assign a param value to an godownPurchase object
            this.godownPurchaseObject = godownPurchase;
            this.godownPurchaseObject.status = SOFT_DELETE_STATUS;
            // Call a godown purchase update service
            this.godownPurchaseService.update(this.godownPurchaseObject)
            .subscribe(
                data => {
                    // If success display the success message in view
                    this.success.next(`Purchase detail deleted successfully.`);
                    // Call a method to display list of purchase
                    this.getGodownPurchaseList();
                },
                (res: HttpErrorResponse) => {
                    // If error response display the error message in view
                    alert(res.error.fieldErrors[0].message);
                }
            );
        }
    }

    /**
     * To view the vendor details and description
     * @param value as object of particular GodownPurchaseDetails Model
     */
    viewVendorDetails(value): void {
        // Show the stock model popup
        this.vendorDetailsModal.show();
        // Assign a value to a variables
        this.godownPurchaseObject = value;
    }

    /**
     * Show modal to add godown stock
     * @param value as object of particular GodownPurchaseDetails Model
     */
    addStock(value: GodownPurchaseDetailsModel): void {
        // Create a new model object for GodownStock and GodownStockDetails
        this.godownStockObject = new GodownStockModel();
        this.godownStockDetailsObject = new GodownStockDetailsModel();
        // Show the stock model popup
        this.godownStockModal.show();
        // Assign a value to a variables
        this.godownStockObject.godownId = value.godownId;
        this.godownStockObject.pickListVarietyId = value.pickListVarietyId;
        this.godownStockObject.pickListCategoryId = value.pickListCategoryId;
        this.godownStockDetailsObject.quantity = value.quantity;
        // Assign a parma object value to an godownPurchaseObject
        this.godownPurchaseObject = value;
    }

    /**
     * Add to stock table from the godown purchase details
     */
    saveStock(): void {
        // Assign a value to a variable
        this.godownStockDetailsObject.price = this.godownPurchaseObject.price;
        // Call a godown stock query service with filter(godownId and pickListCategoryId)
        this.godownStockService.query({
            filter: {
                'godownId.equals': this.godownStockObject.godownId,
                'pickListCategoryId.equals': this.godownStockObject.pickListCategoryId,
                'status.greaterThan': SOFT_DELETE_STATUS
            }
        }).subscribe((res: HttpResponse<IGodownStock[]>) => {
            // If already stock have the record for same category add the quantity
            // and update the record otherwise create a new record
            if (res.body.length > 0) {
                this.godownStockObject = res.body[res.body.length - 1];
                // Add a new quantity to old quantity and assign a value
                this.godownStockObject.currentQuantity =
                    +this.godownStockObject.currentQuantity + +this.godownStockDetailsObject.quantity;
                this.godownStockObject.addedQuantity = +this.godownStockObject.addedQuantity + +this.godownStockDetailsObject.quantity;
                // Call a godown stock update service
                this.godownStockService.update(this.godownStockObject).subscribe(
                    data => {
                        this.godownStockDetailsObject.godownStockId = data.body.id;
                        // Call a method to create stock details
                        this.createGodownStockDetails(this.godownStockDetailsObject);
                    },
                    (err: HttpErrorResponse) => {
                        // If error response display the error message in view
                        this.error.next(err.error.fieldErrors[0].message);
                    }
                );
            } else {
                // Assign a quantity to a variable
                this.godownStockObject.status = STATUS_ACTIVE;
                this.godownStockDetailsObject.status = STATUS_ADDED_FROM_PURCHASE;
                this.godownStockObject.currentQuantity = this.godownStockDetailsObject.quantity;
                this.godownStockObject.addedQuantity = this.godownStockDetailsObject.quantity;
                this.godownStockObject.financialYearGodownStockId = this.financialYearId;
                // Call a godown stock create service
                this.godownStockService.create(this.godownStockObject).subscribe(
                    data => {
                        // Assign a value to an variable
                        this.godownStockDetailsObject.godownStockId = data.body.id;
                        // Call a method to create stock details
                        this.createGodownStockDetails(this.godownStockDetailsObject);
                    },
                    (err: HttpErrorResponse) => {
                        // If error response display the error message in view
                        this.error.next(err.error.fieldErrors[0].message);
                    }
                );
            }
        });
    }

    /**
     * Create the godown stock details using the stock objects
     * @param godownStockDetailsObject as object
     */
    createGodownStockDetails(godownStockDetailsObject): void {
        // Assign a value to a object
        this.godownStockDetailsObject = godownStockDetailsObject;
        this.godownStockDetailsObject.date = moment(this.godownStockDetailsObject.date, DATE_TIME_FORMAT);
        this.godownStockDetailsObject.financialYearGodownStockDetailsId = this.financialYearId;
        // Call a service to create a stock details
        this.godownStockDetailsService.create(this.godownStockDetailsObject).subscribe(
            data => {
                // Assign a status to godown purchase object
                this.godownPurchaseObject.status = STATUS_ADDED_TO_STOCK;
                // Call a service to update the purchase details
                this.godownPurchaseService.update(this.godownPurchaseObject)
                .subscribe(output => {
                    // hide the model popup
                    this.godownStockModal.hide();
                    // If success display the success message in view
                    this.success.next('Successfully moved to stock area.');
                }, (err: HttpErrorResponse) => {
                    // If error response display the error message in view
                    this.error.next(err.error.fieldErrors[0].message);
                });
            },
            (res: HttpErrorResponse) => {
                // If error response display the error message in view
                this.error.next(res.error.fieldErrors[0].message);
            }
        );
    }

    /**
     * If the purchase model pop up closed, to call the get purchase list function
     */
    closegodownPurchaseModal(): void {
        // Hide the purchase model pop-up
        this.godownPurchaseModal.hide();
        // Call the getGodownPurchaseList function
        this.getGodownPurchaseList();
    }

    /**
     * On keypress event validation the key value
     * If entered value is number allow to enter otherwise return as false
     */
    numberOnly(event): boolean {
        const charCode = (event.which) ? event.which : event.keyCode;
        if (charCode > 31 && (charCode < 48 || charCode > 57)) {
            return false;
        }
        return true;
    }

    // Load the page
    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    // Based on sort load the data
    transition() {
        this.router.navigate(['/godown-purchase-details'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.getGodownPurchaseList();
    }

    // Clear the filter
    clear() {
        this.page = 0;
        this.router.navigate([
            '/godown-purchase-details',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.getGodownPurchaseList();
    }

    // Get the sorting type
    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    // Get the row index of data
    trackId(index: number, item: IGodownPurchaseDetails) {
        return item.id;
    }

    // Set the page size, total record count in header
    private paginate(data: IGodownPurchaseDetails[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.godownPurchases = data;
    }
}
