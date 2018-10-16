/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/12
 *  Target: yarn
 *******************************************************************************/

// Import needed component, model and dependency
import { Component, OnInit, ViewChild } from '@angular/core';
import { NurseryInventoryService } from 'app/entities/service/nursery-inventory.service';
import { NurseryInventoryDetailsService } from 'app/entities/service/nursery-inventory-details.service';
import { ZonalService } from 'app/entities/service/zonal.service';
import { SectorService } from 'app/entities/service/sector.service';
import { NurseryService } from 'app/entities/service/nursery.service';
import { PickListService } from 'app/entities/service/pick-list.service';
import { PickListValueService } from 'app/entities/service/pick-list-value.service';
import { INurseryInventory, STATUS_SEEDS, STATUS_COVER, NurseryInventory } from 'app/shared/model/nursery-inventory.model';
import {
    STATUS_ADD,
    STATUS_CONSUME,
    STATUS_DAMAGE,
    NurseryInventoryDetails,
    INurseryInventoryDetails,
    DISPLAY_NAME_VARIETY,
    DISPLAY_NAME_QUANTITY_TYPE,
    DISPLAY_NAME_DAMAGE_TYPE,
    DISPLAY_NAME_DAMAGE_REASON
} from 'app/shared/model/nursery-inventory-details.model';
import { IZonal } from 'app/shared/model/zonal.model';
import { ISector } from 'app/shared/model/sector.model';
import { INursery } from 'app/shared/model/nursery.model';
import { IPickList } from 'app/shared/model/pick-list.model';
import { IPickListValue } from 'app/shared/model/pick-list-value.model';
import { FinancialYearSettingsService } from 'app/entities/service/financial-year-settings.service';
import { IFinancialYearSettings } from 'app/shared/model/financial-year-settings.model';

import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import * as moment from 'moment';
import { DATE_TIME_FORMAT, STATUS_ACTIVE, SOFT_DELETE_STATUS, ALERT_TIME_OUT_5000 } from 'app/shared';
import { Observable } from 'rxjs';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { Subject } from 'rxjs';
import { debounceTime } from 'rxjs/operators';
import { CoverFilling, ICoverFilling } from 'app/shared/model/cover-filling.model';
import { CoverFillingService } from 'app/entities/cover-filling';
import { CoverFillingDetails, ICoverFillingDetails } from 'app/shared/model/cover-filling-details.model';
import { CoverFillingDetailsService } from 'app/entities/service/cover-filling-details.service';
import { NgbDatepickerConfig } from '@ng-bootstrap/ng-bootstrap';

// Mension the html, css/sass files
@Component({
    selector: 'jhi-nursery-inventory',
    templateUrl: 'nursery-inventory.component.html'
})

/**
 * Class NurseryInventoryMgntComponent used to create/update a godown,
 * List all nurseryInventorys.
 * Declared an Array variable to set list of nursery inventory.
 */
export class NurseryInventoryMgntComponent implements OnInit {
    // create an empty object
    nurseryInventory: NurseryInventory = new NurseryInventory();
    nurseryInventoryDetails: NurseryInventoryDetails = new NurseryInventoryDetails();
    coverFilling: CoverFilling = new CoverFilling();
    coverFillingDetails: CoverFillingDetails = new CoverFillingDetails();

    // create empty array for each service
    nurseryInventorys: INurseryInventory[];
    coverInventorys: INurseryInventory[];
    inventoryDetails: INurseryInventoryDetails[];
    coverFillings: ICoverFilling[];
    preparedCoverDetails: ICoverFillingDetails[];

    zonals: IZonal[];
    sectors: ISector[];
    nurserys: INursery[];
    pickLists: IPickList[];
    varietys: IPickListValue[];
    categorys: IPickListValue[];
    quantityTypes: IPickListValue[];
    damageTypes: IPickListValue[];
    damageDescription: IPickListValue[];

    isCollapsed = true;
    isCollapsedInventoryDetails = true;
    isCoverCollapsed = true;
    isCoverFillingCollapsed = true;

    inventoryDateDp: any;
    coverInvenotyDateDp: any;
    seedsDateDp: any;
    addInventoryDateDp: any;
    consumeInventoryDateDp: any;
    damageInventoryDateDp: any;
    damagesInventoryDateDp: any;
    inventoryStatus: any;
    inventoryTitle: any;
    financialYearId: number;

    seedInventoryStatus: number;
    coverInventoryStatus: number;

    // To display the success message
    private success = new Subject<string>();
    successMessage: string;

    // To display the error message
    private error = new Subject<string>();
    errorMessage: string;

