import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { CommonSettingsComponentsPage, CommonSettingsUpdatePage } from './common-settings.page-object';

describe('CommonSettings e2e test', () => {
    let navBarPage: NavBarPage;
    let commonSettingsUpdatePage: CommonSettingsUpdatePage;
    let commonSettingsComponentsPage: CommonSettingsComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load CommonSettings', () => {
        navBarPage.goToEntity('common-settings');
        commonSettingsComponentsPage = new CommonSettingsComponentsPage();
        expect(commonSettingsComponentsPage.getTitle()).toMatch(/projectghApp.commonSettings.home.title/);
    });

    it('should load create CommonSettings page', () => {
        commonSettingsComponentsPage.clickOnCreateButton();
        commonSettingsUpdatePage = new CommonSettingsUpdatePage();
        expect(commonSettingsUpdatePage.getPageTitle()).toMatch(/projectghApp.commonSettings.home.createOrEditLabel/);
        commonSettingsUpdatePage.cancel();
    });

    it('should create and save CommonSettings', () => {
        commonSettingsComponentsPage.clickOnCreateButton();
        commonSettingsUpdatePage.setDaysToCloseBatchInput('5');
        expect(commonSettingsUpdatePage.getDaysToCloseBatchInput()).toMatch('5');
        commonSettingsUpdatePage.setStatusInput('5');
        expect(commonSettingsUpdatePage.getStatusInput()).toMatch('5');
        commonSettingsUpdatePage.save();
        expect(commonSettingsUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
