import { element, by, promise, ElementFinder } from 'protractor';

export class NurseryInventoryDetailsComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-nursery-inventory-details div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class NurseryInventoryDetailsUpdatePage {
    pageTitle = element(by.id('jhi-nursery-inventory-details-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    dateInput = element(by.id('field_date'));
    quantityInput = element(by.id('field_quantity'));
    statusInput = element(by.id('field_status'));
    descriptionInput = element(by.id('field_description'));
    nurseryInventorySelect = element(by.id('field_nurseryInventory'));
    damageTypeSelect = element(by.id('field_damageType'));
    inventoryDamageDescriptionSelect = element(by.id('field_inventoryDamageDescription'));

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

    setStatusInput(status): promise.Promise<void> {
        return this.statusInput.sendKeys(status);
    }

    getStatusInput() {
        return this.statusInput.getAttribute('value');
    }

    setDescriptionInput(description): promise.Promise<void> {
        return this.descriptionInput.sendKeys(description);
    }

    getDescriptionInput() {
        return this.descriptionInput.getAttribute('value');
    }

    nurseryInventorySelectLastOption(): promise.Promise<void> {
        return this.nurseryInventorySelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    nurseryInventorySelectOption(option): promise.Promise<void> {
        return this.nurseryInventorySelect.sendKeys(option);
    }

    getNurseryInventorySelect(): ElementFinder {
        return this.nurseryInventorySelect;
    }

    getNurseryInventorySelectedOption() {
        return this.nurseryInventorySelect.element(by.css('option:checked')).getText();
    }

    damageTypeSelectLastOption(): promise.Promise<void> {
        return this.damageTypeSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    damageTypeSelectOption(option): promise.Promise<void> {
        return this.damageTypeSelect.sendKeys(option);
    }

    getDamageTypeSelect(): ElementFinder {
        return this.damageTypeSelect;
    }

    getDamageTypeSelectedOption() {
        return this.damageTypeSelect.element(by.css('option:checked')).getText();
    }

    inventoryDamageDescriptionSelectLastOption(): promise.Promise<void> {
        return this.inventoryDamageDescriptionSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    inventoryDamageDescriptionSelectOption(option): promise.Promise<void> {
        return this.inventoryDamageDescriptionSelect.sendKeys(option);
    }

    getInventoryDamageDescriptionSelect(): ElementFinder {
        return this.inventoryDamageDescriptionSelect;
    }

    getInventoryDamageDescriptionSelectedOption() {
        return this.inventoryDamageDescriptionSelect.element(by.css('option:checked')).getText();
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
