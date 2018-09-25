import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { NurseryInventoryComponentsPage, NurseryInventoryUpdatePage } from './nursery-inventory.page-object';

describe('NurseryInventory e2e test', () => {
    let navBarPage: NavBarPage;
    let nurseryInventoryUpdatePage: NurseryInventoryUpdatePage;
    let nurseryInventoryComponentsPage: NurseryInventoryComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load NurseryInventories', () => {
        navBarPage.goToEntity('nursery-inventory');
        nurseryInventoryComponentsPage = new NurseryInventoryComponentsPage();
        expect(nurseryInventoryComponentsPage.getTitle()).toMatch(/projectghApp.nurseryInventory.home.title/);
    });

    it('should load create NurseryInventory page', () => {
        nurseryInventoryComponentsPage.clickOnCreateButton();
        nurseryInventoryUpdatePage = new NurseryInventoryUpdatePage();
        expect(nurseryInventoryUpdatePage.getPageTitle()).toMatch(/projectghApp.nurseryInventory.home.createOrEditLabel/);
        nurseryInventoryUpdatePage.cancel();
    });

    it('should create and save NurseryInventories', () => {
        nurseryInventoryComponentsPage.clickOnCreateButton();
        nurseryInventoryUpdatePage.setCurrentQuantityInput('5');
        expect(nurseryInventoryUpdatePage.getCurrentQuantityInput()).toMatch('5');
        nurseryInventoryUpdatePage.setAddedQuantityInput('5');
        expect(nurseryInventoryUpdatePage.getAddedQuantityInput()).toMatch('5');
        nurseryInventoryUpdatePage.setConsumedQuantityInput('5');
        expect(nurseryInventoryUpdatePage.getConsumedQuantityInput()).toMatch('5');
        nurseryInventoryUpdatePage.setDescriptionInput('description');
        expect(nurseryInventoryUpdatePage.getDescriptionInput()).toMatch('description');
        nurseryInventoryUpdatePage.setStatusInput('5');
        expect(nurseryInventoryUpdatePage.getStatusInput()).toMatch('5');
        nurseryInventoryUpdatePage.setDamageQuantityInput('5');
        expect(nurseryInventoryUpdatePage.getDamageQuantityInput()).toMatch('5');
        nurseryInventoryUpdatePage.nurserysSelectLastOption();
        nurseryInventoryUpdatePage.pickListVarietySelectLastOption();
        nurseryInventoryUpdatePage.pickListCategorySelectLastOption();
        nurseryInventoryUpdatePage.quantityTypeSelectLastOption();
        nurseryInventoryUpdatePage.save();
        expect(nurseryInventoryUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