    // By default close the alert with statc time
    staticAlertClosed = false;

    // To display the model pop-up for add, consume and damage
    @ViewChild('addParticularInventory') public addParticularInventory: ModalDirective;
    @ViewChild('consumeParticularInventory') public consumeParticularInventory: ModalDirective;
    @ViewChild('damageParticularInventory') public damageParticularInventory: ModalDirective;
    @ViewChild('damageCoverFillingModel') public damageCoverFillingModel: ModalDirective;
    // constructor(private nurseryInventoryService: NurseryInventoryService) {}
    constructor(
        private zonalService: ZonalService,
        private sectorService: SectorService,
        private nurseryService: NurseryService,
        private pickListService: PickListService,
        private pickListValueService: PickListValueService,
        private nurseryInventoryService: NurseryInventoryService,
        private nurseryInventoryDetailsService: NurseryInventoryDetailsService,
        private settingsService: FinancialYearSettingsService,
        private coverFillingService: CoverFillingService,
        private coverFillingDetailsService: CoverFillingDetailsService,
        private config: NgbDatepickerConfig
    ) {
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

        this.seedInventoryStatus = STATUS_SEEDS;
        this.coverInventoryStatus = STATUS_COVER;
    }

    ngOnInit() {
        // Call a function to get list of seed inventory
        this.getSeedsInventoryList();
        // Call a function to get list of cover inventory
        this.getCoverInventoryList();
        // Call a function to get active batch id
        this.getActiveRecord();
        // Call a function to get list of active zonal
        this.getZonalList();
        // Call a function to get list of variety and quantity type
        this.getPickList();
        // Call a function to get list of cover filling details
        this.getCoverFillingList();

        // To set the time for automatic alert close
        setTimeout(() => (this.staticAlertClosed = true), ALERT_TIME_OUT_5000);

        // Set the success message with debounce time
        this.success.subscribe(message => (this.successMessage = message));
        this.success.pipe(debounceTime(ALERT_TIME_OUT_5000)).subscribe(() => (this.successMessage = null));

        // To set the error message with debounce time
        this.error.subscribe(message => (this.errorMessage = message));
        this.error.pipe(debounceTime(ALERT_TIME_OUT_5000)).subscribe(() => (this.errorMessage = null));
    }

    /**
     * Call a service function to get list of active batch
     */
    getActiveRecord(): void {
        // Get the list of active batch record and
        // assign a 0th index array value to an batch id
        this.settingsService
            .query({
                filter: { 'status.equals': STATUS_ACTIVE }
            })
            .subscribe((res: HttpResponse<IFinancialYearSettings[]>) => {
                // Assign a value to an object
                this.financialYearId = res.body[0].id;
            });
    }

    /**
     * To get the Seeds inventory List of all the nurseries
     */
    getSeedsInventoryList(): void {
        this.nurseryInventory = {};
        this.nurseryInventoryDetails = {};
        this.isCollapsed = true;
        this.isCollapsedInventoryDetails = true;
        this.isCoverCollapsed = true;
        this.isCoverFillingCollapsed = true;
        // Get the list of godown
        // this.nurseryInventoryService.queryGetSeedsList(STATUS_SEEDS)
        this.nurseryInventoryService
            .query({ filter: { 'status.equals': STATUS_SEEDS } })
            .subscribe((res: HttpResponse<INurseryInventory[]>) => {
                this.nurseryInventorys = res.body;
            });
    }

    /**
     * To get the Cover inventory Lists of all the nurseries
     */
    getCoverInventoryList(): void {
        this.nurseryInventory = {};
        this.nurseryInventoryDetails = {};
        this.isCollapsed = true;
        this.isCollapsedInventoryDetails = true;
        this.isCoverCollapsed = true;
        this.isCoverFillingCollapsed = true;
        // Get the list of godown
        // this.nurseryInventoryService.queryGetCoverList(STATUS_COVER)
        this.nurseryInventoryService
            .query({ filter: { 'status.equals': STATUS_COVER } })
            .subscribe((res: HttpResponse<INurseryInventory[]>) => {
                this.coverInventorys = res.body;
            });
    }

    /**
     * Display the seed create form and reset the form fields
     */
    addSeeds(): void {
        this.isCollapsed = false;
        this.isCollapsedInventoryDetails = true;
        this.resetReceiveForm();
    }

    /**
     * Display the cover create form and reset the form fields
     */
    addCover(): void {
        this.isCoverCollapsed = false;
        this.isCollapsedInventoryDetails = true;
        this.resetReceiveForm();
    }

