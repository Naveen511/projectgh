// Import needed component and dependency
import { Component, OnInit } from '@angular/core';
import { OperationalHeadService } from 'app/entities/service/operational-head.service';
import { IOperationalHead, OperationalHeadModel, STATUS_ACTIVE } from 'app/shared/model/operational-head.model';

import { ModalDirective } from 'ngx-bootstrap/modal';
import { ViewChild } from '@angular/core';

import { JhiParseLinks } from 'ng-jhipster';

import * as moment from 'moment';
import { DATE_TIME_FORMAT, ITEMS_PER_PAGE, SOFT_DELETE_STATUS } from 'app/shared';
import { HttpResponse, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';

// Display the alert message of success and error
import { Subject } from 'rxjs';
import { debounceTime } from 'rxjs/operators';
import { EntityAuditEvent, ACTION_STATUS_UPDATE } from 'app/admin/entity-audit/entity-audit-event.model';
import { EntityAuditService } from 'app/admin/entity-audit/entity-audit.service';

// Mension the html, css/sass files
@Component({
    selector: 'jhi-operational-head',
    templateUrl: 'operational-head.component.html'
})

/**
 * Class OperationalHeadComponent used to create/update a head office, List all head office.
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
        // Call a function to get list of head office
        this.getList();

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

    // Call a service function to get list of head office
    getList(): void {
        // Get the list of head office
        this.operationalHeadService.query({
            page: this.page - 1,
            size: this.itemsPerPage,
            sort: this.sort(),
            filter: {'status.equals': STATUS_ACTIVE}
        })
        .subscribe((res: HttpResponse<IOperationalHead[]>) => this.paginateLists(res.body, res.headers));
    }

    // Send a head office object to a service (create or update)
    save() {
        this.operationalHead.status = STATUS_ACTIVE;
        if (this.operationalHead.id !== undefined) {
            this.alertTitle = 'updated';
            this.subscribeToSaveResponse(
                this.operationalHeadService.update(this.operationalHead), this.alertTitle);
        } else {
            this.alertTitle = 'created';
            this.subscribeToSaveResponse(
                this.operationalHeadService.create(this.operationalHead), this.alertTitle);
        }
    }

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
    create(): void {
        this.operationalHead = new OperationalHeadModel();
        this.operationalHeadModal.show();
        this.title = 'Create Operation Head:';
    }

    // show model popup to update value
    getValue(value: OperationalHeadModel): void {
        this.operationalHeadModal.show();
        this.operationalHead = value;
        this.title = `Update Operation Head: ${value.name}`;
    }

    // getAuditHistory(id: number): void {
    //     this.auditService.findByParticularUpdatedEntityDetails('com.niche.ng.domain.OperationalHead', id, ACTION_STATUS_UPDATE, this.selectedLimit)
    //     .subscribe((res: HttpResponse<EntityAuditEvent[]>) => {
    //         console.log(res.body);
    //         this.audits = res.body;
    //         this.isCollapsed = false;
    //     });
    // }

    // soft delete
    softDelete(headOffice: OperationalHeadModel): void {
        // console.log('head', headOffice);
        this.operationalHead = headOffice;
        this.operationalHead.status = SOFT_DELETE_STATUS;
        this.operationalHeadService.update(this.operationalHead)
        .subscribe(
            data => {
                // console.log('upda', this.operationalHead);
                alert('Delete Successfully.');
                this.getList();
            },
            (res: HttpErrorResponse) => {
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    // commented for making the delete as soft one which means not to delete from db
    // delete(headOffice: OperationalHeadModel): void {
    //     this.operationalHeadService.delete(headOffice.id).subscribe(data => {
    //         // alert('Head Office deleted Successfully.');
    //         this.success.next(`Deleted successfully`);
    //         this.list = this.list.filter(u => u !== headOffice);
    //     });
    // }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

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

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    trackId(index: number, item: IOperationalHead) {
        return item.id;
    }

    private paginateLists(data: IOperationalHead[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.list = data;
    }
}
