// Import needed component and dependency
import { Component, OnInit, ViewChild } from '@angular/core';
import { GodownStockService } from 'app/entities/service/godown-stock.service';
import { IGodownStock, GodownStockModel, STATUS_DIRECT, GodownStock } from 'app/shared/model/godown-stock.model';
import { IGodownStockDetails, GodownStockDetails, STATUS_ADD } from 'app/shared/model/godown-stock-details.model';
import { JhiParseLinks } from 'ng-jhipster';

import { IPickList } from 'app/shared/model/pick-list.model';
import { IPickListValue } from 'app/shared/model/pick-list-value.model';
import { PickListService } from 'app/entities/service/pick-list.service';
import { PickListValueService } from 'app/entities/service/pick-list-value.service';
import { GodownService } from 'app/entities/service/godown.service';
import { IGodown } from 'app/shared/model/godown.model';
import { FinancialYearSettingsService } from 'app/entities/service/financial-year-settings.service';
import { IFinancialYearSettings } from 'app/shared/model/financial-year-settings.model';
import { HttpResponse, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { GodownStockDetailsService } from 'app/entities/service/godown-stock-details.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ITEMS_PER_PAGE, STATUS_ACTIVE } from 'app/shared';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared';
import { Observable } from 'rxjs';

import { ModalDirective } from 'ngx-bootstrap/modal';

// Mention the html, css/sass files
@Component({
    selector: 'jhi-godown-stock',
    templateUrl: 'godown-stock.component.html'
})

export class GodownStockComponent implements OnInit {

    // godownPurchaseObject: GodownPurchaseDetailsModel = new GodownPurchaseDetailsModel();
    godownStock: GodownStockModel = new GodownStockModel();
    godownStockDetail: GodownStockDetails = new GodownStockDetails();

    // create empty array for each service
    // godownPurchases: IGodownPurchaseDetails[];

    // collapsed status
    isCollapsed = true;
    isCollapsedStockDetails = true;

    // create empty array for each service
    godownStocks: IGodownStock[];
    godownStockDetails: IGodownStockDetails[];
    pickLists: IPickList[];
    varietys: IPickListValue[];
    categorys: IPickListValue[];
    quantityTypes: IPickListValue[];
    godown: IGodown[];
    stockDateDp: any;
    addInventoryDateDp: any;
    errorMessage: any;

    financialYearId: number;
    @ViewChild('addParticularStock') public addParticularStock: ModalDirective;

    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;

    // @ViewChild('shadeAreaRecordModal') public shadeAreaRecordModal: ModalDirective;
    constructor( private godownStockService: GodownStockService,
        private godownStockDetailService: GodownStockDetailsService,
        private pickListService: PickListService,
        private pickListValueService: PickListValueService,
        private godownService: GodownService,
        private settingsService: FinancialYearSettingsService,
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
        this.getgodownStockList();
        // to get the pick list
        this.getPickList();

         // Get the list of picklist
         this.godownService.query({
            filter: {'status.equals': STATUS_ACTIVE}
         }).subscribe((res: HttpResponse<IGodown[]>) => {
            this.godown = res.body;
       });
       // Call a function to get active batch id
       this.getActiveRecord();
    }

    // Call a service function to get list of active batch
    getActiveRecord(): void {
        // Get the list of active batch record and assign a 0th index array value to an batch id
        this.settingsService.getActiveRecord().subscribe((res: HttpResponse<IFinancialYearSettings[]>) => {
            this.financialYearId = res.body[0].id;
        });
    }

    getPickList(): void {
        // Get the list of picklist
        this.pickListService.query().subscribe((res: HttpResponse<IPickList[]>) => {
            this.pickLists = res.body;
            // console.log("pick", this.pickLists);
        });
    }

    // Get the variety from the picklist value
    getVariety(id): void {
        this.pickListValueService.getVariety(id).subscribe((res: HttpResponse<IPickListValue[]>) => {
            // console.log("variety", res.body);
            this.varietys = res.body;
        });
    }

    // Get category from the picklist value
    getCategory(id): void {
        this.pickListValueService.getCategory(id)
        .subscribe((res: HttpResponse<IPickListValue[]>) => {
            // console.log("category", res.body);
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

    getgodownStockList(): void {
        this.godownStockService.query({
            page: this.page - 1,
            size: this.itemsPerPage,
            sort: this.sort()
        })
        .subscribe((res: HttpResponse<IGodownStock[]>) => this.paginateGodownStocks(res.body, res.headers));
    }

    getStockDetails(id): void {
        this.godownStockDetailService.getParticularStocks(id)
        .subscribe((res: HttpResponse<IGodownStockDetails[]>) => {
            this.godownStockDetails = res.body;
            this.isCollapsedStockDetails = false;
        });
    }

    addStockDetail(): void {
        this.godownStock.status = STATUS_DIRECT;
        this.godownStockDetail.status = STATUS_ADD;
        this.godownStockService.getGodownCategoryStock(this.godownStock.godownId, this.godownStock.pickListCategoryId)
        .subscribe((res: HttpResponse<IGodownStock[]>) => {
            // console.log(res.body);
            // console.log(res.body.length);
            if (res.body.length > 0) {
                this.godownStock = res.body[res.body.length - 1];
                this.godownStock.currentQuantity = +this.godownStock.currentQuantity + +this.godownStockDetail.quantity;
                this.godownStock.addedQuantity = +this.godownStock.addedQuantity + +this.godownStockDetail.quantity;
                this.subscribeToSaveResponse(this.godownStockService.update(this.godownStock));
            } else {
                this.godownStock.currentQuantity = this.godownStockDetail.quantity;
                this.godownStock.addedQuantity = this.godownStockDetail.quantity;
                this.subscribeToSaveResponse(this.godownStockService.create(this.godownStock));
            }
        });
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IGodownStock>>) {
        result.subscribe(
            (res: HttpResponse<IGodownStock>) => {
                console.log(this.godownStockDetail);
                console.log('After parent save');
                this.godownStockDetail.godownStockId = res.body.id;
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
        this.godownStockDetail.date = moment(this.godownStockDetail.date, DATE_TIME_FORMAT);
        this.godownStockDetailService.create(this.godownStockDetail)
        .subscribe(data => {
                // console.log(data.body);
                // alert('Successfully updated the stock.');
                // this.success.next(`Successfully created`);
                // Call a function to get list of nursery Stocks
                this.getgodownStockList();
                this.godownStock = new GodownStockModel();
                this.godownStockDetail = new GodownStockDetails();
                this.isCollapsed = true;
                this.addParticularStock.hide();
            },
            (res: HttpErrorResponse) => {
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    // Add more stock
    addMoreStock(value): void {
        this.godownStock = new GodownStock();
        this.godownStock = value;
        this.godownStockDetail = new GodownStockDetails();
        this.godownStockDetail.status = STATUS_ADD;
        this.addParticularStock.show();
    }

    // Add the inventory with inventory details
    addInventoryQuantity(value): void {
        this.godownStock.currentQuantity = +value.currentQuantity + +this.godownStockDetail.quantity;
        this.godownStock.addedQuantity = +value.addedQuantity + +this.godownStockDetail.quantity;
        this.subscribeToSaveResponse(this.godownStockService.update(this.godownStock));
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        this.router.navigate(['/godown-stock'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.getgodownStockList();
    }

    clear() {
        this.page = 0;
        this.router.navigate([
            '/godown-stock',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.getgodownStockList();
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    trackId(index: number, item: IGodownStock) {
        return item.id;
    }

    private paginateGodownStocks(data: IGodownStock[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.godownStocks = data;
    }
}