    /**
     * Display the cover filling create form and reset the form fields
     */
    addCoverFilling(): void {
        this.isCoverFillingCollapsed = false;
        this.isCollapsedInventoryDetails = true;
        this.isCollapsed = false;
        this.resetCoverFilling();
    }

    /**
     * Get created zonal from zonal table
     */
    getZonalList(): void {
        // Get the list of zone
        this.zonalService
            .query({
                filter: { 'status.equals': STATUS_ACTIVE }
            })
            .subscribe((res: HttpResponse<IZonal[]>) => {
                this.zonals = res.body;
            });
    }

    /**
     * Get pick list value variety and quantity type from the pick list table
     */
    getPickList(): void {
        // To get the active pick list variety Id from Pick List based on label
        this.pickListService
            .query({
                filter: {
                    'status.equals': STATUS_ACTIVE,
                    'displayLabelName.equals': DISPLAY_NAME_VARIETY
                }
            })
            .subscribe((res: HttpResponse<IPickList[]>) => {
                if (res.body.length > 0) {
                    // Call a function to get list of variety
                    this.getVariety(res.body[0].id);
                }
            });

        // To get the active pick list quantity type Id from pickList based on label
        this.pickListService
            .query({
                filter: {
                    'status.equals': STATUS_ACTIVE,
                    'displayLabelName.equals': DISPLAY_NAME_QUANTITY_TYPE
                }
            })
            .subscribe((res: HttpResponse<IPickList[]>) => {
                if (res.body.length > 0) {
                    // Call a function to get list of quantity
                    this.getQuantityType(res.body[0].id);
                }
            });
    }

    /**
     * Get the active sector value based on zonal id
     * @param zoneId as zonal model auto increment id
     */
    getSector(zoneId): void {
        // Get the list of sector
        // this.sectorService.getSectors(zoneId)
        this.sectorService
            .query({
                filter: {
                    'status.equals': STATUS_ACTIVE,
                    'zonalId.equals': zoneId
                }
            })
            .subscribe((res: HttpResponse<ISector[]>) => {
                // Assign a response value to an object
                this.sectors = res.body;
            });
    }

    /**
     * To get the active nursery based on the sector Id
     * @param sectorId as sector model auto increment id
     */
    getNursery(sectorId): void {
        // Get the list of nursery
        // this.nurseryService.getNurserys(sectorId)
        this.nurseryService
            .query({
                filter: {
                    'status.equals': STATUS_ACTIVE,
                    'sectorId.equals': sectorId
                }
            })
            .subscribe((res: HttpResponse<INursery[]>) => {
                // Assign a response value to an object
                this.nurserys = res.body;
            });
    }

    /**
     * Get the active variety from the picklistvalue
     * @param id as pickList model auto increment id
     */
    getVariety(id): void {
        // this.pickListValueService.getVariety(id)
        this.pickListValueService
            .query({
                filter: {
                    'status.equals': STATUS_ACTIVE,
                    'pickListId.equals': id
                }
            })
            .subscribe((res: HttpResponse<IPickListValue[]>) => {
                // Assign a response value to an object
                this.varietys = res.body;
            });
    }

    /**
     * Get the active quantity type from the picklistvalue
     * @param id as pickList model auto increment id
     */
    getQuantityType(id): void {
        // this.pickListValueService.getVariety(id)
        this.pickListValueService
            .query({
                filter: {
                    'status.equals': STATUS_ACTIVE,
                    'pickListId.equals': id
                }
            })
            .subscribe((res: HttpResponse<IPickListValue[]>) => {
                // Assign a response value to an object
                this.quantityTypes = res.body;
            });
    }

    /**
     * Get category from the picklist value
     * @param id pickList model auto increment id
     */
    getCategory(id): void {
        // this.pickListValueService.getCategory(id)
        this.pickListValueService
            .query({
                filter: {
                    'status.equals': STATUS_ACTIVE,
                    'pickValueId.equals': id
                }
            })
            .subscribe((res: HttpResponse<IPickListValue[]>) => {
                // Assign a response value to an object
                this.categorys = res.body;
            });
    }

    /**
     * To get the active damage type from the picklistvalue table
     * @param id pickList model auto increment id
     */
    getDamageType(id): void {
        // Call a pick list query service to get active record of damage type
        // this.pickListValueService.getVariety(id)
        this.pickListValueService
            .query({
                filter: {
                    'status.equals': STATUS_ACTIVE,
                    'pickListId.equals': id
                }
            })
            .subscribe((res: HttpResponse<IPickListValue[]>) => {
                // Assign a response value to an object
                this.damageTypes = res.body;
            });
    }

