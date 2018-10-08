import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { IZonal } from 'app/shared/model/zonal.model';
import { ISector } from 'app/shared/model/sector.model';
import { INursery } from 'app/shared/model/nursery.model';
import { IPickList } from 'app/shared/model/pick-list.model';
import { IPickListValue, PickListValue } from 'app/shared/model/pick-list-value.model';
import { IBatch, BatchModel } from 'app/shared/model/batch.model';
import { IShadeArea, ShadeAreaModel, ShadeArea } from 'app/shared/model/shade-area.model';
import { NurseryStockModel, STATUS_FROM_BATCH, INurseryStock } from 'app/shared/model/nursery-stock.model';
import { NurseryStockDetailsModel, STATUS_ADD, NurseryStockDetails } from 'app/shared/model/nursery-stock-details.model';
import { IDamage, DamageModel, STATUS_SEEDS, STATUS_SEEDLING } from 'app/shared/model/damage.model';
import { ZonalService } from 'app/entities/service/zonal.service';
import { SectorService } from 'app/entities/service/sector.service';
import { NurseryService } from 'app/entities/service/nursery.service';
import { BatchService } from 'app/entities/service/batch.service';
import { DamageService } from 'app/entities/service/damage.service';
import { PickListService } from 'app/entities/service/pick-list.service';
import { PickListValueService } from 'app/entities/service/pick-list-value.service';
import { ShadeAreaService } from 'app/entities/service/shade-area.service';
import { NurseryStockService } from 'app/entities/service/nursery-stock.service';
import { NurseryStockDetailsService } from 'app/entities/service/nursery-stock-details.service';

import * as moment from 'moment';
import { DATE_TIME_FORMAT, MONTHS, ITEMS_PER_PAGE, Filter, SOFT_DELETE_STATUS, STATUS_ACTIVE } from 'app/shared';
import { HttpResponse, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IMotherBed, MotherBedModel, STATUS_OCCUPIED, STATUS_AVAILABLE } from 'app/shared/model/mother-bed.model';
import { MotherBedService } from 'app/entities/service/mother-bed.service';
import { FinancialYearSettingsService } from 'app/entities/service/financial-year-settings.service';
import { IFinancialYearSettings } from 'app/shared/model/financial-year-settings.model';

import { JhiParseLinks } from 'ng-jhipster';
import { ActivatedRoute, Router } from '@angular/router';
import { QuantityService } from 'app/entities/service/quantity.service';
import { IQuantity } from 'app/shared/model/quantity.model';

@Component({
    selector: 'jhi-batchmgnt',
    templateUrl: 'batchmgnt.component.html'
})
export class BatchmgntComponent implements OnInit {
    // Create object of each model for create form
    batch: BatchModel = new BatchModel();
    updateBatchValue: BatchModel = new BatchModel();
    damage: DamageModel = new DamageModel();
    shadeArea: ShadeAreaModel = new ShadeAreaModel();
    nurseryStock: NurseryStockModel = new NurseryStockModel();
    nurseryStockDetails: NurseryStockDetailsModel = new NurseryStockDetailsModel();
    motherBedModel: MotherBedModel = new MotherBedModel();
    filter: Filter = new Filter();
    sowingDateDp: any;
    closedDateDp: any;
    dateDamageDp: any;
    dateShadeAreaDp: any;
    dateSeasoningAreaDp: any;
    fromDate: any;
    toDate: any;
    damageCount: any;
    shadeCount: any;
    quantityType: any;
    motherBedValue: any;

    // create empty array for each service
    zonals: IZonal[];
    sectors: ISector[];
    nurserys: INursery[];
    pickLists: IPickList[];
    varietys: IPickListValue[];
    categorys: IPickListValue[];
    quantityTypes: IPickListValue[];
    batchs: IBatch[];
    shadeAreas: IShadeArea[];
    damages: IDamage[];
    motherBeds: IMotherBed[];
    months = MONTHS;
    addStockStatus = STATUS_ADD;
    financialYearId: number;

    isCollapsed = true;

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

