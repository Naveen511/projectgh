/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date   : 2018/08/02 11:27:58
 *  Target : yarn
 *******************************************************************************/

// Import angular dependency, model, service and shared
import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { HttpResponse, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiParseLinks } from 'ng-jhipster';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbDatepickerConfig } from '@ng-bootstrap/ng-bootstrap';

import { IZonal } from 'app/shared/model/zonal.model';
import { ISector } from 'app/shared/model/sector.model';
import { INursery } from 'app/shared/model/nursery.model';
import { IPickList } from 'app/shared/model/pick-list.model';
import { IPickListValue } from 'app/shared/model/pick-list-value.model';
import { IBatch, BatchModel,
    DISPLAY_NAME_VARIETY, DISPLAY_NAME_QUANTITY_TYPE,
    DISPLAY_NAME_DAMAGE_AREA, DISPLAY_NAME_DAMAGE_REASON,
    STATUS_BATCH_CLOSE, STATUS_SHOWING_TYPE_COVER } from 'app/shared/model/batch.model';
import { IShadeArea, ShadeAreaModel, ShadeArea } from 'app/shared/model/shade-area.model';
import {
    NurseryStockModel, STATUS_FROM_BATCH, INurseryStock
} from 'app/shared/model/nursery-stock.model';
import {
    NurseryStockDetailsModel, STATUS_ADD, NurseryStockDetails
} from 'app/shared/model/nursery-stock-details.model';
import {
    IDamage, DamageModel, STATUS_SEEDS, STATUS_SEEDLING
} from 'app/shared/model/damage.model';
import {
    IMotherBed, MotherBedModel, STATUS_OCCUPIED, STATUS_AVAILABLE
} from 'app/shared/model/mother-bed.model';
import {
    BatchQuantityModel, IBatchQuantity, STATUS_ADDED
} from 'app/shared/model/batch-quantity.model';

// import { ZonalService } from 'app/entities/service/zonal.service';
// import { SectorService } from 'app/entities/service/sector.service';
import { NurseryService } from 'app/entities/service/nursery.service';
import { BatchService } from 'app/entities/service/batch.service';
import { DamageService } from 'app/entities/service/damage.service';
import { PickListService } from 'app/entities/service/pick-list.service';
import { PickListValueService } from 'app/entities/service/pick-list-value.service';
import { ShadeAreaService } from 'app/entities/service/shade-area.service';
import { NurseryStockService } from 'app/entities/service/nursery-stock.service';
import {
    NurseryStockDetailsService
} from 'app/entities/service/nursery-stock-details.service';
import { MotherBedService } from 'app/entities/service/mother-bed.service';
import {
    FinancialYearSettingsService
} from 'app/entities/service/financial-year-settings.service';
import { IFinancialYearSettings } from 'app/shared/model/financial-year-settings.model';
import { BatchQuantityService } from 'app/entities/service/batch-quantity.service';

import {
    DATE_TIME_FORMAT, MONTHS, ITEMS_PER_PAGE, SOFT_DELETE_STATUS, STATUS_ACTIVE
} from 'app/shared';

// Display the error Message and subject details
import { Subject } from 'rxjs';
import { debounceTime } from 'rxjs/operators';
import * as moment from 'moment';

// Mention the html, css/sass files
@Component({
    selector: 'jhi-batchmgnt',
    templateUrl: 'batchmgnt.component.html'
})

/**
 * Class BatchmgntComponent used to create/update a batch,
 * moving of seedlings to the shade/damaged area and
 * saplings to the seasoning/damaged area finally moved to stock.
 * Declared an batch object to create and update.
 * Declared an Array variable to set list of batch values.
 * Using a modal popup directive create and update form is displayed.
 */
export class BatchmgntComponent implements OnInit {
    // Create object of each model for create form
    batch: BatchModel = new BatchModel();
    updateBatchValue: BatchModel = new BatchModel();
    damage: DamageModel = new DamageModel();
    shadeArea: ShadeAreaModel = new ShadeAreaModel();
    nurseryStock: NurseryStockModel = new NurseryStockModel();
    nurseryStockDetails: NurseryStockDetailsModel = new NurseryStockDetailsModel();
    motherBedModel: MotherBedModel = new MotherBedModel();
    batchQuantity: BatchQuantityModel = new BatchQuantityModel();
    sowingDateDp: any;
    closedDateDp: any;
    dateDamageDp: any;
    dateShadeAreaDp: any;
    dateSeasoningAreaDp: any;
    dateQuantityDp: any;
    fromDate: any;
    toDate: any;
    damageCount: any;
    shadeCount: any;
    quantityType: any;
    motherBedValue: any;
    quantityTypeKg: string;
    quantityTypeNos: string;

    // Used for date filtering
    // filter: Filter = new Filter();

    // create empty array for each service
    zonals: IZonal[];
    sectors: ISector[];
    nurserys: INursery[];
    pickLists: IPickList[];
    varieties: IPickListValue[];
    categories: IPickListValue[];
    quantityTypes: IPickListValue[];
    damageAreas: IPickListValue[];
    batches: IBatch[];
    shadeAreas: IShadeArea[];
    damages: IDamage[];
    motherBeds: IMotherBed[];
    batchQuantities: IBatchQuantity[];
    months = MONTHS;
    addStockStatus = STATUS_ADD;
    financialYearId: number;
    activeNurseryId: number;

    isCollapsed = true;

    // For pagination
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;
    pickListVarietyId: any;
    pickListCategoryId: any;
    expecQuantity: any;
    shadeDamageCount: any;
    actualQuatity: any;
    shadeDamageAndUserCount: any;
    marked = false;
    theCheckbox = false;

    // To display the success message
    private success = new Subject<string>();
    successMessage: string;

    // To display the error message
    private error = new Subject<string>();
    errorMessage: string;

    // By default close the alert with statc time
    staticAlertClosed = false;

