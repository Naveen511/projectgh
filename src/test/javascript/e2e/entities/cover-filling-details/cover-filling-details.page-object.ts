import { element, by, promise, ElementFinder } from 'protractor';

export class CoverFillingDetailsComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-cover-filling-details div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class CoverFillingDetailsUpdatePage {
    pageTitle = element(by.id('jhi-cover-filling-details-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    quantityInput = element(by.id('field_quantity'));
    dateInput = element(by.id('field_date'));
    statusInput = element(by.id('field_status'));
    descriptionInput = element(by.id('field_description'));
    coverFillingSelect = element(by.id('field_coverFilling'));
    damageTypeSelect = element(by.id('field_damageType'));
    coverFillingDamageDescriptionSelect = element(by.id('field_coverFillingDamageDescription'));

    getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    setQuantityInput(quantity): promise.Promise<void> {
        return this.quantityInput.sendKeys(quantity);
    }

    getQuantityInput() {
        return this.quantityInput.getAttribute('value');
    }

    setDateInput(date): promise.Promise<void> {
        return this.dateInput.sendKeys(date);
    }

    getDateInput() {
        return this.dateInput.getAttribute('value');
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

    coverFillingSelectLastOption(): promise.Promise<void> {
        return this.coverFillingSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    coverFillingSelectOption(option): promise.Promise<void> {
        return this.coverFillingSelect.sendKeys(option);
    }

    getCoverFillingSelect(): ElementFinder {
        return this.coverFillingSelect;
    }

    getCoverFillingSelectedOption() {
        return this.coverFillingSelect.element(by.css('option:checked')).getText();
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

    coverFillingDamageDescriptionSelectLastOption(): promise.Promise<void> {
        return this.coverFillingDamageDescriptionSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    coverFillingDamageDescriptionSelectOption(option): promise.Promise<void> {
        return this.coverFillingDamageDescriptionSelect.sendKeys(option);
    }

    getCoverFillingDamageDescriptionSelect(): ElementFinder {
        return this.coverFillingDamageDescriptionSelect;
    }

    getCoverFillingDamageDescriptionSelectedOption() {
        return this.coverFillingDamageDescriptionSelect.element(by.css('option:checked')).getText();
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