    /**
     * Get the active damage Description type from the picklistvalue table
     * @param id pickList model auto increment id
     */
    getDamageDescriptionType(id): void {
        // Call a pick list query service to get active record of damage description
        // this.pickListValueService.getVariety(id)
        this.pickListValueService
            .query({
                filter: {
                    'status.equals': STATUS_ACTIVE,
                    'pickListId.equals': id
                }
            })
            .subscribe((res: HttpResponse<IPickListValue[]>) => {
                // Assign a response value to an object
                this.damageDescription = res.body;
            });
    }

    /**
     * To reset/empty the value of add seeds and covers
     */
    resetReceiveForm(): void {
        // console.log('resetValue');
        // console.log(this.nurseryInventory);
        this.nurseryInventory = {};
        this.nurseryInventoryDetails = {};
        // console.log(this.nurseryInventory);
    }

    /**
     * Create new Batch
     */
    saveSeeds(): void {
        // Check the empty validation for Nursery and Category.
        if (this.nurseryInventory.nurserysId === null || this.nurseryInventory.nurserysId === undefined) {
            this.error.next(`Nursery should not be empty`);
        } else if (this.nurseryInventory.pickListCategoryId === null || this.nurseryInventory.pickListCategoryId === undefined) {
            this.error.next(`Category should not be empty`);
        } else {
            // } else if (this.nurseryInventory.quantityTypeId === null || this.nurseryInventory.quantityTypeId === undefined) {
            // this.error.next(`Quantity Type should not be empty`);
            // Assign a value to an variable
            this.nurseryInventory.status = STATUS_SEEDS;
            this.nurseryInventoryDetails.status = STATUS_ADD;

            // this.nurseryInventoryService
            //     .getNurseryCategoryInventory(this.nurseryInventory.nurserysId, this.nurseryInventory.pickListCategoryId)
            // Call a nursery inventory query service
            // to get active particular nursery category list
            this.nurseryInventoryService
                .query({
                    filter: {
                        'nurserysId.equals': this.nurseryInventory.nurserysId,
                        'pickListCategoryId.equals': this.nurseryInventory.pickListCategoryId,
                        'status.greaterThan': SOFT_DELETE_STATUS
                    }
                })
                .subscribe(
                    (res: HttpResponse<INurseryInventory[]>) => {
                        // If the total count is greater than 0
                        // (that means already that nursery added the needs)
                        // To update the nursery seeds details
                        if (res.body.length > 0) {
                            this.nurseryInventory = res.body[res.body.length - 1];
                            // Add a new quantity to old quantity and assign a value
                            this.nurseryInventory.currentQuantity =
                                +this.nurseryInventory.currentQuantity + +this.nurseryInventoryDetails.quantity;
                            this.nurseryInventory.addedQuantity =
                                +this.nurseryInventory.addedQuantity + +this.nurseryInventoryDetails.quantity;
                            this.subscribeToSaveResponse(this.nurseryInventoryService.update(this.nurseryInventory));
                        } else {
                            // Assign a value to an variable
                            this.nurseryInventory.currentQuantity = this.nurseryInventoryDetails.quantity;
                            this.nurseryInventory.addedQuantity = this.nurseryInventoryDetails.quantity;
                            this.subscribeToSaveResponse(this.nurseryInventoryService.create(this.nurseryInventory));
                        }
                    },
                    (res: HttpErrorResponse) => {
                        // If error response display the error message in view
                        this.error.next(res.error.fieldErrors[0].message);
                    }
                );
        }
    }

    /**
     * To save the inventory details
     * @param result object of inventory details details
     */
    private subscribeToSaveResponse(result: Observable<HttpResponse<INurseryInventory>>) {
        result.subscribe(
            (res: HttpResponse<INurseryInventory>) => {
                // Assign a value to an variable
                this.nurseryInventoryDetails.nurseryInventoryId = res.body.id;
                // Call a function to get list of inventory details
                this.saveInventoryDetails();
            },
            (res: HttpErrorResponse) => {
                // If error response display the error message in view
                this.error.next(res.error.fieldErrors[0].message);
            }
        );
    }

