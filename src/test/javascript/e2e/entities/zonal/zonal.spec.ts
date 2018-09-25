import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { ZonalComponentsPage, ZonalUpdatePage } from './zonal.page-object';

describe('Zonal e2e test', () => {
    let navBarPage: NavBarPage;
    let zonalUpdatePage: ZonalUpdatePage;
    let zonalComponentsPage: ZonalComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Zonals', () => {
        navBarPage.goToEntity('zonal');
        zonalComponentsPage = new ZonalComponentsPage();
        expect(zonalComponentsPage.getTitle()).toMatch(/projectghApp.zonal.home.title/);
    });

    it('should load create Zonal page', () => {
        zonalComponentsPage.clickOnCreateButton();
        zonalUpdatePage = new ZonalUpdatePage();
        expect(zonalUpdatePage.getPageTitle()).toMatch(/projectghApp.zonal.home.createOrEditLabel/);
        zonalUpdatePage.cancel();
    });

    it('should create and save Zonals', () => {
        zonalComponentsPage.clickOnCreateButton();
        zonalUpdatePage.setZoneNameInput('zoneName');
        expect(zonalUpdatePage.getZoneNameInput()).toMatch('zoneName');
        zonalUpdatePage.setZoneAddressInput('zoneAddress');
        expect(zonalUpdatePage.getZoneAddressInput()).toMatch('zoneAddress');
        zonalUpdatePage.setStatusInput('5');
        expect(zonalUpdatePage.getStatusInput()).toMatch('5');
        zonalUpdatePage.financialYearSelectLastOption();
        zonalUpdatePage.operationalHeadSelectLastOption();
        zonalUpdatePage.save();
        expect(zonalUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
