import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { GodownPurchaseDetailsComponentsPage, GodownPurchaseDetailsUpdatePage } from './godown-purchase-details.page-object';

describe('GodownPurchaseDetails e2e test', () => {
    let navBarPage: NavBarPage;
    let godownPurchaseDetailsUpdatePage: GodownPurchaseDetailsUpdatePage;
    let godownPurchaseDetailsComponentsPage: GodownPurchaseDetailsComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load GodownPurchaseDetails', () => {
        navBarPage.goToEntity('godown-purchase-details');
        godownPurchaseDetailsComponentsPage = new GodownPurchaseDetailsComponentsPage();
        expect(godownPurchaseDetailsComponentsPage.getTitle()).toMatch(/projectghApp.godownPurchaseDetails.home.title/);
    });

    it('should load create GodownPurchaseDetails page', () => {
        godownPurchaseDetailsComponentsPage.clickOnCreateButton();
        godownPurchaseDetailsUpdatePage = new GodownPurchaseDetailsUpdatePage();
        expect(godownPurchaseDetailsUpdatePage.getPageTitle()).toMatch(/projectghApp.godownPurchaseDetails.home.createOrEditLabel/);
        godownPurchaseDetailsUpdatePage.cancel();
    });

    it('should create and save GodownPurchaseDetails', () => {
        godownPurchaseDetailsComponentsPage.clickOnCreateButton();
        godownPurchaseDetailsUpdatePage.setQuantityInput('5');
        expect(godownPurchaseDetailsUpdatePage.getQuantityInput()).toMatch('5');
        godownPurchaseDetailsUpdatePage.setDateInput('2000-12-31');
        expect(godownPurchaseDetailsUpdatePage.getDateInput()).toMatch('2000-12-31');
        godownPurchaseDetailsUpdatePage.setPriceInput('5');
        expect(godownPurchaseDetailsUpdatePage.getPriceInput()).toMatch('5');
        godownPurchaseDetailsUpdatePage.setOwnedByInput('ownedBy');
        expect(godownPurchaseDetailsUpdatePage.getOwnedByInput()).toMatch('ownedBy');
        godownPurchaseDetailsUpdatePage.setVendorNameInput('vendorName');
        expect(godownPurchaseDetailsUpdatePage.getVendorNameInput()).toMatch('vendorName');
        godownPurchaseDetailsUpdatePage.setVendorAddressInput('vendorAddress');
        expect(godownPurchaseDetailsUpdatePage.getVendorAddressInput()).toMatch('vendorAddress');
        godownPurchaseDetailsUpdatePage.setVendorPhoneInput('5');
        expect(godownPurchaseDetailsUpdatePage.getVendorPhoneInput()).toMatch('5');
        godownPurchaseDetailsUpdatePage.setDescriptionInput('description');
        expect(godownPurchaseDetailsUpdatePage.getDescriptionInput()).toMatch('description');
        godownPurchaseDetailsUpdatePage.setStatusInput('5');
        expect(godownPurchaseDetailsUpdatePage.getStatusInput()).toMatch('5');
        godownPurchaseDetailsUpdatePage.pickListVarietySelectLastOption();
        godownPurchaseDetailsUpdatePage.pickListCategorySelectLastOption();
        godownPurchaseDetailsUpdatePage.pickListQuantityTypeSelectLastOption();
        godownPurchaseDetailsUpdatePage.godownSelectLastOption();
        godownPurchaseDetailsUpdatePage.financialYearGodownPurchaseSelectLastOption();
        godownPurchaseDetailsUpdatePage.save();
        expect(godownPurchaseDetailsUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
