import { element, by, promise, ElementFinder } from 'protractor';

export class NurseryComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-nursery div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class NurseryUpdatePage {
    pageTitle = element(by.id('jhi-nursery-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    nurseryNameInput = element(by.id('field_nurseryName'));
    nurseryAddressInput = element(by.id('field_nurseryAddress'));
    statusInput = element(by.id('field_status'));
    codeInput = element(by.id('field_code'));
    sectorSelect = element(by.id('field_sector'));
    nurseryTypeSelect = element(by.id('field_nurseryType'));
    financialYearNurserySelect = element(by.id('field_financialYearNursery'));

    getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    setNurseryNameInput(nurseryName): promise.Promise<void> {
        return this.nurseryNameInput.sendKeys(nurseryName);
    }

    getNurseryNameInput() {
        return this.nurseryNameInput.getAttribute('value');
    }

    setNurseryAddressInput(nurseryAddress): promise.Promise<void> {
        return this.nurseryAddressInput.sendKeys(nurseryAddress);
    }

    getNurseryAddressInput() {
        return this.nurseryAddressInput.getAttribute('value');
    }

    setStatusInput(status): promise.Promise<void> {
        return this.statusInput.sendKeys(status);
    }

    getStatusInput() {
        return this.statusInput.getAttribute('value');
    }

    setCodeInput(code): promise.Promise<void> {
        return this.codeInput.sendKeys(code);
    }

    getCodeInput() {
        return this.codeInput.getAttribute('value');
    }

    sectorSelectLastOption(): promise.Promise<void> {
        return this.sectorSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    sectorSelectOption(option): promise.Promise<void> {
        return this.sectorSelect.sendKeys(option);
    }

    getSectorSelect(): ElementFinder {
        return this.sectorSelect;
    }

    getSectorSelectedOption() {
        return this.sectorSelect.element(by.css('option:checked')).getText();
    }

    nurseryTypeSelectLastOption(): promise.Promise<void> {
        return this.nurseryTypeSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    nurseryTypeSelectOption(option): promise.Promise<void> {
        return this.nurseryTypeSelect.sendKeys(option);
    }

    getNurseryTypeSelect(): ElementFinder {
        return this.nurseryTypeSelect;
    }

    getNurseryTypeSelectedOption() {
        return this.nurseryTypeSelect.element(by.css('option:checked')).getText();
    }

    financialYearNurserySelectLastOption(): promise.Promise<void> {
        return this.financialYearNurserySelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    financialYearNurserySelectOption(option): promise.Promise<void> {
        return this.financialYearNurserySelect.sendKeys(option);
    }

    getFinancialYearNurserySelect(): ElementFinder {
        return this.financialYearNurserySelect;
    }

    getFinancialYearNurserySelectedOption() {
        return this.financialYearNurserySelect.element(by.css('option:checked')).getText();
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
