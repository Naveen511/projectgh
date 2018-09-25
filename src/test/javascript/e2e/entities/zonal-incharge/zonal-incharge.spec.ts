import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { ZonalInchargeComponentsPage, ZonalInchargeUpdatePage } from './zonal-incharge.page-object';

describe('ZonalIncharge e2e test', () => {
    let navBarPage: NavBarPage;
    let zonalInchargeUpdatePage: ZonalInchargeUpdatePage;
    let zonalInchargeComponentsPage: ZonalInchargeComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load ZonalIncharges', () => {
        navBarPage.goToEntity('zonal-incharge');
        zonalInchargeComponentsPage = new ZonalInchargeComponentsPage();
        expect(zonalInchargeComponentsPage.getTitle()).toMatch(/projectghApp.zonalIncharge.home.title/);
    });

    it('should load create ZonalIncharge page', () => {
        zonalInchargeComponentsPage.clickOnCreateButton();
        zonalInchargeUpdatePage = new ZonalInchargeUpdatePage();
        expect(zonalInchargeUpdatePage.getPageTitle()).toMatch(/projectghApp.zonalIncharge.home.createOrEditLabel/);
        zonalInchargeUpdatePage.cancel();
    });

    it('should create and save ZonalIncharges', () => {
        zonalInchargeComponentsPage.clickOnCreateButton();
        zonalInchargeUpdatePage.setFromDateInput('2000-12-31');
        expect(zonalInchargeUpdatePage.getFromDateInput()).toMatch('2000-12-31');
        zonalInchargeUpdatePage.setToDateInput('2000-12-31');
        expect(zonalInchargeUpdatePage.getToDateInput()).toMatch('2000-12-31');
        zonalInchargeUpdatePage.setDescriptionInput('description');
        expect(zonalInchargeUpdatePage.getDescriptionInput()).toMatch('description');
        zonalInchargeUpdatePage.setStatusInput('5');
        expect(zonalInchargeUpdatePage.getStatusInput()).toMatch('5');
        zonalInchargeUpdatePage.zonalSelectLastOption();
        zonalInchargeUpdatePage.save();
        expect(zonalInchargeUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
