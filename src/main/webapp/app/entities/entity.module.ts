import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { ProjectghZonalModule } from './zonal/zonal.module';
import { ProjectghSectorModule } from './sector/sector.module';
import { ProjectghNurseryModule } from './nursery/nursery.module';
import { ProjectghPickListModule } from './pick-list/pick-list.module';
import { ProjectghPickListValueModule } from './pick-list-value/pick-list-value.module';
import { ProjectghBatchModule } from './batch/batch.module';
import { ProjectghDamageModule } from './damage/damage.module';
import { ProjectghShadeAreaModule } from './shade-area/shade-area.module';
import { ProjectghNurseryStockModule } from './nursery-stock/nursery-stock.module';
import { ProjectghNurseryStockDetailsModule } from './nursery-stock-details/nursery-stock-details.module';
import { ProjectghGodownModule } from './godown/godown.module';
import { ProjectghGodownPurchaseDetailsModule } from './godown-purchase-details/godown-purchase-details.module';
import { ProjectghGodownStockModule } from './godown-stock/godown-stock.module';
import { ProjectghGodownStockDetailsModule } from './godown-stock-details/godown-stock-details.module';
import { ProjectghQuantityModule } from './quantity/quantity.module';
import { ProjectghMotherBedModule } from './mother-bed/mother-bed.module';
import { ProjectghNurseryInventoryModule } from './nursery-inventory/nursery-inventory.module';
import { ProjectghNurseryInventoryDetailsModule } from './nursery-inventory-details/nursery-inventory-details.module';
import { ProjectghFinancialYearSettingsModule } from './financial-year-settings/financial-year-settings.module';
import { ProjectghCommonSettingsModule } from './common-settings/common-settings.module';
import { ProjectghPointOfSaleDetailsModule } from './point-of-sale-details/point-of-sale-details.module';
import { ProjectghCoverFillingModule } from './cover-filling/cover-filling.module';
import { ProjectghCoverFillingDetailsModule } from './cover-filling-details/cover-filling-details.module';
import { ProjectghOperationalHeadModule } from './operational-head/operational-head.module';
import { ProjectghMapZonalWithOhModule } from './map-zonal-with-oh/map-zonal-with-oh.module';
import { ProjectghZonalInchargeModule } from './zonal-incharge/zonal-incharge.module';
import { ProjectghMapSectorWithZonalModule } from './map-sector-with-zonal/map-sector-with-zonal.module';
import { ProjectghSectorInchargeModule } from './sector-incharge/sector-incharge.module';
import { ProjectghMapNurseryWithSectorModule } from './map-nursery-with-sector/map-nursery-with-sector.module';
import { ProjectghNurseryInchargeModule } from './nursery-incharge/nursery-incharge.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        ProjectghZonalModule,
        ProjectghSectorModule,
        ProjectghNurseryModule,
        ProjectghPickListModule,
        ProjectghPickListValueModule,
        ProjectghBatchModule,
        ProjectghDamageModule,
        ProjectghShadeAreaModule,
        ProjectghNurseryStockModule,
        ProjectghNurseryStockDetailsModule,
        ProjectghGodownModule,
        ProjectghGodownPurchaseDetailsModule,
        ProjectghGodownStockModule,
        ProjectghGodownStockDetailsModule,
        ProjectghQuantityModule,
        ProjectghMotherBedModule,
        ProjectghNurseryInventoryModule,
        ProjectghNurseryInventoryDetailsModule,
        ProjectghFinancialYearSettingsModule,
        ProjectghCommonSettingsModule,
        ProjectghPointOfSaleDetailsModule,
        ProjectghCoverFillingModule,
        ProjectghCoverFillingDetailsModule,
        ProjectghOperationalHeadModule,
        ProjectghMapZonalWithOhModule,
        ProjectghZonalInchargeModule,
        ProjectghMapSectorWithZonalModule,
        ProjectghSectorInchargeModule,
        ProjectghMapNurseryWithSectorModule,
        ProjectghNurseryInchargeModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ProjectghEntityModule {}
