import { element, by, promise, ElementFinder } from 'protractor';

export class NurseryStockDetailsComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-nursery-stock-details div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class NurseryStockDetailsUpdatePage {
    pageTitle = element(by.id('jhi-nursery-stock-details-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    dateInput = element(by.id('field_date'));
    quantityInput = element(by.id('field_quantity'));
    descriptionInput = element(by.id('field_description'));
    statusInput = element(by.id('field_status'));
    batchSelect = element(by.id('field_batch'));
    nurseryStockSelect = element(by.id('field_nurseryStock'));
    itNurserySelect = element(by.id('field_itNursery'));
    saplingDamageAreaSelect = element(by.id('field_saplingDamageArea'));
    financialYearStockDetailsSelect = element(by.id('field_financialYearStockDetails'));

    getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    setDateInput(date): promise.Promise<void> {
        return this.dateInput.sendKeys(date);
    }

    getDateInput() {
        return this.dateInput.getAttribute('value');
    }

    setQuantityInput(quantity): promise.Promise<void> {
        return this.quantityInput.sendKeys(quantity);
    }

    getQuantityInput() {
        return this.quantityInput.getAttribute('value');
    }

    setDescriptionInput(description): promise.Promise<void> {
        return this.descriptionInput.sendKeys(description);
    }

    getDescriptionInput() {
        return this.descriptionInput.getAttribute('value');
    }

    setStatusInput(status): promise.Promise<void> {
        return this.statusInput.sendKeys(status);
    }

    getStatusInput() {
        return this.statusInput.getAttribute('value');
    }

    batchSelectLastOption(): promise.Promise<void> {
        return this.batchSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    batchSelectOption(option): promise.Promise<void> {
        return this.batchSelect.sendKeys(option);
    }

    getBatchSelect(): ElementFinder {
        return this.batchSelect;
    }

    getBatchSelectedOption() {
        return this.batchSelect.element(by.css('option:checked')).getText();
    }

    nurseryStockSelectLastOption(): promise.Promise<void> {
        return this.nurseryStockSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    nurseryStockSelectOption(option): promise.Promise<void> {
        return this.nurseryStockSelect.sendKeys(option);
    }

    getNurseryStockSelect(): ElementFinder {
        return this.nurseryStockSelect;
    }

    getNurseryStockSelectedOption() {
        return this.nurseryStockSelect.element(by.css('option:checked')).getText();
    }

    itNurserySelectLastOption(): promise.Promise<void> {
        return this.itNurserySelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    itNurserySelectOption(option): promise.Promise<void> {
        return this.itNurserySelect.sendKeys(option);
    }

    getItNurserySelect(): ElementFinder {
        return this.itNurserySelect;
    }

    getItNurserySelectedOption() {
        return this.itNurserySelect.element(by.css('option:checked')).getText();
    }

    saplingDamageAreaSelectLastOption(): promise.Promise<void> {
        return this.saplingDamageAreaSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    saplingDamageAreaSelectOption(option): promise.Promise<void> {
        return this.saplingDamageAreaSelect.sendKeys(option);
    }

    getSaplingDamageAreaSelect(): ElementFinder {
        return this.saplingDamageAreaSelect;
    }

    getSaplingDamageAreaSelectedOption() {
        return this.saplingDamageAreaSelect.element(by.css('option:checked')).getText();
    }

    financialYearStockDetailsSelectLastOption(): promise.Promise<void> {
        return this.financialYearStockDetailsSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    financialYearStockDetailsSelectOption(option): promise.Promise<void> {
        return this.financialYearStockDetailsSelect.sendKeys(option);
    }

    getFinancialYearStockDetailsSelect(): ElementFinder {
        return this.financialYearStockDetailsSelect;
    }

    getFinancialYearStockDetailsSelectedOption() {
        return this.financialYearStockDetailsSelect.element(by.css('option:checked')).getText();
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
