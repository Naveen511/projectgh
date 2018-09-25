import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { NurseryStockComponentsPage, NurseryStockUpdatePage } from './nursery-stock.page-object';

describe('NurseryStock e2e test', () => {
    let navBarPage: NavBarPage;
    let nurseryStockUpdatePage: NurseryStockUpdatePage;
    let nurseryStockComponentsPage: NurseryStockComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load NurseryStocks', () => {
        navBarPage.goToEntity('nursery-stock');
        nurseryStockComponentsPage = new NurseryStockComponentsPage();
        expect(nurseryStockComponentsPage.getTitle()).toMatch(/projectghApp.nurseryStock.home.title/);
    });

    it('should load create NurseryStock page', () => {
        nurseryStockComponentsPage.clickOnCreateButton();
        nurseryStockUpdatePage = new NurseryStockUpdatePage();
        expect(nurseryStockUpdatePage.getPageTitle()).toMatch(/projectghApp.nurseryStock.home.createOrEditLabel/);
        nurseryStockUpdatePage.cancel();
    });

    it('should create and save NurseryStocks', () => {
        nurseryStockComponentsPage.clickOnCreateButton();
        nurseryStockUpdatePage.setCurrentQuantityInput('5');
        expect(nurseryStockUpdatePage.getCurrentQuantityInput()).toMatch('5');
        nurseryStockUpdatePage.setAddedQuantityInput('5');
        expect(nurseryStockUpdatePage.getAddedQuantityInput()).toMatch('5');
        nurseryStockUpdatePage.setConsumedQuantityInput('5');
        expect(nurseryStockUpdatePage.getConsumedQuantityInput()).toMatch('5');
        nurseryStockUpdatePage.setDescriptionInput('description');
        expect(nurseryStockUpdatePage.getDescriptionInput()).toMatch('description');
        nurseryStockUpdatePage.setStatusInput('5');
        expect(nurseryStockUpdatePage.getStatusInput()).toMatch('5');
        nurseryStockUpdatePage.setPosQuantityInput('5');
        expect(nurseryStockUpdatePage.getPosQuantityInput()).toMatch('5');
        nurseryStockUpdatePage.nurserySelectLastOption();
        nurseryStockUpdatePage.pickListVarietySelectLastOption();
        nurseryStockUpdatePage.pickListCategorySelectLastOption();
        nurseryStockUpdatePage.financialYearNurseryStockSelectLastOption();
        nurseryStockUpdatePage.save();
        expect(nurseryStockUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
