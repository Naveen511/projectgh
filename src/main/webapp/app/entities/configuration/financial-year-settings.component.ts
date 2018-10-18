/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02 11:27:58
 *  Target: yarn
 *******************************************************************************/

// Import needed model, service, shared, subject to display alert message
// and angular dependency
import { Component, OnInit } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { JhiParseLinks } from 'ng-jhipster';
import * as moment from 'moment';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Subject } from 'rxjs';
import { debounceTime } from 'rxjs/operators';

import {
    FinancialYearSettings, IFinancialYearSettings, STATUS_ACTIVE, DISPLAY_NAME_YEAR
} from 'app/shared/model/financial-year-settings.model';
import { IPickList } from 'app/shared/model/pick-list.model';
import { IPickListValue } from 'app/shared/model/pick-list-value.model';

import {
    FinancialYearSettingsService
} from 'app/entities/service/financial-year-settings.service';
import { PickListService } from 'app/entities/service/pick-list.service';
import { PickListValueService } from 'app/entities/service/pick-list-value.service';

import {
    DATE_TIME_FORMAT, DEFAULT_STATUS, ITEMS_PER_PAGE, SOFT_DELETE_STATUS,
    ALERT_TIME_OUT_5000, ALERT_TIME_OUT_3000
} from 'app/shared';

// Mention the html, css/sass files
@Component({
    selector: 'jhi-financial-year-settings',
    templateUrl: 'financial-year-settings.component.html'
})

/**
 * Class FinancialYearSettingsComponent used to create/update a settings,
 * List all settings.
 * Declared an FinancialYearSettings object to create and update.
 * Declared an Array variable to set list of financialYearSettings.
 * Using a modal popup directive create and update form is displayed.
 */
export class FinancialYearSettingsComponent implements OnInit {
    // Create object for model
    setting: FinancialYearSettings = new FinancialYearSettings();
    // create empty array for each service
    settings: IFinancialYearSettings[];
    pickLists: IPickList[];
    years: IPickListValue[];

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

    // Date picker id declation
    startDateDp: any;
    endDateDp: any;

    statusArray: any;

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

    pickListId: any;

    // Declare a modal popup
    @ViewChild('settingsModal') public settingsModal: ModalDirective;

    constructor(
        private settingsService: FinancialYearSettingsService,
        private pickListService: PickListService,
        private pickListValueService: PickListValueService,
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
        // Call a function to get list of financial year settings
        this.getYearSettingsList();
        // To get picklist value
        // this.getPickList();
        this.statusArray = DEFAULT_STATUS;

        // To set the time for automatic alert close
        setTimeout(() => (this.staticAlertClosed = true), ALERT_TIME_OUT_5000);

        // Set the success message with debounce time
        this.success.subscribe(message => (this.successMessage = message));
        this.success.pipe(debounceTime(ALERT_TIME_OUT_3000))
            .subscribe(() => (this.successMessage = null));

        // To set the error message with debounce time
        this.error.subscribe(message => (this.errorMessage = message));
        this.error.pipe(debounceTime(ALERT_TIME_OUT_5000))
            .subscribe(() => (this.errorMessage = null));
    }

    /**
     * Get the settings from the settings(Financial Year table)
     * using active status and set into the settings object
     */
    getYearSettingsList(): void {
        // Get the list of financial year settings
        this.settingsService.query({
            filter: { 'status.greaterThan': SOFT_DELETE_STATUS }
        }).subscribe((res: HttpResponse<IFinancialYearSettings[]>) => {
            this.settings = res.body;
        });
    }

    /**
     * Get the pick list value from the pick list table
     * using the active status and set into the pick list object
     */
    /*getPickList(): void {
        // Get the list of picklist
        this.pickListService.query({
            filter: { 'status.equals': STATUS_ACTIVE }
        }).subscribe((res: HttpResponse<IPickList[]>) => {
            this.pickLists = res.body;
        });
    }*/

