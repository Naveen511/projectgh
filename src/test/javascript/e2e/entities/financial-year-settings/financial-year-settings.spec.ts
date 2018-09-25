import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { FinancialYearSettingsComponentsPage, FinancialYearSettingsUpdatePage } from './financial-year-settings.page-object';

describe('FinancialYearSettings e2e test', () => {
    let navBarPage: NavBarPage;
    let financialYearSettingsUpdatePage: FinancialYearSettingsUpdatePage;
    let financialYearSettingsComponentsPage: FinancialYearSettingsComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load FinancialYearSettings', () => {
        navBarPage.goToEntity('financial-year-settings');
        financialYearSettingsComponentsPage = new FinancialYearSettingsComponentsPage();
        expect(financialYearSettingsComponentsPage.getTitle()).toMatch(/projectghApp.financialYearSettings.home.title/);
    });

    it('should load create FinancialYearSettings page', () => {
        financialYearSettingsComponentsPage.clickOnCreateButton();
        financialYearSettingsUpdatePage = new FinancialYearSettingsUpdatePage();
        expect(financialYearSettingsUpdatePage.getPageTitle()).toMatch(/projectghApp.financialYearSettings.home.createOrEditLabel/);
        financialYearSettingsUpdatePage.cancel();
    });

    it('should create and save FinancialYearSettings', () => {
        financialYearSettingsComponentsPage.clickOnCreateButton();
        financialYearSettingsUpdatePage.setBatchNameInput('batchName');
        expect(financialYearSettingsUpdatePage.getBatchNameInput()).toMatch('batchName');
        financialYearSettingsUpdatePage.setStartDateInput('2000-12-31');
        expect(financialYearSettingsUpdatePage.getStartDateInput()).toMatch('2000-12-31');
        financialYearSettingsUpdatePage.setEndDateInput('2000-12-31');
        expect(financialYearSettingsUpdatePage.getEndDateInput()).toMatch('2000-12-31');
        financialYearSettingsUpdatePage.setStatusInput('5');
        expect(financialYearSettingsUpdatePage.getStatusInput()).toMatch('5');
        financialYearSettingsUpdatePage.financialYearSelectLastOption();
        financialYearSettingsUpdatePage.save();
        expect(financialYearSettingsUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
