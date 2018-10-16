// Import needed component and dependency
import { Component, OnInit, ViewChild } from '@angular/core';
import { NurseryStockService } from 'app/entities/service/nursery-stock.service';
import { NurseryStockDetailsService } from 'app/entities/service/nursery-stock-details.service';
import { PointOfSaleDetailsService } from 'app/entities/service/point-of-sale-details.service';
import { ZonalService } from 'app/entities/service/zonal.service';
import { SectorService } from 'app/entities/service/sector.service';
import { NurseryService } from 'app/entities/service/nursery.service';
import { PickListService } from 'app/entities/service/pick-list.service';
import { PickListValueService } from 'app/entities/service/pick-list-value.service';
import { INurseryStock, STATUS_DIRECT, NurseryStock } from 'app/shared/model/nursery-stock.model';
import { STATUS_ADD, NurseryStockDetails, INurseryStockDetails,
    STATUS_CONSUME, STATUS_SAPLING_DAMAGE, STATUS_SALE_POS } from 'app/shared/model/nursery-stock-details.model';
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
import { PointOfSaleDetails, IPointOfSaleDetails } from 'app/shared/model/point-of-sale-details.model';

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

    zonals: IZonal[];
    sectors: ISector[];
    receivedSectors: ISector[];
    nurserys: INursery[];
    nurserysList: INursery[];
    receivedNurserys: INursery[];
    pickLists: IPickList[];
    varietys: IPickListValue[];
    categorys: IPickListValue[];
    damageAreaList: IPickListValue[];
    isCollapsed = true;
    isCollapsedStockDetails = true;
    stockDateDp: any;
    stockDetailsDateDp: any;
    financialYearId: number;

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

    constructor(
        private zonalService: ZonalService,
        private sectorService: SectorService,
        private nurseryService: NurseryService,
        private pickListService: PickListService,
        private pickListValueService: PickListValueService,
        private nurseryStockService: NurseryStockService,
        private nurseryStockDetailsService: NurseryStockDetailsService,
        private settingsService: FinancialYearSettingsService,
        private pointOfSaleDetailsService: PointOfSaleDetailsService
    ) {

    }

    ngOnInit() {
        // Call a function to get list of nursery Stocks
        this.getNurseryStockList();
        this.getZonalList();
        this.getPickList();
        this.getPointOfSaleList();
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

        // Get the list of nursery
        this.nurseryService.query({
            filter: {'status.equals': STATUS_ACTIVE}
        }).subscribe((res: HttpResponse<INursery[]>) => {
            // console.log(res.body);
            this.nurserysList = res.body;
        });
    }

    // Get Zonal list from the zonal table
    getZonalList(): void {
        // Get the list of zone
        this.zonalService.query({
            filter: {'status.equals': STATUS_ACTIVE}
        }).subscribe((res: HttpResponse<IZonal[]>) => {
            this.zonals = res.body;
        });
    }

    // Get picklist value from the picklist table
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
    getNurseryStockList(): void {
        // Get the list of godown
        this.nurseryStockService.query().subscribe((res: HttpResponse<INurseryStock[]>) => {
            this.nurseryStocks = res.body;
        });
    }

    // to get the It list based on the nursery
    getParticularList(nurseryId): void {
        // console.log('nurse', nurseryId);
        // Get the Nursery based list
        this.nurseryStockDetailsService.getParticularNurseryList(nurseryId).subscribe((res: HttpResponse<INurseryStockDetails[]>) => {
            this.particularNurseryDetails = res.body;
        });
    }

    addToStock(value: NurseryStockDetails): void {
        this.nurseryStock = value;
        this.nurseryStockDetails.quantity = value.quantity;
        this.nurseryStockDetails.status = STATUS_ADD;
        this.nurseryStock.nurseryId = value.itNurseryId;
        this.nurseryStock.pickListCategoryId = value.stockCategoryId;
        this.nurseryStock.pickListVarietyId = value.stockVarietyId;
        this.nurseryStockService.getNurseryCategoryStock(
            this.nurseryStock.nurseryId, this.nurseryStock.pickListCategoryId
        )
        .subscribe((res: HttpResponse<INurseryStock[]>) => {
            if (res.body.length > 0) {
                this.nurseryStock = res.body[res.body.length - 1];
                this.nurseryStock.currentQuantity = +this.nurseryStock.currentQuantity + +this.nurseryStockDetails.quantity;
                this.nurseryStock.addedQuantity = +this.nurseryStock.addedQuantity + +this.nurseryStockDetails.quantity;
                // console.log("create if", this.godownStockObject);
                this.nurseryStockService.update(this.nurseryStock)
                .subscribe(
                    data => {
                        this.nurseryStockDetails.nurseryStockId = data.body.id;
                        this.createNurseryStockDetails(this.nurseryStockDetails);
                    },
                    (err: HttpErrorResponse) => {
                        alert(err.error.fieldErrors[0].message);
                    }
                );
            } else {
                this.nurseryStock = new NurseryStock();
                this.nurseryStock = value;
                this.nurseryStock.status = STATUS_ACTIVE;
                this.nurseryStockDetails = value;
                this.nurseryStock.nurseryId = value.itNurseryId;
                this.nurseryStock.id = null;
                this.nurseryStockDetails.status = STATUS_ADD;
                this.nurseryStock.pickListVarietyPickListValue = value.stockVariety;
                this.nurseryStock.pickListCategoryPickListValue = value.stockcategory;
                this.nurseryStock.currentQuantity = this.nurseryStockDetails.quantity;
                this.nurseryStock.addedQuantity = this.nurseryStockDetails.quantity;
                // console.log('else', this.nurseryStock);
                this.nurseryStockService.create(this.nurseryStock)
                .subscribe(data => {
                        alert('Added to Nursery Stock');
                        this.nurseryStockDetails.nurseryStockId = data.body.id;
                        this.createNurseryStockDetails(this.nurseryStockDetails);
                        this.getNurseryStockList();
                    },
                    (err: HttpErrorResponse) => {
                        alert(err.error.fieldErrors[0].message);
                    }
                );
            }
        });
    }

    createNurseryStockDetails(nurseryStockDetails): void {
        this.nurseryStockDetails = nurseryStockDetails;
        this.nurseryStockDetails.date = moment(this.nurseryStockDetails.date, DATE_TIME_FORMAT);
        // console.log('nurserydetails', this.nurseryStockDetails);
        this.nurseryStockDetailsService.create(this.nurseryStockDetails)
        .subscribe(data => {
                console.log(data.body);
            },
            (res: HttpErrorResponse) => {
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    // Call a service function to get list of stock details
    getNurseryStockMovedList(): void {
        // Get the list of stock details from the service
        this.nurseryStockDetailsService.query().subscribe((res: HttpResponse<INurseryStockDetails[]>) => {
            this.stockDetails = res.body;
            // console.log('moved' , this.stockDetails);
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

    // Get nursery based on the sector
    getNursery(sectorId): void {
        // Get the list of nursery
        this.nurseryService.getNurserys(sectorId).subscribe((res: HttpResponse<INursery[]>) => {
            // console.log(res.body);
            this.nurserys = res.body;
        });
    }

    // Get variety based on the picklist Id
    getVariety(id): void {
        this.pickListValueService.getVariety(id).subscribe((res: HttpResponse<IPickListValue[]>) => {
            // console.log(res.body);
            this.varietys = res.body;
        });
    }

    // Get variety based on the picklist Id
    getDamageType(id): void {
        this.pickListValueService.getVariety(id).subscribe((res: HttpResponse<IPickListValue[]>) => {
            // console.log(res.body);
            this.damageAreaList = res.body;
        });
    }

    // Receive sector based on the Zonal Id
    getReceivedSector(zonalId): void {
        // Get the list of sector
        this.sectorService.getSectors(zonalId).subscribe((res: HttpResponse<ISector[]>) => {
            // console.log(res.body);
            this.receivedSectors = res.body;
        });
    }

    // Receive Nursery based on the sector Id
    getReceivedNursery(sectorId): void {
        // Get the list of nursery
        this.nurseryService.getNurserys(sectorId).subscribe((res: HttpResponse<INursery[]>) => {
            // console.log(res.body);
            this.receivedNurserys = res.body;
        });
    }

    // Get category based on the picklist value id
    getCategory(id): void {
        this.pickListValueService.getCategory(id).subscribe((res: HttpResponse<IPickListValue[]>) => {
            // console.log(res.body);
            this.categorys = res.body;
        });
    }

    // Create new Batch
    save(): void {
        console.log('save seeds function');
        if (this.nurseryStock.nurseryId === null
            || this.nurseryStock.nurseryId === undefined) {
            this.error.next(`Nursery should not be empty`);
        } else if (this.nurseryStock.pickListCategoryId === null
            || this.nurseryStock.pickListCategoryId === undefined) {
            this.error.next(`Category should not be empty`);
        } else if (this.nurseryStockDetails.date === null
            || this.nurseryStockDetails.date === undefined) {
            this.error.next(`Date should not be empty`);
        } else if (this.nurseryStockDetails.quantity === null
            || this.nurseryStockDetails.quantity === undefined) {
            this.error.next(`Quantity should not be empty`);
        } else {
            // console.log(this.batch);
            this.nurseryStock.status = STATUS_DIRECT;
            this.nurseryStockDetails.status = STATUS_ADD;
            this.nurseryStockService.getNurseryCategoryStock(this.nurseryStock.nurseryId, this.nurseryStock.pickListCategoryId)
            .subscribe((res: HttpResponse<INurseryStock[]>) => {
                // console.log(res.body);
                // console.log(res.body.length);
                if (res.body.length > 0) {
                    this.nurseryStock = res.body[res.body.length - 1];
                    this.nurseryStock.currentQuantity = +this.nurseryStock.currentQuantity + +this.nurseryStockDetails.quantity;
                    this.nurseryStock.addedQuantity = +this.nurseryStock.addedQuantity + +this.nurseryStockDetails.quantity;
                    this.subscribeToSaveResponse(this.nurseryStockService.update(this.nurseryStock));
                } else {
                    this.nurseryStock.currentQuantity = this.nurseryStockDetails.quantity;
                    this.nurseryStock.addedQuantity = this.nurseryStockDetails.quantity;
                    this.nurseryStock.financialYearNurseryStockId = this.financialYearId;
                    this.subscribeToSaveResponse(this.nurseryStockService.create(this.nurseryStock));
                }
            });
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<INurseryStock>>) {
        result.subscribe(
            (res: HttpResponse<INurseryStock>) => {
                this.nurseryStockDetails.nurseryStockId = res.body.id;
                this.saveStockDetails();
            },
            (res: HttpErrorResponse) => {
                // alert('Batch Not Saved.');
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    // Save stock details
    saveStockDetails(): void {
        this.nurseryStockDetails.date = moment(this.nurseryStockDetails.date, DATE_TIME_FORMAT);
        this.nurseryStockDetails.financialYearStockDetailsId = this.financialYearId;
        this.nurseryStockDetailsService.create(this.nurseryStockDetails)
        .subscribe(data => {
                // console.log(data.body);
                // alert('Successfully updated the stock.');
                this.success.next(`Successfully created`);
                // Call a function to get list of nursery Stocks
                this.getNurseryStockList();
                if (this.nurseryStockDetails.status === STATUS_SALE_POS) {
                    console.log('save function');
                    console.log(this.nurseryStockDetails.status);
                    this.pointOfSaleDetails.nurseryStockId = this.nurseryStock.id;
                    // Save POs Details
                    this.savePOsDetails();
                }
                this.nurseryStock = new NurseryStock();
                this.nurseryStockDetails = new NurseryStockDetails();
                this.isCollapsed = true;
                this.addParticularStock.hide();
            },
            (res: HttpErrorResponse) => {
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    // Save stock details
    savePOsDetails(): void {
        console.log('save POs Details function');
        this.pointOfSaleDetails.status = STATUS_ACTIVE;
        this.pointOfSaleDetails.date = moment(this.pointOfSaleDetails.date, DATE_TIME_FORMAT);
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
                this.success.next(`Successfully created`);
                // Call a function to get list of nursery Stocks
                this.getNurseryStockList();

                // After create the point of sale, empty the model object value
                this.pointOfSaleDetails = new PointOfSaleDetails();
                this.isCollapsed = true;
                this.addParticularStock.hide();
            },
            (res: HttpErrorResponse) => {
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    // Reset the nursery stock and stock details
    resetForm(): void {
        this.nurseryStock = {};
        this.nurseryStockDetails = {};
    }

    // Get stock details based on the ID
    getStockDetails(id): void {
        this.nurseryStockDetailsService.getParticularStocks(id)
        .subscribe((res: HttpResponse<IPickListValue[]>) => {
            this.isCollapsedStockDetails = false;
            this.stockDetails = res.body;
        });
    }

    // Add more stock
    addMoreStock(value): void {
        this.nurseryStock = new NurseryStock();
        this.nurseryStock = value;
        this.nurseryStockDetails = new NurseryStockDetails();
        this.nurseryStockDetails.status = STATUS_ADD;
        this.addParticularStock.show();
    }

    // Consume Stock
    consumeStock(value): void {
        this.nurseryStock = new NurseryStock();
        this.nurseryStock = value;
        this.nurseryStockDetails = new NurseryStockDetails();
        this.nurseryStockDetails.status = STATUS_CONSUME;
        this.addParticularStock.show();
    }

    // Damge Stock, If the stock is damge add int damge list
    damageStock(value): void {
        this.nurseryStock = new NurseryStock();
        this.nurseryStock = value;
        this.nurseryStockDetails = new NurseryStockDetails();
        this.nurseryStockDetails.status = STATUS_SAPLING_DAMAGE;
        this.addParticularStock.show();
    }

    // To sale the stock through POS
    saleStock(value): void {
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

    // Add stock Quantity
    addStockQuantity(value): void {
        this.nurseryStock.currentQuantity = +value.currentQuantity + +this.nurseryStockDetails.quantity;
        this.nurseryStock.addedQuantity = +value.addedQuantity + +this.nurseryStockDetails.quantity;
        this.subscribeToSaveResponse(this.nurseryStockService.update(this.nurseryStock));
    }

    // ConsumeStockQuantity fro the current stock of the quantity
    consumeStockQuantity(value): void {
        this.nurseryStock.currentQuantity = +value.currentQuantity - +this.nurseryStockDetails.quantity;
        this.nurseryStock.consumedQuantity = +value.consumedQuantity + +this.nurseryStockDetails.quantity;
        this.subscribeToSaveResponse(this.nurseryStockService.update(this.nurseryStock));
    }

    savePOs(value): void {
        // console.log(value);
        // console.log('Save POs');
        // Save the status as saled(through pos)
        this.nurseryStockDetails.status = STATUS_SALE_POS;
        this.nurseryStock.currentQuantity = +value.currentQuantity - +this.nurseryStockDetails.quantity;
        this.nurseryStock.consumedQuantity = +value.consumedQuantity + +this.nurseryStockDetails.quantity;
        this.subscribeToSaveResponse(this.nurseryStockService.update(this.nurseryStock));
        // Have to create one more field in saleQuantity
    }

    // Call a service function to get list of stocks
    getPointOfSaleList(): void {
        // Get the list of godown
        this.pointOfSaleDetailsService.query()
        .subscribe((res: HttpResponse<IPointOfSaleDetails[]>) => {
            this.pointOfSales = res.body;
            // console.log(this.pointOfSales);
        });
    }
}