    /**
     * Get the calendar settings years from the Pick list value table
     * using status and picklistId from the PickListValue
     * and set the values into the years object
     * @param id number
     */
    getYears(id): void {
        // this.pickListValueService.getVariety(id)
        this.pickListValueService.query({
            filter: {
                'status.equals': STATUS_ACTIVE,
                'pickListId.equals': id
            }
        }).subscribe((res: HttpResponse<IPickListValue[]>) => {
            this.years = res.body;
        });
    }

    /**
     * Save the calendar settings(Create/Update)
     * By default set the status as active and alert title
     */
    save() {
        this.setting.endDate = moment(this.setting.endDate, DATE_TIME_FORMAT);
        this.setting.status = STATUS_ACTIVE;
        // console.log(this.setting);
        // If the settings id is not equal to undefined
        if (this.setting.id !== undefined) {
            // Create the settings of financial year
            this.subscribeToSaveResponse(
                this.settingsService.update(this.setting), 'Updated'
            );
        } else {
            // Create the settings of financial year
            this.subscribeToSaveResponse(
                this.settingsService.create(this.setting), 'Created'
            );
        }
    }

    /**
     * Return saved response and hide the settings model
     * @param result settings model
     * @param alertTitle message for alert title
     */
    private subscribeToSaveResponse(result: Observable<HttpResponse<IFinancialYearSettings>>, alertTitle) {
        result.subscribe(
            (res: HttpResponse<IFinancialYearSettings>) => {
                // Hide the settings modal
                this.settingsModal.hide();
                // Create new row for the Financial Year Settings
                this.setting = new FinancialYearSettings();
                // alert('Created/Updated Successfully.');
                this.success.next(`${alertTitle} successfully.`);
                this.getYearSettingsList();
            },
            (res: HttpErrorResponse) => {
                // Return the error alert for the create/update
                this.error.next(res.error.fieldErrors[0].message);
            }
        );
    }

    /**
     * Create the calendar settings and show the settings modal
     * Get the calendar year from the pick list table and passed
     * into the getYear function
     */
    create(): void {
        this.setting = new FinancialYearSettings();
        this.settingsModal.show();
        // To get the pick list Id for Pick List Year
        // this.pickListService.getPickListId(DISPLAY_NAME_YEAR)
        this.pickListService.query({
            filter: {
                'status.equals': STATUS_ACTIVE,
                'displayLabelName.equals': DISPLAY_NAME_YEAR
            }
        })
        .subscribe((res: HttpResponse<IPickList[]>) => {
            if (res.body.length > 0) {
                this.getYears(res.body[0].id);
            }

        });
    }

    /**
     * Show model popup to update financial year settings value
     * set the settings value as FinancialYearSettings
     * @param value FinancialYearSettings
     */
    getParticularRow(value: FinancialYearSettings): void {
        this.settingsModal.show();
        this.setting = value;
    }

    /**
     * Delete the calendar settings by updated the status as delete
     * @param yearSettings FinancialYearSettings
     */
    softDelete(yearSettings: FinancialYearSettings): void {
        // Confirmation Pop-up for the Delete
        if (window.confirm('Are sure you want to delete?')) {
            this.setting = yearSettings;
            this.setting.status = SOFT_DELETE_STATUS;
            // console.log('date', this.sectorObject);
            this.settingsService.update(this.setting).subscribe(
                data => {
                    // console.log('upda', this.sectorObject);
                    this.success.next(`Successfully deleted.`);
                    this.getYearSettingsList();
                },
                (res: HttpErrorResponse) => {
                    // Return the error alert
                    alert(res.error.fieldErrors[0].message);
                }
            );
        }
    }

    // commented for making the delete as soft one which means not to delete from db
    // delete(yearSettings: FinancialYearSettings): void {
    //     this.settingsService.delete(yearSettings.id).subscribe(data => {
    //         // alert('Deleted Successfully.');
    //         this.success.next(`Successfully deleted`);
    //         this.settings = this.settings.filter(u => u !== yearSettings);
    //     });
    // }

    /**
     * Changed the status as active or inactive for the calendar settings
     * of the particular row values
     * @param value as object of FinancialYearSettings
     */
    changeStatus(value: FinancialYearSettings): void {
        // Updated the existing record using hte financial year Id
        this.subscribeToSaveResponse(
            this.settingsService.update(value), 'Updated'
        );
    }
}
