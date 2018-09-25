import { element, by, promise, ElementFinder } from 'protractor';

export class SectorInchargeComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-sector-incharge div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class SectorInchargeUpdatePage {
    pageTitle = element(by.id('jhi-sector-incharge-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    fromDateInput = element(by.id('field_fromDate'));
    toDateInput = element(by.id('field_toDate'));
    descriptionInput = element(by.id('field_description'));
    statusInput = element(by.id('field_status'));
    sectorSelect = element(by.id('field_sector'));

    getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    setFromDateInput(fromDate): promise.Promise<void> {
        return this.fromDateInput.sendKeys(fromDate);
    }

    getFromDateInput() {
        return this.fromDateInput.getAttribute('value');
    }

    setToDateInput(toDate): promise.Promise<void> {
        return this.toDateInput.sendKeys(toDate);
    }

    getToDateInput() {
        return this.toDateInput.getAttribute('value');
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

    sectorSelectLastOption(): promise.Promise<void> {
        return this.sectorSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    sectorSelectOption(option): promise.Promise<void> {
        return this.sectorSelect.sendKeys(option);
    }

    getSectorSelect(): ElementFinder {
        return this.sectorSelect;
    }

    getSectorSelectedOption() {
        return this.sectorSelect.element(by.css('option:checked')).getText();
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
