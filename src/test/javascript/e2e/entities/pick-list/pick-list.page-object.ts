import { element, by, promise, ElementFinder } from 'protractor';

export class PickListComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-pick-list div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class PickListUpdatePage {
    pageTitle = element(by.id('jhi-pick-list-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    pickListNameInput = element(by.id('field_pickListName'));
    statusInput = element(by.id('field_status'));
    displayLabelNameInput = element(by.id('field_displayLabelName'));

    getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    setPickListNameInput(pickListName): promise.Promise<void> {
        return this.pickListNameInput.sendKeys(pickListName);
    }

    getPickListNameInput() {
        return this.pickListNameInput.getAttribute('value');
    }

    setStatusInput(status): promise.Promise<void> {
        return this.statusInput.sendKeys(status);
    }

    getStatusInput() {
        return this.statusInput.getAttribute('value');
    }

    setDisplayLabelNameInput(displayLabelName): promise.Promise<void> {
        return this.displayLabelNameInput.sendKeys(displayLabelName);
    }

    getDisplayLabelNameInput() {
        return this.displayLabelNameInput.getAttribute('value');
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
