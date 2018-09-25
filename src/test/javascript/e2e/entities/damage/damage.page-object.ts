import { element, by, promise, ElementFinder } from 'protractor';

export class DamageComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-damage div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class DamageUpdatePage {
    pageTitle = element(by.id('jhi-damage-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    noOfQuantityInput = element(by.id('field_noOfQuantity'));
    dateInput = element(by.id('field_date'));
    statusInput = element(by.id('field_status'));
    batchSelect = element(by.id('field_batch'));
    descriptionSelect = element(by.id('field_description'));
    damageAreaSelect = element(by.id('field_damageArea'));
    financialYearDamageSelect = element(by.id('field_financialYearDamage'));

    getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    setNoOfQuantityInput(noOfQuantity): promise.Promise<void> {
        return this.noOfQuantityInput.sendKeys(noOfQuantity);
    }

    getNoOfQuantityInput() {
        return this.noOfQuantityInput.getAttribute('value');
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

    descriptionSelectLastOption(): promise.Promise<void> {
        return this.descriptionSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    descriptionSelectOption(option): promise.Promise<void> {
        return this.descriptionSelect.sendKeys(option);
    }

    getDescriptionSelect(): ElementFinder {
        return this.descriptionSelect;
    }

    getDescriptionSelectedOption() {
        return this.descriptionSelect.element(by.css('option:checked')).getText();
    }

    damageAreaSelectLastOption(): promise.Promise<void> {
        return this.damageAreaSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    damageAreaSelectOption(option): promise.Promise<void> {
        return this.damageAreaSelect.sendKeys(option);
    }

    getDamageAreaSelect(): ElementFinder {
        return this.damageAreaSelect;
    }

    getDamageAreaSelectedOption() {
        return this.damageAreaSelect.element(by.css('option:checked')).getText();
    }

    financialYearDamageSelectLastOption(): promise.Promise<void> {
        return this.financialYearDamageSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    financialYearDamageSelectOption(option): promise.Promise<void> {
        return this.financialYearDamageSelect.sendKeys(option);
    }

    getFinancialYearDamageSelect(): ElementFinder {
        return this.financialYearDamageSelect;
    }

    getFinancialYearDamageSelectedOption() {
        return this.financialYearDamageSelect.element(by.css('option:checked')).getText();
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
