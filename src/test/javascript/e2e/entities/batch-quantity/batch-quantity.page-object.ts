import { element, by, promise, ElementFinder } from 'protractor';

export class BatchQuantityComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-batch-quantity div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class BatchQuantityUpdatePage {
    pageTitle = element(by.id('jhi-batch-quantity-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    quantityInput = element(by.id('field_quantity'));
    dateInput = element(by.id('field_date'));
    remarksInput = element(by.id('field_remarks'));
    statusInput = element(by.id('field_status'));
    batchSelect = element(by.id('field_batch'));

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

    setRemarksInput(remarks): promise.Promise<void> {
        return this.remarksInput.sendKeys(remarks);
    }

    getRemarksInput() {
        return this.remarksInput.getAttribute('value');
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
