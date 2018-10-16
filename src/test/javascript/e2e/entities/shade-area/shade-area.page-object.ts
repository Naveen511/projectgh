import { element, by, promise, ElementFinder } from 'protractor';

export class ShadeAreaComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-shade-area div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class ShadeAreaUpdatePage {
    pageTitle = element(by.id('jhi-shade-area-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    noOfSeedlingsInput = element(by.id('field_noOfSeedlings'));
    dateInput = element(by.id('field_date'));
    statusInput = element(by.id('field_status'));
    damageInput = element(by.id('field_damage'));
    saplingsInput = element(by.id('field_saplings'));
    roundInput = element(by.id('field_round'));
    batchSelect = element(by.id('field_batch'));
    financialYearShadeAreaSelect = element(by.id('field_financialYearShadeArea'));

    getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    setNoOfSeedlingsInput(noOfSeedlings): promise.Promise<void> {
        return this.noOfSeedlingsInput.sendKeys(noOfSeedlings);
    }

    getNoOfSeedlingsInput() {
        return this.noOfSeedlingsInput.getAttribute('value');
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

    setDamageInput(damage): promise.Promise<void> {
        return this.damageInput.sendKeys(damage);
    }

    getDamageInput() {
        return this.damageInput.getAttribute('value');
    }

    setSaplingsInput(saplings): promise.Promise<void> {
        return this.saplingsInput.sendKeys(saplings);
    }

    getSaplingsInput() {
        return this.saplingsInput.getAttribute('value');
    }

    setRoundInput(round): promise.Promise<void> {
        return this.roundInput.sendKeys(round);
    }

    getRoundInput() {
        return this.roundInput.getAttribute('value');
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

    financialYearShadeAreaSelectLastOption(): promise.Promise<void> {
        return this.financialYearShadeAreaSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    financialYearShadeAreaSelectOption(option): promise.Promise<void> {
        return this.financialYearShadeAreaSelect.sendKeys(option);
    }

    getFinancialYearShadeAreaSelect(): ElementFinder {
        return this.financialYearShadeAreaSelect;
    }

    getFinancialYearShadeAreaSelectedOption() {
        return this.financialYearShadeAreaSelect.element(by.css('option:checked')).getText();
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
