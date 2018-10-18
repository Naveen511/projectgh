/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/22
 *  Target: yarn
 *******************************************************************************/

// Import needed component, model and dependency
import { Component, OnInit, ViewChild } from '@angular/core';
import {
    HttpResponse, HttpHeaders, HttpErrorResponse
} from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import * as moment from 'moment';
import { Observable } from 'rxjs';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { Subject } from 'rxjs';
import { debounceTime } from 'rxjs/operators';
import { NgbDatepickerConfig } from '@ng-bootstrap/ng-bootstrap';
import { JhiParseLinks } from 'ng-jhipster';

import {
    IGodownStock, GodownStockModel, DISPLAY_NAME_VARIETY,
    DISPLAY_NAME_QUANTITY_TYPE, GodownStock
} from 'app/shared/model/godown-stock.model';
import {
    IGodownStockDetails, GodownStockDetails, STATUS_DIRECT_ADD
} from 'app/shared/model/godown-stock-details.model';
import { IPickList } from 'app/shared/model/pick-list.model';
import { IPickListValue } from 'app/shared/model/pick-list-value.model';
import { IGodown } from 'app/shared/model/godown.model';
import { IFinancialYearSettings } from 'app/shared/model/financial-year-settings.model';

import {
    GodownStockDetailsService
} from 'app/entities/service/godown-stock-details.service';
import { GodownStockService } from 'app/entities/service/godown-stock.service';
import { PickListService } from 'app/entities/service/pick-list.service';
import { PickListValueService } from 'app/entities/service/pick-list-value.service';
import { GodownService } from 'app/entities/service/godown.service';
import {
    FinancialYearSettingsService
} from 'app/entities/service/financial-year-settings.service';

import {
    DATE_TIME_FORMAT, ITEMS_PER_PAGE, STATUS_ACTIVE, ALERT_TIME_OUT_5000,
    SOFT_DELETE_STATUS
} from 'app/shared';

// Mention the html, css/sass files
@Component({
    selector: 'jhi-godown-stock',
    templateUrl: 'godown-stock.component.html'
})

/**
 * Class GodownStockComponent used to create/update a godownStock,
 * List all godownStocks.
 * Declared an GodownStock object to create and update.
 * Declared an Array variable to set list of godownStock.
 * Using a modal popup directive create and update form is displayed.
 */
export class GodownStockComponent implements OnInit {
    // create empty object
    godownStock: GodownStockModel = new GodownStockModel();
    godownStockDetail: GodownStockDetails = new GodownStockDetails();
    // create empty array for each service
    godownStocks: IGodownStock[];
    godownStockDetails: IGodownStockDetails[];
    pickLists: IPickList[];
    varieties: IPickListValue[];
    categories: IPickListValue[];
    quantityTypes: IPickListValue[];
    // godown: IGodown[];

    // collapsed status
    isCollapsed = true;
    isCollapsedStockDetails = true;

    // Declare a constant variable
    financialYearId: number;
    stockDateDp: any;
    addInventoryDateDp: any;

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
    @ViewChild('addParticularStock') public addParticularStock: ModalDirective;

