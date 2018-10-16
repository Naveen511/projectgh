/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02 11:27:58
 *  Target: yarn
 *******************************************************************************/

// Import needed component and dependency
import { Component, OnInit } from '@angular/core';
import { OperationalHeadService } from 'app/entities/service/operational-head.service';
import { IOperationalHead, OperationalHeadModel, STATUS_ACTIVE } from 'app/shared/model/operational-head.model';

import { ModalDirective } from 'ngx-bootstrap/modal';
import { ViewChild } from '@angular/core';
import { JhiParseLinks } from 'ng-jhipster';

import { ITEMS_PER_PAGE, SOFT_DELETE_STATUS, ALERT_TIME_OUT_5000, ALERT_TIME_OUT_3000 } from 'app/shared';
import { HttpResponse, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';

// Display the alert message of success and error
import { Subject } from 'rxjs';
import { debounceTime } from 'rxjs/operators';
import { EntityAuditEvent } from 'app/admin/entity-audit/entity-audit-event.model';
import { EntityAuditService } from 'app/admin/entity-audit/entity-audit.service';

// Mension the html, css/sass files
@Component({
    selector: 'jhi-operational-head',
    templateUrl: 'operational-head.component.html'
})

/**
 * Class OperationalHeadComponent used to create/update a head office,
 * List all head office.
 * Declared an OperationalHead object to create and update.
 * Declared an Array variable to set list of head office.
 * Using a modal popup directive create and update form is displayed.
 */
export class OperationalHeadComponent implements OnInit {
    // Create object for model
    operationalHead: OperationalHeadModel = new OperationalHeadModel();

    // create empty array for each service
    list: IOperationalHead[];
    audits: EntityAuditEvent[];
    isCollapsed = true;

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

    limits = [25, 50, 100, 200];
    selectedLimit = this.limits[0];

    // Declare a modal popup
    @ViewChild('operationalHeadModal') public operationalHeadModal: ModalDirective;

    constructor(
        private operationalHeadService: OperationalHeadService,
        private parseLinks: JhiParseLinks,
        private router: Router,
        private activatedRoute: ActivatedRoute,
        private auditService: EntityAuditService
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
        // Call a function to get list of head office
        this.getList();

        // To set the time for automatic alert close
        setTimeout(() => (this.staticAlertClosed = true), ALERT_TIME_OUT_5000);

        // Set the success message with debounce time
        this.success.subscribe(message => (this.successMessage = message));
        this.success.pipe(debounceTime(ALERT_TIME_OUT_3000)).subscribe(() => (this.successMessage = null));

        // To set the error message with debounce time
        this.error.subscribe(message => (this.errorMessage = message));
        this.error.pipe(debounceTime(ALERT_TIME_OUT_5000)).subscribe(() => (this.errorMessage = null));
    }

    /**
     * Get the head of list from the operational head table
     * using the active status
     */
    getList(): void {
        // Get the list of head office
        this.operationalHeadService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort(),
                filter: { 'status.equals': STATUS_ACTIVE }
            })
            .subscribe((res: HttpResponse<IOperationalHead[]>) => {
                this.paginateLists(res.body, res.headers);
            });
    }

    /**
     * Send a head office object to a service (create or update)
     */
    save() {
        // If the operation Id is not empty, goto update action
        if (this.operationalHead.id !== undefined) {
            this.operationalHeadService
                .query({
                    filter: {
                        'id.equals': this.operationalHead.id,
                        'name.equals': this.operationalHead.name,
                        'status.equals': STATUS_ACTIVE
                    }
                })
                .subscribe((res: HttpResponse<IOperationalHead[]>) => {
                    // Same value - 1, differnt value 0
                    if (res.body.length === 1) {
                        this.saveHeadOffice();
                    } else if (res.body.length === 0) {
                        this.validateHeadOffice();
                    } else {
                        this.error.next('Already this name has taken');
                    }
                });
        } else {
            // Validate the head office
            this.validateHeadOffice();
        }
    }

    /**
     * Save the head office,
     * By default set statu as active
     */
    saveHeadOffice() {
        this.operationalHead.status = STATUS_ACTIVE;
        if (this.operationalHead.id !== undefined) {
            this.alertTitle = 'updated';
            this.subscribeToSaveResponse(this.operationalHeadService.update(this.operationalHead), this.alertTitle);
        } else {
            this.alertTitle = 'created';
            this.subscribeToSaveResponse(this.operationalHeadService.create(this.operationalHead), this.alertTitle);
        }
    }

    /**
     * Validate the Head office
     */
    validateHeadOffice() {
        this.operationalHeadService
            .query({
                filter: {
                    'name.equals': this.operationalHead.name,
                    'status.equals': STATUS_ACTIVE
                }
            })
            .subscribe((res: HttpResponse<IOperationalHead[]>) => {
                if (res.body.length > 0) {
                    this.error.next('Already this name has taken');
                } else {
                    this.saveHeadOffice();
                }
            });
    }

    /**
     * return the saved response of success and
     * error from the operational head table
     * @param result object
     * @param alertTitle string
     */
    private subscribeToSaveResponse(result: Observable<HttpResponse<IOperationalHead>>, alertTitle) {
        result.subscribe(
            (res: HttpResponse<IOperationalHead>) => {
                this.operationalHeadModal.hide();
                this.operationalHead = new OperationalHeadModel();
                this.success.next(`Successfully ${alertTitle}`);
                this.getList();
            },
            (res: HttpErrorResponse) => {
                this.error.next(res.error.fieldErrors[0].message);
            }
        );
    }

    // show model popup to create head office value
    /**
     * create operational head with new row and
     * show the operataionalHeadModal
     * set the title for the Head office
     */
    create(): void {
        this.operationalHead = new OperationalHeadModel();
        this.operationalHeadModal.show();
        // Set title for the Head office
        this.title = 'Create Head Office:';
    }

    /**
     * Show model popup to update the head office
     * @param value OperationalHeadModel
     */
    getValue(value: OperationalHeadModel): void {
        this.operationalHeadModal.show();
        this.operationalHead = value;
        this.title = `Update Head Office: ${value.name}`;
    }

    /**
     * Delete the calendar settings by updated the status as delete
     * @param headOffice OperationalHeadModel
     */
    softDelete(headOffice: OperationalHeadModel): void {
        // if(confirm("test")) { }
        // Confirmation Pop-up for the Delete
        if (window.confirm('Are sure you want to delete?')) {
            // console.log('head', headOffice);
            this.operationalHead = headOffice;
            this.operationalHead.status = SOFT_DELETE_STATUS;
            this.operationalHeadService.update(this.operationalHead).subscribe(
                data => {
                    // console.log('upda', this.operationalHead);
                    // alert('Delete Successfully.');
                    this.success.next(`Deleted successfully`);
                    this.getList();
                },
                (res: HttpErrorResponse) => {
                    alert(res.error.fieldErrors[0].message);
                }
            );
        }
    }

    /**
     * If the zone model pop up closed,
     * call the get zone list function, to diplay original records
     */
    closeOperationalHeadModal(): void {
        // Hide the zone model pop-up
        this.operationalHeadModal.hide();
        // Call the getZone List function
        this.getList();
    }

    /**
     * Get the page number
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
        this.router.navigate(['/operational-head'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.getList();
    }

    /**
     * Clear the filter
     */
    clear() {
        this.page = 0;
        this.router.navigate([
            '/operational-head',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.getList();
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
     * @param index number
     * @param item IOperationalHead
     */
    trackId(index: number, item: IOperationalHead) {
        return item.id;
    }

    /**
     * Set the page size, total record count in header
     * @param data IOperationalHead
     * @param headers headers
     */
    private paginateLists(data: IOperationalHead[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.list = data;
    }

    /**
    // getAuditHistory(id: number): void {
    //     this.auditService.findByParticularUpdatedEntityDetails('com.niche.ng.domain.OperationalHead', id, ACTION_STATUS_UPDATE, this.selectedLimit)
    //     .subscribe((res: HttpResponse<EntityAuditEvent[]>) => {
    //         console.log(res.body);
    //         this.audits = res.body;
    //         this.isCollapsed = false;
    //     });
    // }

    // commented for making the delete as soft one which means not to delete from db
    delete(headOffice: OperationalHeadModel): void {
        this.operationalHeadService.delete(headOffice.id).subscribe(data => {
            // alert('Head Office deleted Successfully.');
            this.success.next(`Deleted successfully`);
            this.list = this.list.filter(u => u !== headOffice);
        });
    } */
}