    @ViewChild('closeBatchModal') public closeBatchModal: ModalDirective;
    @ViewChild('damageModal') public damageModal: ModalDirective;
    @ViewChild('shiftBatchModal') public shiftBatchModal: ModalDirective;
    @ViewChild('shadeAreaRecordModal') public shadeAreaRecordModal: ModalDirective;
    @ViewChild('damageRecordModal') public damageRecordModal: ModalDirective;
    @ViewChild('stockModal') public stockModal: ModalDirective;

    constructor(
        private zonalService: ZonalService,
        private sectorService: SectorService,
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
        private quantityService: QuantityService,
        private parseLinks: JhiParseLinks,
        private router: Router,
        private activatedRoute: ActivatedRoute
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            // console.log(data);
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
    }

    ngOnInit() {
        // console.log("Inside Batch Component");
        // Get the list of zone
        this.zonalService
            .query({
                filter: { 'status.equals': STATUS_ACTIVE }
            })
            .subscribe((res: HttpResponse<IZonal[]>) => {
                this.zonals = res.body;
            });

        // Get the list of picklist
        this.pickListService
            .query({
                filter: { 'status.equals': STATUS_ACTIVE }
            })
            .subscribe((res: HttpResponse<IPickList[]>) => {
                this.pickLists = res.body;
            });

        // Get the list of batch
        this.getBatchList();

        // Call a function to get active batch id
        this.getActiveRecord();
    }

    // Get the Batch list from the batch table
    getBatchList(): void {
        // Get the list of batch
        this.batchService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe((res: HttpResponse<IBatch[]>) => this.paginateBatchs(res.body, res.headers));
    }

    // Call a service function to get list of active batch
    getActiveRecord(): void {
        // Get the list of active batch record and assign a 0th index array value to an batch id
        this.settingsService.getActiveRecord().subscribe((res: HttpResponse<IFinancialYearSettings[]>) => {
            this.financialYearId = res.body[0].id;
        });
    }

    // Get the shadeAreaList from the shade area table
    getShadeAreaList(): void {
        // console.log('Indide getShadeAreaList');
        // Get the list of shade area
        this.shadeAreaService.query().subscribe((res: HttpResponse<IShadeArea[]>) => {
            this.shadeAreas = res.body;
        });
    }

