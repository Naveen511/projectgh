import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { BatchQuantityComponentsPage, BatchQuantityUpdatePage } from './batch-quantity.page-object';

describe('BatchQuantity e2e test', () => {
    let navBarPage: NavBarPage;
    let batchQuantityUpdatePage: BatchQuantityUpdatePage;
    let batchQuantityComponentsPage: BatchQuantityComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load BatchQuantities', () => {
        navBarPage.goToEntity('batch-quantity');
        batchQuantityComponentsPage = new BatchQuantityComponentsPage();
        expect(batchQuantityComponentsPage.getTitle()).toMatch(/projectghApp.batchQuantity.home.title/);
    });

    it('should load create BatchQuantity page', () => {
        batchQuantityComponentsPage.clickOnCreateButton();
        batchQuantityUpdatePage = new BatchQuantityUpdatePage();
        expect(batchQuantityUpdatePage.getPageTitle()).toMatch(/projectghApp.batchQuantity.home.createOrEditLabel/);
        batchQuantityUpdatePage.cancel();
    });

    it('should create and save BatchQuantities', () => {
        batchQuantityComponentsPage.clickOnCreateButton();
        batchQuantityUpdatePage.setQuantityInput('5');
        expect(batchQuantityUpdatePage.getQuantityInput()).toMatch('5');
        batchQuantityUpdatePage.setDateInput('2000-12-31');
        expect(batchQuantityUpdatePage.getDateInput()).toMatch('2000-12-31');
        batchQuantityUpdatePage.setRemarksInput('remarks');
        expect(batchQuantityUpdatePage.getRemarksInput()).toMatch('remarks');
        batchQuantityUpdatePage.setStatusInput('5');
        expect(batchQuantityUpdatePage.getStatusInput()).toMatch('5');
        batchQuantityUpdatePage.batchSelectLastOption();
        batchQuantityUpdatePage.save();
        expect(batchQuantityUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
