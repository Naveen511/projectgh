// Angular
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { ProjectghSharedModule } from 'app/shared';

import { DamagemgntRoutingModule } from 'app/entities/damage-management/damage-management.routing.module';

// Module Component
import { TabsModule } from 'ngx-bootstrap/tabs';
import { DamageService } from 'app/entities/service/damage.service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { DamagemgntComponent } from 'app/entities/damage-management/damagemgnt.component';
import { CollapseModule } from 'ngx-bootstrap/collapse';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        TabsModule.forRoot(),
        DamagemgntRoutingModule,
        FontAwesomeModule,
        NgbModule.forRoot(),
        CollapseModule.forRoot(),
        ProjectghSharedModule
    ],
    declarations: [DamagemgntComponent],
    providers: [DamageService],
    exports: [NgbModule],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DamagemgntModule {}
