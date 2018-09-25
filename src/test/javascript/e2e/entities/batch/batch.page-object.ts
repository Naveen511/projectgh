import { element, by, promise, ElementFinder } from 'protractor';

export class BatchComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-batch div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class BatchUpdatePage {
    pageTitle = element(by.id('jhi-batch-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    batchNoInput = element(by.id('field_batchNo'));
    batchNameInput = element(by.id('field_batchName'));
    quantityInput = element(by.id('field_quantity'));
    showingTypeInput = element(by.id('field_showingType'));
    sowingDateInput = element(by.id('field_sowingDate'));
    closedDateInput = element(by.id('field_closedDate'));
    roundInput = element(by.id('field_round'));
    remarksInput = element(by.id('field_remarks'));
    statusInput = element(by.id('field_status'));
    nurserySelect = element(by.id('field_nursery'));
    pickListVarietySelect = element(by.id('field_pickListVariety'));
    pickListCategorySelect = element(by.id('field_pickListCategory'));
    quantityTypeSelect = element(by.id('field_quantityType'));
    motherBedSelect = element(by.id('field_motherBed'));
    financialYearBatchSelect = element(by.id('field_financialYearBatch'));

    getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    setBatchNoInput(batchNo): promise.Promise<void> {
        return this.batchNoInput.sendKeys(batchNo);
    }

    getBatchNoInput() {
        return this.batchNoInput.getAttribute('value');
    }

    setBatchNameInput(batchName): promise.Promise<void> {
        return this.batchNameInput.sendKeys(batchName);
    }

    getBatchNameInput() {
        return this.batchNameInput.getAttribute('value');
    }

    setQuantityInput(quantity): promise.Promise<void> {
        return this.quantityInput.sendKeys(quantity);
    }

    getQuantityInput() {
        return this.quantityInput.getAttribute('value');
    }

    setShowingTypeInput(showingType): promise.Promise<void> {
        return this.showingTypeInput.sendKeys(showingType);
    }

    getShowingTypeInput() {
        return this.showingTypeInput.getAttribute('value');
    }

    setSowingDateInput(sowingDate): promise.Promise<void> {
        return this.sowingDateInput.sendKeys(sowingDate);
    }

    getSowingDateInput() {
        return this.sowingDateInput.getAttribute('value');
    }

    setClosedDateInput(closedDate): promise.Promise<void> {
        return this.closedDateInput.sendKeys(closedDate);
    }

    getClosedDateInput() {
        return this.closedDateInput.getAttribute('value');
    }

    setRoundInput(round): promise.Promise<void> {
        return this.roundInput.sendKeys(round);
    }

    getRoundInput() {
        return this.roundInput.getAttribute('value');
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

    nurserySelectLastOption(): promise.Promise<void> {
        return this.nurserySelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    nurserySelectOption(option): promise.Promise<void> {
        return this.nurserySelect.sendKeys(option);
    }

    getNurserySelect(): ElementFinder {
        return this.nurserySelect;
    }

    getNurserySelectedOption() {
        return this.nurserySelect.element(by.css('option:checked')).getText();
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

    quantityTypeSelectLastOption(): promise.Promise<void> {
        return this.quantityTypeSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    quantityTypeSelectOption(option): promise.Promise<void> {
        return this.quantityTypeSelect.sendKeys(option);
    }

    getQuantityTypeSelect(): ElementFinder {
        return this.quantityTypeSelect;
    }

    getQuantityTypeSelectedOption() {
        return this.quantityTypeSelect.element(by.css('option:checked')).getText();
    }

    motherBedSelectLastOption(): promise.Promise<void> {
        return this.motherBedSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    motherBedSelectOption(option): promise.Promise<void> {
        return this.motherBedSelect.sendKeys(option);
    }

    getMotherBedSelect(): ElementFinder {
        return this.motherBedSelect;
    }

    getMotherBedSelectedOption() {
        return this.motherBedSelect.element(by.css('option:checked')).getText();
    }

    financialYearBatchSelectLastOption(): promise.Promise<void> {
        return this.financialYearBatchSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    financialYearBatchSelectOption(option): promise.Promise<void> {
        return this.financialYearBatchSelect.sendKeys(option);
    }

    getFinancialYearBatchSelect(): ElementFinder {
        return this.financialYearBatchSelect;
    }

    getFinancialYearBatchSelectedOption() {
        return this.financialYearBatchSelect.element(by.css('option:checked')).getText();
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
