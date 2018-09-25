import { element, by, promise, ElementFinder } from 'protractor';

export class CoverFillingComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-cover-filling div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class CoverFillingUpdatePage {
    pageTitle = element(by.id('jhi-cover-filling-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    noOfCoverInput = element(by.id('field_noOfCover'));
    dateInput = element(by.id('field_date'));
    descriptionInput = element(by.id('field_description'));
    statusInput = element(by.id('field_status'));
    damageQuantityInput = element(by.id('field_damageQuantity'));

    getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    setNoOfCoverInput(noOfCover): promise.Promise<void> {
        return this.noOfCoverInput.sendKeys(noOfCover);
    }

    getNoOfCoverInput() {
        return this.noOfCoverInput.getAttribute('value');
    }

    setDateInput(date): promise.Promise<void> {
        return this.dateInput.sendKeys(date);
    }

    getDateInput() {
        return this.dateInput.getAttribute('value');
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
