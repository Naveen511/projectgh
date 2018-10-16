// Import needed component and dependency
import { Component, OnInit } from '@angular/core';
import { NurseryService } from 'app/entities/service/nursery.service';
import { SectorService } from 'app/entities/service/sector.service';
import { ActivatedRoute, Router } from '@angular/router';
import { INursery, NurseryModel } from 'app/shared/model/nursery.model';
import { ISector } from 'app/shared/model/sector.model';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { ViewChild } from '@angular/core';

import { JhiParseLinks } from 'ng-jhipster';

import * as moment from 'moment';
import { DATE_TIME_FORMAT, ITEMS_PER_PAGE, STATUS_ACTIVE, SOFT_DELETE_STATUS } from 'app/shared';
import { HttpResponse, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ZonalService } from 'app/entities/service/zonal.service';
import { IZonal } from 'app/shared/model/zonal.model';
import { IPickList } from 'app/shared/model/pick-list.model';
import { IPickListValue } from 'app/shared/model/pick-list-value.model';
import { PickListService } from 'app/entities/service/pick-list.service';
import { PickListValueService } from 'app/entities/service/pick-list-value.service';

// Display the alert message of success and error
import { Subject } from 'rxjs';
import { debounceTime } from 'rxjs/operators';
import { FinancialYearSettingsService } from 'app/entities/service/financial-year-settings.service';
import { IFinancialYearSettings } from 'app/shared/model/financial-year-settings.model';
import { OperationalHeadService } from 'app/entities/service/operational-head.service';
import { IOperationalHead } from 'app/shared/model/operational-head.model';
import {
    MapNurseryWithSector,
    IMapNurseryWithSector,
    MapNurseryWithSectorModel,
    STATUS_INACTIVE
} from 'app/shared/model/map-nursery-with-sector.model';
import { MapNurseryWithSectorService } from 'app/entities/service/map-nursery-with-sector.service';

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
    varietys: IPickListValue[];
    mapNurseryWithSectors: IMapNurseryWithSector[];
    toDate: string;
    batchId: number;
    updateStatus: number;
    isCollapsed = true;
    fromDateDp: any;

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
        private activatedRoute: ActivatedRoute
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
        // Call a function to get list of active headOffice
        this.getOperationalHead();
        // Call a function to get list of zonals and Nurserys
        this.getNurseryList();
        this.getPickList();
        // Call a function to get active batch id
        this.getActiveRecord();

        // To set the time for automatic alert close
        setTimeout(() => (this.staticAlertClosed = true), 20000);

        // Set the success message with debounce time
        this.success.subscribe(message => (this.successMessage = message));
        this.success.pipe(debounceTime(5000)).subscribe(() => (this.successMessage = null));

        // To set the error message with debounce time
        this.error.subscribe(message => (this.errorMessage = message));
        this.error.pipe(debounceTime(5000)).subscribe(() => (this.errorMessage = null));
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

    // Get the sector value based on zonal id
    getSector(zoneId): void {
        // Get the list of sector
        this.sectorService.getSectors(zoneId).subscribe((res: HttpResponse<ISector[]>) => {
            this.sectors = res.body;
        });
    }

    // Call a service function to get list of Nurserys
    getNurseryList(): void {
        this.nurseryService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort(),
                filter: { 'status.equals': STATUS_ACTIVE }
            })
            .subscribe((res: HttpResponse<INursery[]>) => this.paginateNurserys(res.body, res.headers));
    }

    // Call a service function to get list of active batch
    getActiveRecord(): void {
        // Get the list of active batch record and assign a 0th index array value to an batch id
        this.settingsService.getActiveRecord().subscribe((res: HttpResponse<IFinancialYearSettings[]>) => {
            this.batchId = res.body[0].id;
        });
    }

    // Call a service function to get list of pickList
    getPickList(): void {
        // Get the list of picklist
        this.pickListService
            .query({
                filter: { 'status.equals': STATUS_ACTIVE }
            })
            .subscribe((res: HttpResponse<IPickList[]>) => {
                this.pickLists = res.body;
            });
    }

    // Get the variety from the picklist table
    getVariety(id): void {
        this.pickListValueService.getVariety(id).subscribe((res: HttpResponse<IPickListValue[]>) => {
            // console.log(res.body);
            this.varietys = res.body;
        });
    }

    // Save the nuresery details
    save() {
        this.nurseryObject.status = 1;
        this.nurseryObject.status = STATUS_ACTIVE;
        // If the nursery id is not equal to undefined, update the old record
        if (this.nurseryObject.id !== undefined) {
            // Set the title for the alert updated
            this.alertTitle = 'updated';
            this.subscribeToSaveResponse(this.nurseryService.update(this.nurseryObject), this.alertTitle);
        } else {
            // Set the title for the alert created
            this.alertTitle = 'created';
            this.nurseryObject.financialYearNurseryId = this.batchId;
            this.subscribeToSaveResponse(this.nurseryService.create(this.nurseryObject), this.alertTitle);
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<INursery>>, alertTitle) {
        result.subscribe(
            (res: HttpResponse<INursery>) => {
                this.nurseryModal.hide();
                this.moveNurseryValue(res.body, this.mapNurseryWithSector);
                // alert('Nursery Created/Updated Successfully.');
                this.success.next(`Nursery ${alertTitle} successfully`);
                this.getNurseryList();
                this.nurseryObject = new NurseryModel();
            },
            (res: HttpErrorResponse) => {
                // alert('Nursery Not Saved.');
                // alert(res.error.fieldErrors[0].message);
                this.error.next(res.error.fieldErrors[0].message);
            }
        );
    }

    saveMapTable(nurseryDetails: NurseryModel, mapNurseryWithSector: MapNurseryWithSector): void {
        this.mapNurseryWithSector = new MapNurseryWithSectorModel();
        // this.mapNurseryWithSector.fromDate = moment(moment(this.mapNurseryWithSector.fromDate).format(DATE_TIME_FORMAT), DATE_TIME_FORMAT);
        this.mapNurseryWithSector.fromDate = mapNurseryWithSector.fromDate;
        this.mapNurseryWithSector.sectorId = nurseryDetails.sectorId;
        this.mapNurseryWithSector.nurseryId = nurseryDetails.id;
        this.mapNurseryWithSector.status = STATUS_ACTIVE;
        console.log(this.mapNurseryWithSector);
        this.mapNurseryWithSectorService.create(this.mapNurseryWithSector).subscribe(
            (res: HttpResponse<IMapNurseryWithSector>) => {
                this.moveNursery.hide();
            },
            (res: HttpErrorResponse) => {
                // alert(res.error.fieldErrors[0].message);
                this.error.next(res.error.fieldErrors[0].message);
            }
        );
    }

    moveNurseryValue(nurseryDetails: NurseryModel, value: MapNurseryWithSector): void {
        this.toDate = moment(value.toDate).format(DATE_TIME_FORMAT);
        // Get the list of active batch record and assign a 0th index array value to an batch id
        this.mapNurseryWithSectorService
            .getParticularNurseryActiveRecord(nurseryDetails.id)
            .subscribe((res: HttpResponse<IMapNurseryWithSector[]>) => {
                if (res.body.length > 0) {
                    this.mapNurseryWithSector = res.body[0];
                    this.mapNurseryWithSector.description = value.description;
                    this.mapNurseryWithSector.toDate = moment(this.toDate, DATE_TIME_FORMAT);
                    this.mapNurseryWithSector.status = STATUS_INACTIVE;
                    this.mapNurseryWithSectorService.update(this.mapNurseryWithSector).subscribe(
                        (output: HttpResponse<IMapNurseryWithSector>) => {
                            this.saveMapTable(nurseryDetails, value);
                        },
                        (error: HttpErrorResponse) => {
                            // alert(res.error.fieldErrors[0].message);
                            this.error.next(error.error.fieldErrors[0].message);
                        }
                    );
                } else {
                    this.saveMapTable(nurseryDetails, value);
                }
            });
    }

    // show model popup to create nursery value
    createNursery(): void {
        this.nurseryObject = new NurseryModel();
        this.nurseryModal.show();
        this.title = 'Create Nursery:';
    }

    // show model popup to update nursery value
    getNurseryValue(value: NurseryModel, status): void {
        // console.log(value);
        // console.log(value.sectorSectorName);
        // this.sectors[0].id = value.sectorId;
        if (status !== this.updateStatus) {
            this.nurseryModal.show();
        } else {
            this.mapNurseryWithSector = new MapNurseryWithSectorModel();
            this.moveNursery.show();
        }
        this.nurseryObject = value;
        this.title = `Update Nursery: ${value.nurseryName}`;
    }

    // commented for making the delete as soft one which means not to delete from db
    // deleteNursery(nursery: NurseryModel): void {
    //     this.nurseryService.delete(nursery.id).subscribe(data => {
    //         // alert('Nursery deleted Successfully.');
    //         this.success.next(`Nursery deleted successfully`);
    //         this.nurserys = this.nurserys.filter(u => u !== nursery);
    //     });
    // }

    // soft delete
    softDelete(nursery: NurseryModel): void {
        this.nurseryObject = nursery;
        this.nurseryObject.status = SOFT_DELETE_STATUS;
        // console.log('date', this.sectorObject);
        this.nurseryService.update(this.nurseryObject).subscribe(
            data => {
                // console.log('upda', this.sectorObject);
                this.success.next(`Nursery deleted successfully`);
                this.getNurseryList();
            },
            (res: HttpErrorResponse) => {
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    // saveMoved(value: NurseryModel): void {
    //    // console.log(this.nurseryObject);
    //    // console.log('val ', value);
    //    this.nurseryObject = value;
    //    this.nurseryObject.status = 2;
    //    // this.nurseryService.update(this.nurseryObject);
    //    this.subscribeToSaveNursery(this.nurseryService.update(this.nurseryObject));
    // }

    // // move nursery from one sector/zonal to another
    // moveNursery(value: NurseryModel): void {
    //     this.nurseryObject = new NurseryModel();
    //     this.moveNurseryModal.show();
    //     this.nurseryObject = value;
    //     // console.log('move ', this.nurseryObject);
    // }

    // private subscribeToSaveNursery(result: Observable<HttpResponse<INursery>>) {
    //     result.subscribe(
    //         (res: HttpResponse<INursery>) => {
    //             this.moveNurseryModal.hide();
    //             console.log('nursery', this.nurseryObject);
    //             this.nurseryObject.id = null;
    //             this.nurseryObject.status = 1;
    //             this.alertTitle = '';
    //             this.nurseryObject = new NurseryModel();
    //             this.nurseryObject.financialYearNurseryId = this.batchId;
    //             this.subscribeToSaveResponse(
    //                 this.nurseryService.create(this.nurseryObject), this.alertTitle);
    //             alert('moved.');
    //             this.getNurseryList();
    //         },
    //         (res: HttpErrorResponse) => {
    //             // alert('Nursery Not Saved.');
    //             alert(res.error.fieldErrors[0].message);
    //         }
    //     );
    // }

    getMapList(id: number): void {
        // Get the list of record based on zonal id
        this.mapNurseryWithSectorService.getParticularNurseryRecord(id).subscribe((res: HttpResponse<IMapNurseryWithSector[]>) => {
            this.isCollapsed = false;
            this.mapNurseryWithSectors = res.body;
        });
    }

    // If the sector model pop up closed, to call the get sector list function
    closeNurseryModal(): void {
        // Hide the sector model pop-up
        this.nurseryModal.hide();
        // Call the getSector List function
        this.getNurseryList();
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

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

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    trackId(index: number, item: INursery) {
        return item.id;
    }

    private paginateNurserys(data: INursery[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.nurserys = data;
    }

    // If the nursery model pop up closed, to call the get nursery list function
    closeMovementModel(): void {
        // Hide the nursery model pop-up
        this.moveNursery.hide();
        // Call the getNursery List function
        this.getNurseryList();
    }
}
