/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date   : 2018/08/02 11:27:58
 *  Target : yarn
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

import { ZonalService } from 'app/entities/service/zonal.service';
import { SectorService } from 'app/entities/service/sector.service';
import {
    FinancialYearSettingsService
} from 'app/entities/service/financial-year-settings.service';
import {
    MapSectorWithZonalService
} from 'app/entities/service/map-sector-with-zonal.service';
import { OperationalHeadService } from 'app/entities/service/operational-head.service';

import { ISector, SectorModel } from 'app/shared/model/sector.model';
import { IZonal } from 'app/shared/model/zonal.model';
import { MapNurseryWithSectorModel } from 'app/shared/model/map-nursery-with-sector.model';
import { IOperationalHead } from 'app/shared/model/operational-head.model';
import { IFinancialYearSettings } from 'app/shared/model/financial-year-settings.model';
import {
    IMapSectorWithZonal, MapSectorWithZonal, STATUS_INACTIVE
} from 'app/shared/model/map-sector-with-zonal.model';

import {
    DATE_TIME_FORMAT, ITEMS_PER_PAGE, STATUS_ACTIVE, SOFT_DELETE_STATUS,
    ALERT_TIME_OUT_5000, STATUS_MOVEMENT, STATUS_UPDATE
} from 'app/shared';

// Mension the html, css/sass files
@Component({
    selector: 'jhi-sector',
    templateUrl: 'sector.component.html'
})
/**
 * Class SectorComponent used to create/update a sector, List all sectors.
 * Declared an sector object to create and update.
 * Declared an Array variable to set list of sectors.
 * Using a modal popup directive create and update form is displayed.
 */
export class SectorComponent implements OnInit {
    // Create object for model
    sectorObject: SectorModel = new SectorModel();
    mapSectorWithZonal: MapSectorWithZonal = new MapSectorWithZonal();
    // create empty array for each service
    operationalHeads: IOperationalHead[];
    zonals: IZonal[];
    sectors: ISector[];
    mapSectorWithZonals: IMapSectorWithZonal[];
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

    /* // To declare the constant for from date and to date
    fromDate: any;
    toDate: any;

    // Declare the variable for filter - Report based on the date
    filter: Filter = new Filter(); */

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

    // Declare a modal popup of sector modal
    @ViewChild('sectorModal') public sectorModal: ModalDirective;
    @ViewChild('moveSector') public moveSector: ModalDirective;