    // Declare a modal popup
    @ViewChild('closeBatchModal') public closeBatchModal: ModalDirective;
    @ViewChild('damageModal') public damageModal: ModalDirective;
    @ViewChild('shiftBatchModal') public shiftBatchModal: ModalDirective;
    @ViewChild('shadeAreaRecordModal') public shadeAreaRecordModal: ModalDirective;
    @ViewChild('damageRecordModal') public damageRecordModal: ModalDirective;
    @ViewChild('stockModal') public stockModal: ModalDirective;
    @ViewChild('addQuantityModal') public addQuantityModal: ModalDirective;
    @ViewChild('quantityRecordModal') public quantityRecordModal: ModalDirective;

    constructor(
        // private zonalService: ZonalService,
        // private sectorService: SectorService,
        private nurseryService: NurseryService,
        private pickListService: PickListService,
        private pickListValueService: PickListValueService,
        private batchService: BatchService,
        private damageService: DamageService,
        private shadeAreaService: ShadeAreaService,
        private nurseryStockService: NurseryStockService,
        private nurseryStockDetailsService: NurseryStockDetailsService,
        private motherBedService: MotherBedService,
        private settingsService: FinancialYearSettingsService,
        private batchQuantityService: BatchQuantityService,
        private parseLinks: JhiParseLinks,
        private router: Router,
        private activatedRoute: ActivatedRoute,
        private config: NgbDatepickerConfig
    ) {
        // Declare a value to url params
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            // console.log(data);
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

        // Set the constant for Quantity type- Kg and Nos
        this.quantityTypeKg = 'KG';
        this.quantityTypeNos = 'NOS';
    }

    ngOnInit() {
        // Get the list of batch
        this.getBatchList();

        // Call a function to get active batch id
        this.getActiveRecord();

        // To get the shade area list
        // this.getShadeAreaList();
        // To get the seedlings damage list
        // this.getDamageList();

        // To set the time for automatic alert close
        setTimeout(() => (this.staticAlertClosed = true), 5000);

        // Set the success message with debounce time
        this.success.subscribe(message => (this.successMessage = message));
        this.success.pipe(debounceTime(3000))
        .subscribe(() => (this.successMessage = null));

        // To set the error message with debounce time
        this.error.subscribe(message => (this.errorMessage = message));
        this.error.pipe(debounceTime(5000))
        .subscribe(() => (this.errorMessage = null));

        // console.log("Inside Batch Component");
        // commented by Naveen to get the nursery id for batch craetion
        // Get the list of zone
        /* this.zonalService
            .query({
                filter: { 'status.equals': STATUS_ACTIVE }
            })
            .subscribe((res: HttpResponse<IZonal[]>) => {
                this.zonals = res.body;
        }); */

        // To get the active nursery of 0th index
        /**  this.nurseryService.query({
            filter: {
                'status.equals': STATUS_ACTIVE
            }
        }).subscribe((res: HttpResponse<INursery[]>) => {
            // console.log(res.body);
            this.activeNurseryId = res.body[0].id;
            // console.log('body', this.activeNurseryId);
            // To get the active motherbed list
            this.motherBedService.query({
                filter: {
                    'status.equals': STATUS_AVAILABLE
                }
            }).subscribe((output: HttpResponse<IMotherBed[]>) => {
                this.motherBeds = output.body;
                // console.log('available ', this.motherBeds);
            });
        });

        // Get the list of picklist
        this.pickListService
            .query({
                filter: { 'status.equals': STATUS_ACTIVE }
            }).subscribe((res: HttpResponse<IPickList[]>) => {
                this.pickLists = res.body;
            });

        // To get the pick list Id for Pick List Nursery Type
        // this.pickListService.getPickListId(DISPLAY_NAME_VARIETY)
        this.pickListService
            .query({
                filter: {
                    'status.equals': STATUS_ACTIVE,
                    'displayLabelName.equals': DISPLAY_NAME_VARIETY
                }
            }).subscribe((res: HttpResponse<IPickList[]>) => {
                if (res.body.length > 0) {
                    this.getVariety(res.body[0].id);
                }
            });

        // To get the pick list Id for Pick List Nursery Type
        // this.pickListService.getPickListId(DISPLAY_NAME_QUANTITY_TYPE)
        this.pickListService.query({
            filter: {
                'status.equals': STATUS_ACTIVE,
                'displayLabelName.equals': DISPLAY_NAME_QUANTITY_TYPE
            }
        }).subscribe((res: HttpResponse<IPickList[]>) => {
            if (res.body.length > 0) {
                this.getQuantityType(res.body[0].id);
            }
        }); */
    }

    /**
     * To create the batch, show the create batch form
     *
     */
    createBatch(): void {
        // Hide the form and reset the batch creation form
        this.isCollapsed = false;
        this.resetForm();

        this.nurseryService.query({ filter: { 'status.equals': STATUS_ACTIVE }
        }).subscribe((res: HttpResponse<INursery[]>) => {
            this.activeNurseryId = res.body[0].id;
        });

        // To get the pick list Id for Pick List Nursery Type
        // this.pickListService.getPickListId(DISPLAY_NAME_VARIETY)
        this.pickListService.query({ filter: {
            'status.equals': STATUS_ACTIVE,
            'displayLabelName.equals': DISPLAY_NAME_VARIETY
            }}).subscribe((res: HttpResponse<IPickList[]>) => {
                if (res.body.length > 0) {
                    this.getVariety(res.body[0].id);
                }
            });

        // To get the pick list Id for Pick List Nursery Type
        // this.pickListService.getPickListId(DISPLAY_NAME_QUANTITY_TYPE)
        this.pickListService.query({ filter: {
            'status.equals': STATUS_ACTIVE,
            'displayLabelName.equals': DISPLAY_NAME_QUANTITY_TYPE
        }}).subscribe((res: HttpResponse<IPickList[]>) => {
            if (res.body.length > 0) {
                this.getQuantityType(res.body[0].id);
            }
        });
    }

