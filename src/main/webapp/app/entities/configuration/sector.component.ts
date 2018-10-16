// Import needed component and dependency
import { Component, OnInit } from '@angular/core';
import { ZonalService } from 'app/entities/service/zonal.service';
import { SectorService } from 'app/entities/service/sector.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ISector, SectorModel } from 'app/shared/model/sector.model';
import { IZonal } from 'app/shared/model/zonal.model';
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
import { IMapSectorWithZonal, MapSectorWithZonal, STATUS_INACTIVE } from 'app/shared/model/map-sector-with-zonal.model';
import { MapSectorWithZonalService } from 'app/entities/service/map-sector-with-zonal.service';
import { MapNurseryWithSectorModel } from 'app/shared/model/map-nursery-with-sector.model';
import { OperationalHeadService } from 'app/entities/service/operational-head.service';
import { IOperationalHead } from 'app/shared/model/operational-head.model';

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
        private activatedRoute: ActivatedRoute
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.updateStatus = 1;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
    }

    ngOnInit() {
        // Call a function to get list of active headOffice
        this.getOperationalHead();
        this.getSectorList();
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
        this.error.subscribe(message => this.errorMessage = message);
        this.error.pipe(
            debounceTime(5000)
        ).subscribe(() => this.errorMessage = null);
    }

    // Call a service function to get list of active head office
    getOperationalHead(): void {
        // Get the list of active batch record and assign a array value to a variable
        this.operationalHeadService.getActiveList().subscribe((res: HttpResponse<IOperationalHead[]>) => {
            this.operationalHeads = res.body;
        });
    }

    // Call a service function to get list of zonals
    getZoneList(operationalHeadId: number): void {
        // Get the list of zone
        this.zonalService.getParticularHeadOfficeRecord(operationalHeadId).subscribe((res: HttpResponse<IZonal[]>) => {
            this.zonals = res.body;
        });
    }

    // Call a service function to get list of sectors
    getSectorList(): void {
        // Get the list of zone
        this.sectorService.query({
            page: this.page - 1,
            size: this.itemsPerPage,
            sort: this.sort(),
            filter: {'status.equals': STATUS_ACTIVE}
        })
        .subscribe((res: HttpResponse<ISector[]>) => this.paginateSectors(res.body, res.headers));
    }

    // Call a service function to get list of active batch
    getActiveRecord(): void {
        // Get the list of active batch record and assign a 0th index array value to an batch id
        this.settingsService.getActiveRecord().subscribe((res: HttpResponse<IFinancialYearSettings[]>) => {
            this.batchId = res.body[0].id;
        });
    }

    // Send a sector object to a service (create or update)
    save() {
        this.sectorObject.status = STATUS_ACTIVE;
        if (this.sectorObject.id !== undefined) {
            this.alertTitle = 'updated';
            this.subscribeToSaveResponse(
                this.sectorService.update(this.sectorObject), this.alertTitle
            );
        } else {
            this.alertTitle = 'created';
            this.sectorObject.financialYearSectorId = this.batchId;
            this.subscribeToSaveResponse(
                this.sectorService.create(this.sectorObject), this.alertTitle
            );
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ISector>>, alertTitle) {
        result.subscribe(
            (res: HttpResponse<ISector>) => {
                this.sectorModal.hide();
                this.moveSectorValue(res.body, this.mapSectorWithZonal);
                // alert('Sector Created/Updated Successfully.');
                this.success.next(`Sector ${alertTitle} successfully`);
                this.getSectorList();
                this.sectorObject = new SectorModel();
            },
            (res: HttpErrorResponse) => {
                // alert(res.error.fieldErrors[0].message);
                this.success.next(res.error.fieldErrors[0].message);
            }
        );
    }

    saveMapTable(sectorDetails: SectorModel): void {
        this.mapSectorWithZonal = new MapNurseryWithSectorModel();
        this.mapSectorWithZonal.fromDate = moment(moment(this.mapSectorWithZonal.fromDate).format(DATE_TIME_FORMAT), DATE_TIME_FORMAT);
        this.mapSectorWithZonal.zonalId = sectorDetails.zonalId;
        this.mapSectorWithZonal.sectorId = sectorDetails.id;
        this.mapSectorWithZonal.status = STATUS_ACTIVE;
        this.mapSectorWithZonalService.create(this.mapSectorWithZonal)
        .subscribe((res: HttpResponse<IMapSectorWithZonal>) => {
            this.moveSector.hide();
        },
        (res: HttpErrorResponse) => {
            // alert(res.error.fieldErrors[0].message);
            this.error.next(res.error.fieldErrors[0].message);
        });
    }

    moveSectorValue(sectorDetails: SectorModel, value: MapSectorWithZonal): void {
        this.toDate = moment(value.toDate).format(DATE_TIME_FORMAT);
        // Get the list of active batch record and assign a 0th index array value to an batch id
        this.mapSectorWithZonalService.getParticularSectorActiveRecord(sectorDetails.id).subscribe((res: HttpResponse<IMapSectorWithZonal[]>) => {
            if (res.body.length > 0) {
                this.mapSectorWithZonal = res.body[0];
                this.mapSectorWithZonal.description = value.description;
                this.mapSectorWithZonal.toDate = moment(this.toDate, DATE_TIME_FORMAT);
                this.mapSectorWithZonal.status = STATUS_INACTIVE;
                this.mapSectorWithZonalService.update(this.mapSectorWithZonal)
                .subscribe(
                    (output: HttpResponse<IMapSectorWithZonal>) => {
                        this.saveMapTable(sectorDetails);
                    },
                    (error: HttpErrorResponse) => {
                        // alert(res.error.fieldErrors[0].message);
                        this.error.next(error.error.fieldErrors[0].message);
                    }
                );
            } else {
                this.saveMapTable(sectorDetails);
            }
        });
    }

    // show model popup to create sector value
    createSector(): void {
        this.sectorObject = new SectorModel();
        this.sectorModal.show();
        this.title = 'Create Sector:';
    }

    // show model popup to update sector value
    getSectorValue(value: SectorModel, status): void {
        if (status !== this.updateStatus) {
            this.sectorModal.show();
        } else {
            this.mapSectorWithZonal = new MapNurseryWithSectorModel();
            this.moveSector.show();
        }
        this.sectorObject = value;
        this.title = `Update sector: ${value.sectorName}`;
    }

    // soft delete
    softDelete(sector: SectorModel): void {
        // console.log('head', sector);
        this.sectorObject = sector;
        this.sectorObject.status = SOFT_DELETE_STATUS;
        // console.log('date', this.sectorObject);
        this.sectorService.update(this.sectorObject)
        .subscribe(
            data => {
                // console.log('upda', this.sectorObject);
                this.success.next(`Sector deleted successfully`);
                this.getSectorList();
            },
            (res: HttpErrorResponse) => {
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    // commented for making the delete as soft one which means not to delete from db
    // deleteSector(sector: SectorModel): void {
    //     this.sectorService.delete(sector.id).subscribe(data => {
    //         // alert('Sector deleted Successfully.');
    //         this.success.next(`Sector deleted successfully`);
    //         this.sectors = this.sectors.filter(u => u !== sector);
    //     });
    // }

    getMapList(id: number): void {
        // Get the list of record based on zonal id
        this.mapSectorWithZonalService.getParticularSectorRecord(id).subscribe((res: HttpResponse<IMapSectorWithZonal[]>) => {
            this.isCollapsed = false;
            this.mapSectorWithZonals = res.body;
        });
    }

    // If the sector model pop up closed, to call the get sector list function
    closeSectorModal(): void {
        // Hide the sector model pop-up
        this.sectorModal.hide();
        // Call the getSector List function
        this.getSectorList();
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

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

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    trackId(index: number, item: ISector) {
        return item.id;
    }

    private paginateSectors(data: ISector[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.sectors = data;
    }

    // If the sector model pop up closed, to call the get sector list function
    closeMovementModel(): void {
        // Hide the sector model pop-up
        this.moveSector.hide();
        // Call the getSector List function
        this.getSectorList();
    }
}