    /**
     * To save the inventory details based on the status
     */
    saveInventoryDetails(): void {
        // Convery a date formate and assign to an variable
        this.nurseryInventoryDetails.date = moment(this.nurseryInventoryDetails.date, DATE_TIME_FORMAT);
        // this.nurseryInventoryDetails.status = STATUS_ADD;
        // Call a service to create a nursery inventory details
        this.nurseryInventoryDetailsService.create(this.nurseryInventoryDetails).subscribe(
            data => {
                // If the status is seeds to display the text message as seeds
                if (this.nurseryInventory.status === STATUS_SEEDS) {
                    this.inventoryStatus = 'seeds';
                    this.isCollapsed = true;
                } else {
                    this.inventoryStatus = 'cover';
                    this.isCoverCollapsed = true;
                }

                // Based on the status displayed the status alert
                if (this.nurseryInventoryDetails.status === STATUS_ADD) {
                    this.success.next(`Successfully added the ${this.inventoryStatus}.`);
                    this.addParticularInventory.hide();
                } else if (this.nurseryInventoryDetails.status === STATUS_CONSUME) {
                    this.success.next(`Successfully consumed the ${this.inventoryStatus}.`);
                    this.consumeParticularInventory.hide();
                } else {
                    this.success.next(`Successfully updated damage ${this.inventoryStatus}.`);
                    this.damageParticularInventory.hide();
                }
                // Call a function to get list of nursery Stocks
                this.getSeedsInventoryList();
                this.getCoverInventoryList();
                this.nurseryInventory = new NurseryInventory();
                this.nurseryInventoryDetails = new NurseryInventoryDetails();
                // this.isCollapsed = true;
                this.isCollapsedInventoryDetails = true;
            },
            (res: HttpErrorResponse) => {
                this.error.next(res.error.fieldErrors[0].message);
            }
        );
    }

    /**
     * To see the inventory details of seeds/covers
     * Particular nursery details of seeds/covers
     */
    getInventoryDetails(id, status): void {
        // To hide the add seeds form
        this.isCollapsed = true;
        // If the status is seeds to set the title as seeds
        if (status === STATUS_SEEDS) {
            this.inventoryTitle = 'Seeds Details';
        } else {
            // If the status is cover to set the status as cover
            this.inventoryTitle = 'Cover Details';
        }
        // Get the particular inventory details
        // this.nurseryInventoryDetailsService.getParticularInventorys(id)
        this.nurseryInventoryDetailsService
            .query({
                filter: { 'nurseryInventoryId.equals': id }
            })
            .subscribe((res: HttpResponse<INurseryInventoryDetails[]>) => {
                this.isCollapsedInventoryDetails = false;
                this.inventoryDetails = res.body;
            });
    }

    /**
     * Add inventory Details
     * @param value as object of particular nurseryInventory
     */
    addMoreInventory(value): void {
        // Create an empty object
        this.nurseryInventory = new NurseryInventory();
        this.nurseryInventoryDetails = new NurseryInventoryDetails();
        // Assign a value to an object
        this.nurseryInventory = value;
        this.nurseryInventoryDetails.status = STATUS_ADD;
        // Show a model pop up
        this.addParticularInventory.show();
    }

    /**
     * Add the inventory with inventory details
     * @param value as object of particular nursery inventory details
     */
    addInventoryQuantity(value): void {
        // Check the empty validation for date and quantity.
        if (this.nurseryInventoryDetails.date === null || this.nurseryInventoryDetails.date === undefined) {
            this.error.next(`Date should not be empty`);
        } else if (
            this.nurseryInventoryDetails.quantity === null ||
            this.nurseryInventoryDetails.quantity <= 0 ||
            this.nurseryInventoryDetails.quantity === undefined
        ) {
            this.error.next(`You cant able to add.
             Please enter valid quantity.`);
        } else {
            // Add a quantity and assign to an variable
            this.nurseryInventory.currentQuantity = +value.currentQuantity + +this.nurseryInventoryDetails.quantity;
            this.nurseryInventory.addedQuantity = +value.addedQuantity + +this.nurseryInventoryDetails.quantity;
            this.subscribeToSaveResponse(this.nurseryInventoryService.update(this.nurseryInventory));
        }
    }

    /**
     * To connnsume the inventory details
     * @param value as object of particular nursery inventory
     */
    consumeInventory(value): void {
        // If current quantity is 0, throw the error
        if (value.currentQuantity === 0 || value.currentQuantity < this.nurseryInventoryDetails.quantity) {
            alert('You cant able to consume. Because your current quantity is 0');
        } else {
            // Create an empty object
            this.nurseryInventory = new NurseryInventory();
            this.nurseryInventoryDetails = new NurseryInventoryDetails();
            // Assign a value to an object
            this.nurseryInventory = value;
            this.nurseryInventoryDetails.status = STATUS_CONSUME;
            // Show the model pop up
            this.consumeParticularInventory.show();
        }
    }

