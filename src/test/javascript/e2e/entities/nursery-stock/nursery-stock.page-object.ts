import { element, by, promise, ElementFinder } from 'protractor';

export class NurseryStockComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-nursery-stock div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class NurseryStockUpdatePage {
    pageTitle = element(by.id('jhi-nursery-stock-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    currentQuantityInput = element(by.id('field_currentQuantity'));
    addedQuantityInput = element(by.id('field_addedQuantity'));
    consumedQuantityInput = element(by.id('field_consumedQuantity'));
    descriptionInput = element(by.id('field_description'));
    statusInput = element(by.id('field_status'));
    posQuantityInput = element(by.id('field_posQuantity'));
    nurserySelect = element(by.id('field_nursery'));
    pickListVarietySelect = element(by.id('field_pickListVariety'));
    pickListCategorySelect = element(by.id('field_pickListCategory'));
    financialYearNurseryStockSelect = element(by.id('field_financialYearNurseryStock'));

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

    setPosQuantityInput(posQuantity): promise.Promise<void> {
        return this.posQuantityInput.sendKeys(posQuantity);
    }

    getPosQuantityInput() {
        return this.posQuantityInput.getAttribute('value');
    }

    nurserySelectLastOption(): promise.Promise<void> {
        return this.nurserySelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    nurserySelectOption(option): promise.Promise<void> {
        return this.nurserySelect.sendKeys(option);
    }

    getNurserySelect(): ElementFinder {
        return this.nurserySelect;
    }

    getNurserySelectedOption() {
        return this.nurserySelect.element(by.css('option:checked')).getText();
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

    financialYearNurseryStockSelectLastOption(): promise.Promise<void> {
        return this.financialYearNurseryStockSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    financialYearNurseryStockSelectOption(option): promise.Promise<void> {
        return this.financialYearNurseryStockSelect.sendKeys(option);
    }

    getFinancialYearNurseryStockSelect(): ElementFinder {
        return this.financialYearNurseryStockSelect;
    }

    getFinancialYearNurseryStockSelectedOption() {
        return this.financialYearNurseryStockSelect.element(by.css('option:checked')).getText();
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
