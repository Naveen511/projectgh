import { element, by, promise, ElementFinder } from 'protractor';

export class CommonSettingsComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-common-settings div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class CommonSettingsUpdatePage {
    pageTitle = element(by.id('jhi-common-settings-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    daysToCloseBatchInput = element(by.id('field_daysToCloseBatch'));
    statusInput = element(by.id('field_status'));

    getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    setDaysToCloseBatchInput(daysToCloseBatch): promise.Promise<void> {
        return this.daysToCloseBatchInput.sendKeys(daysToCloseBatch);
    }

    getDaysToCloseBatchInput() {
        return this.daysToCloseBatchInput.getAttribute('value');
    }

    setStatusInput(status): promise.Promise<void> {
        return this.statusInput.sendKeys(status);
    }

    getStatusInput() {
        return this.statusInput.getAttribute('value');
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
