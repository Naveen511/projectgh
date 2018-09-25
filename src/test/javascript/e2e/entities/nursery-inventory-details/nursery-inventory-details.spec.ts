import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { NurseryInventoryDetailsComponentsPage, NurseryInventoryDetailsUpdatePage } from './nursery-inventory-details.page-object';

describe('NurseryInventoryDetails e2e test', () => {
    let navBarPage: NavBarPage;
    let nurseryInventoryDetailsUpdatePage: NurseryInventoryDetailsUpdatePage;
    let nurseryInventoryDetailsComponentsPage: NurseryInventoryDetailsComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load NurseryInventoryDetails', () => {
        navBarPage.goToEntity('nursery-inventory-details');
        nurseryInventoryDetailsComponentsPage = new NurseryInventoryDetailsComponentsPage();
        expect(nurseryInventoryDetailsComponentsPage.getTitle()).toMatch(/projectghApp.nurseryInventoryDetails.home.title/);
    });

    it('should load create NurseryInventoryDetails page', () => {
        nurseryInventoryDetailsComponentsPage.clickOnCreateButton();
        nurseryInventoryDetailsUpdatePage = new NurseryInventoryDetailsUpdatePage();
        expect(nurseryInventoryDetailsUpdatePage.getPageTitle()).toMatch(/projectghApp.nurseryInventoryDetails.home.createOrEditLabel/);
        nurseryInventoryDetailsUpdatePage.cancel();
    });

    it('should create and save NurseryInventoryDetails', () => {
        nurseryInventoryDetailsComponentsPage.clickOnCreateButton();
        nurseryInventoryDetailsUpdatePage.setDateInput('2000-12-31');
        expect(nurseryInventoryDetailsUpdatePage.getDateInput()).toMatch('2000-12-31');
        nurseryInventoryDetailsUpdatePage.setQuantityInput('5');
        expect(nurseryInventoryDetailsUpdatePage.getQuantityInput()).toMatch('5');
        nurseryInventoryDetailsUpdatePage.setStatusInput('5');
        expect(nurseryInventoryDetailsUpdatePage.getStatusInput()).toMatch('5');
        nurseryInventoryDetailsUpdatePage.setDescriptionInput('description');
        expect(nurseryInventoryDetailsUpdatePage.getDescriptionInput()).toMatch('description');
        nurseryInventoryDetailsUpdatePage.nurseryInventorySelectLastOption();
        nurseryInventoryDetailsUpdatePage.damageTypeSelectLastOption();
        nurseryInventoryDetailsUpdatePage.inventoryDamageDescriptionSelectLastOption();
        nurseryInventoryDetailsUpdatePage.save();
        expect(nurseryInventoryDetailsUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
