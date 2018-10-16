// Import needed component and dependency
import { Component, OnInit } from '@angular/core';
import { GodownPurchaseDetailsService } from 'app/entities/service/godown-purchase-details.service';
import { IGodownPurchaseDetails, GodownPurchaseDetailsModel } from 'app/shared/model/godown-purchase-details.model';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { ViewChild } from '@angular/core';
import { IPickList } from 'app/shared/model/pick-list.model';
import { IPickListValue } from 'app/shared/model/pick-list-value.model';
import { PickListService } from 'app/entities/service/pick-list.service';
import { PickListValueService } from 'app/entities/service/pick-list-value.service';

// Date picker getting from the angular component
import * as moment from 'moment';
import { DATE_TIME_FORMAT, ITEMS_PER_PAGE, SOFT_DELETE_STATUS, STATUS_ACTIVE } from 'app/shared';
import { HttpResponse, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { JhiParseLinks } from 'ng-jhipster';

// Godown stock details files imported
import { GodownStockDetailsModel } from 'app/shared/model/godown-stock-details.model';
import { GodownStockDetailsService } from 'app/entities/service/godown-stock-details.service';
import { IGodownStock, GodownStockModel } from 'app/shared/model/godown-stock.model';
import { GodownService } from 'app/entities/service/godown.service';
import { IGodown } from 'app/shared/model/godown.model';
import { GodownStockService } from 'app/entities/service/godown-stock.service';
import { FinancialYearSettingsService } from 'app/entities/service/financial-year-settings.service';
import { IFinancialYearSettings } from 'app/shared/model/financial-year-settings.model';

// Display the alert message of success and error
import { Subject } from 'rxjs';
import { debounceTime } from 'rxjs/operators';
import { ActivatedRoute, Router } from '@angular/router';

// Mention the html, css/sass files
@Component({
    selector: 'jhi-godown-purchase',
    templateUrl: 'godown-purchase.component.html'
})

/**
 * Class GodownPurchaseComponent used to create/update a godownPurchase, List all godownPurchases.
 * Declared an GodownPurchase object to create and update.
 * Declared an Array variable to set list of godownPurchases.
 * Using a modal popup directive create and update form is displayed.
 */
export class GodownPurchaseComponent implements OnInit {
    // Create object for model
    godownPurchaseObject: GodownPurchaseDetailsModel = new GodownPurchaseDetailsModel();
    godownStockDetailsObject: GodownStockDetailsModel = new GodownStockDetailsModel();
    godownStockObject: GodownStockModel = new GodownStockModel();
    // create empty array for each service
    godownPurchases: IGodownPurchaseDetails[];
    financialYearId: number;

    // Get the picklist calue and category
    pickLists: IPickList[];
    varietys: IPickListValue[];
    purchaseDateDp: any;
    stockDateDp: any;
    alerts: any;

    variety: IPickListValue[];
    categorys: IPickListValue[];
    godown: IGodown[];

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

    // Declare a modal popup
    @ViewChild('godownPurchaseModal') public godownPurchaseModal: ModalDirective;
    @ViewChild('godownStockModal') public godownStockModal: ModalDirective;

    constructor(private godownPurchaseService: GodownPurchaseDetailsService,
        private pickListService: PickListService,
        private pickListValueService: PickListValueService,
        private godownStockService: GodownStockService,
        private godownStockDetailsService: GodownStockDetailsService,
        private godownService: GodownService,
        private settingsService: FinancialYearSettingsService,
        private parseLinks: JhiParseLinks,
        private router: Router,
        private activatedRoute: ActivatedRoute
    ) {
        // this.maxDate.setDate(this.maxDate.getDate() + 7);
        // this.bsRangeValue = [this.bsValue, this.maxDate];
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
        // Get the list of picklist
        this.godownService.query({
            filter: {'status.equals': STATUS_ACTIVE}
        }).subscribe((res: HttpResponse<IGodown[]>) => {
            this.godown = res.body;
        });
        // Call a function to get active batch id
        this.getActiveRecord();
        // console.log(this.godown);
        // Call a function to get list of godownPurchases
        this.getGodownPurchaseList();
        // to get the pick list
        this.getPickList();
        // to get the quantity variety
        // this.getVariety(id);

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

    // Call a service function to get list of godowns
    getGodownPurchaseList(): void {
        // Get the list of godownPurchase
        this.godownPurchaseService.query({
            page: this.page - 1,
            size: this.itemsPerPage,
            sort: this.sort()
        })
        .subscribe((res: HttpResponse<IGodownPurchaseDetails[]>) => this.paginate(res.body, res.headers));
    }

    // Call a service function to get list of active batch
    getActiveRecord(): void {
        // Get the list of active batch record and assign a 0th index array value to an batch id
        this.settingsService.getActiveRecord().subscribe((res: HttpResponse<IFinancialYearSettings[]>) => {
            this.financialYearId = res.body[0].id;
        });
    }

    // Send a godownPurchase object to a service (create or update)
    save() {
        this.godownPurchaseObject.date = moment(this.godownPurchaseObject.date, DATE_TIME_FORMAT);
        this.godownPurchaseObject.status = STATUS_ACTIVE;
        if (this.godownPurchaseObject.id !== undefined) {
            this.alertTitle = 'updated';
            this.subscribeToSaveResponse(
                this.godownPurchaseService.update(this.godownPurchaseObject), this.alertTitle
            );
        } else {
            this.alertTitle = 'created';
            this.godownPurchaseObject.financialYearGodownPurchaseId = this.financialYearId;
            this.subscribeToSaveResponse(
                this.godownPurchaseService.create(this.godownPurchaseObject), this.alertTitle
            );
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IGodownPurchaseDetails>>, alertTitle) {
        result.subscribe(
            (res: HttpResponse<IGodownPurchaseDetails>) => {
                this.godownPurchaseModal.hide();
                this.godownPurchaseObject = new GodownPurchaseDetailsModel();
                // alert('Purchase Details Created/Updated Successfully.');
                this.success.next(`Successfully ${alertTitle}`);
                this.getGodownPurchaseList();
            },
            (res: HttpErrorResponse) => {
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    // show model popup to create godownPurchase value
    createGodownPurchase(): void {
        this.godownPurchaseModal.show();
        this.title = 'Create';
    }

    // show model popup to update godownPurchase value
    getGodownPurchaseValue(value: GodownPurchaseDetailsModel): void {
        // console.log(value);
        this.godownPurchaseModal.show();
        this.godownPurchaseObject = value;
        // this.title = `Update Zone: ${value.zoneName}`;
        this.title = 'Update';
    }

    getPickList(): void {
        // Get the list of picklist
        this.pickListService.query({
            filter: {'status.equals': STATUS_ACTIVE}
        }).subscribe((res: HttpResponse<IPickList[]>) => {
            this.pickLists = res.body;
            // console.log("pick", this.pickLists);
        });
    }

    // Get the variety from the picklist value
    getVariety(id): void {
        this.pickListValueService.getVariety(id).subscribe((res: HttpResponse<IPickListValue[]>) => {
            // console.log("variety", res.body);
            this.variety = res.body;
        });
    }

    // Get the variety from the picklist value
    getVarietys(id): void {
        this.pickListValueService.getVariety(id).subscribe((res: HttpResponse<IPickListValue[]>) => {
            // console.log("variety", res.body);
            this.varietys = res.body;
        });
    }

    // Get category from the picklist value
    getCategory(id): void {
        this.pickListValueService.getCategory(id).subscribe((res: HttpResponse<IPickListValue[]>) => {
            // console.log("category", res.body);
            this.categorys = res.body;
        });
    }

    // soft delete
    softDelete(godownPurchase: GodownPurchaseDetailsModel): void {
        this.godownPurchaseObject = godownPurchase;
        this.godownPurchaseObject.status = SOFT_DELETE_STATUS;
        this.godownPurchaseService.update(this.godownPurchaseObject)
        .subscribe(
            data => {
                this.success.next(`Purchase deleted successfully`);
                this.getGodownPurchaseList();
            },
            (res: HttpErrorResponse) => {
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    // delete godownPurchase value
    // deleteGodownPurchase(godownPurchase: GodownPurchaseDetailsModel): void {
    //     this.godownPurchaseService.delete(godownPurchase.id).subscribe(data => {
    //         // alert('Purchase Details deleted Successfully.');
    //         this.success.next(`Purchase details deleted successfully`);
    //         this.godownPurchases = this.godownPurchases.filter(u => u !== godownPurchase);
    //     });
    // }

    // Show modal to add godown stock
    addStock(value: GodownPurchaseDetailsModel): void {
        this.godownStockModal.show();
        this.godownStockObject.godownId = value.godownId;
        this.godownStockObject.pickListVarietyId = value.pickListVarietyId;
        this.godownStockObject.pickListCategoryId = value.pickListCategoryId;
        this.godownStockDetailsObject.quantity = value.quantity;
        this.godownPurchaseObject = value;
        // this.godownPurchaseObject.status = value.status;
        // console.log("add tostock ", value);
    }

    // Add to stock table from the godown purchase details
    saveStock(): void {
        this.godownStockDetailsObject.status = STATUS_ACTIVE;
        this.godownStockDetailsObject.price = this.godownPurchaseObject.price;
        // this.godownStockDetailsObject.date = moment(this.godownStockDetailsObject.date, DATE_TIME_FORMAT);
        // this.godownStockDetailsObject.updatedAt = moment(this.updatedAt, DATE_TIME_FORMAT);
        // console.log("save stock ", this.godownStockDetailsObject);
        this.godownStockService.getGodownCategoryStock(
            this.godownStockObject.godownId, this.godownStockObject.pickListCategoryId
        )
        .subscribe((res: HttpResponse<IGodownStock[]>) => {
            // console.log('response', res.body);
            if (res.body.length > 0) {
                this.godownStockObject = res.body[res.body.length - 1];
                this.godownStockObject.currentQuantity = +this.godownStockObject.currentQuantity + +this.godownStockDetailsObject.quantity;
                this.godownStockObject.addedQuantity = +this.godownStockObject.addedQuantity + +this.godownStockDetailsObject.quantity;
                // console.log("create if", this.godownStockObject);
                this.godownStockService.update(this.godownStockObject)
                .subscribe(
                    data => {
                        this.godownStockDetailsObject.godownStockId = data.body.id;
                        this.createGodownStockDetails(this.godownStockDetailsObject);
                    },
                    (err: HttpErrorResponse) => {
                        alert(err.error.fieldErrors[0].message);
                    }
                );
            } else {
                this.godownStockObject.currentQuantity = this.godownStockDetailsObject.quantity;
                this.godownStockObject.addedQuantity = this.godownStockDetailsObject.quantity;
                this.godownStockObject.financialYearGodownStockId = this.financialYearId;
                // console.log("else", this.godownStockObject);
                this.godownStockService.create(this.godownStockObject)
                .subscribe(data => {
                        // console.log(data.body);
                        this.godownStockDetailsObject.godownStockId = data.body.id;
                        this.createGodownStockDetails(this.godownStockDetailsObject);
                    },
                    (err: HttpErrorResponse) => {
                        alert(err.error.fieldErrors[0].message);
                    }
                );
            }
        });

        // console.log("save", this.godownStockObject);
        // if (this.godownStockObject.id !== undefined) {
        //     this.subscribeToStockResponse(this.godownStockService.update(this.godownStockObject));
        //     console.log("save !==", this.godownStockObject);
        // } else {
        //     this.godownStockObject.createdAt = moment(this.createdAt, DATE_TIME_FORMAT);
        //     this.subscribeToStockResponse(this.godownStockService.create(this.godownStockObject));
        // }
    }

    createGodownStockDetails(godownStockDetailsObject): void {
        this.godownStockDetailsObject = godownStockDetailsObject;
        this.godownStockDetailsObject.date = moment(this.godownStockDetailsObject.date, DATE_TIME_FORMAT);
        this.godownStockDetailsObject.financialYearGodownStockDetailsId = this.financialYearId;
        // console.log("creategodown", godownStockDetailsObject);
        this.godownStockDetailsService.create(this.godownStockDetailsObject)
        .subscribe(data => {
                console.log(data.body);
                this.godownStockModal.hide();
                alert('Successfully Moved to Stock Area.');
                this.godownPurchaseObject.status = 2;
                console.log('see', this.godownPurchaseObject);
                this.godownPurchaseService.update(this.godownPurchaseObject);
                // .subscribe(data => {
                //     console.log('updated status');
                // },(err: HttpErrorResponse) => {
                //     alert(err.error.fieldErrors[0].message);
                // });
                console.log('status', this.godownPurchaseObject);
            },
            (res: HttpErrorResponse) => {
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        this.router.navigate(['/godown-purchase-details'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.getGodownPurchaseList();
    }

    clear() {
        this.page = 0;
        this.router.navigate([
            '/godown-purchase-details',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.getGodownPurchaseList();
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    trackId(index: number, item: IGodownPurchaseDetails) {
        return item.id;
    }

    private paginate(data: IGodownPurchaseDetails[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.godownPurchases = data;
    }
}