    /**
     * To make the toggle visibility for checkbox
     *
     * @param e checked status
     */
    toggleVisibility(e) {
        this.marked = e.target.checked;
    }

    /**
     * Get the list of Batch values from the batch table
     */
    getBatchList(): void {
        // Get the list of batch
        this.batchService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe((res: HttpResponse<IBatch[]>) => {
                this.paginateBatchs(res.body, res.headers);
            });
    }

    /**
     * Get the active record from the calendar settings
     */
    getActiveRecord(): void {
        // Get the list of active batch record and
        // assign a 0th index array value to an batch id
        this.settingsService
            .query({
                filter: { 'status.equals': STATUS_ACTIVE }
            }).subscribe((res: HttpResponse<IFinancialYearSettings[]>) => {
                // Assign a value to an object
                this.financialYearId = res.body[0].id;
            });
    }

    /**
     * Get the shadeAreaList from the shade area table
     */
    getShadeAreaList(): void {
        // console.log('Indide getShadeAreaList');
        // Get the list of shade area
        this.shadeAreaService.query()
        .subscribe((res: HttpResponse<IShadeArea[]>) => {
            this.shadeAreas = res.body;
        });
    }

    /**
     * Get the Damage list from the damage
     */
    getDamageList(): void {
        // console.log('Indide getDamageList');
        // Get the list of damage
        this.damageService.query().subscribe((res: HttpResponse<IDamage[]>) => {
            this.damages = res.body;
        });
    }

    // Get the sector value based on zonal id
    // commented by Naveen to get the nursery id for batch craetion
    /* getSector(zoneId): void {
        // console.log(zoneId);
        // Get the list of sector
        // this.sectorService.getSectors(zoneId)
        this.sectorService.query({
            filter: {
                'status.equals': STATUS_ACTIVE,
                'zonalId.equals': zoneId
            }
        })
        .subscribe((res: HttpResponse<ISector[]>) => {
            // console.log(res.body);
            this.sectors = res.body;
        });
    }

    // Get the nursery based on the sector Id
    getNursery(sectorId): void {
        // Get the list of nursery
        // this.nurseryService.getNurserys(sectorId)
        this.nurseryService.query({
            filter: {
                'status.equals': STATUS_ACTIVE,
                'sectorId.equals': sectorId
            }
        })
        .subscribe((res: HttpResponse<INursery[]>) => {
            // console.log(res.body);
            this.nurserys = res.body;
        });
    }

    // Get Motherbed based on the nurseryId
    getMotherBedValue(nurseryId): void {
        // Get the list of motherBed List
        this.motherBedService.getMotherBed(nurseryId)
        .subscribe((res: HttpResponse<IMotherBed[]>) => {
            // console.log(res.body);
            this.motherBeds = res.body;
        });
    }

    getMotherBedForNursery(nurseryId): void {
        console.log('nurse id', nurseryId);
        // Get the list of motherBed List
        this.motherBedService.getMotherBedNursery(nurseryId).subscribe((res: HttpResponse<IMotherBed[]>) => {
            // console.log(res.body);
            this.motherBeds = res.body;
            // console.log('motherbed', this.motherBeds);
        });
    } */

    /**
     * Get picklist variety based on the pick list ID
     *
     * @param id id of picklist
     */
    getVariety(id): void {
        // this.pickListValueService.getVariety(id)
        this.pickListValueService
            .query({
                filter: {
                    'status.equals': STATUS_ACTIVE,
                    'pickListId.equals': id
                }
            }).subscribe((res: HttpResponse<IPickListValue[]>) => {
                // console.log(res.body);
                this.varieties = res.body;
            });
    }

    /**
     * Get the quantity type based on the quantity table id
     *
     * @param id id of quantity
     */
    getQuantityType(id): void {
        // this.pickListValueService.getVariety(id)
        this.pickListValueService
            .query({
                filter: {
                    'status.equals': STATUS_ACTIVE,
                    'pickListId.equals': id
                }
            }).subscribe((res: HttpResponse<IPickListValue[]>) => {
                // console.log(res.body);
                this.quantityTypes = res.body;
            });
    }

    /**
     * Get the picklist category based on the pick list value table id
     *
     * @param id id of category
     */
    getCategory(id): void {
        // this.pickListValueService.getCategory(id)
        this.pickListValueService
            .query({
                filter: {
                    'status.equals': STATUS_ACTIVE,
                    'pickValueId.equals': id
                }
            }).subscribe((res: HttpResponse<IPickListValue[]>) => {
                // console.log(res.body);
                this.categories = res.body;
            });
    }

    /**
     * Get the damage area based on the pick list value table id
     *
     * @param id id of damage area
     */
    getDamageArea(id): void {
        // this.pickListValueService.getVariety(id)
        this.pickListValueService
            .query({
                filter: {
                    'status.equals': STATUS_ACTIVE,
                    'pickListId.equals': id
                }
            }).subscribe((res: HttpResponse<IPickListValue[]>) => {
                // console.log(res.body);
                this.damageAreas = res.body;
            });
    }

    /**
     * To save the batch values
     */
    save() {
        // console.log('Testing');
        // console.log(this.batch);
        this.batch.status = STATUS_ACTIVE;
        this.batch.nurseryId = this.activeNurseryId;
        this.batch.sowingDate = moment(this.batch.sowingDate, DATE_TIME_FORMAT);
        this.batch.financialYearBatchId = this.financialYearId;
        // To check if showing type is choosed and motherbed not choosen
        if (this.batch.showingType == 1 && this.batch.motherBedId != null) {
            this.subscribeToSaveResponse(this.batchService.create(this.batch));
        } else if (this.batch.showingType == 2) {
            this.subscribeToSaveResponse(this.batchService.create(this.batch));
        } else {
            this.error.next('Please choose motherbed.');
        }
    }

