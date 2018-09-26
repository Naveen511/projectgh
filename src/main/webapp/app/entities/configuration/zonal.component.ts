// Import needed component and dependency
import { Component, OnInit } from '@angular/core';
import { ZonalService } from 'app/entities/service/zonal.service';
import { MapZonalWithOhService } from 'app/entities/service/map-zonal-with-oh.service';
import { OperationalHeadService } from 'app/entities/service/operational-head.service';
import { IZonal, ZonalModel } from 'app/shared/model/zonal.model';
import { IMapZonalWithOh, MapZonalWithOhModel, STATUS_INACTIVE } from 'app/shared/model/map-zonal-with-oh.model';
import { IOperationalHead } from 'app/shared/model/operational-head.model';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { ViewChild } from '@angular/core';

import { JhiParseLinks } from 'ng-jhipster';

import * as moment from 'moment';
import { DATE_TIME_FORMAT, ITEMS_PER_PAGE, STATUS_ACTIVE, SOFT_DELETE_STATUS } from 'app/shared';
import { HttpResponse, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

// Display the alert message of success and error
import { Subject } from 'rxjs';
import { debounceTime } from 'rxjs/operators';
import { FinancialYearSettingsService } from 'app/entities/service/financial-year-settings.service';
import { IFinancialYearSettings } from 'app/shared/model/financial-year-settings.model';
import { ActivatedRoute, Router } from '@angular/router';
import { EntityAuditService } from 'app/admin/entity-audit/entity-audit.service';
import { EntityAuditEvent, ACTION_STATUS_UPDATE } from 'app/admin/entity-audit/entity-audit-event.model';

// Mension the html, css/sass files
@Component({
    selector: 'jhi-zonal',
    templateUrl: 'zonal.component.html'
})

/**
 * Class ZonalComponent used to create/update a zonal, List all zonals.
 * Declared an Zonal object to create and update.
 * Declared an Array variable to set list of zonals.
 * Using a modal popup directive create and update form is displayed.
 */
export class ZonalComponent implements OnInit {
    // Create object for model
    zonalObject: ZonalModel = new ZonalModel();
    mapZoneWithOh: MapZonalWithOhModel = new MapZonalWithOhModel();
    // create empty array for each service
    operationalHeads: IOperationalHead[];
    zonals: IZonal[];
    mapZoneWithOhs: IMapZonalWithOh[];
    toDate: string;
    batchId: number;
    updateStatus: number;
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
    @ViewChild('zoneModal') public zoneModal: ModalDirective;
    @ViewChild('moveZone') public moveZone: ModalDirective;

    constructor(
        private zonalService: ZonalService,
        private operationalHeadService: OperationalHeadService,
        private settingsService: FinancialYearSettingsService,
        private mapZoneWithOhService: MapZonalWithOhService,
        private parseLinks: JhiParseLinks,
        private router: Router,
        private activatedRoute: ActivatedRoute,
        private auditService: EntityAuditService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.updateStatus = 1;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            // console.log(data);
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
    }

    ngOnInit() {
        // console.log('ngOnInit');
        // Call a function to get list of zonals
        this.getZoneList();
        // Call a function to get active batch id
        // this.getActiveRecord();
        // Call a function to get list of active headOffice
        this.getOperationalHead();

        // To set the time for automatic alert close
        setTimeout(() => (this.staticAlertClosed = true), 20000);

        // Set the success message with debounce time
        this.success.subscribe(message => (this.successMessage = message));
        this.success.pipe(debounceTime(5000)).subscribe(() => (this.successMessage = null));

        // To set the error message with debounce time
        this.error.subscribe(message => (this.errorMessage = message));
        this.error.pipe(debounceTime(5000)).subscribe(() => (this.errorMessage = null));
    }

    // Call a service function to get list of zonals
    getZoneList(): void {
        // Get the list of zone
        this.zonalService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort(),
                filter: { 'status.equals': STATUS_ACTIVE }
            })
            .subscribe((res: HttpResponse<IZonal[]>) => this.paginateZonals(res.body, res.headers));
    }

    // Call a service function to get list of active batch
    getActiveRecord(): void {
        // Get the list of active batch record and assign a 0th
        // index array value to an batch id
        this.settingsService.getActiveRecord().subscribe((res: HttpResponse<IFinancialYearSettings[]>) => {
            if (res.body.length > 0) {
                // Set the batch Id
                this.batchId = res.body[0].id;
                // To create a new zonal model and display the pop up
                this.zonalObject = new ZonalModel();
                this.zoneModal.show();
                // To set the Zone title
                this.title = 'Create Zone:';
            } else {
                // alert('There is no active financial year');
                this.error.next('There is no active financial year');
            }
        });
    }

    // Call a service function to get list of active head office
    getOperationalHead(): void {
        // Get the list of active batch record and assign a 0th index array value to an batch id
        this.operationalHeadService.getActiveList().subscribe((res: HttpResponse<IOperationalHead[]>) => {
            this.operationalHeads = res.body;
        });
    }

    // Send a zonal object to a service (create or update)
    save() {
        // console.log(this.zonalObject);
        this.zonalObject.status = STATUS_ACTIVE;
        // console.log(this.zonalObject.updatedAt);
        if (this.zonalObject.id !== undefined) {
            this.alertTitle = 'updated';
            this.subscribeToSaveResponse(this.zonalService.update(this.zonalObject), this.alertTitle);
        } else {
            this.alertTitle = 'created';
            this.zonalObject.financialYearId = this.batchId;
            // this.mapZoneWithOh = new MapZonalWithOhModel();
            this.subscribeToSaveResponse(this.zonalService.create(this.zonalObject), this.alertTitle);
        }
    }

    // To save the response with zonal
    private subscribeToSaveResponse(result: Observable<HttpResponse<IZonal>>, alertTitle) {
        result.subscribe(
            (res: HttpResponse<IZonal>) => {
                this.zoneModal.hide();
                this.moveZonal(res.body, this.mapZoneWithOh);
                // alert('Zone Created/Updated Successfully.');
                this.success.next(`Zone ${alertTitle} successfully`);
                this.getZoneList();
                this.zonalObject = new ZonalModel();
            },
            (res: HttpErrorResponse) => {
                // alert(res.error.fieldErrors[0].message);
                this.error.next(res.error.fieldErrors[0].message);
            }
        );
    }

    saveMapTable(zonalDetails: ZonalModel): void {
        this.mapZoneWithOh = new MapZonalWithOhModel();
        this.mapZoneWithOh.fromDate = moment(moment(this.mapZoneWithOh.fromDate).format(DATE_TIME_FORMAT), DATE_TIME_FORMAT);
        this.mapZoneWithOh.operationalHeadId = zonalDetails.operationalHeadId;
        this.mapZoneWithOh.zonalId = zonalDetails.id;
        this.mapZoneWithOh.status = STATUS_ACTIVE;
        this.mapZoneWithOhService.create(this.mapZoneWithOh).subscribe(
            (res: HttpResponse<IMapZonalWithOh>) => {
                this.moveZone.hide();
            },
            (res: HttpErrorResponse) => {
                // alert(res.error.fieldErrors[0].message);
                this.error.next(res.error.fieldErrors[0].message);
            }
        );
    }

    moveZonal(zonalDetails: ZonalModel, value: MapZonalWithOhModel): void {
        this.toDate = moment(value.toDate).format(DATE_TIME_FORMAT);
        // Get the list of active batch record and assign a 0th index array value to an batch id
        this.mapZoneWithOhService.getParticularZonalActiveRecord(zonalDetails.id).subscribe((res: HttpResponse<IMapZonalWithOh[]>) => {
            if (res.body.length > 0) {
                this.mapZoneWithOh = res.body[0];
                this.mapZoneWithOh.description = value.description;
                this.mapZoneWithOh.toDate = moment(this.toDate, DATE_TIME_FORMAT);
                this.mapZoneWithOh.status = STATUS_INACTIVE;
                this.mapZoneWithOhService.update(this.mapZoneWithOh).subscribe(
                    (output: HttpResponse<IMapZonalWithOh>) => {
                        this.saveMapTable(zonalDetails);
                    },
                    (error: HttpErrorResponse) => {
                        // alert(res.error.fieldErrors[0].message);
                        this.error.next(error.error.fieldErrors[0].message);
                    }
                );
            } else {
                this.saveMapTable(zonalDetails);
            }
        });
    }

    // show model popup to create zonal value
    createZone(): void {
        // this.zonalObject = new ZonalModel();
        // this.zoneModal.show();
        this.getActiveRecord();
        // this.title = 'Create Zone:';
    }

    // show model popup to update zonal value
    getZoneValue(value: ZonalModel, status): void {
        // console.log('Edit value');
        // console.log(value);
        if (status !== this.updateStatus) {
            this.zoneModal.show();
        } else {
            this.mapZoneWithOh = new MapZonalWithOhModel();
            this.moveZone.show();
        }
        this.zonalObject = value;
        this.title = `Update Zone: ${value.zoneName}`;
        // console.log(this.title);
    }

    // soft delete
    softDelete(zone: ZonalModel): void {
        this.zonalObject = zone;
        this.zonalObject.status = SOFT_DELETE_STATUS;
        this.zonalService.update(this.zonalObject).subscribe(
            data => {
                this.success.next(`Zone deleted successfully`);
                this.getZoneList();
            },
            (res: HttpErrorResponse) => {
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    // commented for making the delete as soft one which means not to delete from db
    // deleteZonal(zonal: ZonalModel): void {
    //     this.zonalService.delete(zonal.id).subscribe(data => {
    //         // alert('Zone deleted Successfully.');
    //         this.success.next(`Zone deleted successfully`);
    //         this.zonals = this.zonals.filter(u => u !== zonal);
    //     });
    // }

    getMapList(id: number): void {
        // Get the list of record based on zonal id
        this.mapZoneWithOhService.getParticularZonalRecord(id).subscribe((res: HttpResponse<IMapZonalWithOh[]>) => {
            this.isCollapsed = false;
            this.mapZoneWithOhs = res.body;
        });
    }

    getAuditHistory(id: number): void {
        this.auditService
            .findByParticularUpdatedEntityDetails('com.niche.ng.domain.Zonal', id, ACTION_STATUS_UPDATE, this.selectedLimit)
            .subscribe((res: HttpResponse<EntityAuditEvent[]>) => {
                console.log(res.body);
            });
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        this.router.navigate(['/zonal'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.getZoneList();
    }

    clear() {
        this.page = 0;
        this.router.navigate([
            '/zonal',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.getZoneList();
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    trackId(index: number, item: IZonal) {
        return item.id;
    }

    private paginateZonals(data: IZonal[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.zonals = data;
    }

    // If the zone model pop up closed, to call the get zone list function
    closeZoneModel(): void {
        // Hide the zone model pop-up
        this.zoneModal.hide();
        // Call the getZone List function
        this.getZoneList();
    }

    // If the zone model pop up closed, to call the get zone list function
    closeMovementModel(): void {
        // Hide the zone model pop-up
        this.moveZone.hide();
        // Call the getZone List function
        this.getZoneList();
    }
}
