// Import needed component and dependency
import { Component, OnInit } from '@angular/core';
import { FinancialYearSettings, IFinancialYearSettings, STATUS_ACTIVE } from 'app/shared/model/financial-year-settings.model';
import { FinancialYearSettingsService } from 'app/entities/service/financial-year-settings.service';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { JhiParseLinks } from 'ng-jhipster';

import * as moment from 'moment';
import { DATE_TIME_FORMAT, DEFAULT_STATUS, ITEMS_PER_PAGE, SOFT_DELETE_STATUS} from 'app/shared';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PickListService } from 'app/entities/service/pick-list.service';
import { PickListValueService } from 'app/entities/service/pick-list-value.service';
import { IPickList } from 'app/shared/model/pick-list.model';
import { IPickListValue } from 'app/shared/model/pick-list-value.model';

// Display the alert message of success and error
import { Subject } from 'rxjs';
import { debounceTime } from 'rxjs/operators';

// Mension the html, css/sass files
@Component({
    selector: 'jhi-financial-year-settings',
    templateUrl: 'financial-year-settings.component.html'
})

/**
 * Class FinancialYearSettingsComponent used to create/update a settings, List all settings.
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

    // Date picker id declation
    startDateDp: any;
    endDateDp: any;

    statusArray: any;

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
    @ViewChild('settingsModal') public settingsModal: ModalDirective;

    constructor(
        private settingsService: FinancialYearSettingsService,
        private pickListService: PickListService,
        private pickListValueService: PickListValueService,
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
        // Call a function to get list of financial year settings
        this.getYearSettingsList();
        this.getPickList();
        this.statusArray = DEFAULT_STATUS;

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

    // Call a service function to get list of financial year settings
    getYearSettingsList(): void {
        // Get the list of financial year settings
        this.settingsService.query({
            filter: {'status.equals': STATUS_ACTIVE}
        }).subscribe((res: HttpResponse<IFinancialYearSettings[]>) => {
            this.settings = res.body;
        });
    }

    // Get the pick list value from the pick list table
    getPickList(): void {
        // Get the list of picklist
        this.pickListService.query({
            filter: {'status.equals': STATUS_ACTIVE}
        }).subscribe((res: HttpResponse<IPickList[]>) => {
            this.pickLists = res.body;
        });
    }

    // Get the years from the financial year table
    getYears(id): void {
        this.pickListValueService.getVariety(id).subscribe((res: HttpResponse<IPickListValue[]>) => {
            // console.log(res.body);
            this.years = res.body;
        });
    }

    // Send a financial year settings object to a service (create or update)
    save() {
        this.setting.endDate = moment(this.setting.endDate, DATE_TIME_FORMAT);
        this.setting.status = STATUS_ACTIVE;
        // console.log(this.setting);
        // If the settings id is not equal to undefined
        if (this.setting.id !== undefined) {
            this.alertTitle = 'Updated';
            this.subscribeToSaveResponse(
                this.settingsService.update(this.setting), this.alertTitle
            );
        } else {
            this.alertTitle = 'Created';
            this.subscribeToSaveResponse(
                this.settingsService.create(this.setting), this.alertTitle
            );
        }
    }

    // To sava the response and hide the settings model
    private subscribeToSaveResponse(result: Observable<HttpResponse<IFinancialYearSettings>>, alertTitle) {
        result.subscribe(
            (res: HttpResponse<IFinancialYearSettings>) => {
                this.settingsModal.hide();
                this.setting = new FinancialYearSettings();
                // alert('Created/Updated Successfully.');
                this.success.next(`${alertTitle} successfully`);
                this.getYearSettingsList();
            },
            (res: HttpErrorResponse) => {
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    // show model popup to create financial year settings value
    create(): void {
        this.setting = new FinancialYearSettings();
        this.settingsModal.show();
    }

    // show model popup to update financial year settings value
    getParticularRow(value: FinancialYearSettings): void {
        this.settingsModal.show();
        this.setting = value;
    }

    // soft delete
    softDelete(yearSettings: FinancialYearSettings): void {
        // console.log('head', sector);
       this.setting = yearSettings;
       this.setting.status = SOFT_DELETE_STATUS;
       // console.log('date', this.sectorObject);
       this.settingsService.update(this.setting)
       .subscribe(
           data => {
                // console.log('upda', this.sectorObject);
               this.success.next(`Year deleted successfully`);
               this.getYearSettingsList();
           },
           (res: HttpErrorResponse) => {
               alert(res.error.fieldErrors[0].message);
           }
       );
   }

    // commented for making the delete as soft one which means not to delete from db
    // delete(yearSettings: FinancialYearSettings): void {
    //     this.settingsService.delete(yearSettings.id).subscribe(data => {
    //         // alert('Deleted Successfully.');
    //         this.success.next(`Successfully deleted`);
    //         this.settings = this.settings.filter(u => u !== yearSettings);
    //     });
    // }

    // changed the status of the particular row values
    changeStatus(value: FinancialYearSettings): void {
        this.alertTitle = 'Updated';
        this.subscribeToSaveResponse(
            this.settingsService.update(value), this.alertTitle
        );
    }
}