    /**
     * Call the save response function
     *
     * @param result as object of batch formation
     */
    private subscribeToSaveResponse(result: Observable<HttpResponse<IBatch>>) {
        result.subscribe(
            (res: HttpResponse<IBatch>) => {
                if (this.batch.showingType == 1) {
                    this.motherBedModel.id = this.batch.motherBedId;
                    this.motherBedModel.value = this.motherBedValue;
                    this.motherBedModel.nurseryId = this.batch.nurseryId;
                    this.motherBedModel.status = STATUS_OCCUPIED;
                    // console.log('motherbed update', this.motherBedModel);
                    this.motherBedService.update(this.motherBedModel).subscribe(
                        data => {
                            // console.log('data ', data.body);
                        },
                        (error: HttpErrorResponse) => {
                            this.error.next(error.error.fieldErrors[0].message);
                            // alert(error.error.fieldErrors[0].message);
                        }
                    );
                }
                // console.log('after motherbed update', this.motherBedModel);
                this.batch = new BatchModel();
                // alert('Batch Created Successfully.');
                this.getBatchList();
                this.isCollapsed = true;
                this.batchQuantity = new BatchQuantityModel();
                this.batchQuantity.batchId = res.body.id;
                this.batchQuantity.quantity = res.body.quantity;
                this.batchQuantity.date = res.body.sowingDate;
                this.batchQuantity.status = STATUS_ADDED;
                this.saveBatchQuantity('Batch created successfully.');
                this.categories = null;
            },
            (res: HttpErrorResponse) => {
                // alert('Batch Not Saved.');
                // alert(res.error.fieldErrors[0].message);
                this.error.next(res.error.fieldErrors[0].message);
            }
        );
    }

