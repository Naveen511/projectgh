import { element, by, promise, ElementFinder } from 'protractor';

export class GodownStockDetailsComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-godown-stock-details div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class GodownStockDetailsUpdatePage {
    pageTitle = element(by.id('jhi-godown-stock-details-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    dateInput = element(by.id('field_date'));
    quantityInput = element(by.id('field_quantity'));
    descriptionInput = element(by.id('field_description'));
    statusInput = element(by.id('field_status'));
    priceInput = element(by.id('field_price'));
    godownStockSelect = element(by.id('field_godownStock'));
    financialYearGodownStockDetailsSelect = element(by.id('field_financialYearGodownStockDetails'));

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

    setPriceInput(price): promise.Promise<void> {
        return this.priceInput.sendKeys(price);
    }

    getPriceInput() {
        return this.priceInput.getAttribute('value');
    }

    godownStockSelectLastOption(): promise.Promise<void> {
        return this.godownStockSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    godownStockSelectOption(option): promise.Promise<void> {
        return this.godownStockSelect.sendKeys(option);
    }

    getGodownStockSelect(): ElementFinder {
        return this.godownStockSelect;
    }

    getGodownStockSelectedOption() {
        return this.godownStockSelect.element(by.css('option:checked')).getText();
    }

    financialYearGodownStockDetailsSelectLastOption(): promise.Promise<void> {
        return this.financialYearGodownStockDetailsSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    financialYearGodownStockDetailsSelectOption(option): promise.Promise<void> {
        return this.financialYearGodownStockDetailsSelect.sendKeys(option);
    }

    getFinancialYearGodownStockDetailsSelect(): ElementFinder {
        return this.financialYearGodownStockDetailsSelect;
    }

    getFinancialYearGodownStockDetailsSelectedOption() {
        return this.financialYearGodownStockDetailsSelect.element(by.css('option:checked')).getText();
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
