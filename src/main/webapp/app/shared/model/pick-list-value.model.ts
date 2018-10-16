import { Moment } from 'moment';
import { IPickListValue } from 'app/shared/model//pick-list-value.model';
import { IBatch } from 'app/shared/model//batch.model';
import { INurseryStock } from 'app/shared/model//nursery-stock.model';
import { IGodownPurchaseDetails } from 'app/shared/model//godown-purchase-details.model';
import { IGodownStock } from 'app/shared/model//godown-stock.model';
import { INursery } from 'app/shared/model//nursery.model';
import { INurseryInventory } from 'app/shared/model//nursery-inventory.model';
import { INurseryInventoryDetails } from 'app/shared/model//nursery-inventory-details.model';
import { IDamage } from 'app/shared/model//damage.model';
import { INurseryStockDetails } from 'app/shared/model//nursery-stock-details.model';
import { IFinancialYearSettings } from 'app/shared/model//financial-year-settings.model';
import { IQuantity } from 'app/shared/model//quantity.model';
import { IPointOfSaleDetails } from 'app/shared/model//point-of-sale-details.model';
import { ICoverFillingDetails } from 'app/shared/model//cover-filling-details.model';

export interface IPickListValue {
    id?: number;
    pickListValue?: string;
    status?: number;
    selfIds?: IPickListValue[];
    varietys?: IBatch[];
    categorys?: IBatch[];
    nurseryStockVarietys?: INurseryStock[];
    nurseryStockCategorys?: INurseryStock[];
    godownPurchaseVarietys?: IGodownPurchaseDetails[];
    godownPurchaseCategorys?: IGodownPurchaseDetails[];
    godownPurchaseQuantityTypes?: IGodownPurchaseDetails[];
    godownStockVarietys?: IGodownStock[];
    godownStockCategorys?: IGodownStock[];
    godownStockQuantityTypes?: IGodownStock[];
    pickListPickListName?: string;
    pickListId?: number;
    pickValuePickListValue?: string;
    pickValueId?: number;
    nurserys?: INursery[];
    batchQuantityTypes?: IBatch[];
    nurseryInventoryVarietys?: INurseryInventory[];
    nurseryInventoryCategorys?: INurseryInventory[];
    nurseryInventoryQuantityTypes?: INurseryInventory[];
    nurseryInventoryDamageTypes?: INurseryInventoryDetails[];
    pickListValueDamageAreas?: IDamage[];
    nurseryStockDamageAreas?: INurseryStockDetails[];
    financialYearNames?: IFinancialYearSettings[];
    damageDescriptions?: IDamage[];
    quantitysVarieties?: IQuantity[];
    quantitysCategories?: IQuantity[];
    pointOfSaleVarietys?: IPointOfSaleDetails[];
    pointOfSaleCategorys?: IPointOfSaleDetails[];
    coverFillingDetails?: ICoverFillingDetails[];
    nurseryInventoryDamageDescs?: INurseryInventoryDetails[];
    coverFillingDamageDescs?: ICoverFillingDetails[];
}

export class PickListValue implements IPickListValue {
    constructor(
        public id?: number,
        public pickListValue?: string,
        public status?: number,
        public selfIds?: IPickListValue[],
        public varietys?: IBatch[],
        public categorys?: IBatch[],
        public nurseryStockVarietys?: INurseryStock[],
        public nurseryStockCategorys?: INurseryStock[],
        public godownPurchaseVarietys?: IGodownPurchaseDetails[],
        public godownPurchaseCategorys?: IGodownPurchaseDetails[],
        public godownPurchaseQuantityTypes?: IGodownPurchaseDetails[],
        public godownStockVarietys?: IGodownStock[],
        public godownStockCategorys?: IGodownStock[],
        public godownStockQuantityTypes?: IGodownStock[],
        public pickListPickListName?: string,
        public pickListId?: number,
        public pickValuePickListValue?: string,
        public pickValueId?: number,
        public nurserys?: INursery[],
        public batchQuantityTypes?: IBatch[],
        public nurseryInventoryVarietys?: INurseryInventory[],
        public nurseryInventoryCategorys?: INurseryInventory[],
        public nurseryInventoryQuantityTypes?: INurseryInventory[],
        public nurseryInventoryDamageTypes?: INurseryInventoryDetails[],
        public pickListValueDamageAreas?: IDamage[],
        public nurseryStockDamageAreas?: INurseryStockDetails[],
        public financialYearNames?: IFinancialYearSettings[],
        public damageDescriptions?: IDamage[],
        public quantitysVarieties?: IQuantity[],
        public quantitysCategories?: IQuantity[],
        public pointOfSaleVarietys?: IPointOfSaleDetails[],
        public pointOfSaleCategorys?: IPointOfSaleDetails[],
        public coverFillingDetails?: ICoverFillingDetails[],
        public nurseryInventoryDamageDescs?: INurseryInventoryDetails[],
        public coverFillingDamageDescs?: ICoverFillingDetails[]

    ) {}
}

export class PickListValueModel {
    id?: number;
    pickListValue?: string;
    status?: number;
    selfIds?: IPickListValue[];
    varietys?: IBatch[];
    categorys?: IBatch[];
    pickListPickListName?: string;
    nurseryStockVarietys?: INurseryStock[];
    nurseryStockCategorys?: INurseryStock[];
    godownPurchaseVarietys?: IGodownPurchaseDetails[];
    godownPurchaseCategorys?: IGodownPurchaseDetails[];
    godownPurchaseQuantityTypes?: IGodownPurchaseDetails[];
    godownStockVarietys?: IGodownStock[];
    godownStockCategorys?: IGodownStock[];
    godownStockQuantityTypes?: IGodownStock[];
    pickListId?: number;
    pickValuePickListValue?: string;
    pickValueId?: number;
    nurserys?: INursery[];
    batchQuantityTypes?: IBatch[];
    pickListValueDamageAreas?: IDamage[];
    subChildValue?: string;
    nurseryInventoryVarietys?: INurseryInventory[];
    nurseryInventoryCategorys?: INurseryInventory[];
    nurseryInventoryQuantityTypes?: INurseryInventory[];
    nurseryInventoryDamageTypes?: INurseryInventoryDetails[];
    nurseryStockDamageAreas?: INurseryStockDetails[];
    financialYearNames?: IFinancialYearSettings[];
    damageDescriptions?: IDamage[];
    quantitysVarieties?: IQuantity[];
    quantitysCategories?: IQuantity[];
    pointOfSaleVarietys?: IPointOfSaleDetails[];
    pointOfSaleCategorys?: IPointOfSaleDetails[];
    coverFillingDetails?: ICoverFillingDetails[];
    nurseryInventoryDamageDescs?: INurseryInventoryDetails[];
    coverFillingDamageDescs?: ICoverFillingDetails[];
}
