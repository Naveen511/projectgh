/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02 11:27:58
 *  Target: yarn
 *******************************************************************************/

// Import needed model, service, shared and angular dependency
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { ViewChild } from '@angular/core';
import { JhiParseLinks } from 'ng-jhipster';
import * as moment from 'moment';
import { HttpResponse, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Subject } from 'rxjs';
import { debounceTime } from 'rxjs/operators';
import { NgbDatepickerConfig } from '@ng-bootstrap/ng-bootstrap';

import { IZonal } from 'app/shared/model/zonal.model';
import { IPickList } from 'app/shared/model/pick-list.model';
import {
    INursery, NurseryModel, DISPLAY_NAME_NURSERY_TYPE
} from 'app/shared/model/nursery.model';
import { ISector } from 'app/shared/model/sector.model';
import { IFinancialYearSettings } from 'app/shared/model/financial-year-settings.model';
import { IOperationalHead } from 'app/shared/model/operational-head.model';
import {
    MapNurseryWithSector, IMapNurseryWithSector, MapNurseryWithSectorModel, STATUS_INACTIVE
} from 'app/shared/model/map-nursery-with-sector.model';

import { NurseryService } from 'app/entities/service/nursery.service';
import { SectorService } from 'app/entities/service/sector.service';
import { ZonalService } from 'app/entities/service/zonal.service';
import { IPickListValue } from 'app/shared/model/pick-list-value.model';
import { PickListService } from 'app/entities/service/pick-list.service';
import { PickListValueService } from 'app/entities/service/pick-list-value.service';
import {
    FinancialYearSettingsService
} from 'app/entities/service/financial-year-settings.service';
import { OperationalHeadService } from 'app/entities/service/operational-head.service';
import {
    MapNurseryWithSectorService
} from 'app/entities/service/map-nursery-with-sector.service';

import {
    DATE_TIME_FORMAT, ITEMS_PER_PAGE, STATUS_ACTIVE, SOFT_DELETE_STATUS,
    ALERT_TIME_OUT_5000, ALERT_TIME_OUT_3000, STATUS_MOVEMENT, STATUS_UPDATE
} from 'app/shared';

// Mension the html, css/sass files
@Component({
    selector: 'jhi-nursery',
    templateUrl: 'nursery.component.html'
})

/**
 * Class NurseryComponent used to create/update a nursery, List all nurserys.
 * Declared an Nursery object to create and update.
 * Declared an Array variable to set list of Nurserys.
 * Using a modal popup directive create and update form is displayed.
 */
export class NurseryComponent implements OnInit {
    nurseryObject: NurseryModel = new NurseryModel();
    mapNurseryWithSector: MapNurseryWithSector = new MapNurseryWithSector();
    // create empty array for each service
    operationalHeads: IOperationalHead[];
    zonals: IZonal[];
    sectors: ISector[];
    nurserys: INursery[];
    pickLists: IPickList[];
    varieties: IPickListValue[];
    mapNurseryWithSectors: IMapNurseryWithSector[];
    toDate: string;
    batchId: number;
    updateStatus: number;
    movementStatus: number;
    isCollapsed = true;

    // Title declation as String
    title: String;

    // Date picker id declation
    dateFromDp: any;
    fromDateDp: any;
    toDateDp: any;
    fromDate: any;

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

    // Declare a modal popup of nursery modal
    @ViewChild('nurseryModal') public nurseryModal: ModalDirective;
    @ViewChild('moveNursery') public moveNursery: ModalDirective;