    constructor(
        private zonalService: ZonalService,
        private sectorService: SectorService,
        private operationalHeadService: OperationalHeadService,
        private settingsService: FinancialYearSettingsService,
        private mapSectorWithZonalService: MapSectorWithZonalService,
        private parseLinks: JhiParseLinks,
        private router: Router,
        private activatedRoute: ActivatedRoute,
        private config: NgbDatepickerConfig
    ) {
        // Declare a value to url params
        this.itemsPerPage = ITEMS_PER_PAGE;
        // this.updateStatus = 1;
        // Set the constant for update and movement of sector status
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
        // To get sector to display the sector list
        this.getSectorList();
        // Call a function to get list of active headOffice
        // this.getOperationalHead();
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
     * Call a service function to get list of active head office
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
     * Call a service function to get list of zonals
     *
     * @param operationalHeadId id of operation head
     */
    getZoneList(operationalHeadId: number): void {
        // console.log('operational Head Id', operationalHeadId);
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
    }

    /**
     * Call a service function to get list of sectors
     */
    getSectorList(): void {
        // Get the list of zone
        this.sectorService.query({
            page: this.page - 1,
            size: this.itemsPerPage,
            sort: this.sort(),
            filter: { 'status.equals': STATUS_ACTIVE }
        }).subscribe((res: HttpResponse<ISector[]>) => {
            this.paginateSectors(res.body, res.headers);
        });
    }

    /**
     * Call a service function to get list of active batch
     */
    getActiveRecord(): void {
        // Get the list of active batch record and assign a 0th index array value
        // to an batch id
        this.settingsService.query({
            filter: { 'status.equals': STATUS_ACTIVE }
        }).subscribe((res: HttpResponse<IFinancialYearSettings[]>) => {
            // Assign a value to an object
            this.batchId = res.body[0].id;
        });
    }

    /**
     * Send a sector object to a service (create or update)
     */
    save() {
        this.sectorObject.status = STATUS_ACTIVE;
        // If the id is not empty call the update function
        if (this.sectorObject.id !== undefined) {
            this.subscribeToSaveResponse(
                this.sectorService.update(this.sectorObject), 'updated'
            );
        } else {
            this.sectorObject.financialYearSectorId = this.batchId;
            this.subscribeToSaveResponse(
                this.sectorService.create(this.sectorObject), 'created'
            );
        }
    }

    /**
     * To save the response and hide the model
     *
     * @param result list of values
     * @param alertTitle message title for alert
     */
    private subscribeToSaveResponse(result: Observable<HttpResponse<ISector>>, alertTitle) {
        result.subscribe(
            (res: HttpResponse<ISector>) => {
                this.sectorModal.hide();
                this.moveSectorValue(res.body, this.mapSectorWithZonal);
                // alert('Sector Created/Updated Successfully.');
                this.success.next(`Sector ${alertTitle} successfully.`);
                this.getSectorList();
                this.zonals = null;
                this.isCollapsed = true;
                this.sectorObject = new SectorModel();
            },
            (res: HttpErrorResponse) => {
                // alert(res.error.fieldErrors[0].message);
                this.error.next(res.error.fieldErrors[0].message);
            }
        );
    }

    /**
     * Save the movement of sector and update the data according to the moment
     *
     * @param sectorDetails sector value
     * @param fromDate date value
     */
    saveMapTable(sectorDetails: SectorModel, fromDate): void {
        this.mapSectorWithZonal = new MapNurseryWithSectorModel();
        // this.mapSectorWithZonal.fromDate
        // = moment(moment(this.mapSectorWithZonal.fromDate).format(DATE_TIME_FORMAT), DATE_TIME_FORMAT);
        this.mapSectorWithZonal.fromDate = moment(fromDate, DATE_TIME_FORMAT);
        this.mapSectorWithZonal.zonalId = sectorDetails.zonalId;
        this.mapSectorWithZonal.sectorId = sectorDetails.id;
        this.mapSectorWithZonal.status = STATUS_ACTIVE;
        this.mapSectorWithZonalService.create(this.mapSectorWithZonal)
        .subscribe(
            (res: HttpResponse<IMapSectorWithZonal>) => {
                this.moveSector.hide();
            },
            (res: HttpErrorResponse) => {
                // alert(res.error.fieldErrors[0].message);
                this.error.next(res.error.fieldErrors[0].message);
            }
        );
    }

    /**
     * Get the previous sector mapped details and update the values
     *
     * @param sectorDetails sector list
     * @param value moment values
     */
    moveSectorValue(sectorDetails: SectorModel, value: MapSectorWithZonal): void {
        // this.toDate = moment(value.toDate).format(DATE_TIME_FORMAT);
        // Get the list of active batch record and assign a 0th index array value
        // to an batch id

        // To set the default date for the from date
        if (value.fromDate == null) {
            // console.log(this.fromDate);
            this.fromDate = moment(value.fromDate).format(DATE_TIME_FORMAT);
            value.fromDate = moment(this.fromDate, DATE_TIME_FORMAT);
        }

        // this.mapSectorWithZonalService.getParticularSectorActiveRecord(sectorDetails.id)
        this.mapSectorWithZonalService.query({
            filter: {
                'sectorId:equals': sectorDetails.id,
                'status.equals': STATUS_ACTIVE
            }
        }).subscribe((res: HttpResponse<IMapSectorWithZonal[]>) => {
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
                this.mapSectorWithZonal = res.body[0];
                this.mapSectorWithZonal.description = value.description;
                this.mapSectorWithZonal.toDate = moment(value.toDate, DATE_TIME_FORMAT);
                this.mapSectorWithZonal.status = STATUS_INACTIVE;
                this.mapSectorWithZonalService.update(this.mapSectorWithZonal)
                    .subscribe(
                        (output: HttpResponse<IMapSectorWithZonal>) => {
                            this.saveMapTable(sectorDetails, value.fromDate);
                        },
                        (error: HttpErrorResponse) => {
                            // alert(res.error.fieldErrors[0].message);
                            this.error.next(error.error.fieldErrors[0].message);
                        }
                    );
            } else {
                this.saveMapTable(sectorDetails, value.fromDate);
            }
        });
    }

