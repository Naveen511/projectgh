import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { NurseryStockDetailsComponentsPage, NurseryStockDetailsUpdatePage } from './nursery-stock-details.page-object';

describe('NurseryStockDetails e2e test', () => {
    let navBarPage: NavBarPage;
    let nurseryStockDetailsUpdatePage: NurseryStockDetailsUpdatePage;
    let nurseryStockDetailsComponentsPage: NurseryStockDetailsComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load NurseryStockDetails', () => {
        navBarPage.goToEntity('nursery-stock-details');
        nurseryStockDetailsComponentsPage = new NurseryStockDetailsComponentsPage();
        expect(nurseryStockDetailsComponentsPage.getTitle()).toMatch(/projectghApp.nurseryStockDetails.home.title/);
    });

    it('should load create NurseryStockDetails page', () => {
        nurseryStockDetailsComponentsPage.clickOnCreateButton();
        nurseryStockDetailsUpdatePage = new NurseryStockDetailsUpdatePage();
        expect(nurseryStockDetailsUpdatePage.getPageTitle()).toMatch(/projectghApp.nurseryStockDetails.home.createOrEditLabel/);
        nurseryStockDetailsUpdatePage.cancel();
    });

    it('should create and save NurseryStockDetails', () => {
        nurseryStockDetailsComponentsPage.clickOnCreateButton();
        nurseryStockDetailsUpdatePage.setDateInput('2000-12-31');
        expect(nurseryStockDetailsUpdatePage.getDateInput()).toMatch('2000-12-31');
        nurseryStockDetailsUpdatePage.setQuantityInput('5');
        expect(nurseryStockDetailsUpdatePage.getQuantityInput()).toMatch('5');
        nurseryStockDetailsUpdatePage.setDescriptionInput('description');
        expect(nurseryStockDetailsUpdatePage.getDescriptionInput()).toMatch('description');
        nurseryStockDetailsUpdatePage.setStatusInput('5');
        expect(nurseryStockDetailsUpdatePage.getStatusInput()).toMatch('5');
        nurseryStockDetailsUpdatePage.batchSelectLastOption();
        nurseryStockDetailsUpdatePage.nurseryStockSelectLastOption();
        nurseryStockDetailsUpdatePage.itNurserySelectLastOption();
        nurseryStockDetailsUpdatePage.saplingDamageAreaSelectLastOption();
        nurseryStockDetailsUpdatePage.financialYearStockDetailsSelectLastOption();
        nurseryStockDetailsUpdatePage.save();
        expect(nurseryStockDetailsUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
