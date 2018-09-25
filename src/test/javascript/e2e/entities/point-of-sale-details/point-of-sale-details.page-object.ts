import { element, by, promise, ElementFinder } from 'protractor';

export class PointOfSaleDetailsComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-point-of-sale-details div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class PointOfSaleDetailsUpdatePage {
    pageTitle = element(by.id('jhi-point-of-sale-details-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    quantityInput = element(by.id('field_quantity'));
    purposeOfTakingInput = element(by.id('field_purposeOfTaking'));
    donorNameInput = element(by.id('field_donorName'));
    donorAddressInput = element(by.id('field_donorAddress'));
    contactNoInput = element(by.id('field_contactNo'));
    discountInput = element(by.id('field_discount'));
    discountAmountInput = element(by.id('field_discountAmount'));
    collectedAmountInput = element(by.id('field_collectedAmount'));
    dateInput = element(by.id('field_date'));
    statusInput = element(by.id('field_status'));
    pickListVarietySelect = element(by.id('field_pickListVariety'));
    pickListCategorySelect = element(by.id('field_pickListCategory'));
    nurseryStockSelect = element(by.id('field_nurseryStock'));

    getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    setQuantityInput(quantity): promise.Promise<void> {
        return this.quantityInput.sendKeys(quantity);
    }

    getQuantityInput() {
        return this.quantityInput.getAttribute('value');
    }

    setPurposeOfTakingInput(purposeOfTaking): promise.Promise<void> {
        return this.purposeOfTakingInput.sendKeys(purposeOfTaking);
    }

    getPurposeOfTakingInput() {
        return this.purposeOfTakingInput.getAttribute('value');
    }

    setDonorNameInput(donorName): promise.Promise<void> {
        return this.donorNameInput.sendKeys(donorName);
    }

    getDonorNameInput() {
        return this.donorNameInput.getAttribute('value');
    }

    setDonorAddressInput(donorAddress): promise.Promise<void> {
        return this.donorAddressInput.sendKeys(donorAddress);
    }

    getDonorAddressInput() {
        return this.donorAddressInput.getAttribute('value');
    }

    setContactNoInput(contactNo): promise.Promise<void> {
        return this.contactNoInput.sendKeys(contactNo);
    }

    getContactNoInput() {
        return this.contactNoInput.getAttribute('value');
    }

    setDiscountInput(discount): promise.Promise<void> {
        return this.discountInput.sendKeys(discount);
    }

    getDiscountInput() {
        return this.discountInput.getAttribute('value');
    }

    setDiscountAmountInput(discountAmount): promise.Promise<void> {
        return this.discountAmountInput.sendKeys(discountAmount);
    }

    getDiscountAmountInput() {
        return this.discountAmountInput.getAttribute('value');
    }

    setCollectedAmountInput(collectedAmount): promise.Promise<void> {
        return this.collectedAmountInput.sendKeys(collectedAmount);
    }

    getCollectedAmountInput() {
        return this.collectedAmountInput.getAttribute('value');
    }

    setDateInput(date): promise.Promise<void> {
        return this.dateInput.sendKeys(date);
    }

    getDateInput() {
        return this.dateInput.getAttribute('value');
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

    nurseryStockSelectLastOption(): promise.Promise<void> {
        return this.nurseryStockSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    nurseryStockSelectOption(option): promise.Promise<void> {
        return this.nurseryStockSelect.sendKeys(option);
    }

    getNurseryStockSelect(): ElementFinder {
        return this.nurseryStockSelect;
    }

    getNurseryStockSelectedOption() {
        return this.nurseryStockSelect.element(by.css('option:checked')).getText();
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