    /**
     * To consume the Inventory Details from the total quantity
     * @param value as object of particular nursery inventory
     */
    consumeInventoryQuantity(value): void {
        // Check the empty validation for date and quantity.
        if (this.nurseryInventoryDetails.date === null || this.nurseryInventoryDetails.date === undefined) {
            this.error.next(`Date should not be empty`);
        } else if (
            this.nurseryInventoryDetails.quantity === null ||
            this.nurseryInventoryDetails.quantity <= 0 ||
            this.nurseryInventoryDetails.quantity === undefined
        ) {
            this.error.next(`You cant able to add.
             Please enter valid quantity.`);
        } else if (value.currentQuantity < this.nurseryInventoryDetails.quantity) {
            this.error.next(`You cant able to consume.
             Because your current quantity is ${value.currentQuantity}.
             Please enter lessthan current quantity.`);
        } else {
            // Reduce the current quantity and assign to a variable
            this.nurseryInventory.currentQuantity = +value.currentQuantity - +this.nurseryInventoryDetails.quantity;
            // this.nurseryInventory.addedQuantity = +value.addedQuantity - +this.nurseryInventoryDetails.quantity;
            // Add the consumed quantity and assign to a variable
            this.nurseryInventory.consumedQuantity = +value.consumedQuantity + +this.nurseryInventoryDetails.quantity;
            this.subscribeToSaveResponse(this.nurseryInventoryService.update(this.nurseryInventory));
        }
    }

    /**
     * Add the damage seeds of nursery
     * @param value as object of particular nursery inventory
     */
    damageInventory(value): void {
        if (value.currentQuantity === 0) {
            // this.error.next(`You cant add to damage. Because Current Quantity is 0.`);
            alert('You cant add to damage. Because Current Quantity is 0.');
        } else {
            // Create an empty object
            this.nurseryInventory = new NurseryInventory();
            // Assign a param value to an object
            this.nurseryInventory = value;
            // Create an empty object
            this.nurseryInventoryDetails = new NurseryInventoryDetails();
            // Assign a value to an variable
            this.nurseryInventoryDetails.status = STATUS_DAMAGE;
            // Show a model pop up
            this.damageParticularInventory.show();

            // To get the active pick list damage type Id from Pick List based on label
            this.pickListService
                .query({
                    filter: {
                        'status.equals': STATUS_ACTIVE,
                        'displayLabelName.equals': DISPLAY_NAME_DAMAGE_TYPE
                    }
                })
                .subscribe((res: HttpResponse<IPickList[]>) => {
                    if (res.body.length > 0) {
                        // Call a function to get list of damage type
                        this.getDamageType(res.body[0].id);
                    }
                });

            // To get the active pick list damage reason Id from Pick List based on label
            this.pickListService
                .query({
                    filter: {
                        'status.equals': STATUS_ACTIVE,
                        'displayLabelName.equals': DISPLAY_NAME_DAMAGE_REASON
                    }
                })
                .subscribe((res: HttpResponse<IPickList[]>) => {
                    if (res.body.length > 0) {
                        // Call a function to get list of damage description
                        this.getDamageDescriptionType(res.body[0].id);
                    }
                });
        }
    }

    /**
     * To consume the Inventory Details from the total quantity
     * @param value as object of particular nursery inventory details
     */
    damageInventoryQuantity(value): void {
        // Check a validation for required field
        if (this.nurseryInventoryDetails.damageTypeId == null || this.nurseryInventoryDetails.damageTypeId === undefined) {
            this.error.next(`Pick damage name should not be empty`);
        } else if (this.nurseryInventoryDetails.date == null || this.nurseryInventoryDetails.date === undefined) {
            this.error.next(`Date should not be empty`);
        } else if (
            this.nurseryInventoryDetails.quantity === null ||
            this.nurseryInventoryDetails.quantity <= 0 ||
            this.nurseryInventoryDetails.quantity === undefined
        ) {
            this.error.next(`Quantity should not be empty`);
        } else if (value.currentQuantity < this.nurseryInventoryDetails.quantity) {
            this.error.next(`You cant able to consume the damage quantity.
                Because your current quantity is ${value.currentQuantity}.
                Please enter lessthan current quantity`);
        } else {
            // Add a damage quantity and reduce a current quantity and
            // assign a value to an variable
            this.nurseryInventory.currentQuantity = +value.currentQuantity - +this.nurseryInventoryDetails.quantity;
            this.nurseryInventory.damageQuantity = +value.damageQuantity + +this.nurseryInventoryDetails.quantity;
            // this.nurseryInventory.addedQuantity = +value.addedQuantity - +this.nurseryInventoryDetails.quantity;
            this.subscribeToSaveResponse(this.nurseryInventoryService.update(this.nurseryInventory));
        }
    }

