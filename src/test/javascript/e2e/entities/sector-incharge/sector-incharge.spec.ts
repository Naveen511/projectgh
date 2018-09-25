import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { SectorInchargeComponentsPage, SectorInchargeUpdatePage } from './sector-incharge.page-object';

describe('SectorIncharge e2e test', () => {
    let navBarPage: NavBarPage;
    let sectorInchargeUpdatePage: SectorInchargeUpdatePage;
    let sectorInchargeComponentsPage: SectorInchargeComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load SectorIncharges', () => {
        navBarPage.goToEntity('sector-incharge');
        sectorInchargeComponentsPage = new SectorInchargeComponentsPage();
        expect(sectorInchargeComponentsPage.getTitle()).toMatch(/projectghApp.sectorIncharge.home.title/);
    });

    it('should load create SectorIncharge page', () => {
        sectorInchargeComponentsPage.clickOnCreateButton();
        sectorInchargeUpdatePage = new SectorInchargeUpdatePage();
        expect(sectorInchargeUpdatePage.getPageTitle()).toMatch(/projectghApp.sectorIncharge.home.createOrEditLabel/);
        sectorInchargeUpdatePage.cancel();
    });

    it('should create and save SectorIncharges', () => {
        sectorInchargeComponentsPage.clickOnCreateButton();
        sectorInchargeUpdatePage.setFromDateInput('2000-12-31');
        expect(sectorInchargeUpdatePage.getFromDateInput()).toMatch('2000-12-31');
        sectorInchargeUpdatePage.setToDateInput('2000-12-31');
        expect(sectorInchargeUpdatePage.getToDateInput()).toMatch('2000-12-31');
        sectorInchargeUpdatePage.setDescriptionInput('description');
        expect(sectorInchargeUpdatePage.getDescriptionInput()).toMatch('description');
        sectorInchargeUpdatePage.setStatusInput('5');
        expect(sectorInchargeUpdatePage.getStatusInput()).toMatch('5');
        sectorInchargeUpdatePage.sectorSelectLastOption();
        sectorInchargeUpdatePage.save();
        expect(sectorInchargeUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
