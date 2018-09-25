import { element, by, promise, ElementFinder } from 'protractor';

export class NurseryInventoryComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-nursery-inventory div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class NurseryInventoryUpdatePage {
    pageTitle = element(by.id('jhi-nursery-inventory-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    currentQuantityInput = element(by.id('field_currentQuantity'));
    addedQuantityInput = element(by.id('field_addedQuantity'));
    consumedQuantityInput = element(by.id('field_consumedQuantity'));
    descriptionInput = element(by.id('field_description'));
    statusInput = element(by.id('field_status'));
    damageQuantityInput = element(by.id('field_damageQuantity'));
    nurserysSelect = element(by.id('field_nurserys'));
    pickListVarietySelect = element(by.id('field_pickListVariety'));
    pickListCategorySelect = element(by.id('field_pickListCategory'));
    quantityTypeSelect = element(by.id('field_quantityType'));

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

    setDamageQuantityInput(damageQuantity): promise.Promise<void> {
        return this.damageQuantityInput.sendKeys(damageQuantity);
    }

    getDamageQuantityInput() {
        return this.damageQuantityInput.getAttribute('value');
    }

    nurserysSelectLastOption(): promise.Promise<void> {
        return this.nurserysSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    nurserysSelectOption(option): promise.Promise<void> {
        return this.nurserysSelect.sendKeys(option);
    }

    getNurserysSelect(): ElementFinder {
        return this.nurserysSelect;
    }

    getNurserysSelectedOption() {
        return this.nurserysSelect.element(by.css('option:checked')).getText();
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

    quantityTypeSelectLastOption(): promise.Promise<void> {
        return this.quantityTypeSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    quantityTypeSelectOption(option): promise.Promise<void> {
        return this.quantityTypeSelect.sendKeys(option);
    }

    getQuantityTypeSelect(): ElementFinder {
        return this.quantityTypeSelect;
    }

    getQuantityTypeSelectedOption() {
        return this.quantityTypeSelect.element(by.css('option:checked')).getText();
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