    // Get the Damage list
    getDamageList(): void {
        // console.log('Indide getDamageList');
        // Get the list of damage
        this.damageService.query().subscribe((res: HttpResponse<IDamage[]>) => {
            this.damages = res.body;
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

    // Get the nursery based on the sector Id
    getNursery(sectorId): void {
        // Get the list of nursery
        this.nurseryService.getNurserys(sectorId).subscribe((res: HttpResponse<INursery[]>) => {
            // console.log(res.body);
            this.nurserys = res.body;
        });
    }

    // Get Motherbed based on the nurseryId
    getMotherBedValue(nurseryId): void {
        // Get the list of motherBed List
        this.motherBedService.getMotherBed(nurseryId).subscribe((res: HttpResponse<IMotherBed[]>) => {
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
    }

    // Get picklist variety based on the pick list ID
    getVariety(id): void {
        this.pickListValueService.getVariety(id).subscribe((res: HttpResponse<IPickListValue[]>) => {
            this.varietys = res.body;
        });
    }

    // Get the quantity type based on the pick list value table id
    getQuantityType(id): void {
        this.pickListValueService.getVariety(id).subscribe((res: HttpResponse<IPickListValue[]>) => {
            // console.log(res.body);
            this.quantityTypes = res.body;
        });
    }

    // Get the picklist category based on the pick list value table id
    getCategory(id): void {
        this.pickListValueService.getCategory(id).subscribe((res: HttpResponse<IPickListValue[]>) => {
            // console.log(res.body);
            this.categorys = res.body;
        });
    }

    // Create new Batch
    save() {
        // console.log('Testing');
        // console.log(this.batch);
        this.batch.status = STATUS_ACTIVE;
        this.batch.sowingDate = moment(this.batch.sowingDate, DATE_TIME_FORMAT);
        this.batch.financialYearBatchId = this.financialYearId;
        this.subscribeToSaveResponse(this.batchService.create(this.batch));
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IBatch>>) {
        result.subscribe(
            (res: HttpResponse<IBatch>) => {
                // console.log(res.body);
                this.motherBedModel.id = this.batch.motherBedId;
                this.motherBedModel.value = this.motherBedValue;
                this.motherBedModel.nurseryId = this.batch.nurseryId;
                this.motherBedModel.status = STATUS_OCCUPIED;
                // console.log('motherbed update', this.motherBedModel);
                this.motherBedService.update(this.motherBedModel).subscribe(
                    data => {
                        // console.log('data ', data.body);
                    },
                    (res: HttpErrorResponse) => {
                        alert(res.error.fieldErrors[0].message);
                    }
                );
                // console.log('after motherbed update', this.motherBedModel);
                this.batch = new BatchModel();
                alert('Batch Created Successfully.');
                this.getBatchList();
                this.isCollapsed = true;
            },
            (res: HttpErrorResponse) => {
                // alert('Batch Not Saved.');
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    // soft delete
    softDeleteBatch(value: BatchModel): void {
        this.batch = value;
        this.batch.status = SOFT_DELETE_STATUS;
        this.batchService.update(this.batch).subscribe(
            data => {
                alert('Successfully Deleted.');
                this.motherBedModel.id = this.batch.motherBedId;
                this.motherBedModel.value = this.batch.motherBedValue;
                this.motherBedModel.nurseryId = this.batch.nurseryId;
                this.motherBedModel.status = STATUS_AVAILABLE;
                // console.log('motherbed update', this.motherBedModel);
                this.motherBedService.update(this.motherBedModel).subscribe(
                    data => {
                        console.log('data ', data.body);
                    },
                    (res: HttpErrorResponse) => {
                        alert(res.error.fieldErrors[0].message);
                    }
                );
                this.getBatchList();
                // this.success.next(`Zone deleted successfully`);
            },
            (res: HttpErrorResponse) => {
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    // expectedQuantity(value: BatchModel): void {
    //     console.log('expec ', value);
    //     this.pickListVarietyId = value.pickListVarietyId;
    //     this.pickListCategoryId = value.pickListCategoryId;
    //     this.quantityService.getExpectedQuantity(this.pickListVarietyId, this.pickListCategoryId).subscribe((res: HttpResponse<IQuantity[]>) => {
    //         this.batch.expectedQuantity = value.quantity * res.body[0].approxQuantity;
    //         console.log(this.batch.expectedQuantity);
    //     });
    // }

    getQuantityTypeValue(event: Event) {
        const selectedText = event.target['options'][event.target['options'].selectedIndex].text;
        this.quantityType = selectedText;
        // console.log(this.quantityType);
    }

    getMotherBedTypeValue(event: Event) {
        const selectedText = event.target['options'][event.target['options'].selectedIndex].text;
        this.motherBedValue = selectedText;
        // console.log('motherbed value', this.motherBedValue);
    }

    // commented for making the delete as soft one which means not to delete from db
    // deleteBatch(batch: BatchModel): void {
    //     this.batchService.delete(batch.id).subscribe(data => {
    //         this.batchs = this.batchs.filter(u => u !== batch);
    //         alert('Successfully Deleted.');
    //     });
    // }

    // show model popup to close the batch
    updateBatch(value: BatchModel): void {
        this.updateBatchValue = new BatchModel();
        this.closeBatchModal.show();
        this.updateBatchValue = value;
    }

    // show model popup to update the damage
    updateDamage(value: BatchModel): void {
        this.updateBatchValue = new BatchModel();
        // console.log(value);
        this.damageModal.show();
        this.updateBatchValue = value;
        this.updateBatchValue.status = STATUS_SEEDS;
    }

    // show model popup to shift the seed to shade area
    updateShift(value: BatchModel): void {
        this.updateBatchValue = new BatchModel();
        // console.log(value);
        this.shiftBatchModal.show();
        this.updateBatchValue = value;
    }

    // show model popup to update the damage in seeldings
    updateSeedlingDamage(value: ShadeAreaModel): void {
        this.shadeArea = value;
        this.updateBatchValue = new BatchModel();
        // console.log(value);
        this.updateBatchValue.id = value.batchId;
        this.updateBatchValue.status = STATUS_SEEDLING;
        this.damageModal.show();
    }

    // show model popup to shift the seed to seasoning area (stock)
    updateStock(value: ShadeAreaModel): void {
        this.shadeArea = value;
        this.nurseryStockDetails = new NurseryStockDetails();
        this.nurseryStockDetails.batchId = value.batchId;
        this.stockModal.show();
    }

    // Close the batch
    closeBatch(batch: BatchModel): void {
        this.batch = batch;
        this.batch.status = -1;
        this.batch.closedDate = moment(this.batch.closedDate, DATE_TIME_FORMAT);
        this.batchService.update(this.batch).subscribe(
            data => {
                alert('Bacth Closed Successfully.');
                this.motherBedModel.id = this.batch.motherBedId;
                this.motherBedModel.value = this.batch.motherBedValue;
                this.motherBedModel.nurseryId = this.batch.nurseryId;
                this.motherBedModel.status = STATUS_AVAILABLE;
                console.log('motherbed update', this.motherBedModel);
                this.motherBedService.update(this.motherBedModel).subscribe(
                    data => {
                        console.log('data ', data.body);
                    },
                    (res: HttpErrorResponse) => {
                        alert(res.error.fieldErrors[0].message);
                    }
                );
            },
            (res: HttpErrorResponse) => {
                alert(res.error.fieldErrors[0].message);
            }
        );
        this.closeBatchModal.hide();
    }

    // Create new damage in created batch
    createDamage(value: DamageModel): void {
        // this.damage = value;
        // console.log(value);
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
                alert('Damage Created Successfully.');
                this.getBatchList();
            },
            (res: HttpErrorResponse) => {
                alert(res.error.fieldErrors[0].message);
            }
        );
        this.damageModal.hide();
    }

    // Create shade area(New Shade area)
    createShadeArea(value: ShadeAreaModel): void {
        // this.shadeArea = value;
        // console.log(value);
        this.shadeArea = new ShadeArea();
        this.shadeArea.batchId = value.id;
        this.shadeArea.noOfSeedlings = value.noOfSeedlings;
        this.shadeArea.date = moment(value.date, DATE_TIME_FORMAT);
        this.shadeArea.financialYearShadeAreaId = this.financialYearId;
        this.shadeArea.status = STATUS_ACTIVE;
        // console.log(this.shadeArea);
        this.shadeAreaService.create(this.shadeArea).subscribe(
            data => {
                alert('Successfully Moved to Shade Area.');
                this.updateBatchValue = value;
                // console.log(this.updateBatchValue.updatedAt);
                // console.log(this.updateBatchValue);
                // If batch round is more then 0 increase the count or set 1 for a variable
                if (this.updateBatchValue.round > 0) {
                    // console.log('>');
                    this.updateBatchValue.round = this.updateBatchValue.round + 1;
                } else {
                    // console.log('<');
                    this.updateBatchValue.round = 1;
                }
                // console.log(this.updateBatchValue);
                // Update the Batch model using service
                this.batchService.update(this.updateBatchValue).subscribe(
                    res => {
                        this.getBatchList();
                    },
                    (res: HttpErrorResponse) => {
                        alert(res.error.fieldErrors[0].message);
                    }
                );
            },
            (res: HttpErrorResponse) => {
                alert(res.error.fieldErrors[0].message);
            }
        );
        this.shiftBatchModal.hide();
    }

    // Create Stock
    createStock(): void {
        this.batch = new BatchModel();
        // console.log(this.nurseryStockDetails);
        this.batchService.find(this.nurseryStockDetails.batchId).subscribe(output => {
            // console.log(res.body);
            this.batch = output.body;
            this.nurseryStock.nurseryId = this.batch.nurseryId;
            this.nurseryStock.pickListVarietyId = this.batch.pickListVarietyId;
            this.nurseryStock.pickListCategoryId = this.batch.pickListCategoryId;
            this.nurseryStockDetails.status = this.addStockStatus;
            // this.nurseryStock.nurseryStockDetails = [this.nurseryStockDetails];
            // console.log(this.nurseryStock);
            this.nurseryStockService
                .getNurseryCategoryStock(this.nurseryStock.nurseryId, this.nurseryStock.pickListCategoryId)
                .subscribe((res: HttpResponse<INurseryStock[]>) => {
                    // console.log(res.body);
                    // console.log(res.body.length);
                    this.nurseryStock.status = STATUS_FROM_BATCH;

                    // If the length is greater than 0, update the old batch records
                    if (res.body.length > 0) {
                        this.nurseryStock = res.body[res.body.length - 1];
                        this.nurseryStock.currentQuantity = +this.nurseryStock.currentQuantity + +this.nurseryStockDetails.quantity;
                        this.nurseryStock.addedQuantity = +this.nurseryStock.addedQuantity + +this.nurseryStockDetails.quantity;
                        this.nurseryStockService.update(this.nurseryStock).subscribe(
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
                        this.nurseryStockService.create(this.nurseryStock).subscribe(
                            data => {
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
    }

    // Create NurseryStockDetails
    createNurseryStockDetails(nurseryStockDetails): void {
        this.nurseryStockDetails = nurseryStockDetails;
        this.nurseryStockDetails.status = STATUS_ADD;
        this.nurseryStockDetails.date = moment(this.nurseryStockDetails.date, DATE_TIME_FORMAT);
        this.nurseryStockDetails.financialYearStockDetailsId = this.financialYearId;
        this.nurseryStockDetailsService.create(this.nurseryStockDetails).subscribe(
            data => {
                // console.log(data.body);
                alert('Successfully Moved To Seasoning Area.');
                this.stockModal.hide();
                this.shadeArea.saplings = +this.shadeArea.saplings + +this.nurseryStockDetails.quantity;
                this.updateShadeAreaQuantity();
            },
            (res: HttpErrorResponse) => {
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    // Get the damage value based on batch id
    getParticularBatchDamage(batchId): void {
        this.damageRecordModal.show();
        // Get the list of damage
        this.damageService.getParticularBatchRecord(batchId).subscribe((res: HttpResponse<IDamage[]>) => {
            this.damages = res.body;
        });
    }

    // Get the shadeArea value based on batch id
    getParticularBatchShadeArea(batchId): void {
        this.shadeAreaRecordModal.show();
        // Get the list of shade area record
        this.shadeAreaService.getParticularBatchRecord(batchId).subscribe((res: HttpResponse<IDamage[]>) => {
            this.shadeAreas = res.body;
        });
    }

    getParticularBatchShadeDamageCount(batchId): void {
        this.shadeAreaService.getParticularBatchShadeCount(batchId).subscribe((res: HttpResponse<String>) => {
            this.shadeCount = res.body;
        });

        this.damageService.getParticularBatchDamageCount(batchId).subscribe((res: HttpResponse<String>) => {
            this.damageCount = res.body;
        });
    }

    // Update the ShadeArea Quantity
    updateShadeAreaQuantity(): void {
        this.shadeAreaService.update(this.shadeArea).subscribe(
            data => {},
            (res: HttpErrorResponse) => {
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    getReport(): void {
        // console.log(this.filter);
        this.batchService
            .getReport(moment(this.filter.fromDate, DATE_TIME_FORMAT), moment(this.filter.toDate, DATE_TIME_FORMAT))
            .subscribe((res: HttpResponse<IBatch[]>) => {
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
        this.router.navigate(['/batch'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.getBatchList();
    }

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

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    trackId(index: number, item: IBatch) {
        return item.id;
    }

    private paginateBatchs(data: IBatch[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.batchs = data;
    }
}
