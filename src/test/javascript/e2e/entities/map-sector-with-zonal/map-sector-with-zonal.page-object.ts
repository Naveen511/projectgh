import { element, by, promise, ElementFinder } from 'protractor';

export class MapSectorWithZonalComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-map-sector-with-zonal div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class MapSectorWithZonalUpdatePage {
    pageTitle = element(by.id('jhi-map-sector-with-zonal-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    fromDateInput = element(by.id('field_fromDate'));
    toDateInput = element(by.id('field_toDate'));
    descriptionInput = element(by.id('field_description'));
    statusInput = element(by.id('field_status'));
    zonalSelect = element(by.id('field_zonal'));
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

    zonalSelectLastOption(): promise.Promise<void> {
        return this.zonalSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    zonalSelectOption(option): promise.Promise<void> {
        return this.zonalSelect.sendKeys(option);
    }

    getZonalSelect(): ElementFinder {
        return this.zonalSelect;
    }

    getZonalSelectedOption() {
        return this.zonalSelect.element(by.css('option:checked')).getText();
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
