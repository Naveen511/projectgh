import { element, by, promise, ElementFinder } from 'protractor';

export class GodownComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-godown div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class GodownUpdatePage {
    pageTitle = element(by.id('jhi-godown-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    nameInput = element(by.id('field_name'));
    addressInput = element(by.id('field_address'));
    inchargeInput = element(by.id('field_incharge'));
    statusInput = element(by.id('field_status'));
    financialYearGodownSelect = element(by.id('field_financialYearGodown'));

    getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    setNameInput(name): promise.Promise<void> {
        return this.nameInput.sendKeys(name);
    }

    getNameInput() {
        return this.nameInput.getAttribute('value');
    }

    setAddressInput(address): promise.Promise<void> {
        return this.addressInput.sendKeys(address);
    }

    getAddressInput() {
        return this.addressInput.getAttribute('value');
    }

    setInchargeInput(incharge): promise.Promise<void> {
        return this.inchargeInput.sendKeys(incharge);
    }

    getInchargeInput() {
        return this.inchargeInput.getAttribute('value');
    }

    setStatusInput(status): promise.Promise<void> {
        return this.statusInput.sendKeys(status);
    }

    getStatusInput() {
        return this.statusInput.getAttribute('value');
    }

    financialYearGodownSelectLastOption(): promise.Promise<void> {
        return this.financialYearGodownSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    financialYearGodownSelectOption(option): promise.Promise<void> {
        return this.financialYearGodownSelect.sendKeys(option);
    }

    getFinancialYearGodownSelect(): ElementFinder {
        return this.financialYearGodownSelect;
    }

    getFinancialYearGodownSelectedOption() {
        return this.financialYearGodownSelect.element(by.css('option:checked')).getText();
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
