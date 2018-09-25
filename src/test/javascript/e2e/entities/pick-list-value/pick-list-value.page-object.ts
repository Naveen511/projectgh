import { element, by, promise, ElementFinder } from 'protractor';

export class PickListValueComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-pick-list-value div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class PickListValueUpdatePage {
    pageTitle = element(by.id('jhi-pick-list-value-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    pickListValueInput = element(by.id('field_pickListValue'));
    statusInput = element(by.id('field_status'));
    pickListSelect = element(by.id('field_pickList'));
    pickValueSelect = element(by.id('field_pickValue'));

    getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    setPickListValueInput(pickListValue): promise.Promise<void> {
        return this.pickListValueInput.sendKeys(pickListValue);
    }

    getPickListValueInput() {
        return this.pickListValueInput.getAttribute('value');
    }

    setStatusInput(status): promise.Promise<void> {
        return this.statusInput.sendKeys(status);
    }

    getStatusInput() {
        return this.statusInput.getAttribute('value');
    }

    pickListSelectLastOption(): promise.Promise<void> {
        return this.pickListSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    pickListSelectOption(option): promise.Promise<void> {
        return this.pickListSelect.sendKeys(option);
    }

    getPickListSelect(): ElementFinder {
        return this.pickListSelect;
    }

    getPickListSelectedOption() {
        return this.pickListSelect.element(by.css('option:checked')).getText();
    }

    pickValueSelectLastOption(): promise.Promise<void> {
        return this.pickValueSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    pickValueSelectOption(option): promise.Promise<void> {
        return this.pickValueSelect.sendKeys(option);
    }

    getPickValueSelect(): ElementFinder {
        return this.pickValueSelect;
    }

    getPickValueSelectedOption() {
        return this.pickValueSelect.element(by.css('option:checked')).getText();
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
