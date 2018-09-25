import { Component, OnInit } from '@angular/core';
import { DamageService } from 'app/entities/service/damage.service';
import { IDamage, STATUS_SEEDS, STATUS_SEEDLING,
    INVENTORY_STATUS_SEEDS, INVENTORY_STATUS_COVER} from 'app/shared/model/damage.model';
import { HttpResponse } from '@angular/common/http';
import { INurseryStockDetails, STATUS_SAPLING_DAMAGE } from 'app/shared/model/nursery-stock-details.model';
import { NurseryStockDetailsService } from 'app/entities/service/nursery-stock-details.service';
import { NurseryInventoryService } from 'app/entities/service/nursery-inventory.service';
import { INurseryInventory} from 'app/shared/model/nursery-inventory.model';
import { NurseryInventoryDetailsService } from 'app/entities/service/nursery-inventory-details.service';
import { IPickListValue } from 'app/shared/model/pick-list-value.model';
import { INurseryInventoryDetails, STATUS_DAMAGE } from 'app/shared/model/nursery-inventory-details.model';

@Component({
    selector: 'jhi-damagemgnt',
    templateUrl: 'damagemgnt.component.html'
})
export class DamagemgntComponent implements OnInit {

    // create empty array for each service
    damageSeeds: IDamage[];
    damageSeedlings: IDamage[];
    damageSaplings: INurseryStockDetails[];
    nurseryInventorys: INurseryInventory[];
    coverInventorys: INurseryInventory[];
    inventoryDetails: INurseryInventoryDetails[];
    varietys: IPickListValue[];
    categorys: IPickListValue[];
    quantityTypes: IPickListValue[];
    inventoryTitle: any;
    isCollapsedInventoryDetails = true;

    constructor(
        private damageService: DamageService,
        private nurseryStockDetailsService: NurseryStockDetailsService,
        private nurseryInventoryService: NurseryInventoryService,
        private nurseryInventoryDetailsService: NurseryInventoryDetailsService
    ) {}

    ngOnInit() {
        this.getSeedDamageList();
        this.getSeedsInventoryList();
        this.getCoverInventoryList();
    }

    // Get the seeds damage list from the damage table, based on the seeds status
    getSeedDamageList(): void {
        // Get the list of seed damage
        this.damageService.queryDamage(STATUS_SEEDS).subscribe((res: HttpResponse<IDamage[]>) => {
            this.damageSeeds = res.body;
        });
    }

    // Get SeedlingDamge List from the damage table, based on the seedsling status
    getSeedlingDamageList(): void {
        // Get the list of seedling damage
        this.damageService.queryDamage(STATUS_SEEDLING).subscribe((res: HttpResponse<IDamage[]>) => {
            this.damageSeedlings = res.body;
        });
    }

    // Get SaplingDamge List from the nursery stock details table, based on the damage status
    getSaplingDamageList(): void {
        // Get the list of sapling damage
        this.nurseryStockDetailsService.queryDamage(STATUS_SAPLING_DAMAGE).subscribe((res: HttpResponse<INurseryStockDetails[]>) => {
            this.damageSaplings = res.body;
        });
    }

    // To get the Seeds inventory List of all the nurseries
    getSeedsInventoryList(): void {
        console.log('getSeedsInventoryList');
        // Get the list of godown
        this.nurseryInventoryService.queryGetSeedsList(INVENTORY_STATUS_SEEDS)
        .subscribe((res: HttpResponse<INurseryInventory[]>) => {
            console.log(res.body);
            this.nurseryInventorys = res.body;
        });
    }

    // To see the inventory details of seeds/covers
    // Particular nursery details of seeds/covers
    getInventoryDetails(id, status): void {
        // If the status is seeds to set the title as seeds
        if (status === INVENTORY_STATUS_SEEDS) {
            this.inventoryTitle = 'Seeds Details';
        } else {
            // If the status is cover to set the status as cover
            this.inventoryTitle = 'Cover Details';
        }
        // Get the particular inventory details
        this.nurseryInventoryDetailsService.getParticularInventoryDamage(id, STATUS_DAMAGE)
            .subscribe((res: HttpResponse<IPickListValue[]>) => {
            // console.log('Inventory Details Damage');
            this.isCollapsedInventoryDetails = false;
            this.inventoryDetails = res.body;
        });
    }

    // To get the Cover inventory Lists of all the nurseries
    getCoverInventoryList(): void {
        console.log('get conver inventory list');
        // Get the list of godown
        this.nurseryInventoryService.queryGetCoverList(INVENTORY_STATUS_COVER)
        .subscribe((res: HttpResponse<INurseryInventory[]>) => {
            console.log(res.body);
            this.coverInventorys = res.body;
        });
    }
}