    /**
     * Soft delete for the particular batch
     *
     * @param value batch values
     */
    softDeleteBatch(value: BatchModel): void {
        this.batch = value;
        this.batch.status = SOFT_DELETE_STATUS;
        this.batchService.update(this.batch).subscribe(
            data => {
                alert('Successfully deleted.');
                if (this.batch.motherBedId != null) {
                    this.motherBedModel.id = this.batch.motherBedId;
                    this.motherBedModel.value = this.batch.motherBedValue;
                    this.motherBedModel.nurseryId = this.batch.nurseryId;
                    this.motherBedModel.status = STATUS_AVAILABLE;
                    // console.log('motherbed update', this.motherBedModel);
                    this.motherBedService.update(this.motherBedModel).subscribe(
                        result => {
                            // console.log('data ', result.body);
                        },
                        (err: HttpErrorResponse) => {
                            alert(err.error.fieldErrors[0].message);
                        }
                    );
                }
                this.getBatchList();
                // this.success.next(`Zone deleted successfully`);
            },
            (res: HttpErrorResponse) => {
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    // Get the motherbed list
    getMotherBed(showingStatus): void {
        // If the showing type as not equal to cover
        if (showingStatus !== STATUS_SHOWING_TYPE_COVER) {
            this.motherBedService.query({ filter: { 'status.equals': STATUS_AVAILABLE }})
            .subscribe((output: HttpResponse<IMotherBed[]>) => {
                this.motherBeds = output.body;
            });
        }
    }

    /**
     * To get the quantity type values while the user click on the event
     *
     * @param event event values
     */
    getQuantityTypeValue(event: Event) {
        // const selectedText = event.target['options'][event.target['options'].selectedIndex].text;
        // this.quantityType = selectedText;
        this.quantityType = event.target['options'][event.target['options'].selectedIndex].text;
        // console.log(this.quantityType);
    }

    /**
     * To get the motherbed type values while the user click on the event
     *
     * @param event event values
     */
    getMotherBedTypeValue(event: Event) {
        // const selectedText = event.target['options'][event.target['options'].selectedIndex].text;
        this.motherBedValue = event.target['options'][event.target['options'].selectedIndex].text;
        // console.log('motherbed value', this.motherBedValue);
    }

    // commented for making the delete as soft one which means not to delete from db
    // deleteBatch(batch: BatchModel): void {
    //     this.batchService.delete(batch.id).subscribe(data => {
    //         this.batches = this.batchs.filter(u => u !== batch);
    //         alert('Successfully Deleted.');
    //     });
    // }

    /**
     * Shown model popup to close the batch
     *
     * @param value batch values
     */
    updateBatch(value: BatchModel): void {
        // console.log('close', value);
        this.updateBatchValue = new BatchModel();
        this.closeBatchModal.show();
        this.updateBatchValue = value;
    }

    /**
     * Shown model popup to update the damage
     *
     * @param value damage update
     */
    updateDamage(value: BatchModel): void {
        this.updateBatchValue = new BatchModel();
        // console.log(value);
        this.damageModal.show();
        this.updateBatchValue = value;
        this.updateBatchValue.status = STATUS_SEEDS;
        this.getDamageAreaAndReason();
    }

    getDamageAreaAndReason(): void {
        // To get the pick list Id for Pick List Nursery Type
        // this.pickListService.getPickListId(DISPLAY_NAME_DAMAGE_AREA)
        this.pickListService
            .query({
                filter: {
                    'status.equals': STATUS_ACTIVE,
                    'displayLabelName.equals': DISPLAY_NAME_DAMAGE_AREA
                }
            }).subscribe((res: HttpResponse<IPickList[]>) => {
                if (res.body.length > 0) {
                    this.getDamageArea(res.body[0].id);
                }
            });

        // To get the pick list Id for Pick List Nursery Type
        // this.pickListService.getPickListId(DISPLAY_NAME_DAMAGE_REASON)
        this.pickListService
            .query({
                filter: {
                    'status.equals': STATUS_ACTIVE,
                    'displayLabelName.equals': DISPLAY_NAME_DAMAGE_REASON
                }
            }).subscribe((res: HttpResponse<IPickList[]>) => {
                if (res.body.length > 0) {
                    this.getVariety(res.body[0].id);
                }
            });
    }

    /**
     * Shown model popup to shift the seed to shade area
     *
     * @param value batch values
     */
    updateShift(value: BatchModel): void {
        this.updateBatchValue = new BatchModel();
        // console.log('batch value', value);
        this.shiftBatchModal.show();
        this.updateBatchValue = value;
    }

    /**
     * Shown model popup to update the damage in seeldings
     *
     * @param value shadearea values
     */
    updateSeedlingDamage(value: ShadeAreaModel): void {
        this.shadeArea = value;
        this.updateBatchValue = new BatchModel();
        // console.log(value);
        this.updateBatchValue.id = value.batchId;
        this.updateBatchValue.status = STATUS_SEEDLING;
        this.updateBatchValue.noOfSeedlings = value.noOfSeedlings;
        this.updateBatchValue.seedlingsCount = value.saplings;
        this.updateBatchValue.damagecount = value.damage;
        console.log('updateBatchValue', this.updateBatchValue);
        this.damageModal.show();
        this.getDamageAreaAndReason();
    }

    /**
     * Shown model popup to shift the seed to seasoning area (stock)
     *
     * @param value shadearea values
     */
    updateStock(value: ShadeAreaModel): void {
        this.shadeArea = value;
        // console.log('row', this.shadeArea);
        this.nurseryStockDetails = new NurseryStockDetails();
        this.nurseryStockDetails.batchId = value.batchId;
        this.stockModal.show();
    }

    /**
     * To add the Quantity directly to batch
     *
     * @param value batch values
     */
    addQuantity(value: BatchModel) {
        this.batchQuantity = new BatchQuantityModel();
        this.updateBatchValue = new BatchModel();
        // console.log(value);
        this.updateBatchValue = value;
        this.addQuantityModal.show();
    }

    /**
     * To close the batch and update the motherbed if user choose motherbed
     *
     * @param batch batch values
     */
    closeBatch(batch: BatchModel): void {
        if ((batch.closedDate === null)
            || (batch.closedDate === undefined)
        ) {
            this.error.next('Close date cannot be blank.');
        } else {
            this.shadeAreaService.getParticularBatchShadeCount(this.updateBatchValue.id)
            .subscribe((result: HttpResponse<String>) => {
                // console.log('len', result.body);
                this.shadeCount = (result.body != null) ? result.body : 0 ;
                this.damageService.getParticularBatchDamageCount(this.updateBatchValue.id)
                .subscribe((resource: HttpResponse<String>) => {
                    this.damageCount = (resource.body != null) ? resource.body : 0 ;
                    // console.log('damage', this.damageCount);
                    this.shadeDamageCount = this.shadeCount + this.damageCount;
                    // console.log('batch', batch.quantity);
                    // console.log('shade', this.shadeDamageCount);
                    if (batch.quantity === this.shadeDamageCount) {
                        this.batch = batch;
                        this.batch.status = STATUS_BATCH_CLOSE;
                        this.batch.closedDate = moment(this.batch.closedDate, DATE_TIME_FORMAT);
                        this.batchService.update(this.batch).subscribe(
                            data => {
                                // alert('Bacth Closed Successfully.');
                                this.success.next('Batch closed successfully.');
                                if (this.batch.motherBedId != null) {
                                    this.motherBedModel.id = this.batch.motherBedId;
                                    this.motherBedModel.value = this.batch.motherBedValue;
                                    this.motherBedModel.nurseryId = this.batch.nurseryId;
                                    this.motherBedModel.status = STATUS_AVAILABLE;
                                    // console.log('motherbed update', this.motherBedModel);
                                    this.motherBedService.update(this.motherBedModel).subscribe(
                                        output => {
                                            // console.log('data ', output.body);
                                        },
                                        (err: HttpErrorResponse) => {
                                            // alert(err.error.fieldErrors[0].message);
                                            this.error.next(err.error.fieldErrors[0].message);
                                        }
                                    );
                                }
                                // Hide the closeBatchModal
                                this.closeBatchModal.hide();
                            },
                            (res: HttpErrorResponse) => {
                                // alert(res.error.fieldErrors[0].message);
                                this.error.next(res.error.fieldErrors[0].message);
                            }
                        );
                        // this.closeBatchModal.hide();
                    } else {
                        const difference = this.updateBatchValue.quantity - this.shadeDamageCount;
                        alert('You cant able to close the batch. Because, you have ' + difference + ' seedlings in your batch.');
                        // Hide the closeBatchModal
                        this.closeBatchModal.hide();
                        // alert('Motherbed having seedlings please move to another area');
                    }
                });
            });
        }
    }

    /**
     * Create new damage in created batch and check the quanity exceeds or not
     *
     * @param value damage value
     */
    createDamage(value: DamageModel): void {
        if ((value.damageAreaId === null)
            || (value.damageAreaId === undefined)
        ) {
            this.error.next('Damage area cannot be blank.');
        } else if ((value.noOfQuantity === null)
            || (value.noOfQuantity === undefined)
            || (value.noOfQuantity <= 0 )
        ) {
            this.error.next('Quantity cannot be blank or 0.');
        } else {
            // this.damage = value;
            // console.log(value);
            // if (this.shadeArea.noOfSeedlings <= this.damage.noOfQuantity) {}
            if (this.updateBatchValue.status === STATUS_SEEDS) {
                this.shadeAreaService.getParticularBatchShadeCount(this.updateBatchValue.id)
                .subscribe((result: HttpResponse<String>) => {
                    // console.log('len', result.body);
                    this.shadeCount = (result.body != null) ? result.body : 0 ;
                    // console.log('shade', this.shadeCount);
                    // Get the count of damage moved the damage area
                    this.damageService.getParticularBatchDamageCount(this.updateBatchValue.id)
                    .subscribe((resource: HttpResponse<String>) => {
                        this.damageCount = (resource.body != null) ? resource.body : 0 ;
                        this.shadeDamageAndUserCount = +(this.shadeCount + this.damageCount) + +value.noOfQuantity;
                        // console.log('shadeDamageAndUserCount', this.shadeDamageAndUserCount);
                        if (this.updateBatchValue.quantity == this.shadeDamageAndUserCount) {
                            // console.log('quantity equal');
                            this.quantityDamageCheck(value);
                        } else if (this.updateBatchValue.quantity < this.shadeDamageAndUserCount) {
                            // console.log('quantity exceed');
                            const shadeDamageCount = this.shadeCount + this.damageCount;
                            const difference = this.updateBatchValue.quantity - shadeDamageCount;
                            // alert('Quantity exceeds than the actual quantity.You have ' + difference + ' seedlings in your batch.');
                            this.error.next('Quantity exceeds than the actual quantity. You have ' + difference + ' seedlings in your batch.');
                        } else if (this.updateBatchValue.quantity > this.shadeDamageAndUserCount) {
                            // console.log('quantity not exceed');
                            // alert('Quantity cannot be blank');
                            this.quantityDamageCheck(value);
                        }
                    });
                });
            } else if (this.updateBatchValue.status === STATUS_SEEDLING) {
                const movedamage = (this.updateBatchValue.damagecount != null) ? this.updateBatchValue.damagecount : 0;
                const movesaplings = (this.updateBatchValue.seedlingsCount != null) ? this.updateBatchValue.seedlingsCount : 0;
                const seedlingshadedamageCount = +(movesaplings + movedamage) + +value.noOfQuantity;
                if (this.updateBatchValue.noOfSeedlings == seedlingshadedamageCount) {
                    this.quantityDamageCheck(value);
                } else if (this.updateBatchValue.noOfSeedlings < seedlingshadedamageCount) {
                    const remaining =  +this.updateBatchValue.noOfSeedlings - +(+movedamage + +movesaplings);
                    alert('Quantity exceeds than the actual quantity.You have ' + remaining + ' seedlings in your batch.');
                } else if (this.updateBatchValue.noOfSeedlings > seedlingshadedamageCount) {
                    this.quantityDamageCheck(value);
                }
            }
        }
    }

    /**
     * To check the damage quantity if it exceeds or not
     *
     * @param value damage values
     */
    quantityDamageCheck(value: DamageModel): void {
        this.damage.batchId = value.id;
        this.damage.noOfQuantity = value.noOfQuantity;
        this.damage.date = moment(value.date, DATE_TIME_FORMAT);
        this.damage.descriptionId = value.descriptionId;
        this.damage.damageAreaId = value.damageAreaId;
        this.damage.status = value.status;
        this.damage.financialYearDamageId = this.financialYearId;
        // console.log(this.damage);
        this.damageService.create(this.damage).subscribe(
            data => {
                // If the damage status as Seedling
                // Save the damage quantity into the shade are damage
                if (this.damage.status === STATUS_SEEDLING) {
                    // console.log(this.shadeArea);
                    this.shadeArea.damage = +this.shadeArea.damage + +this.damage.noOfQuantity;
                    this.updateShadeAreaQuantity();
                }
                // alert('Damage Created Successfully.');
                this.success.next('Successfully added to damage.');
                this.getBatchList();
                this.damageModal.hide();
            },
            (res: HttpErrorResponse) => {
                // alert(res.error.fieldErrors[0].message);
                this.error.next(res.error.fieldErrors[0].message);
            }
        );
    }

    /**
     * Create shade area(New Shade area) and move the seedlings from batch to shadearea
     *
     * @param value  shadearea values
     */
    createShadeArea(value: ShadeAreaModel): void {
        if ((value.noOfSeedlings === null)
            || (value.noOfSeedlings === undefined)
            || (value.noOfSeedlings <= 0 )
        ) {
            this.error.next('Quantity cannot be blank or 0');
        } else {
            // this.shadeArea = value;
            // console.log('shade area', value);
            this.updateBatchValue = value;
            // console.log('compare', this.updateBatchValue.quantity);
            // console.log('compares', value.noOfSeedlings);
            this.shadeAreaService.getParticularBatchShadeCount(this.updateBatchValue.id)
            .subscribe((result: HttpResponse<String>) => {
                // console.log('len', result.body);
                this.shadeCount = (result.body != null) ? result.body : 0;
                // console.log('shade', this.shadeCount);
                // Get the count of damage moved the damage area

                this.damageService.getParticularBatchDamageCount(this.updateBatchValue.id)
                .subscribe((resource: HttpResponse<String>) => {
                    this.damageCount = (resource.body != null) ? resource.body : 0;
                    // console.log('damage', this.damageCount);
                    this.shadeDamageAndUserCount = +(this.shadeCount + this.damageCount) + +value.noOfSeedlings;
                    // console.log('shadeDamageAndUserCount', this.shadeDamageAndUserCount);
                    if (this.updateBatchValue.quantity === this.shadeDamageAndUserCount) {
                        // console.log('quantity equal');
                        if (this.theCheckbox === true) {
                            this.updateBatchValue.status = STATUS_BATCH_CLOSE;
                        }
                        this.quantityCheck(value);
                    } else if (this.updateBatchValue.quantity < this.shadeDamageAndUserCount) {
                        // console.log('quantity exceed');
                        const shadeDamageCount = this.shadeCount + this.damageCount;
                        const difference = this.updateBatchValue.quantity - shadeDamageCount;
                        // alert('Quantity exceeds than the actual quantity.You have ' + difference + ' seedlings in your batch.');
                        this.error.next('Quantity exceeds than the actual quantity. You have ' + difference + ' seedlings in your batch.');
                    } else if (this.updateBatchValue.quantity > this.shadeDamageAndUserCount) {
                        // console.log('quantity not exceed');
                        // alert('Quantity cannot be blank');
                        if (this.theCheckbox === true) {
                            const difference = this.updateBatchValue.quantity - this.shadeDamageAndUserCount;
                            // alert('You have ' + difference + ' seedling in your batch.');
                            this.error.next('You have ' + difference + ' seedling in your batch.');
                            // alert('you cant able to close the batch, Because your remaining seedlings count is'
                            //     + difference + 'so please move to shade after close the batch')
                        } else {
                            this.quantityCheck(value);
                        }
                    }
                });
            });
        }
    }

    /**
     * To check the quantity moving to shade area is exceeds or not
     *
     * @param value shadearea values
     */
    quantityCheck(value: ShadeAreaModel): void {
        if (this.updateBatchValue.round > 0) {
            // console.log('>');
            this.updateBatchValue.round = this.updateBatchValue.round + 1;
        } else {
            // console.log('<');
            this.updateBatchValue.round = 1;
        }
        this.shadeArea = new ShadeArea();
        this.shadeArea.batchId = value.id;
        this.shadeArea.noOfSeedlings = value.noOfSeedlings;
        this.shadeArea.date = moment(value.date, DATE_TIME_FORMAT);
        this.shadeArea.financialYearShadeAreaId = this.financialYearId;
        this.shadeArea.status = STATUS_ACTIVE;
        this.shadeArea.round = this.updateBatchValue.round;
        // console.log(this.shadeArea);
        this.shadeAreaService.create(this.shadeArea).subscribe(
            data => {
                // alert('Successfully Moved to Shade Area.');
                this.success.next('Successfully moved to shade area.');
                // console.log(this.updateBatchValue);
                // Update the Batch model using service
                this.batchService.update(this.updateBatchValue).subscribe(
                    res => {
                        this.getBatchList();
                        this.shiftBatchModal.hide();
                    },
                    (res: HttpErrorResponse) => {
                        alert(res.error.fieldErrors[0].message);
                    }
                );
            },
            (res: HttpErrorResponse) => {
                this.error.next(res.error.fieldErrors[0].message);
                // alert(res.error.fieldErrors[0].message);
            }
        );
        // this.shiftBatchModal.hide();
    }

    /**
     * Create Stock by moving the saplings from the seasonong area
     */
    createStock(): void {
        // console.log('stock', this.shadeArea.noOfSeedlings);
        // console.log('nurse', this.nurseryStockDetails.quantity);
        if (this.nurseryStockDetails.quantity <= this.shadeArea.noOfSeedlings) {
            this.batch = new BatchModel();
            // console.log(this.nurseryStockDetails);
            this.batchService.find(this.nurseryStockDetails.batchId)
            .subscribe(output => {
                this.batch = output.body;
                this.nurseryStock.nurseryId = this.batch.nurseryId;
                this.nurseryStock.pickListVarietyId = this.batch.pickListVarietyId;
                this.nurseryStock.pickListCategoryId = this.batch.pickListCategoryId;
                this.nurseryStockDetails.status = this.addStockStatus;
                // this.nurseryStock.nurseryStockDetails = [this.nurseryStockDetails];
                // console.log(this.nurseryStock);
                // this.nurseryStockService.getNurseryCategoryStock(this.nurseryStock.nurseryId, this.nurseryStock.pickListCategoryId)
                this.nurseryStockService.query({
                    filter: {
                        'nurseryId.equals': this.nurseryStock.nurseryId,
                        'pickListCategoryId.equals': this.nurseryStock.pickListCategoryId
                    }
                })
                .subscribe((res: HttpResponse<INurseryStock[]>) => {
                    this.nurseryStock.status = STATUS_FROM_BATCH;
                    // If the length is greater than 0, update the old batch records
                    if (res.body.length > 0) {
                        this.nurseryStock = res.body[res.body.length - 1];
                        this.nurseryStock.currentQuantity = +this.nurseryStock.currentQuantity + +this.nurseryStockDetails.quantity;
                        this.nurseryStock.addedQuantity = +this.nurseryStock.addedQuantity + +this.nurseryStockDetails.quantity;
                        this.nurseryStockService.update(this.nurseryStock)
                        .subscribe(
                            data => {
                                // alert('Bacth Closed Successfully.');
                                this.nurseryStockDetails.nurseryStockId = data.body.id;
                                this.createNurseryStockDetails(this.nurseryStockDetails);
                            },
                            (err: HttpErrorResponse) => {
                                alert(err.error.fieldErrors[0].message);
                            }
                        );
                    } else {
                        this.nurseryStock.currentQuantity = this.nurseryStockDetails.quantity;
                        this.nurseryStock.addedQuantity = this.nurseryStockDetails.quantity;
                        this.nurseryStock.financialYearNurseryStockId = this.financialYearId;
                        this.nurseryStockService.create(this.nurseryStock)
                        .subscribe(data => {
                                // console.log(data.body);
                                this.nurseryStockDetails.nurseryStockId = data.body.id;
                                this.createNurseryStockDetails(this.nurseryStockDetails);
                            },
                            (err: HttpErrorResponse) => {
                                alert(err.error.fieldErrors[0].message);
                            }
                        );
                    }
                });
            });
        } else {
            alert('Quantity exceeds than the actual quantity.');
        }
    }

    /**
     * Created the stock details fro the particular nursery when saplings are moved from
     * seasoning area
     *
     * @param nurseryStockDetails stock values
     */
    createNurseryStockDetails(nurseryStockDetails): void {
        this.nurseryStockDetails = nurseryStockDetails;
        this.nurseryStockDetails.status = STATUS_ADD;
        this.nurseryStockDetails.date = moment(this.nurseryStockDetails.date, DATE_TIME_FORMAT);
        this.nurseryStockDetails.financialYearStockDetailsId = this.financialYearId;
        this.nurseryStockDetailsService.create(this.nurseryStockDetails).subscribe(
            data => {
                // console.log(data.body);
                alert('Successfully moved to seasoning area.');
                this.stockModal.hide();
                this.shadeArea.saplings = +this.shadeArea.saplings + +this.nurseryStockDetails.quantity;
                this.updateShadeAreaQuantity();
            },
            (res: HttpErrorResponse) => {
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    /**
     * To update the quantity values in batch
     *
     * @param value batch quantity values
     */
    updateQuantity() {
        this.updateBatchValue.quantity = +this.updateBatchValue.quantity + +this.batchQuantity.quantity;
        this.batchService.update(this.updateBatchValue)
        .subscribe(
            data => {
                this.batchQuantity.batchId = data.body.id;
                this.batchQuantity.status = STATUS_ADDED;
                this.saveBatchQuantity('Successfully updated the quantity.');
            },
            (res: HttpErrorResponse) => {
                // alert(res.error.fieldErrors[0].message);
                this.error.next(res.error.fieldErrors[0].message);
            }
        );
    }

    /**
     * To save the batch Quantity and update the date of updation
     */
    saveBatchQuantity(message: string) {
        this.batchQuantity.date = moment(this.batchQuantity.date, DATE_TIME_FORMAT);
        this.batchQuantityService.create(this.batchQuantity).subscribe(
            data => {
                // Display the alert message
                alert(message);
                // console.log(data.body);
                this.addQuantityModal.hide();
            },
            (err: HttpErrorResponse) => {
                this.error.next(err.error.fieldErrors[0].message);
                // alert(err.error.fieldErrors[0].message);
            }
        );
    }

    /**
     * Get the damage value based on batch id
     *
     * @param batchId batch id value
     */
    getParticularBatchDamage(batchId): void {
        this.damageRecordModal.show();
        // Get the list of damage
        // this.damageService.getParticularBatchRecord(batchId)
        this.damageService.query({
            filter: { 'batchId.equals': batchId }
        })
        .subscribe((res: HttpResponse<IDamage[]>) => {
            this.damages = res.body;
        });
    }

    /**
     * Get the shadeArea value based on batch id
     *
     * @param batchId batch id value
     */
    getParticularBatchShadeArea(batchId): void {
        this.shadeAreaRecordModal.show();
        // Get the list of shade area record
        // this.shadeAreaService.getParticularBatchRecord(batchId)
        this.shadeAreaService.query({
            filter: { 'batchId.equals': batchId }
        })
        .subscribe((res: HttpResponse<IShadeArea[]>) => {
            this.shadeAreas = res.body;
        });
    }

    /**
     * To get shade and damage count details of the batch using batch Id
     *
     * @param batchId batch id value
     */
    getParticularBatchShadeDamageCount(batchId): void {
        // Get the count of stock moved the shade area
        this.shadeAreaService.getParticularBatchShadeCount(batchId)
        .subscribe((res: HttpResponse<String>) => {
            this.shadeCount = res.body;
        });

        // Get the count of damage moved the damage area
        this.damageService.getParticularBatchDamageCount(batchId)
        .subscribe((res: HttpResponse<String>) => {
            this.damageCount = res.body;
        });
    }

    /**
     * Get the Quantity value based on batch id
     *
     * @param batchId batch id value
     */
    getParticularBatchQuantityDetails(batchId): void {
        this.quantityRecordModal.show();
        // Get the list of quantity record
        // this.batchQuantityService.getParticularBatchRecord(batchId)
        this.batchQuantityService
            .query({
                filter: { 'batchId.equals': batchId }
            }).subscribe((res: HttpResponse<IBatchQuantity[]>) => {
                this.batchQuantities = res.body;
            });
    }

    /**
     * Update the ShadeArea Quantity
     */
    updateShadeAreaQuantity(): void {
        this.shadeAreaService.update(this.shadeArea).subscribe(
            data => {},
            (res: HttpErrorResponse) => {
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    /**
     * To get the report based on the from date and to date
     * Assign the date to the value
     */
    /** getReport(): void {
        this.batchService
            .getReport(
                moment(this.filter.fromDate, DATE_TIME_FORMAT),
                moment(this.filter.toDate, DATE_TIME_FORMAT)
            ).subscribe((res: HttpResponse<IBatch[]>) => {
            });
    }  */

    /**
     * Reset the values of updateBatch table
     * Close the shiftBatchModel
     */
    closeShiftBatchModal(): void {
        this.shiftBatchModal.hide();
        this.updateBatchValue.noOfSeedlings = null;
        this.updateBatchValue.date = null;
        this.getBatchList();
    }

    /**
     * Reset the values of updateBatch table
     * Close the damage Model
     */
    closeDamageModal(): void {
        this.damageModal.hide();
        this.updateBatchValue.damageAreaId = null;
        this.updateBatchValue.noOfQuantity = null;
        this.updateBatchValue.descriptionId = null;
        this.updateBatchValue.date = null;
        this.getBatchList();
    }

    /**
     * Reset the value of batch model
     * Close the addQuantityModal
     */
    closeAddQuantityModal(): void {
        this.addQuantityModal.hide();
        this.batchQuantity = {};
        this.getBatchList();
    }

    /**
     * Reset the close batch value from the batchQuantity table
     * Close the batch modal
     */
    batchModalClose(): void {
        this.closeBatchModal.hide();
        this.updateBatchValue = {};
    }

    /**
     * On keypress event validation the key value
     * If entered value is number allow to enter otherwise return as false
     */
    numberOnly(event): boolean {
        const charCode = (event.which) ? event.which : event.keyCode;
        if (charCode > 31 && (charCode < 48 || charCode > 57)) {
            return false;
        }
        return true;
    }

    /**
     * To load the page number
     *
     * @param page number
     */
    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    /**
     * Load the transition for the page according to the size
     */
    transition() {
        this.router.navigate(['/batch'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.getBatchList();
    }

    /**
     * Clear the filter
     */
    clear() {
        this.page = 0;
        this.router.navigate([
            '/batch',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.getBatchList();
    }

    /**
     * Reset the form for the batch
     */
    resetForm(): void {
        this.batch = {};
    }

    /**
     * Sorting the batch
     */
    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    /**
     * Track the id for the batch
     *
     * @param index number
     * @param item batch item
     */
    trackId(index: number, item: IBatch) {
        return item.id;
    }

    /**
     * Pagination for the batch
     *
     * @param data values
     * @param headers header values
     */
    private paginateBatchs(data: IBatch[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.batches = data;
    }
}
