/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/22
 *  Target: yarn
 *******************************************************************************/

// Import needed component, model and dependency
import { Component, OnInit } from '@angular/core';
import { ViewChild } from '@angular/core';
import { JhiParseLinks } from 'ng-jhipster';
import { HttpResponse, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { debounceTime } from 'rxjs/operators';
import { ModalDirective } from 'ngx-bootstrap/modal';

import { IGodown, GodownModel } from 'app/shared/model/godown.model';
import { IFinancialYearSettings } from 'app/shared/model/financial-year-settings.model';

import { GodownService } from 'app/entities/service/godown.service';
import {
    FinancialYearSettingsService
} from 'app/entities/service/financial-year-settings.service';

import {
    ITEMS_PER_PAGE, SOFT_DELETE_STATUS, STATUS_ACTIVE, ALERT_TIME_OUT_5000
} from 'app/shared';

// Mension the html, css/sass files
@Component({
    selector: 'jhi-godown',
    templateUrl: 'godown.component.html'
})

/**
 * Class GodownComponent used to create/update a godown, List all godowns.
 * Declared an Godown object to create and update.
 * Declared an Array variable to set list of godowns.
 * Using a modal popup directive create and update form is displayed.
 */
export class GodownComponent implements OnInit {
    // Create object for model
    godownObject: GodownModel = new GodownModel();
    // create empty array for each service
    godowns: IGodown[];
    financialYearId: number;

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
    @ViewChild('godownModal') public godownModal: ModalDirective;

    constructor(
        private godownService: GodownService,
        private settingsService: FinancialYearSettingsService,
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
        // Call a function to get list of godowns
        this.getGodownList();
        // Call a function to get active batch id
        // this.getActiveRecord();

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
     * Call a service function to get list of godowns
     */
    getGodownList(): void {
        // Get the list of godown
        this.godownService.query({
            page: this.page - 1,
            size: this.itemsPerPage,
            sort: this.sort(),
            filter: { 'status.equals': STATUS_ACTIVE }
        })
        .subscribe((res: HttpResponse<IGodown[]>) => {
            this.paginateGodownLists(res.body, res.headers);
        });
    }

    /**
     * Call a service function to get list of active batch
     */
    getActiveRecord(): void {
        // Get the list of active batch record and
        // assign a 0th index array value to an batch id
        this.settingsService.query({ filter: { 'status.equals': STATUS_ACTIVE }})
        .subscribe((res: HttpResponse<IFinancialYearSettings[]>) => {
            if (res.body.length > 0) {
                this.financialYearId = res.body[0].id;
            } else {
                // If error response display the error message in view
                this.error.next('There is no active year in calendar settings.');
            }
        });
    }

    /**
     * Send a godown object to a service (create or update)
     */
    save() {
        // Assign a value to an variable
        this.godownObject.status = STATUS_ACTIVE;
        if (this.godownObject.id !== undefined) {
            // Call a metheod to update record
            this.subscribeToSaveResponse(
                this.godownService.update(this.godownObject),
                'updated.'
            );
        } else {
            // Assign a value to an varaible
            this.godownObject.financialYearGodownId = this.financialYearId;
            // Call a metheod to create record
            this.subscribeToSaveResponse(
                this.godownService.create(this.godownObject),
                'created.'
            );
        }
    }

    /**
     * To save the godown and get the object
     * @param result object of godown details
     * @param alertTitle message title for alert
     */
    private subscribeToSaveResponse(
        result: Observable<HttpResponse<IGodown>>, alertTitle
    ) {
        result.subscribe(
            (res: HttpResponse<IGodown>) => {
                // Hide the model popup
                this.godownModal.hide();
                // Create empty object for godown
                this.godownObject = new GodownModel();
                // If success display the success message in view
                this.success.next(`Successfully ${alertTitle}`);
                // Call a function to display list of godown
                this.getGodownList();
            },
            (res: HttpErrorResponse) => {
                // If error response display the error message in view
                this.error.next(res.error.fieldErrors[0].message);
            }
        );
    }

    /**
     * Show model popup to create godown value
     */
    createGodown(): void {
        // Call a function to get active batch id
        this.getActiveRecord();
        // Create empty object for godown
        this.godownObject = new GodownModel();
        // Show the model popup
        this.godownModal.show();
        this.title = 'Create Godown:';
    }

    /**
     * Show model popup to update godown value
     * @param value as object of godown
     */
    getGodownValue(value: GodownModel): void {
        // Show the model popup
        this.godownModal.show();
        // Assign a value to a object
        this.godownObject = value;
        this.title = `Update Godown: ${value.name}`;
    }

    /**
     * Soft delete - update the status as deleted
     * @param value as object of godown
     */
    softDelete(value: GodownModel): void {
        // Confirmation Pop-up for the Delete
        if (window.confirm('Are sure you want to delete?')) {
            // Assign a value to a object
            this.godownObject = value;
            this.godownObject.status = SOFT_DELETE_STATUS;
            // Call a godown update service
            this.godownService.update(this.godownObject).subscribe(
                data => {
                    // If success display the success message in view
                    this.success.next(`Godown deleted successfully.`);
                    // Call a function to display list of godown
                    this.getGodownList();
                },
                (res: HttpErrorResponse) => {
                    // If error response display the error message in view
                    this.error.next(res.error.fieldErrors[0].message);
                }
            );
        }
    }

    /**
     * If the godown model pop up closed, to call the get godown list function
     */
    closeGodownModal(): void {
        // Hide the godown model pop-up
        this.godownModal.hide();
        // Call the get godown List function
        this.getGodownList();
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
        this.router.navigate(['/godown'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.getGodownList();
    }

    // Clear the filter
    clear() {
        this.page = 0;
        this.router.navigate([
            '/godown',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.getGodownList();
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
    trackId(index: number, item: IGodown) {
        return item.id;
    }

    // Set the page size, total record count in header
    private paginateGodownLists(data: IGodown[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.godowns = data;
    }
}