    /**
     * Shown model popup to create sector value with the title
     */
    createSector(): void {
        // Call a function to get list of active headOffice
        this.getOperationalHead();
        // Call a function to get active batch id
        this.getActiveRecord();
        this.sectorObject = new SectorModel();
        this.mapSectorWithZonal = new MapNurseryWithSectorModel();
        this.sectorModal.show();
        this.title = 'Create Sector:';
    }

    /**
     * Shown model popup to update sector value
     *
     * @param value sector value
     * @param status status
     */
    getSectorValue(value: SectorModel, status): void {
        // If the status is not equal to movement status
        if (status !== this.movementStatus) {
            this.sectorModal.show();
            this.title = `Update sector: ${value.sectorName}`;
        } else {
            // To get Operational Head
            this.getOperationalHead();
            this.mapSectorWithZonal = new MapNurseryWithSectorModel();
            this.moveSector.show();
            this.title = `Move sector: ${value.sectorName}`;
        }
        this.sectorObject = value;

        this.zonalService
            .query({
                filter: { 'id.equals': value.zonalId }
            }).subscribe((res: HttpResponse<IZonal[]>) => {
                if (res.body[0] !== null && res.body !== undefined) {
                    this.sectorObject.operationalHeadId = res.body[0].operationalHeadId;
                    this.getZoneList(res.body[0].operationalHeadId);
                }
                // this.sectorObject.zonalId = value.zonalId;
            });
    }

    /**
     * Soft delete the values and update the table
     *
     * @param sector sector values
     */
    softDelete(sector: SectorModel): void {
        // Confirmation Pop-up for the Delete
        if (window.confirm('Are sure you want to delete?')) {
            this.sectorObject = sector;
            this.sectorObject.status = SOFT_DELETE_STATUS;
            // console.log('date', this.sectorObject);
            this.sectorService.update(this.sectorObject)
            .subscribe(
                data => {
                    // console.log('upda', this.sectorObject);
                    this.success.next(`Sector deleted successfully`);
                    this.getSectorList();
                    this.isCollapsed = true;
                },
                (res: HttpErrorResponse) => {
                    alert(res.error.fieldErrors[0].message);
                }
            );
        }
    }

    // commented for making the delete as soft one which means not to delete from db
    // deleteSector(sector: SectorModel): void {
    //     this.sectorService.delete(sector.id).subscribe(data => {
    //         // alert('Sector deleted Successfully.');
    //         this.success.next(`Sector deleted successfully`);
    //         this.sectors = this.sectors.filter(u => u !== sector);
    //     });
    // }

    /**
     * Get the particular nursery map details
     *
     * @param id id number
     */
    getMapList(id: number): void {
        // Get the list of record based on zonal id
        // this.mapSectorWithZonalService.getParticularSectorRecord(id)
        this.mapSectorWithZonalService.query({
            filter: { 'sectorId:equals': id }
        }).subscribe((res: HttpResponse<IMapSectorWithZonal[]>) => {
            this.isCollapsed = false;
            this.mapSectorWithZonals = res.body;
        });
    }

    /**
     * If the sector model pop up closed, to call the get sector list function
     */
    closeSectorModal(): void {
        // Hide the sector model pop-up
        this.sectorModal.hide();
        // Call the getSector List function
        this.getSectorList();
        this.zonals = null;
        this.isCollapsed = true;
    }

    /**
     * Get the page number
     *
     * @param page page number
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
        this.router.navigate(['/sector'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.getSectorList();
    }

    /**
     * Clear the filter
     */
    clear() {
        this.page = 0;
        this.router.navigate([
            '/sector',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.getSectorList();
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
     *
     * @param index number
     * @param item sector vlaue
     */
    trackId(index: number, item: ISector) {
        return item.id;
    }

    /**
     * Set the page size, total record count in header
     *
     * @param data values
     * @param headers header values
     */
    private paginateSectors(data: ISector[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.sectors = data;
    }

    /**
     * If the sector model pop up closed, to call the get sector list function
     */
    closeMovementModel(): void {
        // Hide the sector model pop-up
        this.moveSector.hide();
        // Call the getSector List function
        this.getSectorList();
    }
}