    constructor(
        private operationalHeadService: OperationalHeadService,
        private zonalService: ZonalService,
        private nurseryService: NurseryService,
        private sectorService: SectorService,
        private pickListService: PickListService,
        private pickListValueService: PickListValueService,
        private settingsService: FinancialYearSettingsService,
        private mapNurseryWithSectorService: MapNurseryWithSectorService,
        private parseLinks: JhiParseLinks,
        private router: Router,
        private activatedRoute: ActivatedRoute,
        private config: NgbDatepickerConfig
    ) {
        // Declare a value to url params
        this.itemsPerPage = ITEMS_PER_PAGE;
        // this.updateStatus = 1;
        this.updateStatus = STATUS_UPDATE;
        this.movementStatus = STATUS_MOVEMENT;
        this.routeData = this.activatedRoute.data.subscribe(data => {
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
        // Call a function to get list of zonals and Nurserys
        this.getNurseryList();

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
     * Get the active record from the calendar settings
     */
    getActiveRecord(): void {
        // Get the list of active batch record and
        // assign a 0th index array value to an batch id
        this.settingsService.query({
            filter: { 'status.equals': STATUS_ACTIVE }
        }).subscribe((res: HttpResponse<IFinancialYearSettings[]>) => {
            // Assign a value to an object
            this.batchId = res.body[0].id;
        });
    }

    /**
     * Get the active operational head using the active status
     * and set into the operationalHeads
     */
    getOperationalHead(): void {
        // Get the list of active batch record and assign a array value to a variable
        // this.operationalHeadService.getActiveList()
        this.operationalHeadService.query({
            filter: { 'status.equals': STATUS_ACTIVE }
        }).subscribe((res: HttpResponse<IOperationalHead[]>) => {
            this.operationalHeads = res.body;
        });
    }

    /**
     * Get the nursery list and using the active status
     */
    getNurseryList(): void {
        this.nurseryService.query({
            page: this.page - 1,
            size: this.itemsPerPage,
            sort: this.sort(),
            filter: { 'status.equals': STATUS_ACTIVE }
        }).subscribe((res: HttpResponse<INursery[]>) => {
            this.paginateNurserys(res.body, res.headers);
        });
    }

    /**
     * Get the zone list based on the operational Head Id
     * If the zonal Id is not null get the sector
     * for the dependent dropdown
     * @param operationalHeadId Model
     * @param zonalId number
     * @param status number
     */
    getZoneList(operationalHeadId: number, zonalId): void {
        // To empty the sector value
        this.sectors = null;
        // Get the list of zone
        // this.zonalService.getParticularHeadOfficeRecord(operationalHeadId)
        this.zonalService.query({
            filter: {
                'status.equals': STATUS_ACTIVE,
                'operationalHeadId.equals': operationalHeadId
            }
        }).subscribe((res: HttpResponse<IZonal[]>) => {
            this.zonals = res.body;
        });

        // Zonal Id variable is passed from the getNursery value
        // If the zone id is not null
        // load the sector for dependent dropdown
        // If it is null to set zonal and sector id is null
        if (zonalId !== null && zonalId !== undefined && zonalId !== '') {
            this.getSector(zonalId);
        } else {
            // Set the zonal and sector id as null
            this.nurseryObject.zoneId = null;
            this.nurseryObject.sectorId = null;
        }

        // if (status != null) {
        //     this.nurseryObject.zoneId = null;
        //     this.nurseryObject.sectorId = null;
        // }

        // Set the zonal Id
        this.nurseryObject.zonalId = zonalId;
    }

    /**
     * Get the sector value based on zonal id
     * @param zoneId number
     */
    getSector(zoneId): void {
        // Get the list of sector
        // this.sectorService.getSectors(zoneId)
        this.sectorService.query({
            filter: {
                'status.equals': STATUS_ACTIVE,
                'zonalId.equals': zoneId
            }
        }).subscribe((res: HttpResponse<ISector[]>) => {
            this.sectors = res.body;
        });
    }

    /**
     * Get the variety from the picklist table
     * using the active status and pickListId from the pickListValue table
     * @param id auto increment value of PickListValue table
     */
    getVariety(id): void {
        // this.pickListValueService.getVariety(id)
        this.pickListValueService.query({
        filter: {
            'status.equals': STATUS_ACTIVE,
            'pickListId.equals': id
        }}).subscribe((res: HttpResponse<IPickListValue[]>) => {
            // console.log(res.body);
            this.varieties = res.body;
        });
    }

    /**
     * To save the nursery by default save as active
     */
    save() {
        // this.nurseryObject.status = 1;
        // Set the status as active
        this.nurseryObject.status = STATUS_ACTIVE;
        // If the nursery id is not equal to undefined, update the old record
        if (this.nurseryObject.id !== undefined) {
            // Update the nursery service
            this.subscribeToSaveResponse(
                this.nurseryService.update(this.nurseryObject), 'updated'
            );
        } else {
            this.nurseryObject.financialYearNurseryId = this.batchId;
            // Create the nursery
            this.subscribeToSaveResponse(
                this.nurseryService.create(this.nurseryObject), 'created'
            );
        }
    }

    /**
     * Return the saved response of success and
     * error from the nursery table
     *
     * @param result object of Nursery
     * @param alertTitle message title for alert
     */
    private subscribeToSaveResponse(result: Observable<HttpResponse<INursery>>, alertTitle) {
        result.subscribe(
            (res: HttpResponse<INursery>) => {
                this.nurseryModal.hide();
                this.moveNurseryValue(res.body, this.mapNurseryWithSector);
                // alert('Nursery Created/Updated Successfully.');
                this.success.next(`Nursery ${alertTitle} successfully.`);
                this.getNurseryList();
                // To empty the value of sector and zonal list
                this.zonals = null;
                this.sectors = null;
                this.isCollapsed = true;
                this.nurseryObject = new NurseryModel();
            },
            (res: HttpErrorResponse) => {
                // alert('Nursery Not Saved.');
                // alert(res.error.fieldErrors[0].message);
                this.error.next(res.error.fieldErrors[0].message);
            }
        );
    }

    /**
     * To ave the maping details of nursery movement
     * @param nurseryDetails model
     * @param fromDate date
     */
    saveMapTable(nurseryDetails: NurseryModel, fromDate): void {
        this.mapNurseryWithSector = new MapNurseryWithSectorModel();
        // this.mapNurseryWithSector.fromDate
        // = moment(moment(this.mapNurseryWithSector.fromDate).format(DATE_TIME_FORMAT), DATE_TIME_FORMAT);
        this.mapNurseryWithSector.fromDate = moment(fromDate, DATE_TIME_FORMAT);
        this.mapNurseryWithSector.sectorId = nurseryDetails.sectorId;
        this.mapNurseryWithSector.nurseryId = nurseryDetails.id;
        this.mapNurseryWithSector.status = STATUS_ACTIVE;
        this.mapNurseryWithSectorService.create(this.mapNurseryWithSector)
        .subscribe((res: HttpResponse<IMapNurseryWithSector>) => {
            this.moveNursery.hide();
        }, (res: HttpErrorResponse) => {
            // alert(res.error.fieldErrors[0].message);
            // Return the error alert
            this.error.next(res.error.fieldErrors[0].message);
        });
    }

    /**
     * Get the previous nursery mapped details and update the values
     * @param nurseryDetails NurseryModel model
     * @param value MapNurseryWithSector model
     */
    moveNurseryValue(nurseryDetails: NurseryModel, value: MapNurseryWithSector): void {
        // Get the list of active batch record and assign a 0th index
        // array value to an batch id
        // To set the default date for the from date
        if (value.fromDate == null) {
            // console.log(this.fromDate);
            this.fromDate = moment(value.fromDate).format(DATE_TIME_FORMAT);
            value.fromDate = moment(this.fromDate, DATE_TIME_FORMAT);
        }

        // .getParticularNurseryActiveRecord(nurseryDetails.id)
        this.mapNurseryWithSectorService.query({
            filter: {
                'nurseryId.equals': nurseryDetails.id,
                'status.equals': STATUS_ACTIVE
            }
        }).subscribe((res: HttpResponse<IMapNurseryWithSector[]>) => {
            // If the length is greater than 0,
            // Set the status as inactive and todate for the old row
            if (res.body.length > 0) {
                // If the todate date is empty,
                // To set the default date for the todate date
                if (value.toDate === null || value.toDate === undefined) {
                    // console.log(this.fromDate);
                    this.toDate = moment(value.toDate).format(DATE_TIME_FORMAT);
                    value.toDate = moment(this.toDate, DATE_TIME_FORMAT);
                }
                this.mapNurseryWithSector = res.body[0];
                this.mapNurseryWithSector.description = value.description;
                this.mapNurseryWithSector.toDate = moment(value.toDate, DATE_TIME_FORMAT);
                this.mapNurseryWithSector.status = STATUS_INACTIVE;
                this.mapNurseryWithSectorService.update(this.mapNurseryWithSector)
                    .subscribe(
                        (output: HttpResponse<IMapNurseryWithSector>) => {
                            this.saveMapTable(nurseryDetails, value.fromDate);
                        },
                        (error: HttpErrorResponse) => {
                            // alert(res.error.fieldErrors[0].message);
                            this.error.next(error.error.fieldErrors[0].message);
                        }
                    );
            } else {
                this.saveMapTable(nurseryDetails, value.fromDate);
            }
        });
    }

    /**
     * To create the nursery and mapping with sector details
     * Show model popup to create nursery value
     */
    createNursery(): void {
        // Call a function to get active batch id
        this.getActiveRecord();

        // Call a function to get list of active headOffice
        this.getOperationalHead();

        this.nurseryObject = new NurseryModel();
        this.mapNurseryWithSector = new MapNurseryWithSectorModel();
        this.nurseryModal.show();
        this.title = 'Create Nursery:';

        // To get the pick list Id for Pick List Nursery Type
        this.pickListService.query({
            filter: {
                'status.equals': STATUS_ACTIVE,
                'displayLabelName.equals': DISPLAY_NAME_NURSERY_TYPE
            }
        }).subscribe((res: HttpResponse<IPickList[]>) => {
            // If the length is greater than 0, get getVariety
            if (res.body.length > 0) {
                this.pickLists = res.body;
                // Call a function to get list of variety
                this.getVariety(res.body[0].id);
            }
        });
    }

    /**
     * Get the nursery value
     * Show model popup to update nursery value
     * @param value NurseryModel
     * @param status number
     */
    getNurseryValue(value: NurseryModel, status): void {
        // If the status is not equal to movement status
        if (status !== this.movementStatus) {
            this.nurseryModal.show();
            this.title = `Update Nursery: ${value.nurseryName}`;
            // To get the pick list Id for Pick List Nursery Type
            // this.pickListService.getPickListId(DISPLAY_NAME_NURSERY_TYPE)
            this.pickListService.query({
                filter: {
                    'status.equals': STATUS_ACTIVE,
                    'displayLabelName.equals': DISPLAY_NAME_NURSERY_TYPE
                }
            }).subscribe((res: HttpResponse<IPickList[]>) => {
                this.getVariety(res.body[0].id);
            });
        } else {
            // Call the operational head
            this.getOperationalHead();
            this.mapNurseryWithSector = new MapNurseryWithSectorModel();
            this.moveNursery.show();
            this.title = `Move Nursery: ${value.nurseryName}`;

            this.sectorService.query({
                filter: { 'id.equals':  value.sectorId }
            }).subscribe((res: HttpResponse<ISector[]>) => {
                // If the res.body is not null get the zonal service details
                if (res.body[0] !== null && res.body !== undefined) {
                    // this.nurseryObject.zonalId = res.body[0].zonalId;
                    // this.nurseryObject.sectorId = value.sectorId;

                    this.zonalService.query({
                        filter: { 'id.equals':  res.body[0].zonalId }
                    }).subscribe((rest: HttpResponse<IZonal[]>) => {
                        if (rest.body[0] !== null && rest.body !== undefined) {
                            this.nurseryObject.operationalHeadId = rest.body[0].operationalHeadId;
                            // this.getZoneList(rest.body[0].operationalHeadId, this.nurseryObject.zonalId);
                            // To get the zonal list based on the operationalHeadId and zonalId
                            this.getZoneList(
                                rest.body[0].operationalHeadId,
                                res.body[0].zonalId
                            );
                            // this.getZoneList(
                            //     rest.body[0].operationalHeadId,
                            //     res.body[0].zonalId, status
                            // );
                        }
                    });
                }
            });
        }
        // Set the nursery model to nurseryObject
        this.nurseryObject = value;
    }

    /**
     * Delete the calendar settings by updated the status as delete
     * @param nursery NurseryModel
     */
    softDelete(nursery: NurseryModel): void {
        // Confirmation Pop-up for the Delete
        if (window.confirm('Are sure you want to delete?')) {
            this.nurseryObject = nursery;
            this.nurseryObject.status = SOFT_DELETE_STATUS;
            // console.log('date', this.sectorObject);
            this.nurseryService.update(this.nurseryObject).subscribe(
                data => {
                    // console.log('upda', this.sectorObject);
                    this.success.next(`Nursery deleted successfully.`);
                    this.getNurseryList();
                },
                (res: HttpErrorResponse) => {
                    alert(res.error.fieldErrors[0].message);
                }
            );
        }
    }

    /**
     * Get the map list based on the id
     * Hide the nursery mapped details modal
     * @param id number
     */
    getMapList(id: number): void {
        // this.mapNurseryWithSectorService.getParticularNurseryRecord(id)
        this.mapNurseryWithSectorService.query({
            filter: { 'nurseryId.equals': id }
        }).subscribe((res: HttpResponse<IMapNurseryWithSector[]>) => {
            this.isCollapsed = false;
            this.mapNurseryWithSectors = res.body;
        });
    }

    /**
     * If the sector model pop up closed
     * Call the get sector list function to diplay original records
     */
    closeNurseryModal(): void {
        // Hide the sector model pop-up
        this.nurseryModal.hide();
        // Call the getSector List function
        this.getNurseryList();
        this.zonals = null;
        this.sectors = null;
        // To close the opened div
        this.isCollapsed = true;
    }

    /**
     * If the nursery model pop up closed,
     * Call the get nursery list function to diplay original records
     */
    closeMovementModel(): void {
        // Hide the nursery model pop-up
        this.moveNursery.hide();
        // Call the getNursery List function
        this.getNurseryList();
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
        this.router.navigate(['/nursery'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.getNurseryList();
    }

    /**
     * Clear the filter
     */
    clear() {
        this.page = 0;
        this.router.navigate([
            '/nursery',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.getNurseryList();
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
     * @param item Nursery
     */
    trackId(index: number, item: INursery) {
        return item.id;
    }

    /**
     * Set the page size, total record count in header
     * @param data Nursery
     * @param headers headers
     */
    private paginateNurserys(data: INursery[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.nurserys = data;
    }

    /**saveMoved(value: NurseryModel): void {
       // console.log(this.nurseryObject);
       // console.log('val ', value);
       this.nurseryObject = value;
       this.nurseryObject.status = 2;
       // this.nurseryService.update(this.nurseryObject);
       this.subscribeToSaveNursery(this.nurseryService.update(this.nurseryObject));
    }

    // move nursery from one sector/zonal to another
    moveNursery(value: NurseryModel): void {
        this.nurseryObject = new NurseryModel();
        this.moveNurseryModal.show();
        this.nurseryObject = value;
        // console.log('move ', this.nurseryObject);
    }

    private subscribeToSaveNursery(result: Observable<HttpResponse<INursery>>) {
        result.subscribe(
            (res: HttpResponse<INursery>) => {
                this.moveNurseryModal.hide();
                console.log('nursery', this.nurseryObject);
                this.nurseryObject.id = null;
                this.nurseryObject.status = 1;
                this.alertTitle = '';
                this.nurseryObject = new NurseryModel();
                this.nurseryObject.financialYearNurseryId = this.batchId;
                this.subscribeToSaveResponse(
                    this.nurseryService.create(this.nurseryObject), this.alertTitle);
                alert('moved.');
                this.getNurseryList();
            },
            (res: HttpErrorResponse) => {
                // alert('Nursery Not Saved.');
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    // commented for making the delete as soft one which means not to delete from db
    deleteNursery(nursery: NurseryModel): void {
        this.nurseryService.delete(nursery.id).subscribe(data => {
            // alert('Nursery deleted Successfully.');
            this.success.next(`Nursery deleted successfully`);
            this.nurserys = this.nurserys.filter(u => u !== nursery);
        });
    } */
}
