// Import needed component and dependency
import { Component, OnInit } from '@angular/core';
import { GodownService } from 'app/entities/service/godown.service';
import { IGodown, GodownModel } from 'app/shared/model/godown.model';
import { FinancialYearSettingsService } from 'app/entities/service/financial-year-settings.service';
import { IFinancialYearSettings } from 'app/shared/model/financial-year-settings.model';

import { ModalDirective } from 'ngx-bootstrap/modal';
import { ViewChild } from '@angular/core';

import { JhiParseLinks } from 'ng-jhipster';

import * as moment from 'moment';
import { DATE_TIME_FORMAT, ITEMS_PER_PAGE, SOFT_DELETE_STATUS, STATUS_ACTIVE } from 'app/shared';
import { HttpResponse, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';

// Display the alert message of success and error
import { Subject } from 'rxjs';
import { debounceTime } from 'rxjs/operators';

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

    // Title and alertTitle declation as String
    title: String;
    alertTitle: String;

    // To display the success message
    private success = new Subject<string>();
    successMessage: string;

    // To display the error message
    // private error = new Subject<string>();
    // errorMessage: string;

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

    // Declare a modal popup
    @ViewChild('godownModal') public godownModal: ModalDirective;

    constructor(
        private godownService: GodownService,
        private settingsService: FinancialYearSettingsService,
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
        // Call a function to get list of godowns
        this.getGodownList();
        // Call a function to get active batch id
        this.getActiveRecord();

        // To set the time for automatic alert close
        setTimeout(() => this.staticAlertClosed = true, 20000);

        // Set the success message with debounce time
        this.success.subscribe(message => this.successMessage = message);
        this.success.pipe(
            debounceTime(5000)
        ).subscribe(() => this.successMessage = null);

        // To set the error message with debounce time
        // this.error.subscribe(message => this.errorMessage = message);
        // this.error.pipe(
            // debounceTime(5000)
        // ).subscribe(() => this.errorMessage = null);
    }

    // Call a service function to get list of godowns
    getGodownList(): void {
        // Get the list of godown
        this.godownService.query({
            page: this.page - 1,
            size: this.itemsPerPage,
            sort: this.sort(),
            filter: {'status.equals': STATUS_ACTIVE}
        })
        .subscribe((res: HttpResponse<IGodown[]>) => this.paginateGodownLists(res.body, res.headers));
    }

    // Call a service function to get list of active batch
    getActiveRecord(): void {
        // Get the list of active batch record and assign a 0th index array value to an batch id
        this.settingsService.getActiveRecord().subscribe((res: HttpResponse<IFinancialYearSettings[]>) => {
            this.financialYearId = res.body[0].id;
        });
    }

    // Send a godown object to a service (create or update)
    save() {
        this.godownObject.status = STATUS_ACTIVE;
        if (this.godownObject.id !== undefined) {
            this.alertTitle = 'updated';
            this.subscribeToSaveResponse(
                this.godownService.update(this.godownObject), this.alertTitle);
        } else {
            this.alertTitle = 'created';
            this.godownObject.financialYearGodownId = this.financialYearId;
            this.subscribeToSaveResponse(
                this.godownService.create(this.godownObject), this.alertTitle);
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IGodown>>, alertTitle) {
        result.subscribe(
            (res: HttpResponse<IGodown>) => {
                this.godownModal.hide();
                this.godownObject = new GodownModel();
                // alert('Godown Created/Updated Successfully.');
                this.success.next(`Successfully ${alertTitle}`);
                this.getGodownList();
            },
            (res: HttpErrorResponse) => {
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    // show model popup to create godown value
    createGodown(): void {
        this.godownObject = new GodownModel();
        this.godownModal.show();
        this.title = 'Create Zone:';
    }

    // show model popup to update godown value
    getGodownValue(value: GodownModel): void {
        this.godownModal.show();
        this.godownObject = value;
        this.title = `Update Zone: ${value.name}`;
    }

    // soft delete
    softDelete(value: GodownModel): void {
        this.godownObject = value;
        this.godownObject.status = SOFT_DELETE_STATUS;
        this.godownService.update(this.godownObject)
        .subscribe(
            data => {
                this.success.next(`Godown deleted successfully`);
                this.getGodownList();
            },
            (res: HttpErrorResponse) => {
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    // commented for making the delete as soft one which means not to delete from db
    // deleteGodown(godown: GodownModel): void {
    //     this.godownService.delete(godown.id).subscribe(data => {
    //         // alert('Godown deleted Successfully.');
    //         this.success.next(`Deleted successfully`);
    //         this.godowns = this.godowns.filter(u => u !== godown);
    //     });
    // }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

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

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    trackId(index: number, item: IGodown) {
        return item.id;
    }

    private paginateGodownLists(data: IGodown[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.godowns = data;
    }
}
