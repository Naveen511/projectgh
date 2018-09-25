import { element, by, promise, ElementFinder } from 'protractor';

export class GodownStockComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-godown-stock div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class GodownStockUpdatePage {
    pageTitle = element(by.id('jhi-godown-stock-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    currentQuantityInput = element(by.id('field_currentQuantity'));
    addedQuantityInput = element(by.id('field_addedQuantity'));
    consumedQuantityInput = element(by.id('field_consumedQuantity'));
    descriptionInput = element(by.id('field_description'));
    statusInput = element(by.id('field_status'));
    pickListVarietySelect = element(by.id('field_pickListVariety'));
    pickListCategorySelect = element(by.id('field_pickListCategory'));
    pickListQuantityTypeSelect = element(by.id('field_pickListQuantityType'));
    godownSelect = element(by.id('field_godown'));
    financialYearGodownStockSelect = element(by.id('field_financialYearGodownStock'));

    getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    setCurrentQuantityInput(currentQuantity): promise.Promise<void> {
        return this.currentQuantityInput.sendKeys(currentQuantity);
    }

    getCurrentQuantityInput() {
        return this.currentQuantityInput.getAttribute('value');
    }

    setAddedQuantityInput(addedQuantity): promise.Promise<void> {
        return this.addedQuantityInput.sendKeys(addedQuantity);
    }

    getAddedQuantityInput() {
        return this.addedQuantityInput.getAttribute('value');
    }

    setConsumedQuantityInput(consumedQuantity): promise.Promise<void> {
        return this.consumedQuantityInput.sendKeys(consumedQuantity);
    }

    getConsumedQuantityInput() {
        return this.consumedQuantityInput.getAttribute('value');
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

    pickListVarietySelectLastOption(): promise.Promise<void> {
        return this.pickListVarietySelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    pickListVarietySelectOption(option): promise.Promise<void> {
        return this.pickListVarietySelect.sendKeys(option);
    }

    getPickListVarietySelect(): ElementFinder {
        return this.pickListVarietySelect;
    }

    getPickListVarietySelectedOption() {
        return this.pickListVarietySelect.element(by.css('option:checked')).getText();
    }

    pickListCategorySelectLastOption(): promise.Promise<void> {
        return this.pickListCategorySelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    pickListCategorySelectOption(option): promise.Promise<void> {
        return this.pickListCategorySelect.sendKeys(option);
    }

    getPickListCategorySelect(): ElementFinder {
        return this.pickListCategorySelect;
    }

    getPickListCategorySelectedOption() {
        return this.pickListCategorySelect.element(by.css('option:checked')).getText();
    }

    pickListQuantityTypeSelectLastOption(): promise.Promise<void> {
        return this.pickListQuantityTypeSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    pickListQuantityTypeSelectOption(option): promise.Promise<void> {
        return this.pickListQuantityTypeSelect.sendKeys(option);
    }

    getPickListQuantityTypeSelect(): ElementFinder {
        return this.pickListQuantityTypeSelect;
    }

    getPickListQuantityTypeSelectedOption() {
        return this.pickListQuantityTypeSelect.element(by.css('option:checked')).getText();
    }

    godownSelectLastOption(): promise.Promise<void> {
        return this.godownSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    godownSelectOption(option): promise.Promise<void> {
        return this.godownSelect.sendKeys(option);
    }

    getGodownSelect(): ElementFinder {
        return this.godownSelect;
    }

    getGodownSelectedOption() {
        return this.godownSelect.element(by.css('option:checked')).getText();
    }

    financialYearGodownStockSelectLastOption(): promise.Promise<void> {
        return this.financialYearGodownStockSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    financialYearGodownStockSelectOption(option): promise.Promise<void> {
        return this.financialYearGodownStockSelect.sendKeys(option);
    }

    getFinancialYearGodownStockSelect(): ElementFinder {
        return this.financialYearGodownStockSelect;
    }

    getFinancialYearGodownStockSelectedOption() {
        return this.financialYearGodownStockSelect.element(by.css('option:checked')).getText();
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