    /**
     * Save the cover inventory
     */
    saveCover(): void {
        // console.log(this.nurseryInventory);
        // Check a validation for required field
        if (this.nurseryInventory.nurserysId === null || this.nurseryInventory.nurserysId === undefined) {
            this.error.next(`Nursery should not be empty`);
        } else if (this.nurseryInventory.quantityTypeId === null || this.nurseryInventory.quantityTypeId === undefined) {
            this.error.next(`Quantity Type should not be empty`);
        } else {
            // console.log('cover save function');
            // Assign a value to an variable
            this.nurseryInventory.status = STATUS_COVER;
            this.nurseryInventoryDetails.status = STATUS_ADD;

            // this.nurseryInventoryService
            //     .getCoverInventory(this.nurseryInventory.nurserysId, STATUS_COVER)
            // Call a nursery inventory query service to
            // get list of particular nursery cover details
            this.nurseryInventoryService
                .query({
                    filter: {
                        'status.equals': STATUS_COVER,
                        'nurserysId.equals': this.nurseryInventory.nurserysId
                    }
                })
                .subscribe((res: HttpResponse<INurseryInventory[]>) => {
                    // If length is greater than 0, to update the old row
                    if (res.body.length > 0) {
                        this.nurseryInventory = res.body[res.body.length - 1];
                        // Add a quantity to current quantity and assign a value
                        this.nurseryInventory.currentQuantity =
                            +this.nurseryInventory.currentQuantity + +this.nurseryInventoryDetails.quantity;
                        this.nurseryInventory.addedQuantity = +this.nurseryInventory.addedQuantity + +this.nurseryInventoryDetails.quantity;
                        this.subscribeToSaveResponse(this.nurseryInventoryService.update(this.nurseryInventory));
                    } else {
                        // Assign a value to current quantity
                        this.nurseryInventory.currentQuantity = this.nurseryInventoryDetails.quantity;
                        this.nurseryInventory.addedQuantity = this.nurseryInventoryDetails.quantity;
                        this.subscribeToSaveResponse(this.nurseryInventoryService.create(this.nurseryInventory));
                    }
                });
        }
    }

    /**
     * To save the cover filling
     */
    saveCoverFilling(): void {
        // this.coverFilling.status = STATUS_COVER;
        // Call a cover filling create service
        this.coverFillingsubscribeToSaveResponse(this.coverFillingService.create(this.coverFilling));
    }

    /**
     * To save the inventory details
     * @param result object of cover filling details
     */
    private coverFillingsubscribeToSaveResponse(result: Observable<HttpResponse<ICoverFilling>>) {
        result.subscribe(
            (res: HttpResponse<ICoverFilling>) => {
                // If success display the success message in view
                this.success.next(`Successfully prepared the cover`);
                this.isCoverFillingCollapsed = true;
                // Call a function to display list of cover filling details
                this.getCoverFillingList();
                this.coverFilling = {};
            },
            (res: HttpErrorResponse) => {
                // If error response display the error message in view
                this.error.next(res.error.fieldErrors[0].message);
            }
        );
    }

    /**
     * To reset the cover filling details
     */
    resetCoverFilling(): void {
        this.coverFilling = {};
    }

    /**
     * Call a service function to get list of stocks
     */
    getCoverFillingList(): void {
        this.isCollapsed = true;
        this.isCollapsedInventoryDetails = true;
        this.isCoverCollapsed = true;
        this.isCoverFillingCollapsed = true;
        this.resetCoverFilling();
        // Call a cover filling query service to get the list of cover
        this.coverFillingService.query().subscribe((res: HttpResponse<ICoverFilling[]>) => {
            this.coverFillings = res.body;
        });
    }

    /**
     * Add the damage seeds of nursery
     * @param value as object of particular cover filling
     */
    damageCoverFilling(value): void {
        const remainingQuantity = +value.noOfCover - +value.damageQuantity;
        // Check the validation for quantity
        if (remainingQuantity === 0) {
            alert(`You cant add to damage. Because your current quantity is ${remainingQuantity}.`);
        } else {
            // Create a new object
            this.coverFilling = new CoverFilling();
            this.coverFillingDetails = new CoverFillingDetails();
            // Assign a value to an object
            this.coverFilling = value;
            this.coverFillingDetails.status = STATUS_DAMAGE;
            // Show the model pop up
            this.damageCoverFillingModel.show();

            // To get the active pick list damage type Id from Pick List based on label
            this.pickListService
                .query({
                    filter: {
                        'status.equals': STATUS_ACTIVE,
                        'displayLabelName.equals': DISPLAY_NAME_DAMAGE_TYPE
                    }
                })
                .subscribe((res: HttpResponse<IPickList[]>) => {
                    if (res.body.length > 0) {
                        // Call a function to get list of damage type
                        this.getDamageType(res.body[0].id);
                    }
                });

            // To get the active pick list damage reason Id from Pick List based on label
            this.pickListService
                .query({
                    filter: {
                        'status.equals': STATUS_ACTIVE,
                        'displayLabelName.equals': DISPLAY_NAME_DAMAGE_REASON
                    }
                })
                .subscribe((res: HttpResponse<IPickList[]>) => {
                    if (res.body.length > 0) {
                        // Call a function to get list of damage description
                        this.getDamageDescriptionType(res.body[0].id);
                    }
                });
        }
    }

