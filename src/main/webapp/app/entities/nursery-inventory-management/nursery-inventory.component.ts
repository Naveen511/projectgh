import { Component, OnInit, ViewChild } from '@angular/core';
import { NurseryInventoryService } from 'app/entities/service/nursery-inventory.service';
import { NurseryInventoryDetailsService } from 'app/entities/service/nursery-inventory-details.service';
import { ZonalService } from 'app/entities/service/zonal.service';
import { SectorService } from 'app/entities/service/sector.service';
import { NurseryService } from 'app/entities/service/nursery.service';
import { PickListService } from 'app/entities/service/pick-list.service';
import { PickListValueService } from 'app/entities/service/pick-list-value.service';
import { INurseryInventory, STATUS_SEEDS, STATUS_COVER, NurseryInventory } from 'app/shared/model/nursery-inventory.model';
import { STATUS_ADD, STATUS_CONSUME, STATUS_DAMAGE, NurseryInventoryDetails, INurseryInventoryDetails } from 'app/shared/model/nursery-inventory-details.model';
import { IZonal } from 'app/shared/model/zonal.model';
import { ISector } from 'app/shared/model/sector.model';
import { INursery } from 'app/shared/model/nursery.model';
import { IPickList } from 'app/shared/model/pick-list.model';
import { IPickListValue } from 'app/shared/model/pick-list-value.model';
import { FinancialYearSettingsService } from 'app/entities/service/financial-year-settings.service';
import { IFinancialYearSettings } from 'app/shared/model/financial-year-settings.model';

import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import * as moment from 'moment';
import { DATE_TIME_FORMAT, STATUS_ACTIVE } from 'app/shared';
import { Observable } from 'rxjs';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { Subject } from 'rxjs';
import { debounceTime } from 'rxjs/operators';
import { CoverFilling, ICoverFilling } from 'app/shared/model/cover-filling.model';
import { CoverFillingService } from 'app/entities/cover-filling';
import { CoverFillingDetails, ICoverFillingDetails } from 'app/shared/model/cover-filling-details.model';
import { CoverFillingDetailsService } from 'app/entities/cover-filling-details';

// Mension the html, css/sass files
@Component({
    selector: 'jhi-nursery-inventory',
    templateUrl: 'nursery-inventory.component.html'
})

