import { element, by, promise, ElementFinder } from 'protractor';

export class SectorComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-sector div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class SectorUpdatePage {
    pageTitle = element(by.id('jhi-sector-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    sectorNameInput = element(by.id('field_sectorName'));
    sectorAddressInput = element(by.id('field_sectorAddress'));
    statusInput = element(by.id('field_status'));
    zonalSelect = element(by.id('field_zonal'));
    financialYearSectorSelect = element(by.id('field_financialYearSector'));

    getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    setSectorNameInput(sectorName): promise.Promise<void> {
        return this.sectorNameInput.sendKeys(sectorName);
    }

    getSectorNameInput() {
        return this.sectorNameInput.getAttribute('value');
    }

    setSectorAddressInput(sectorAddress): promise.Promise<void> {
        return this.sectorAddressInput.sendKeys(sectorAddress);
    }

    getSectorAddressInput() {
        return this.sectorAddressInput.getAttribute('value');
    }

    setStatusInput(status): promise.Promise<void> {
        return this.statusInput.sendKeys(status);
    }

    getStatusInput() {
        return this.statusInput.getAttribute('value');
    }

    zonalSelectLastOption(): promise.Promise<void> {
        return this.zonalSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    zonalSelectOption(option): promise.Promise<void> {
        return this.zonalSelect.sendKeys(option);
    }

    getZonalSelect(): ElementFinder {
        return this.zonalSelect;
    }

    getZonalSelectedOption() {
        return this.zonalSelect.element(by.css('option:checked')).getText();
    }

    financialYearSectorSelectLastOption(): promise.Promise<void> {
        return this.financialYearSectorSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    financialYearSectorSelectOption(option): promise.Promise<void> {
        return this.financialYearSectorSelect.sendKeys(option);
    }

    getFinancialYearSectorSelect(): ElementFinder {
        return this.financialYearSectorSelect;
    }

    getFinancialYearSectorSelectedOption() {
        return this.financialYearSectorSelect.element(by.css('option:checked')).getText();
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
