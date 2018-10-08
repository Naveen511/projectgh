import { element, by, promise, ElementFinder } from 'protractor';

export class ZonalComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-zonal div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class ZonalUpdatePage {
    pageTitle = element(by.id('jhi-zonal-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    zoneNameInput = element(by.id('field_zoneName'));
    statusInput = element(by.id('field_status'));
    financialYearSelect = element(by.id('field_financialYear'));
    operationalHeadSelect = element(by.id('field_operationalHead'));

    getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    setZoneNameInput(zoneName): promise.Promise<void> {
        return this.zoneNameInput.sendKeys(zoneName);
    }

    getZoneNameInput() {
        return this.zoneNameInput.getAttribute('value');
    }

    setStatusInput(status): promise.Promise<void> {
        return this.statusInput.sendKeys(status);
    }

    getStatusInput() {
        return this.statusInput.getAttribute('value');
    }

    financialYearSelectLastOption(): promise.Promise<void> {
        return this.financialYearSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    financialYearSelectOption(option): promise.Promise<void> {
        return this.financialYearSelect.sendKeys(option);
    }

    getFinancialYearSelect(): ElementFinder {
        return this.financialYearSelect;
    }

    getFinancialYearSelectedOption() {
        return this.financialYearSelect.element(by.css('option:checked')).getText();
    }

    operationalHeadSelectLastOption(): promise.Promise<void> {
        return this.operationalHeadSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    operationalHeadSelectOption(option): promise.Promise<void> {
        return this.operationalHeadSelect.sendKeys(option);
    }

    getOperationalHeadSelect(): ElementFinder {
        return this.operationalHeadSelect;
    }

    getOperationalHeadSelectedOption() {
        return this.operationalHeadSelect.element(by.css('option:checked')).getText();
    }

    save(): promise.Promise<void> {
        return this.saveButton.click();
    }

    cancel(): promise.Promise<void> {
        return this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}