/**
 * Class NurseryInventoryMgntComponent used to create/update a godown, List all nurseryInventorys.
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
    damageDescription: IPickListValue[];

    isCollapsed = true;
    isCollapsedInventoryDetails = true;
    isCoverCollapsed = true;
    isCoverFillingCollapsed = true;

    inventoryDateDp: any;
    seedsDateDp: any;
    addInventoryDateDp: any;
    consumeInventoryDateDp: any;
    damageInventoryDateDp: any;
    inventoryStatus: any;
    inventoryTitle: any;
    financialYearId: number;

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
        private coverFillingDetailsService: CoverFillingDetailsService
    ) {
    }

    ngOnInit() {
        // Call a function to get list of nursery
        this.getSeedsInventoryList();
        this.getCoverInventoryList();
        this.getZonalList();
        this.getPickList();
        this.getCoverFillingList();
        // Call a function to get active batch id
        this.getActiveRecord();

        // To set the time for automatic alert close
        setTimeout(() => this.staticAlertClosed = true, 5000);

        // Set the success message with debounce time
        this.success.subscribe(message => this.successMessage = message);
        this.success.pipe(
            debounceTime(3000)
        ).subscribe(() => this.successMessage = null);

        // To set the error message with debounce time
        this.error.subscribe(message => this.errorMessage = message);
        this.error.pipe(
            debounceTime(5000)
        ).subscribe(() => this.errorMessage = null);
    }

    // Get created zonal from zonal table
    getZonalList(): void {
        // Get the list of zone
        this.zonalService.query({
            filter: {'status.equals': STATUS_ACTIVE}
        }).subscribe((res: HttpResponse<IZonal[]>) => {
            this.zonals = res.body;
        });
    }

    // Get pick list from the pick list table
    getPickList(): void {
        // Get the list of picklist
        this.pickListService.query({
            filter: {'status.equals': STATUS_ACTIVE}
        }).subscribe((res: HttpResponse<IPickList[]>) => {
            this.pickLists = res.body;
        });
    }

    // Call a service function to get list of active batch
    getActiveRecord(): void {
        // Get the list of active batch record and assign a 0th index array value to an batch id
        this.settingsService.getActiveRecord().subscribe((res: HttpResponse<IFinancialYearSettings[]>) => {
            this.financialYearId = res.body[0].id;
        });
    }

    // Call a service function to get list of stocks
    // getNurseryInventoryList(): void {
    //     // Get the list of godown
    //     this.nurseryInventoryService.query().subscribe((res: HttpResponse<INurseryInventory[]>) => {
    //         this.nurseryInventorys = res.body;
    //     });
    // }

    // To get the Seeds inventory List of all the nurseries
    getSeedsInventoryList(): void {
        // Get the list of godown
        this.nurseryInventoryService.queryGetSeedsList(STATUS_SEEDS)
        .subscribe((res: HttpResponse<INurseryInventory[]>) => {
            this.nurseryInventorys = res.body;
        });
    }

    // To get the Cover inventory Lists of all the nurseries
    getCoverInventoryList(): void {
        // Get the list of godown
        this.nurseryInventoryService.queryGetCoverList(STATUS_COVER)
        .subscribe((res: HttpResponse<INurseryInventory[]>) => {
            this.coverInventorys = res.body;
        });
    }

    // Get the sector value based on zonal id
    getSector(zoneId): void {
        // console.log(zoneId);
        // Get the list of sector
        this.sectorService.getSectors(zoneId).subscribe((res: HttpResponse<ISector[]>) => {
            // console.log(res.body);
            this.sectors = res.body;
        });
    }

    // To get the nursery based on the sector Id
    getNursery(sectorId): void {
        // Get the list of nursery
        this.nurseryService.getNurserys(sectorId).subscribe((res: HttpResponse<INursery[]>) => {
            // console.log(res.body);
            this.nurserys = res.body;
        });
    }

    // To get the picklist variety
    getVariety(id): void {
        this.pickListValueService.getVariety(id).subscribe((res: HttpResponse<IPickListValue[]>) => {
            // console.log(res.body);
            this.varietys = res.body;
        });
    }

    // To get the pick list category
    getCategory(id): void {
        this.pickListValueService.getCategory(id).subscribe((res: HttpResponse<IPickListValue[]>) => {
            // console.log(res.body);
            this.categorys = res.body;
        });
    }

    // To get the quantity type from the pick list value table
    getQuantityType(id): void {
        // console.log('get quantity type');
        // console.log(id);
        this.pickListValueService.getVariety(id).subscribe((res: HttpResponse<IPickListValue[]>) => {
            // console.log(res.body);
            this.quantityTypes = res.body;
        });
    }

    // To get the quantity type from the picklist value table
    getDamageType(id): void {
        // console.log('get quantity type');
        // console.log(id);
        this.pickListValueService.getVariety(id).subscribe((res: HttpResponse<IPickListValue[]>) => {
            // console.log(res.body);
            this.quantityTypes = res.body;
        });
    }

    // Get the quantity Description type
    getDamageDescriptionType(id): void {
        this.pickListValueService.getVariety(id).subscribe((res: HttpResponse<IPickListValue[]>) => {
            this.damageDescription = res.body;
        });
    }

    // To reset/empty the value of add seeds and covers
    resetReceiveForm(): void {
        // console.log('resetValue');
        // console.log(this.nurseryInventory);
        this.nurseryInventory = {};
        this.nurseryInventoryDetails = {};
        // console.log(this.nurseryInventory);
    }

    // Create new Batch
    saveSeeds(): void {
        // console.log('save seeds function');
        if (this.nurseryInventory.nurserysId === null
            || this.nurseryInventory.nurserysId === undefined) {
            this.error.next(`Nursery should not be empty`);
        } else if (this.nurseryInventory.pickListCategoryId === null
            || this.nurseryInventory.pickListCategoryId === undefined) {
            this.error.next(`Category should not be empty`);
        } else if (this.nurseryInventory.quantityTypeId === null
            || this.nurseryInventory.quantityTypeId === undefined) {
            this.error.next(`Quantity Type should not be empty`);
        } else {
            // console.log('save function');
            this.nurseryInventory.status = STATUS_SEEDS;
            this.nurseryInventoryDetails.status = STATUS_ADD;
            // console.log(this.nurseryInventory);
            this.nurseryInventoryService.getNurseryCategoryInventory(this.nurseryInventory.nurserysId, this.nurseryInventory.pickListCategoryId)
            .subscribe((res: HttpResponse<INurseryInventory[]>) => {
                // console.log(res.body);
                // console.log(res.body.length);
                // If the total count is greater than 0
                // (that means already that nursery added the needs)
                // To update the nursery seeds details
                if (res.body.length > 0) {
                    this.nurseryInventory = res.body[res.body.length - 1];
                    this.nurseryInventory.currentQuantity = +this.nurseryInventory.currentQuantity + +this.nurseryInventoryDetails.quantity;
                    this.nurseryInventory.addedQuantity = +this.nurseryInventory.addedQuantity + +this.nurseryInventoryDetails.quantity;
                    this.subscribeToSaveResponse(this.nurseryInventoryService.update(this.nurseryInventory));
                } else {
                    this.nurseryInventory.currentQuantity = this.nurseryInventoryDetails.quantity;
                    this.nurseryInventory.addedQuantity = this.nurseryInventoryDetails.quantity;
                    this.subscribeToSaveResponse(this.nurseryInventoryService.create(this.nurseryInventory));
                }
            });
        }
    }

    // To save the inventory details
    private subscribeToSaveResponse(result: Observable<HttpResponse<INurseryInventory>>) {
        result.subscribe(
            (res: HttpResponse<INurseryInventory>) => {
                this.nurseryInventoryDetails.nurseryInventoryId = res.body.id;
                this.saveInventoryDetails();
            },
            (res: HttpErrorResponse) => {
                this.error.next(res.error.fieldErrors[0].message);
            }
        );
    }

    // To save the inventory details based on the status
    saveInventoryDetails(): void {
        this.nurseryInventoryDetails.date = moment(this.nurseryInventoryDetails.date, DATE_TIME_FORMAT);
        // this.nurseryInventoryDetails.status = STATUS_ADD;
        this.nurseryInventoryDetailsService.create(this.nurseryInventoryDetails)
        .subscribe(data => {
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
        });
    }

    // To see the inventory details of seeds/covers
    // Particular nursery details of seeds/covers
    getInventoryDetails(id, status): void {
        // If the status is seeds to set the title as seeds
        if (status === STATUS_SEEDS) {
            this.inventoryTitle = 'Seeds Details';
        } else {
            // If the status is cover to set the status as cover
            this.inventoryTitle = 'Cover Details';
        }
        // Get the particular inventory details
        this.nurseryInventoryDetailsService.getParticularInventorys(id)
            .subscribe((res: HttpResponse<IPickListValue[]>) => {
            this.isCollapsedInventoryDetails = false;
            this.inventoryDetails = res.body;
        });
    }

    // Add inventory Details
    addMoreInventory(value): void {
        // console.log(this.nurseryInventory);
        this.nurseryInventory = new NurseryInventory();
        this.nurseryInventory = value;
        this.nurseryInventoryDetails = new NurseryInventoryDetails();
        this.nurseryInventoryDetails.status = STATUS_ADD;
        this.addParticularInventory.show();
    }

    // Add the inventory with inventory details
    addInventoryQuantity(value): void {
        // console.log('Add');
        // console.log(value);
        // console.log(this.nurseryInventoryDetails.date);
        if (this.nurseryInventoryDetails.date == null
            || this.nurseryInventoryDetails.date === undefined) {
            this.error.next(`Date should not be empty`);
        } else if (value.currentQuantity == null
            || value.currentQuantity === NaN
            || value.currentQuantity === undefined) {
            this.error.next(`You cant able to add. Because Current Quantity is empty or 0.`);
        } else if (this.nurseryInventoryDetails.quantity == null
            || this.nurseryInventoryDetails.quantity === NaN
            || this.nurseryInventoryDetails.quantity === undefined) {
            this.error.next(`Quantity should not be empty`);
        } else {
            this.nurseryInventory.currentQuantity = +value.currentQuantity + +this.nurseryInventoryDetails.quantity;
            this.nurseryInventory.addedQuantity = +value.addedQuantity + +this.nurseryInventoryDetails.quantity;
            this.subscribeToSaveResponse(this.nurseryInventoryService.update(this.nurseryInventory));
        }
    }

    // To connnsume the inventory details
    consumeInventory(value): void {
        // If current quantity is 0, throw the error
        if (value.currentQuantity === 0
            || value.currentQuantity < this.nurseryInventoryDetails.quantity) {
            this.error.next(`You cant able to consume. Because Current Quantity is 0.`);
        } else {
            this.nurseryInventory = new NurseryInventory();
            this.nurseryInventory = value;
            this.nurseryInventoryDetails = new NurseryInventoryDetails();
            this.nurseryInventoryDetails.status = STATUS_CONSUME;
            this.consumeParticularInventory.show();
        }
    }

    // To consume the Inventory Details from the total quantity
    consumeInventoryQuantity(value): void {
        if (this.nurseryInventoryDetails.date == null
            || this.nurseryInventoryDetails.date === undefined) {
            this.error.next(`Date should not be empty`);
        } else if (this.nurseryInventoryDetails.quantity == null
            || this.nurseryInventoryDetails.quantity === NaN
            || this.nurseryInventoryDetails.quantity === undefined) {
            this.error.next(`Quantity should not be empty`);
        } else if (value.currentQuantity < this.nurseryInventoryDetails.quantity) {
            this.error.next(`You cant able to consume. Because Current Quantity is 0.`);
        } else {
            this.nurseryInventory.currentQuantity = +value.currentQuantity - +this.nurseryInventoryDetails.quantity;
            // this.nurseryInventory.addedQuantity = +value.addedQuantity - +this.nurseryInventoryDetails.quantity;
            this.nurseryInventory.consumedQuantity = +value.consumedQuantity + +this.nurseryInventoryDetails.quantity;
            this.subscribeToSaveResponse(this.nurseryInventoryService.update(this.nurseryInventory));
        }
    }

    // Add the damage seeds of nursery
    damageInventory(value): void {
        if (value.currentQuantity === 0) {
            this.error.next(`You cant add to damage. Because Current Quantity is 0.`);
        } else {
            this.nurseryInventory = new NurseryInventory();
            this.nurseryInventory = value;
            // console.log(this.nurseryInventoryDetails.quantity);
            this.nurseryInventoryDetails = new NurseryInventoryDetails();
            this.nurseryInventoryDetails.status = STATUS_DAMAGE;
            this.damageParticularInventory.show();
        }
    }

    // To consume the Inventory Details from the total quantity
    damageInventoryQuantity(value): void {
        if (this.nurseryInventoryDetails.damageTypeId == null
            || this.nurseryInventoryDetails.damageTypeId === undefined) {
            this.error.next(`Pick damage name should not be empty`);
        } else if (this.nurseryInventoryDetails.date == null
            || this.nurseryInventoryDetails.date === undefined) {
            this.error.next(`Date should not be empty`);
        } else if (this.nurseryInventoryDetails.quantity == null
            || this.nurseryInventoryDetails.quantity === NaN
            || this.nurseryInventoryDetails.quantity === undefined) {
            this.error.next(`Quantity should not be empty`);
        } else if (value.currentQuantity < this.nurseryInventoryDetails.quantity) {
            this.error.next(`You cant able to consume.
                Because Current Quantity is 0.`);
        } else {
            this.nurseryInventory.currentQuantity = +value.currentQuantity - +this.nurseryInventoryDetails.quantity;
            this.nurseryInventory.damageQuantity = +value.damageQuantity + +this.nurseryInventoryDetails.quantity;
            // this.nurseryInventory.addedQuantity = +value.addedQuantity - +this.nurseryInventoryDetails.quantity;
            this.subscribeToSaveResponse(this.nurseryInventoryService.update(this.nurseryInventory));
        }
    }

    // Save the cover inventory
    saveCover(): void {
        // console.log(this.nurseryInventory);
        if (this.nurseryInventory.nurserysId === null
            || this.nurseryInventory.nurserysId === undefined) {
            this.error.next(`Nursery should not be empty`);
        } else if (this.nurseryInventory.quantityTypeId === null
            || this.nurseryInventory.quantityTypeId === undefined) {
            this.error.next(`Quantity Type should not be empty`);
        // } else if (this.nurseryInventoryDetails.quantity === null
        //     || this.nurseryInventoryDetails.quantity === undefined) {
        //     this.error.next(`Quantity should not be empty`);
        // } else if (this.nurseryInventoryDetails.date === null
        //     || this.nurseryInventoryDetails.date === undefined) {
        //     this.error.next(`Date of Receiving should not be empty`);
        } else {
            // console.log('cover save function');
            this.nurseryInventory.status = STATUS_COVER;
            this.nurseryInventoryDetails.status = STATUS_ADD;
            // this.nurseryInventory.createdAt = moment(this.createdAt, DATE_TIME_FORMAT);
            // this.nurseryInventory.currentQuantity = this.nurseryInventoryDetails.quantity;
            // this.nurseryInventory.addedQuantity = this.nurseryInventoryDetails.quantity;
            // this.subscribeToSaveResponse(this.nurseryInventoryService.create(this.nurseryInventory));

            this.nurseryInventoryService.getCoverInventory(this.nurseryInventory.nurserysId, STATUS_COVER)
            .subscribe((res: HttpResponse<INurseryInventory[]>) => {
                // console.log(res.body);
                // console.log(res.body.length);
                // If length is greater than 0, to update the old row
                if (res.body.length > 0) {
                    this.nurseryInventory = res.body[res.body.length - 1];
                    this.nurseryInventory.currentQuantity = +this.nurseryInventory.currentQuantity + +this.nurseryInventoryDetails.quantity;
                    this.nurseryInventory.addedQuantity = +this.nurseryInventory.addedQuantity + +this.nurseryInventoryDetails.quantity;
                    this.subscribeToSaveResponse(this.nurseryInventoryService.update(this.nurseryInventory));
                } else {
                    // console.log('Create function');
                    this.nurseryInventory.currentQuantity = this.nurseryInventoryDetails.quantity;
                    this.nurseryInventory.addedQuantity = this.nurseryInventoryDetails.quantity;
                    this.subscribeToSaveResponse(this.nurseryInventoryService.create(this.nurseryInventory));
                }
            });
        }
    }

    // To save cover filling
    // Save the cover inventory
    saveCoverFilling(): void {
        // this.coverFilling.status = STATUS_COVER;
        this.coverFillingsubscribeToSaveResponse(this.coverFillingService.create(this.coverFilling));
    }

    // To save the inventory details
    private coverFillingsubscribeToSaveResponse(result: Observable<HttpResponse<ICoverFilling>>) {
        result.subscribe(
            (res: HttpResponse<ICoverFilling>) => {
                // console.log('created');
                this.success.next(`Successfully prepared the cover`);
                this.isCoverFillingCollapsed = true;
                this.getCoverFillingList();
                this.coverFilling = {};
            },
            (res: HttpErrorResponse) => {
                // console.log('Not created');
                // console.log(res.error.fieldErrors[0].message);
                this.error.next(res.error.fieldErrors[0].message);
            }
        );
    }

    resetCoverFilling(): void {
        this.coverFilling = {};
    }

    // Call a service function to get list of stocks
    getCoverFillingList(): void {
        // Get the list of godown
        this.coverFillingService.query().subscribe((res: HttpResponse<INurseryInventory[]>) => {
        this.coverFillings = res.body;
        });
    }

    // Add the damage seeds of nursery
    damageCoverFilling(value): void {
        if (value.currentQuantity === 0) {
            this.error.next(`You cant add to damage. Because Current Quantity is 0.`);
        } else {
            this.coverFilling = new CoverFilling();
            this.coverFilling = value;
            // console.log(this.coverFilling.noOfCover);
            // console.log(this.nurseryInventoryDetails.quantity);
            this.coverFillingDetails = new CoverFillingDetails();
            this.coverFillingDetails.status = STATUS_DAMAGE;
            this.damageCoverFillingModel.show();
        }
    }

    // To consume the Inventory Details from the total quantity
    saveDamageCoverFilling(value): void {
        if (this.coverFillingDetails.damageTypeId == null
            || this.coverFillingDetails.damageTypeId === undefined) {
            this.error.next(`Pick damage name should not be empty`);
        } else if (value.noOfCover < this.coverFillingDetails.quantity) {
            this.error.next(`You cant able to consume.
                Because Current Quantity is 0.`);
        } else {
            this.coverFilling.noOfCover = +value.noOfCover - +this.coverFillingDetails.quantity;
            this.coverFilling.damageQuantity = +value.damageQuantity + +this.coverFillingDetails.quantity;
            // this.nurseryInventory.addedQuantity = +value.addedQuantity - +this.nurseryInventoryDetails.quantity;
            this.saveCoverFillingSaveResponse(this.coverFillingService.update(this.coverFilling));
        }
    }

    // To save the inventory details
    private saveCoverFillingSaveResponse(result: Observable<HttpResponse<ICoverFilling>>) {
        result.subscribe(
            (res: HttpResponse<ICoverFilling>) => {
                this.coverFillingDetails.coverFillingId = res.body.id;
                this.saveCoverFillingDetails();
            },
            (res: HttpErrorResponse) => {
                this.error.next(res.error.fieldErrors[0].message);
            }
        );
    }

    // To save the inventory details based on the status
    saveCoverFillingDetails(): void {
        this.coverFillingDetails.date = moment(this.coverFillingDetails.date, DATE_TIME_FORMAT);
        // this.nurseryInventoryDetails.status = STATUS_ADD;
        this.coverFillingDetailsService.create(this.coverFillingDetails)
        .subscribe(data => {
            // Based on the status displayed the status alert
            this.success.next(`Successfully updated damage ${this.inventoryStatus}.`);
            this.damageParticularInventory.hide();
            // Call a function to get list of nursery Stocks
            this.getCoverFillingList();
            this.damageCoverFillingModel.hide();
            this.coverFilling = new NurseryInventory();
            this.coverFillingDetails = new NurseryInventoryDetails();
            // this.isCollapsed = true;
            // this.isCollapsedInventoryDetails = true;
        },
            (res: HttpErrorResponse) => {
            // this.error.next(res.error.fieldErrors[0].message);
            this.success.next(res.error.fieldErrors[0].message);
        });
    }

    // To see the inventory details of seeds/covers
    // Particular nursery details of seeds/covers
    getCoverFillingDetails(id): void {
        // Get the particular inventory details
        this.coverFillingDetailsService.getParticularCover(id)
            .subscribe((res: HttpResponse<IPickListValue[]>) => {
            this.isCollapsedInventoryDetails = false;
            this.inventoryDetails = res.body;
        });
    }
}
