import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { NurseryInchargeComponentsPage, NurseryInchargeUpdatePage } from './nursery-incharge.page-object';

describe('NurseryIncharge e2e test', () => {
    let navBarPage: NavBarPage;
    let nurseryInchargeUpdatePage: NurseryInchargeUpdatePage;
    let nurseryInchargeComponentsPage: NurseryInchargeComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load NurseryIncharges', () => {
        navBarPage.goToEntity('nursery-incharge');
        nurseryInchargeComponentsPage = new NurseryInchargeComponentsPage();
        expect(nurseryInchargeComponentsPage.getTitle()).toMatch(/projectghApp.nurseryIncharge.home.title/);
    });

    it('should load create NurseryIncharge page', () => {
        nurseryInchargeComponentsPage.clickOnCreateButton();
        nurseryInchargeUpdatePage = new NurseryInchargeUpdatePage();
        expect(nurseryInchargeUpdatePage.getPageTitle()).toMatch(/projectghApp.nurseryIncharge.home.createOrEditLabel/);
        nurseryInchargeUpdatePage.cancel();
    });

    it('should create and save NurseryIncharges', () => {
        nurseryInchargeComponentsPage.clickOnCreateButton();
        nurseryInchargeUpdatePage.setFromDateInput('2000-12-31');
        expect(nurseryInchargeUpdatePage.getFromDateInput()).toMatch('2000-12-31');
        nurseryInchargeUpdatePage.setToDateInput('2000-12-31');
        expect(nurseryInchargeUpdatePage.getToDateInput()).toMatch('2000-12-31');
        nurseryInchargeUpdatePage.setDescriptionInput('description');
        expect(nurseryInchargeUpdatePage.getDescriptionInput()).toMatch('description');
        nurseryInchargeUpdatePage.setStatusInput('5');
        expect(nurseryInchargeUpdatePage.getStatusInput()).toMatch('5');
        nurseryInchargeUpdatePage.nurserySelectLastOption();
        nurseryInchargeUpdatePage.save();
        expect(nurseryInchargeUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
