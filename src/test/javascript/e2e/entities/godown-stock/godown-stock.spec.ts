import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { GodownStockComponentsPage, GodownStockUpdatePage } from './godown-stock.page-object';

describe('GodownStock e2e test', () => {
    let navBarPage: NavBarPage;
    let godownStockUpdatePage: GodownStockUpdatePage;
    let godownStockComponentsPage: GodownStockComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load GodownStocks', () => {
        navBarPage.goToEntity('godown-stock');
        godownStockComponentsPage = new GodownStockComponentsPage();
        expect(godownStockComponentsPage.getTitle()).toMatch(/projectghApp.godownStock.home.title/);
    });

    it('should load create GodownStock page', () => {
        godownStockComponentsPage.clickOnCreateButton();
        godownStockUpdatePage = new GodownStockUpdatePage();
        expect(godownStockUpdatePage.getPageTitle()).toMatch(/projectghApp.godownStock.home.createOrEditLabel/);
        godownStockUpdatePage.cancel();
    });

    it('should create and save GodownStocks', () => {
        godownStockComponentsPage.clickOnCreateButton();
        godownStockUpdatePage.setCurrentQuantityInput('5');
        expect(godownStockUpdatePage.getCurrentQuantityInput()).toMatch('5');
        godownStockUpdatePage.setAddedQuantityInput('5');
        expect(godownStockUpdatePage.getAddedQuantityInput()).toMatch('5');
        godownStockUpdatePage.setConsumedQuantityInput('5');
        expect(godownStockUpdatePage.getConsumedQuantityInput()).toMatch('5');
        godownStockUpdatePage.setDescriptionInput('description');
        expect(godownStockUpdatePage.getDescriptionInput()).toMatch('description');
        godownStockUpdatePage.setStatusInput('5');
        expect(godownStockUpdatePage.getStatusInput()).toMatch('5');
        godownStockUpdatePage.pickListVarietySelectLastOption();
        godownStockUpdatePage.pickListCategorySelectLastOption();
        godownStockUpdatePage.pickListQuantityTypeSelectLastOption();
        godownStockUpdatePage.godownSelectLastOption();
        godownStockUpdatePage.financialYearGodownStockSelectLastOption();
        godownStockUpdatePage.save();
        expect(godownStockUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