    constructor(
        private godownStockService: GodownStockService,
        private godownStockDetailService: GodownStockDetailsService,
        private pickListService: PickListService,
        private pickListValueService: PickListValueService,
        private godownService: GodownService,
        private settingsService: FinancialYearSettingsService,
        private parseLinks: JhiParseLinks,
        private router: Router,
        private activatedRoute: ActivatedRoute,
        private config: NgbDatepickerConfig
    ) {
        // Declare a value to url params
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            // console.log(data);
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
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
        // Get the list of godown
        /** this.godownService.query({
            filter: { 'status.equals': STATUS_ACTIVE }
        }).subscribe((res: HttpResponse<IGodown[]>) => {
            this.godown = res.body;
        }); */

        // Get the list of godown stocks
        this.getgodownStockList();

        // // Call a function to get active batch id
        // this.getActiveRecord();
        // // to get the pick list
        // this.getPickList();

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

    // Call a service function to get list of active batch
    getActiveRecord(): void {
        // Get the list of active batch record and
        // assign a 0th index array value to an batch id
        this.settingsService.query({
            filter: { 'status.equals': STATUS_ACTIVE }
        }).subscribe((res: HttpResponse<IFinancialYearSettings[]>) => {
            // Assign a value to an object
            this.financialYearId = res.body[0].id;
        });
    }

    // Get the list of picklist
    getPickList(): void {
        // To get the active pick list variety Id from Pick List based on label
        this.pickListService.query({
            filter: {
                'status.equals': STATUS_ACTIVE,
                'displayLabelName.equals': DISPLAY_NAME_VARIETY
            }
        })
        .subscribe((res: HttpResponse<IPickList[]>) => {
            if (res.body.length > 0) {
                // Call a function to get list of variety
                this.getVariety(res.body[0].id);
            }
        });

        // To get the active pick list quantity type Id from pickList based on label
        this.pickListService.query({
            filter: {
                'status.equals': STATUS_ACTIVE,
                'displayLabelName.equals': DISPLAY_NAME_QUANTITY_TYPE
            }
        })
        .subscribe((res: HttpResponse<IPickList[]>) => {
            if (res.body.length > 0) {
                // Call a function to get list of quantity
                this.getQuantityType(res.body[0].id);
            }
        });
    }

    /**
     * Get the active variety from the picklistvalue
     * @param id as pickList model auto increment id
     */
    getVariety(id): void {
        // this.pickListValueService.getVariety(id)
        this.pickListValueService.query({
            filter: {
                'status.equals': STATUS_ACTIVE,
                'pickListId.equals': id
            }
        })
        .subscribe((res: HttpResponse<IPickListValue[]>) => {
            // Assign a response value to an object
            this.varieties = res.body;
        });
    }

    /**
     * Get category from the picklist value
     * @param id pickList model auto increment id
     */
    getCategory(id): void {
        // this.pickListValueService.getCategory(id)
        this.pickListValueService.query({
            filter: {
                'status.equals': STATUS_ACTIVE,
                'pickValueId.equals': id
            }
        })
        .subscribe((res: HttpResponse<IPickListValue[]>) => {
            // Assign a response value to an object
            this.categories = res.body;
        });
    }

    /**
     * Get the active quantity type from the picklistvalue
     * @param id as pickList model auto increment id
     */
    getQuantityType(id): void {
        // this.pickListValueService.getVariety(id)
        this.pickListValueService.query({
            filter: {
                'status.equals': STATUS_ACTIVE,
                'pickListId.equals': id
            }
        })
        .subscribe((res: HttpResponse<IPickListValue[]>) => {
            // Assign a response value to an object
            this.quantityTypes = res.body;
        });
    }

    /**
     * To get the list of godown stock details
     */
    getgodownStockList(): void {
        // Call a godown stock query service to get list of records
        this.godownStockService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort(),
                filter: { 'status.greaterThan': SOFT_DELETE_STATUS }
            })
            .subscribe((res: HttpResponse<IGodownStock[]>) => {
                this.paginateGodownStocks(res.body, res.headers);
            });
    }

    /**
     * Show from div to create godown stock
     */
    createGodownStock(): void {
        this.isCollapsedStockDetails = true;
        // Get the list of godown
        this.godownService.query({
            filter: { 'status.equals': STATUS_ACTIVE }
        }).subscribe((res: HttpResponse<IGodown[]>) => {
            // this.godown = res.body;
            if (res.body.length > 0) {
                // Call a function to get active batch id
                this.getActiveRecord();
                // to get the pick list
                this.getPickList();
                // Create a new model object
                this.godownStock = new GodownStockModel();
                this.godownStockDetail = new GodownStockDetails();
                // Assign a value to an variable
                this.godownStock.godownId = res.body[0].id;
                // Display a collapsed div
                this.isCollapsed = false;
            } else {
                // Display the error message in view
                alert('There is no godown to add stock.');
            }

        });

        /**this.isCollapsedStockDetails = true;
        if (this.godown.length > 0) {
            // Call a function to get active batch id
            this.getActiveRecord();
            // to get the pick list
            this.getPickList();
            // Create a new model object
            this.godownStock = new GodownStockModel();
            this.godownStockDetail = new GodownStockDetails();
            // Assign a value to an variable
            this.godownStock.godownId = this.godown[0].id;
            // Display a collapsed div
            this.isCollapsed = false;
        } else {
            // Display the error message in view
            alert('There is no godown to add stock.');
        } */
    }

    /**
     * To display particulat godown stock details
     * @param id as godownStock model auto increment id
     */
    getStockDetails(id): void {
        this.isCollapsed = true;
        // Call a godown stock query service to get particular stock details
        this.godownStockDetailService.query({
            filter: {
                'godownStockId.equals': id
            }
        })
        .subscribe((res: HttpResponse<IGodownStockDetails[]>) => {
            // Assign a value to an godown purchase object
            this.godownStockDetails = res.body;
            // Hide a collapsed div
            this.isCollapsedStockDetails = false;
        });
    }

    /**
     * To add the stock to the godown. If already particular category have a value
     * add only quantity in stock details in child table otherwise create a new record
     */
    addStockDetail(): void {
        if (this.godownStockDetail.quantity <= 0) {
            this.error.next('Quantity should be greater than 0 ');
        } else if (this.godownStock.pickListCategoryId != null) {
            // Assign a value to an variable
            this.godownStockDetail.status = STATUS_DIRECT_ADD;
            // console.log('before', this.godownStock);
            // Call a godown stock query service to get particular godown category list
            this.godownStockService.query({
                filter: {
                    'godownId.equals': this.godownStock.godownId,
                    'pickListCategoryId.equals': this.godownStock.pickListCategoryId,
                    'status.greaterThan': SOFT_DELETE_STATUS
                }
            }).subscribe((res: HttpResponse<IGodownStock[]>) => {
                // If already stock have the record for same category add the quantity
                // and update the record otherwise create a new record
                if (res.body.length > 0) {
                    this.godownStock = res.body[res.body.length - 1];
                    // Add a new quantity to old quantity and assign a value
                    this.godownStock.currentQuantity =
                        +this.godownStock.currentQuantity + +this.godownStockDetail.quantity;
                    this.godownStock.addedQuantity =
                        +this.godownStock.addedQuantity + +this.godownStockDetail.quantity;
                    // Call a godown stock update service
                    this.subscribeToSaveResponse(this.godownStockService.update(this.godownStock));
                } else {
                    // Assigna quantity to a variable
                    this.godownStock.status = STATUS_ACTIVE;
                    this.godownStock.currentQuantity = this.godownStockDetail.quantity;
                    this.godownStock.addedQuantity = this.godownStockDetail.quantity;
                    // Call a godown stock create service
                    this.subscribeToSaveResponse(this.godownStockService.create(this.godownStock));
                }
            });
        } else {
            // alert('Variety and Category cannot be blank');
            this.error.next('Variety and Category cannot be blank');
        }
    }

    /**
     * Save the stock and get the response from the service
     * @param result object of godown stock details
     */
    private subscribeToSaveResponse(result: Observable<HttpResponse<IGodownStock>>) {
        result.subscribe(
            (res: HttpResponse<IGodownStock>) => {
                // console.log(this.godownStockDetail);
                // console.log('After parent save');
                this.godownStockDetail.godownStockId = res.body.id;
                // Call a method to create stock details
                this.saveStockDetails();
            },
            (res: HttpErrorResponse) => {
                // If error response display the error message in view
                this.error.next(res.error.fieldErrors[0].message);
            }
        );
    }

    /**
     * Save stock details
     */
    saveStockDetails(): void {
        // Assign a value to an variable
        this.godownStockDetail.date = moment(
            this.godownStockDetail.date, DATE_TIME_FORMAT
        );
        // Call a service to create a stock details
        this.godownStockDetailService.create(this.godownStockDetail).subscribe(
            data => {
                // Call a function to get list of nursery Stocks
                this.getgodownStockList();
                // Create a new object for stock and stock details
                this.godownStock = new GodownStockModel();
                this.godownStockDetail = new GodownStockDetails();
                this.isCollapsed = true;
                // Hide the model popup
                this.addParticularStock.hide();
                // If success display the success message in view
                this.success.next('Successfully updated the stock.');
                // To set the null for category
                this.categories = null;
            },
            (res: HttpErrorResponse) => {
                // If error response display the error message in view
                this.error.next(res.error.fieldErrors[0].message);
            }
        );
    }

    /**
     * Add more stock
     * @param value as object of godownStock
     */
    addMoreStock(value): void {
        this.isCollapsedStockDetails = true;
        // Create a new object for stock and stock details
        this.godownStock = new GodownStock();
        // Assign a params value to an stock bject
        this.godownStock = value;
        this.godownStockDetail = new GodownStockDetails();
        // Assign a value to a variable
        this.godownStockDetail.status = STATUS_DIRECT_ADD;
        // Show the model popup
        this.addParticularStock.show();
    }

    /**
     * Add the inventory with inventory details
     * @param value as object of godownStock
     */
    addInventoryQuantity(value): void {
        // Check the empty validation for date and quantity.
        if ((this.godownStockDetail.date != null) && (this.godownStockDetail.quantity != null)) {
            // Add a new quantity with old quantity and assign to a field
            this.godownStock.currentQuantity =
                +value.currentQuantity + +this.godownStockDetail.quantity;
            this.godownStock.addedQuantity =
                +value.addedQuantity + +this.godownStockDetail.quantity;
            // Call a godown stock update service to update the record
            this.subscribeToSaveResponse(this.godownStockService.update(this.godownStock));
        } else {
            this.error.next('Date and quantity cannot be blank.');
        }
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

    // Get the page number
    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    // Based on sort load the data
    transition() {
        this.router.navigate(['/godown-stock'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.getgodownStockList();
    }

    // Clear the filter
    clear() {
        this.page = 0;
        this.router.navigate([
            '/godown-stock',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.getgodownStockList();
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
    trackId(index: number, item: IGodownStock) {
        return item.id;
    }

    // Set the page size, total record count in header
    private paginateGodownStocks(data: IGodownStock[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.godownStocks = data;
    }
}
