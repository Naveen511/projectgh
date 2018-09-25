import { element, by, promise, ElementFinder } from 'protractor';

export class GodownPurchaseDetailsComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-godown-purchase-details div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class GodownPurchaseDetailsUpdatePage {
    pageTitle = element(by.id('jhi-godown-purchase-details-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    quantityInput = element(by.id('field_quantity'));
    dateInput = element(by.id('field_date'));
    priceInput = element(by.id('field_price'));
    ownedByInput = element(by.id('field_ownedBy'));
    vendorNameInput = element(by.id('field_vendorName'));
    vendorAddressInput = element(by.id('field_vendorAddress'));
    vendorPhoneInput = element(by.id('field_vendorPhone'));
    descriptionInput = element(by.id('field_description'));
    statusInput = element(by.id('field_status'));
    pickListVarietySelect = element(by.id('field_pickListVariety'));
    pickListCategorySelect = element(by.id('field_pickListCategory'));
    pickListQuantityTypeSelect = element(by.id('field_pickListQuantityType'));
    godownSelect = element(by.id('field_godown'));
    financialYearGodownPurchaseSelect = element(by.id('field_financialYearGodownPurchase'));

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

    setPriceInput(price): promise.Promise<void> {
        return this.priceInput.sendKeys(price);
    }

    getPriceInput() {
        return this.priceInput.getAttribute('value');
    }

    setOwnedByInput(ownedBy): promise.Promise<void> {
        return this.ownedByInput.sendKeys(ownedBy);
    }

    getOwnedByInput() {
        return this.ownedByInput.getAttribute('value');
    }

    setVendorNameInput(vendorName): promise.Promise<void> {
        return this.vendorNameInput.sendKeys(vendorName);
    }

    getVendorNameInput() {
        return this.vendorNameInput.getAttribute('value');
    }

    setVendorAddressInput(vendorAddress): promise.Promise<void> {
        return this.vendorAddressInput.sendKeys(vendorAddress);
    }

    getVendorAddressInput() {
        return this.vendorAddressInput.getAttribute('value');
    }

    setVendorPhoneInput(vendorPhone): promise.Promise<void> {
        return this.vendorPhoneInput.sendKeys(vendorPhone);
    }

    getVendorPhoneInput() {
        return this.vendorPhoneInput.getAttribute('value');
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

    pickListVarietySelectLastOption(): promise.Promise<void> {
        return this.pickListVarietySelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    pickListVarietySelectOption(option): promise.Promise<void> {
        return this.pickListVarietySelect.sendKeys(option);
    }

    getPickListVarietySelect(): ElementFinder {
        return this.pickListVarietySelect;
    }

    getPickListVarietySelectedOption() {
        return this.pickListVarietySelect.element(by.css('option:checked')).getText();
    }

    pickListCategorySelectLastOption(): promise.Promise<void> {
        return this.pickListCategorySelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    pickListCategorySelectOption(option): promise.Promise<void> {
        return this.pickListCategorySelect.sendKeys(option);
    }

    getPickListCategorySelect(): ElementFinder {
        return this.pickListCategorySelect;
    }

    getPickListCategorySelectedOption() {
        return this.pickListCategorySelect.element(by.css('option:checked')).getText();
    }

    pickListQuantityTypeSelectLastOption(): promise.Promise<void> {
        return this.pickListQuantityTypeSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    pickListQuantityTypeSelectOption(option): promise.Promise<void> {
        return this.pickListQuantityTypeSelect.sendKeys(option);
    }

    getPickListQuantityTypeSelect(): ElementFinder {
        return this.pickListQuantityTypeSelect;
    }

    getPickListQuantityTypeSelectedOption() {
        return this.pickListQuantityTypeSelect.element(by.css('option:checked')).getText();
    }

    godownSelectLastOption(): promise.Promise<void> {
        return this.godownSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    godownSelectOption(option): promise.Promise<void> {
        return this.godownSelect.sendKeys(option);
    }

    getGodownSelect(): ElementFinder {
        return this.godownSelect;
    }

    getGodownSelectedOption() {
        return this.godownSelect.element(by.css('option:checked')).getText();
    }

    financialYearGodownPurchaseSelectLastOption(): promise.Promise<void> {
        return this.financialYearGodownPurchaseSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    financialYearGodownPurchaseSelectOption(option): promise.Promise<void> {
        return this.financialYearGodownPurchaseSelect.sendKeys(option);
    }

    getFinancialYearGodownPurchaseSelect(): ElementFinder {
        return this.financialYearGodownPurchaseSelect;
    }

    getFinancialYearGodownPurchaseSelectedOption() {
        return this.financialYearGodownPurchaseSelect.element(by.css('option:checked')).getText();
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
