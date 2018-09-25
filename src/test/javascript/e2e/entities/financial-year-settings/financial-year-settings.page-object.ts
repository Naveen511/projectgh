import { element, by, promise, ElementFinder } from 'protractor';

export class FinancialYearSettingsComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-financial-year-settings div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class FinancialYearSettingsUpdatePage {
    pageTitle = element(by.id('jhi-financial-year-settings-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    batchNameInput = element(by.id('field_batchName'));
    startDateInput = element(by.id('field_startDate'));
    endDateInput = element(by.id('field_endDate'));
    statusInput = element(by.id('field_status'));
    financialYearSelect = element(by.id('field_financialYear'));

    getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    setBatchNameInput(batchName): promise.Promise<void> {
        return this.batchNameInput.sendKeys(batchName);
    }

    getBatchNameInput() {
        return this.batchNameInput.getAttribute('value');
    }

    setStartDateInput(startDate): promise.Promise<void> {
        return this.startDateInput.sendKeys(startDate);
    }

    getStartDateInput() {
        return this.startDateInput.getAttribute('value');
    }

    setEndDateInput(endDate): promise.Promise<void> {
        return this.endDateInput.sendKeys(endDate);
    }

    getEndDateInput() {
        return this.endDateInput.getAttribute('value');
    }

    setStatusInput(status): promise.Promise<void> {
        return this.statusInput.sendKeys(status);
    }

    getStatusInput() {
        return this.statusInput.getAttribute('value');
    }

    financialYearSelectLastOption(): promise.Promise<void> {
        return this.financialYearSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    financialYearSelectOption(option): promise.Promise<void> {
        return this.financialYearSelect.sendKeys(option);
    }

    getFinancialYearSelect(): ElementFinder {
        return this.financialYearSelect;
    }

    getFinancialYearSelectedOption() {
        return this.financialYearSelect.element(by.css('option:checked')).getText();
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