    /**
     * To consume the Inventory Details from the total quantity
     * @param value as object of particular cover filling details
     */
    saveDamageCoverFilling(value): void {
        const remainingQuantity = +value.noOfCover - +value.damageQuantity;
        // Check the validation for required field
        if (this.coverFillingDetails.damageTypeId == null || this.coverFillingDetails.damageTypeId === undefined) {
            this.error.next(`Pick damage name should not be empty`);
        } else if (remainingQuantity < this.coverFillingDetails.quantity) {
            this.error.next(`You cant able to consume.
                Because your current quantity is ${remainingQuantity}.`);
        } else {
            // Reduce the current quantity and assign the value
            // this.coverFilling.noOfCover = +value.noOfCover - +this.coverFillingDetails.quantity;
            this.coverFilling.damageQuantity = +value.damageQuantity + +this.coverFillingDetails.quantity;
            // this.nurseryInventory.addedQuantity = +value.addedQuantity - +this.nurseryInventoryDetails.quantity;
            this.saveCoverFillingSaveResponse(this.coverFillingService.update(this.coverFilling));
        }
    }

    /**
     * To save the inventory details
     * @param result as object of cover filling details
     */
    private saveCoverFillingSaveResponse(result: Observable<HttpResponse<ICoverFilling>>) {
        result.subscribe(
            (res: HttpResponse<ICoverFilling>) => {
                // Assign a resonse object value to a variable
                this.coverFillingDetails.coverFillingId = res.body.id;
                // Call a function to save cover details
                this.saveCoverFillingDetails();
            },
            (res: HttpErrorResponse) => {
                // If error response display the error message in view
                this.error.next(res.error.fieldErrors[0].message);
            }
        );
    }

    /**
     * To save the inventory details based on the status
     */
    saveCoverFillingDetails(): void {
        // Convert a date format and assign to a variable
        this.coverFillingDetails.date = moment(this.coverFillingDetails.date, DATE_TIME_FORMAT);
        // this.nurseryInventoryDetails.status = STATUS_ADD;
        // Call a cover filling details create service
        this.coverFillingDetailsService.create(this.coverFillingDetails).subscribe(
            data => {
                // Based on the status displayed the status alert
                // this.success.next(`Successfully updated damage ${this.inventoryStatus}.`);
                this.success.next(`Successfully updated damage.`);
                this.damageParticularInventory.hide();
                // Call a function to get list of cover filling details
                this.getCoverFillingList();
                this.damageCoverFillingModel.hide();
                // Create an empty object
                this.coverFilling = new NurseryInventory();
                this.coverFillingDetails = new NurseryInventoryDetails();
                // this.isCollapsed = true;
                // this.isCollapsedInventoryDetails = true;
            },
            (res: HttpErrorResponse) => {
                // If error response display the error message in view
                this.error.next(res.error.fieldErrors[0].message);
            }
        );
    }

    /**
     * To see the inventory details of seeds/covers
     * Particular nursery details of seeds/covers
     * @param id as cover filling model auto increment id
     */
    getCoverFillingDetails(id): void {
        // To hide the form
        this.isCollapsed = false;
        // Get the particular inventory details
        // this.coverFillingDetailsService.getParticularCover(id)
        this.coverFillingDetailsService
            .query({
                filter: {
                    'coverFillingId.equals': id
                }
            })
            .subscribe((res: HttpResponse<ICoverFillingDetails[]>) => {
                // Display the div
                this.isCollapsedInventoryDetails = false;
                // Assign a response value to an object
                this.inventoryDetails = res.body;
            });
    }

    /**
     * On keypress event validation the key value
     * If entered value is number allow to enter otherwise return as false
     */
    numberOnly(event): boolean {
        const charCode = event.which ? event.which : event.keyCode;
        if (charCode > 31 && (charCode < 48 || charCode > 57)) {
            return false;
        }
        return true;
    }
}
