/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/09/02
 *  Target: yarn
 *******************************************************************************/
// Import needed component and dependency
import { Component, OnInit, ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import * as moment from 'moment';
import { Observable } from 'rxjs';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { Subject } from 'rxjs';
import { debounceTime } from 'rxjs/operators';
import { NgbDatepickerConfig } from '@ng-bootstrap/ng-bootstrap';

import { NurseryStockService } from 'app/entities/service/nursery-stock.service';
import {
    NurseryStockDetailsService
} from 'app/entities/service/nursery-stock-details.service';
import {
    PointOfSaleDetailsService
} from 'app/entities/service/point-of-sale-details.service';
import { ZonalService } from 'app/entities/service/zonal.service';
import { SectorService } from 'app/entities/service/sector.service';
import { NurseryService } from 'app/entities/service/nursery.service';
import { PickListService } from 'app/entities/service/pick-list.service';
import { PickListValueService } from 'app/entities/service/pick-list-value.service';
import {
    FinancialYearSettingsService
} from 'app/entities/service/financial-year-settings.service';

import {
    INurseryStock, STATUS_DIRECT, NurseryStock
} from 'app/shared/model/nursery-stock.model';
import {
    NurseryStockDetails, INurseryStockDetails, STATUS_ADD, STATUS_CONSUME,
    STATUS_SAPLING_DAMAGE, STATUS_SALE_POS, DISPLAY_NAME_VARIETY, IT_STATUS_DISTRIBUTED,
    IT_STATUS_ADDED
} from 'app/shared/model/nursery-stock-details.model';
import { IZonal } from 'app/shared/model/zonal.model';
import { ISector } from 'app/shared/model/sector.model';
import { INursery } from 'app/shared/model/nursery.model';
import { IPickList } from 'app/shared/model/pick-list.model';
import { IPickListValue } from 'app/shared/model/pick-list-value.model';
import { IFinancialYearSettings } from 'app/shared/model/financial-year-settings.model';
import {
    PointOfSaleDetails, IPointOfSaleDetails
} from 'app/shared/model/point-of-sale-details.model';
import {
    DISPLAY_NAME_DAMAGE_AREA
} from 'app/shared/model/nursery-inventory-details.model';
import { DISPLAY_NAME_DAMAGE_REASON } from 'app/shared/model/batch.model';

import { DATE_TIME_FORMAT, STATUS_ACTIVE, ALERT_TIME_OUT_5000 } from 'app/shared';

// Mension the html, css/sass files
@Component({
    selector: 'jhi-godown',
    templateUrl: 'nursery-stock.component.html'
})

/**
 * Class NurseryStockMgntComponent used to create/update a godown, List all nurseryStocks.
 * Declared an Array variable to set list of nursery stocks.
 */
export class NurseryStockMgntComponent implements OnInit {
    // create an empty object
    nurseryStock: NurseryStock = new NurseryStock();
    nurseryStockDetails: NurseryStockDetails = new NurseryStockDetails();
    pointOfSaleDetails: PointOfSaleDetails = new PointOfSaleDetails();

    // create empty array for each service
    nurseryStocks: INurseryStock[];
    stockDetails: INurseryStockDetails[];
    particularNurseryDetails: INurseryStockDetails[];
    pointOfSales: PointOfSaleDetails[];
    addedNurseryDetails: INurseryStockDetails[];

    zonals: IZonal[];
    sectors: ISector[];
    receivedSectors: ISector[];
    nurserys: INursery[];
    nurserysList: INursery[];
    receivedNurserys: INursery[];
    pickLists: IPickList[];
    varieties: IPickListValue[];
    categories: IPickListValue[];
    damageAreaList: IPickListValue[];
    damageReasons: IPickListValue[];
    isCollapsed = true;
    isCollapsedStockDetails = true;

    // For datepicker initialization
    stockDateDp: any;
    stockDetailsDateDp: any;
    addToStockDate: any;
    financialYearId: number;
    stockDetailId: number;

    damageQuantity: number;
    statusDamage: number;
    statusAdd: number;
    statusConsume: number;
    statusPos: number;
    modalTitle: any;

    // For Damage
    damageDescription: any;
    saplingDamageAreaId: number;

    // To display the success message
    private success = new Subject<string>();
    successMessage: string;

    // To display the error message
    private error = new Subject<string>();
    errorMessage: string;

    // By default close the alert with statc time
    staticAlertClosed = false;

    @ViewChild('addParticularStock') public addParticularStock: ModalDirective;
    @ViewChild('stockPOS') public stockPOS: ModalDirective;
    @ViewChild('addToStockModel') public addToStockModel: ModalDirective;

    constructor(
        private zonalService: ZonalService,
        private sectorService: SectorService,
        private nurseryService: NurseryService,
        private pickListService: PickListService,
        private pickListValueService: PickListValueService,
        private nurseryStockService: NurseryStockService,
        private nurseryStockDetailsService: NurseryStockDetailsService,
        private settingsService: FinancialYearSettingsService,
        private pointOfSaleDetailsService: PointOfSaleDetailsService,
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

        this.statusDamage = STATUS_SAPLING_DAMAGE;
        this.statusConsume = STATUS_CONSUME;
        this.statusAdd = STATUS_ADD;
        this.statusPos = STATUS_SALE_POS;
    }

    ngOnInit() {
        // Call a function to get list of nursery Stocks
        this.getNurseryStockList();

        // this.getZonalList();
        // this.getPickList();
        // this.getPointOfSaleList();
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

    // Display the seed create form and reset the form fields
    addSaplings(): void {
        // Hide the collapse of create sapling and view sapling details
        this.isCollapsed = false;
        this.isCollapsedStockDetails = true;
        // Reset the form values
        this.resetForm();
        // Get the zonal List
        this.getZonalList();
        // Get the pick list calue to display the variety
        this.getPickList();
        // Get the active records
        this.getActiveRecord();
    }

    /**
     * To get the active batch record from the financialYear settings
     */
    getActiveRecord(): void {
        // Get the list of active batch record and assign a 0th index array value to an batch id
        this.settingsService.query({ filter: { 'status.equals': STATUS_ACTIVE }
        }).subscribe((res: HttpResponse<IFinancialYearSettings[]>) => {
            this.financialYearId = res.body[0].id;
        });
    }

    /**
     * Get Zonal list from the zonal table
     */
    getZonalList(): void {
        // Get the list of zone
        this.zonalService.query({ filter: {'status.equals': STATUS_ACTIVE }
        }).subscribe((res: HttpResponse<IZonal[]>) => {
            this.zonals = res.body;
        });
    }

    /**
     * Get the sector value based on zonal id
     * @param zoneId number
     */
    getSector(zoneId): void {
        // Get the list of sector
        this.sectorService.query({
            filter: {
                'status.equals': STATUS_ACTIVE,
                'zonalId.equals': zoneId
            }
        }).subscribe((res: HttpResponse<ISector[]>) => {
            // console.log(res.body);
            this.sectors = res.body;
        });
    }

    /**
     * Get nursery based on the sector
     * @param sectorId number
     */
    getNursery(sectorId): void {
        // Get the list of nursery
        this.nurseryService.query({
            filter: {
                'status.equals': STATUS_ACTIVE,
                'sectorId.equals': sectorId
            }
        }).subscribe((res: HttpResponse<INursery[]>) => {
            // console.log(res.body);
            this.nurserys = res.body;
        });
    }

    /**
     * Get variety based on the picklist Id
     * @param id number
     */
    getVariety(id): void {
        // this.pickListValueService.getVariety(id)
        this.pickListValueService.query({
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
     * Get picklist value from the picklist table
     */
    getPickList(): void {
        // Get the list of picklist
        this.pickListService
        .query({
            filter: {
                'status.equals': STATUS_ACTIVE,
                'displayLabelName.equals':  DISPLAY_NAME_VARIETY
            }
        }).subscribe((res: HttpResponse<IPickList[]>) => {
            if (res.body.length > 0) {
                this.getVariety(res.body[0].id);
            }
        });
    }

    /**
     * Call a service function to get list of stocks
     */
    getNurseryStockList(): void {
        // Get the list of godown
        this.nurseryStockService.query()
        .subscribe((res: HttpResponse<INurseryStock[]>) => {
            this.nurseryStocks = res.body;
        });
    }

    /**
     * To get the It list based on the nursery
     * @param nurseryId number
     */
    getParticularList(nurseryId): void {
        this.nurseryStockDetailsService
        .query({
            filter: {
                'itNurseryId.equals': nurseryId,
                'status.equals':  2
            }
        }).subscribe((res: HttpResponse<INurseryStockDetails[]>) => {
            this.particularNurseryDetails = res.body;
        });
        this.getAddedStockList(nurseryId);
    }

    /**
     * Display the added stock details into the nursery for the IT
     * @param nurseryId number
     */
    getAddedStockList(nurseryId): void {
        this.nurseryStockDetailsService
        .query({
            filter: {
                'itNurseryId.equals': nurseryId,
                'itStatus.equals':  IT_STATUS_ADDED
            }
        }).subscribe((res: HttpResponse<INurseryStockDetails[]>) => {
            this.addedNurseryDetails = res.body;
            // console.log(this.addedNurseryDetails);
        });
    }

    /**
     * Get the Ponit of sale detail, to display the POS details
     */
    getPointOfSaleList(): void {
        this.isCollapsedStockDetails = true;
        this.isCollapsed = true;
        // To get the point of details
        this.pointOfSaleDetailsService.query()
        .subscribe((res: HttpResponse<IPointOfSaleDetails[]>) => {
            this.pointOfSales = res.body;
            // console.log(this.pointOfSales);
        });
    }

    /**
     * To get the damage type using the picklist value id
     * @param id number
     */
    getDamageType(id): void {
        // this.pickListValueService.getVariety(id)
        this.pickListValueService.query({
            filter: {
                'status.equals': STATUS_ACTIVE,
                'pickListId.equals': id
            }
        })
        .subscribe((res: HttpResponse<IPickListValue[]>) => {
            // console.log(res.body);
            this.damageAreaList = res.body;
        });
    }

    /**
     * Get the damage reason from the pick list value table
     * @param id number
     */
    getDamageReason(id): void {
        // this.pickListValueService.getVariety(id)
        this.pickListValueService.query({
            filter: {
                'status.equals': STATUS_ACTIVE,
                'pickListId.equals': id
            }
        })
        .subscribe((res: HttpResponse<IPickListValue[]>) => {
            // console.log(res.body);
            this.damageReasons = res.body;
        });
    }

    /**
     * For distribute used getReceivedSector Function
     * Receive sector based on the Zonal Id
     * @param zonalId number
     */
    getReceivedSector(zonalId): void {
        // Get the list of sector
        // this.sectorService.getSectors(zonalId)
        this.sectorService.query({
            filter: {
                'status.equals': STATUS_ACTIVE,
                'zonalId.equals': zonalId
            }
        })
        .subscribe((res: HttpResponse<ISector[]>) => {
            // console.log(res.body);
            this.receivedSectors = res.body;
        });
    }

    /**
     * For distribute used getReceivedNursery Function
     * Receive nursery based on the Sector Id
     * @param sectorId number
     */
    getReceivedNursery(sectorId): void {
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
            this.receivedNurserys = res.body;
        });
    }

    /**
     * To get the PickList category using the pick list table Id
     * Set the picklist value to this.category object.
     * @param id number
     */
    getCategory(id): void {
        // this.pickListValueService.getCategory(id)
        this.pickListValueService.query({
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
     * To get the stock details using the nursery stock
     * Here displays the distribute, damage and added stock deails
     */
    getStockDetails(id, title): void {
        // To hide the collpase of stock details
        this.isCollapsed = true;
        this.modalTitle = title;
        // this.nurseryStockDetailsService.getParticularStocks(id)
        this.nurseryStockDetailsService.query({
            filter: { 'nurseryStockId.equals': id }
        })
        .subscribe((res: HttpResponse<INurseryStockDetails[]>) => {
            this.isCollapsedStockDetails = false;
            this.stockDetails = res.body;
        });
    }

    /**
     * To get the damage area from the pick list using the active status
     * and display label name as DISPLAY_NAME_DAMAGE_AREA
     */
    getDamageArea(): void {
        // To get the damage area from the pick list
        this.pickListService
        .query({
            filter: {
                'status.equals': STATUS_ACTIVE,
                'displayLabelName.equals': DISPLAY_NAME_DAMAGE_AREA
            }
        }).subscribe((res: HttpResponse<IPickList[]>) => {
            if (res.body.length > 0) {
                this.getDamageType(res.body[0].id);
            }
        });
    }

    /**
     * To get the damage reason using the active status
     * and display label name as DISPLAY_NAME_DAMAGE_REASON
     */
    getPickDamageReason(): void {
        this.pickListService
        .query({
            filter: {
                'status.equals': STATUS_ACTIVE,
                'displayLabelName.equals': DISPLAY_NAME_DAMAGE_REASON
            }
        }).subscribe((res: HttpResponse<IPickList[]>) => {
            if (res.body.length > 0) {
                this.getDamageReason(res.body[0].id);
            }
        });
    }

    /**
     * Show addTostockModel Form
     * @param value NurseryStockDetails
     */
    addToStocks(value: NurseryStockDetails) {
        this.addToStockModel.show();
        this.nurseryStockDetails = value;
        this.nurseryStockDetails.description = null;
    }

    /**
     * Added the distributed saplings, using the ItNursery Id
     * @param stockDetails stockdetails
     * @param damagedQuantity number
     */
    addStockToNursery(stockDetails, damagedQuantity) {
        this.stockDetailId = stockDetails.id;
        this.damageDescription = stockDetails.description;
        this.saplingDamageAreaId = stockDetails.saplingDamageAreaId;

        this.nurseryStock = stockDetails;
        this.nurseryStockDetails.status = STATUS_ADD;
        this.nurseryStockDetails.itStatus = STATUS_ADD;
        // .getNurseryCategoryStock(
        //     stockDetails.itNurseryId, stockDetails.stockCategoryId
        // )
        this.nurseryStockService.query({
            filter: {
                 'nurseryId.equals': stockDetails.itNurseryId,
                 'pickListCategoryId.equals': stockDetails.stockCategoryId
            }
        }).subscribe((res: HttpResponse<INurseryStock[]>) => {
            // If the length is greater than zero,
            // add the new row for nursery stock
            if (res.body.length > 0) {
                // this.nurseryStock = res.body[res.body.length - 1];
                this.nurseryStock = res.body[0];
                this.nurseryStock.currentQuantity = +this.nurseryStock.currentQuantity + +this.nurseryStockDetails.quantity;
                this.nurseryStock.addedQuantity = +this.nurseryStock.addedQuantity + +this.nurseryStockDetails.quantity;
                this.nurseryStockService.update(this.nurseryStock).subscribe(
                    data => {
                        this.nurseryStockDetails.nurseryStockId = data.body.id;
                        this.createNurseryStockDetails(
                            data.body,
                            this.nurseryStockDetails,
                            damagedQuantity, this.stockDetailId,
                            this.damageDescription, this.saplingDamageAreaId
                        );
                    },
                    (err: HttpErrorResponse) => {
                        alert(err.error.fieldErrors[0].message);
                    }
                );
            } else {
                this.nurseryStock = new NurseryStock();
                this.nurseryStock = stockDetails;
                this.nurseryStock.id = null;
                this.nurseryStock.status = STATUS_ACTIVE;
                this.nurseryStock.nurseryId = stockDetails.itNurseryId;
                this.nurseryStock.pickListCategoryId = stockDetails.stockCategoryId;
                this.nurseryStock.pickListVarietyId = stockDetails.stockVarietyId;
                // console.log('new row', this.nurseryStock);
                this.nurseryStockDetails = stockDetails;
                this.nurseryStockDetails.status = STATUS_ADD;
                this.nurseryStock.currentQuantity = this.nurseryStockDetails.quantity;
                this.nurseryStock.addedQuantity = this.nurseryStockDetails.quantity;
                this.nurseryStock.pickListCategoryId = stockDetails.stockCategoryId;
                this.nurseryStock.pickListVarietyId = stockDetails.stockVarietyId;
                this.nurseryStockService.create(this.nurseryStock).subscribe(
                    data => {
                        alert('Added to Nursery Stock');
                        this.nurseryStockDetails.nurseryStockId = data.body.id;
                        this.createNurseryStockDetails(
                            data.body,
                            this.nurseryStockDetails,
                            damagedQuantity, this.stockDetailId,
                            this.damageDescription, this.saplingDamageAreaId
                        );
                        this.getNurseryStockList();
                    },
                    (err: HttpErrorResponse) => {
                        alert(err.error.fieldErrors[0].message);
                    }
                );
            }
        });
    }

    /**
     * To create the nursery stock details
     * @param nurseryStock NurseryStockModel
     * @param nurseryStockDetails NurseryStockDetailsModel
     * @param damagedQuantity DamagedQuantity
     * @param stockDetailId StockDetailId
     * @param damageDescription damageDescription
     * @param saplingDamageAreaId SaplingDamageAreaId
     */
    createNurseryStockDetails(
        nurseryStock, nurseryStockDetails,
        damagedQuantity, stockDetailId,
        damageDescription, saplingDamageAreaId
    ): void {
        this.nurseryStockDetails = new NurseryStockDetails();
        this.nurseryStockDetails = nurseryStockDetails;
        this.nurseryStockDetails.id = null;
        this.nurseryStockDetails.itStatus = STATUS_ADD;
        this.nurseryStockDetails.date
            = moment(this.nurseryStockDetails.date, DATE_TIME_FORMAT);
        this.nurseryStockDetailsService.create(this.nurseryStockDetails).subscribe(
            data => {
                // alert('Added to Nursery Stock');
                // Get the list of picklist
                this.nurseryStockDetailsService
                .query({
                    filter: {
                        'id.equals': stockDetailId,
                    }
                }).subscribe((result: HttpResponse<INurseryStockDetails[]>) => {
                    result.body[0].itStatus = IT_STATUS_DISTRIBUTED;
                    this.nurseryStockDetails.itStatus = IT_STATUS_DISTRIBUTED;
                    this.nurseryStockDetailsService.update(result.body[0]).subscribe(
                        res => {
                            // console.log('alert', res.body);
                        },
                        (res: HttpErrorResponse) => {
                            alert(res.error.fieldErrors[0].message);
                        }
                    );
                });

                // If the damage quantity is not equal to null
                if (damagedQuantity !== null
                    && damagedQuantity !== undefined
                    && damagedQuantity !== ''
                ) {
                    this.nurseryStockDetails = new NurseryStockDetails;
                    this.nurseryStockDetails = data.body;
                    this.nurseryStockDetails.id = null;
                    this.nurseryStockDetails.itStatus = null;
                    this.nurseryStockDetails.itNurseryId = null;
                    this.nurseryStockDetails.quantity = damagedQuantity;
                    this.nurseryStockDetails.status = STATUS_SAPLING_DAMAGE;
                    this.nurseryStockDetails.description = damageDescription;
                    this.nurseryStockDetails.saplingDamageAreaId = saplingDamageAreaId;
                    this.nurseryStock = nurseryStock;
                    this.nurseryStock.currentQuantity = +this.nurseryStock.currentQuantity - +damagedQuantity;
                    this.nurseryStock.damageQuantity = +this.nurseryStock.damageQuantity + +damagedQuantity;
                    this.subsribeSaveDamageResponse(
                        this.nurseryStockService.update(this.nurseryStock)
                    );
                } else {
                    this.addToStockModel.hide();
                    this.getAddedStockList(this.nurseryStockDetails.itNurseryId);
                    this.success.next('Successfully added.');
                    // To set the null value for the sectors and nursery
                    this.sectors = null;
                    this.nurserys = null;
                }
            },
            (res: HttpErrorResponse) => {
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    /**
     * Response for the created saplings to nursery stock table
     * @param result response
     */
    private subsribeSaveDamageResponse(result: Observable<HttpResponse<INurseryStock>>) {
        result.subscribe(
            (res: HttpResponse<INurseryStock>) => {
                // console.log('damage start:', res.body);
                this.nurseryStockDetails.nurseryStockId = res.body.id;
                this.saveStockDamageDetails();
            },
            (res: HttpErrorResponse) => {
                // alert('Batch Not Saved.');
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    /**
     * To save the damage while receiving the
     * saplings from distributed quantity
     */
    saveStockDamageDetails(): void {
        // Create nursery stock details
        this.nurseryStockDetailsService.create(this.nurseryStockDetails)
        .subscribe(data => {
            this.addToStockModel.hide();
            this.getAddedStockList(this.nurseryStockDetails.itNurseryId);
            this.success.next('Successfully added.');
        }, (
            res: HttpErrorResponse) => {
            this.error.next(res.error.fieldErrors[0].message);
        });
    }

    // Call a service function to get list of stock details
    /** getNurseryStockMovedList(): void {
        // Get the list of stock details from the service
        this.nurseryStockDetailsService.query()
        .subscribe((res: HttpResponse<INurseryStockDetails[]>) => {
            this.stockDetails = res.body;
            // console.log('moved' , this.stockDetails);
        });
    } */

    /**
     * To create the nurery stock using variety and category
     * By using the stock Id saved the stock details
     */
    save(): void {
        if ((this.nurseryStock.nurseryId === null)
            || (this.nurseryStock.nurseryId === undefined)
        ) {
            this.error.next(`Nursery cannot be blank.`);
        } else if ((this.nurseryStock.pickListCategoryId === null)
            || (this.nurseryStock.pickListCategoryId === undefined)
        ) {
            this.error.next(`Category cannot be blank.`);
        } else if ((this.nurseryStockDetails.quantity === null)
            || (this.nurseryStockDetails.quantity === undefined)
        ) {
            this.error.next(`Quantity cannot be blank.`);
        } else if ((this.nurseryStockDetails.date === null)
            || (this.nurseryStockDetails.date === undefined)
        ) {
            this.error.next(`Date cannot be blank.`);
        } else {
            // console.log(this.batch);
            this.nurseryStock.status = STATUS_DIRECT;
            this.nurseryStockDetails.status = STATUS_ADD;
            // this.nurseryStockService
            //     .getNurseryCategoryStock(this.nurseryStock.nurseryId, this.nurseryStock.pickListCategoryId)
            this.nurseryStockService.query({
                filter: {
                     'nurseryId.equals': this.nurseryStock.nurseryId,
                     'pickListCategoryId.equals': this.nurseryStock.pickListCategoryId
                }
            }).subscribe((res: HttpResponse<INurseryStock[]>) => {
                // If the length is greater than 0, To update the old row
                if (res.body.length > 0) {
                    // this.nurseryStock = res.body[res.body.length - 1];
                    this.nurseryStock = res.body[0];
                    this.nurseryStock.currentQuantity = +this.nurseryStock.currentQuantity + +this.nurseryStockDetails.quantity;
                    this.nurseryStock.addedQuantity = +this.nurseryStock.addedQuantity + +this.nurseryStockDetails.quantity;

                    // Update the stock details
                    this.subscribeToSaveResponse(
                        this.nurseryStockService.update(this.nurseryStock),
                        'Successfully updated.'
                    );
                } else {
                    this.nurseryStock.currentQuantity = this.nurseryStockDetails.quantity;
                    this.nurseryStock.addedQuantity = this.nurseryStockDetails.quantity;
                    this.nurseryStock.financialYearNurseryStockId = this.financialYearId;
                    // Create a new row for the Stock
                    this.subscribeToSaveResponse(
                        this.nurseryStockService.create(this.nurseryStock),
                        'Successfully created.'
                    );
                }
            });
        }
    }

    /**
     * To save the saplings details into nursery stock table
     * By using the nursery stock id save Nursery Stock details
     *
     * @param result object of nursery stock details
     * @param alertTitle message title for alert
     */
    private subscribeToSaveResponse(result: Observable<HttpResponse<INurseryStock>>, alertTitle) {
        result.subscribe(
            (res: HttpResponse<INurseryStock>) => {
                this.nurseryStockDetails.nurseryStockId = res.body.id;
                this.saveStockDetails(alertTitle);
            },
            (res: HttpErrorResponse) => {
                // alert('Batch Not Saved.');
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    /**
     * To save the stock details and called the nursery stock list
     * Check if the status as POS, Set the nursery Id into Pos table
     * and call the save POs function
     * @param alertTitle message title for alert
     */
    saveStockDetails(alertTitle): void {
        this.nurseryStockDetails.date = moment(this.nurseryStockDetails.date, DATE_TIME_FORMAT);
        this.nurseryStockDetails.financialYearStockDetailsId = this.financialYearId;
        this.nurseryStockDetailsService.create(this.nurseryStockDetails)
        .subscribe(data => {
                this.success.next(alertTitle);
                // Call a function to get list of nursery Stocks
                this.getNurseryStockList();
                // If the staus as POS,set the nurerys stockId into POS table
                if (this.nurseryStockDetails.status === STATUS_SALE_POS) {
                    // console.log('save function');
                    // console.log(this.nurseryStockDetails.status);
                    this.pointOfSaleDetails.nurseryStockId = this.nurseryStock.id;
                    // Save POs Details
                    this.savePOsDetails();
                }
                // Set the empty row for Nursery Stock and Stock Details
                this.nurseryStock = new NurseryStock();
                this.nurseryStockDetails = new NurseryStockDetails();
                this.isCollapsed = true;
                this.isCollapsedStockDetails = true;
                this.addParticularStock.hide();
                // To set the null value for the sectors and nursery
                this.sectors = null;
                this.nurserys = null;
                this.categories = null;
            },
            (res: HttpErrorResponse) => {
                // alert(res.error.fieldErrors[0].message);
                this.error.next(res.error.fieldErrors[0].message);
            }
        );
    }

    /**
     * To save the Point of Sale Details using the nursery stock Id
     */
    savePOsDetails(): void {
        // console.log('save POs Details function');
        this.pointOfSaleDetails.status = STATUS_ACTIVE;
        this.pointOfSaleDetails.date = moment(this.nurseryStockDetails.date, DATE_TIME_FORMAT);
        // console.log('save');
        // console.log(this.nurseryStockDetails.quantity);
        this.pointOfSaleDetails.quantity = this.nurseryStockDetails.quantity;
        this.pointOfSaleDetails.pickListCategoryId = this.nurseryStock.pickListCategoryId;
        this.pointOfSaleDetails.pickListVarietyId = this.nurseryStock.pickListVarietyId;
        // console.log(this.nurseryStock);
        // console.log(this.pointOfSaleDetails);
        this.pointOfSaleDetailsService.create(this.pointOfSaleDetails)
        .subscribe(data => {
                // console.log(data.body);
                // alert('Successfully updated the stock.');
                this.success.next(`Successfully created POS.`);
                // Call a function to get list of nursery Stocks
                this.getNurseryStockList();

                // After create the point of sale, empty the model object value
                this.pointOfSaleDetails = new PointOfSaleDetails();
                this.isCollapsed = true;
                this.addParticularStock.hide();
                this.stockPOS.hide();
            },
            (res: HttpErrorResponse) => {
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    /**
     * Reset the nursery stock and stock details
     */
    resetForm(): void {
        this.nurseryStock = {};
        this.nurseryStockDetails = {};
    }

    /**
     * Add the stock to Nursery stock details
     * Add the distributed quantity into particular nursery
     * Show the addPrticular Stock Form
     * @param value : nurseryStock
     */
    addMoreStock(value): void {
        // console.log('Add Stock');
        this.nurseryStock = new NurseryStock();
        this.nurseryStock = value;
        this.nurseryStockDetails = new NurseryStockDetails();
        this.nurseryStockDetails.status = STATUS_ADD;
        this.addParticularStock.show();
        this.modalTitle = 'Add Quantity';
    }

    /**
     * To distribute the nursery stock to another nursery
     * Show the addPrticular Stock Form
     * @param value : Nursery Stock
     */
    distributeStock(value): void {
        // If current quantity is 0, throw the error
        if (value.currentQuantity <= 0
            || value.currentQuantity < this.nurseryStockDetails.quantity
        ) {
            // this.error.next(`You cant able to consume. Because Current Quantity is 0.`);
            alert('You cant able to distribute saplings to other nursery. Please check your current quantity.');
        } else {
            this.nurseryStock = new NurseryStock();
            this.nurseryStock = value;
            this.nurseryStockDetails = new NurseryStockDetails();
            this.nurseryStockDetails.status = STATUS_CONSUME;
            // this.nurseryStockDetails.itStatus = STATUS_CONSUME;
            this.nurseryStockDetails.fromNurseryStockDetailsId = value.nurseryId;
            this.addParticularStock.show();
            this.modalTitle = 'Distribute saplings from';
            this.getZonalList();
        }
    }

    /**
     * To save the damage saplings into the nursery
     * Show the addPrticular Stock Form
     * @param value : nursery Stock
     */
    damageStock(value): void {
        if (value.currentQuantity <= 0) {
            // this.error.next(`You cant add to damage. Because Current Quantity is 0.`);
            alert('You cant able to add damage. Please check your current quantity.');
        } else {
            this.nurseryStock = new NurseryStock();
            this.nurseryStock = value;
            this.nurseryStockDetails = new NurseryStockDetails();
            this.nurseryStockDetails.status = STATUS_SAPLING_DAMAGE;
            this.addParticularStock.show();
            this.modalTitle = 'Add to Damage Saplings';
            // To get the damage area and reason
            this.getDamageArea();
            this.getPickDamageReason();
        }
    }

    /**
     * To sale the saplings to some person through POS
     * @param value nursery Stock
     */
    saplingPOS(value): void {
        if (value.currentQuantity <= 0) {
            // this.error.next(`You cant add to damage. Because Current Quantity is 0.`);
            alert('You cant able to sale stock. Please check your current quantity.');
        } else {
            // console.log('stock POS');
            // To create a new row in nursery stock details
            this.nurseryStockDetails = new NurseryStockDetails();
            this.nurseryStock = value;
            this.nurseryStockDetails = new NurseryStockDetails();
            this.nurseryStockDetails.status = STATUS_SALE_POS;
            // console.log(this.nurseryStockDetails.status);
            this.pointOfSaleDetails = new PointOfSaleDetails();
            // Display the POS sale form
            this.stockPOS.show();
        }
    }

    /**
     * Add the saplings to nursery stock, save the saplings
     * @param value nursery stock
     */
    addStockQuantity(value): void {
        if ((this.nurseryStockDetails.date === null)
            || (this.nurseryStockDetails.date === undefined)
        ) {
            this.error.next(`Date cannot be blank.`);
        } else if ((this.nurseryStockDetails.quantity === null)
            || (this.nurseryStockDetails.quantity <= 0)
            || (this.nurseryStockDetails.quantity === undefined)
        ) {
            this.error.next(`You cant able to add.
             Please enter valid quantity.`);
        } else {
            this.nurseryStock.currentQuantity = +value.currentQuantity + +this.nurseryStockDetails.quantity;
            this.nurseryStock.addedQuantity = +value.addedQuantity + +this.nurseryStockDetails.quantity;

            // Update the nursery stock with current and added quantity
            this.subscribeToSaveResponse(
                this.nurseryStockService.update(this.nurseryStock),
                'Successfully added.'
            );
        }
    }

    /**
     * Consume stock quantity to distribute saplings
     * Record the damage stock details
     * @param value nursery stock
     * @param status consume/damage
     */
    consumeStockQuantity(value, status): void {
        if ((status === this.statusConsume)
            && ((this.nurseryStockDetails.itNurseryId === null)
            || (this.nurseryStockDetails.itNurseryId === undefined))
        ) {
            this.error.next(`Nursery cannot be blank.`);
        } else if ((this.nurseryStockDetails.date === null)
            || (this.nurseryStockDetails.date === undefined)
        ) {
            this.error.next(`Date cannot be blank.`);
        } else if (
            (this.nurseryStockDetails.quantity === null) ||
            (this.nurseryStockDetails.quantity <= 0) ||
            (this.nurseryStockDetails.quantity === undefined)
        ) {
            this.error.next(`You cant able to distribute.
             Please enter valid quantity.`);
        } else if (
            (value.currentQuantity) < (this.nurseryStockDetails.quantity)
        ) {
            this.error.next(`You cant able to distribute.
             Because your current quantity is ${value.currentQuantity}.
             Please enter less than current quantity.`);
        } else if ((status === this.statusDamage)
            && (this.nurseryStockDetails.saplingDamageAreaId === null
            || this.nurseryStockDetails.saplingDamageAreaId === undefined)
        ) {
            this.error.next(`Damage area cannot be blank.`);
        } else if ((this.nurseryStockDetails.description === null)
            || (this.nurseryStockDetails.description === undefined)
        ) {
            this.error.next(`Description cannot be blank.`);
        } else if (status === this.statusConsume) {
            // If the status as consume to add the total distributed quantity
            this.nurseryStock.currentQuantity = +value.currentQuantity - +this.nurseryStockDetails.quantity;
            this.nurseryStock.consumedQuantity = +value.consumedQuantity + +this.nurseryStockDetails.quantity;
            // If the status as consume to add the total distributed quantity
            this.subscribeToSaveResponse(
                this.nurseryStockService.update(this.nurseryStock),
                'Successfully distributed.'
            );
        } else {
            this.nurseryStock.currentQuantity = +value.currentQuantity - +this.nurseryStockDetails.quantity;
            this.nurseryStock.damageQuantity = +value.damageQuantity + +this.nurseryStockDetails.quantity;
            this.subscribeToSaveResponse(
                this.nurseryStockService.update(this.nurseryStock),
                'Successfully added to damage.'
            );
        }
    }

    /**
     * Save the saplings through POS
     * @param value nurseryStock
     */
    savePointOfSale(value): void {
        // console.log(value);
        // console.log('Save POs');
        // Save the status as saled(through pos)
        this.nurseryStockDetails.description = this.pointOfSaleDetails.purposeOfTaking;
        this.nurseryStockDetails.status = STATUS_SALE_POS;
        this.nurseryStock.currentQuantity = +value.currentQuantity - +this.nurseryStockDetails.quantity;
        this.nurseryStock.posQuantity = +value.posQuantity + +this.nurseryStockDetails.quantity;
        // return;
        this.subscribeToSaveResponse(
            this.nurseryStockService.update(this.nurseryStock),
            'Successfully saled'
        );
        // Have to create one more field in saleQuantity
    }

    /**
     * If the quanity is not null set the damage status as 2
     * Show the damge area and damage reason in add stock form
     * @param damagedQuantity number
     */
    getDamageDetails(damagedQuantity): void {
        // If the damage quantity is not null
        if (damagedQuantity !== null
            && damagedQuantity !== undefined
            && damagedQuantity !== ''
        ) {
            this.statusDamage = 2;
            // To get the damage area and reason
            this.getDamageArea();
            this.getPickDamageReason();
        } else {
            this.statusDamage = 5;
        }
    }

    /**
     * While cliking the IT tab empty the choosed nursery details
     * Set empty array for particularNurseryDetails and addedNurseryDetails
     */
    emptyItNurseryDetails(): void {
        this.isCollapsedStockDetails = true;
        this.isCollapsed = true;
        // Set the nursery id as null
        this.nurseryStock.nurseryId = null;
        // Set the particularNurseryDetails & addedNurseryDetails as null
        this.particularNurseryDetails = [];
        this.addedNurseryDetails = [];

        // Get the list of nursery of Internal Transportation
        this.nurseryService.query({ filter: {'status.equals': STATUS_ACTIVE }
        }).subscribe((res: HttpResponse<INursery[]>) => {
            // Set the values to the nursery list
            this.nurserysList = res.body;
        });
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
}
