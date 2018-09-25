import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { PointOfSaleDetailsComponentsPage, PointOfSaleDetailsUpdatePage } from './point-of-sale-details.page-object';

describe('PointOfSaleDetails e2e test', () => {
    let navBarPage: NavBarPage;
    let pointOfSaleDetailsUpdatePage: PointOfSaleDetailsUpdatePage;
    let pointOfSaleDetailsComponentsPage: PointOfSaleDetailsComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load PointOfSaleDetails', () => {
        navBarPage.goToEntity('point-of-sale-details');
        pointOfSaleDetailsComponentsPage = new PointOfSaleDetailsComponentsPage();
        expect(pointOfSaleDetailsComponentsPage.getTitle()).toMatch(/projectghApp.pointOfSaleDetails.home.title/);
    });

    it('should load create PointOfSaleDetails page', () => {
        pointOfSaleDetailsComponentsPage.clickOnCreateButton();
        pointOfSaleDetailsUpdatePage = new PointOfSaleDetailsUpdatePage();
        expect(pointOfSaleDetailsUpdatePage.getPageTitle()).toMatch(/projectghApp.pointOfSaleDetails.home.createOrEditLabel/);
        pointOfSaleDetailsUpdatePage.cancel();
    });

    it('should create and save PointOfSaleDetails', () => {
        pointOfSaleDetailsComponentsPage.clickOnCreateButton();
        pointOfSaleDetailsUpdatePage.setQuantityInput('5');
        expect(pointOfSaleDetailsUpdatePage.getQuantityInput()).toMatch('5');
        pointOfSaleDetailsUpdatePage.setPurposeOfTakingInput('purposeOfTaking');
        expect(pointOfSaleDetailsUpdatePage.getPurposeOfTakingInput()).toMatch('purposeOfTaking');
        pointOfSaleDetailsUpdatePage.setDonorNameInput('donorName');
        expect(pointOfSaleDetailsUpdatePage.getDonorNameInput()).toMatch('donorName');
        pointOfSaleDetailsUpdatePage.setDonorAddressInput('donorAddress');
        expect(pointOfSaleDetailsUpdatePage.getDonorAddressInput()).toMatch('donorAddress');
        pointOfSaleDetailsUpdatePage.setContactNoInput('contactNo');
        expect(pointOfSaleDetailsUpdatePage.getContactNoInput()).toMatch('contactNo');
        pointOfSaleDetailsUpdatePage.setDiscountInput('5');
        expect(pointOfSaleDetailsUpdatePage.getDiscountInput()).toMatch('5');
        pointOfSaleDetailsUpdatePage.setDiscountAmountInput('5');
        expect(pointOfSaleDetailsUpdatePage.getDiscountAmountInput()).toMatch('5');
        pointOfSaleDetailsUpdatePage.setCollectedAmountInput('5');
        expect(pointOfSaleDetailsUpdatePage.getCollectedAmountInput()).toMatch('5');
        pointOfSaleDetailsUpdatePage.setDateInput('2000-12-31');
        expect(pointOfSaleDetailsUpdatePage.getDateInput()).toMatch('2000-12-31');
        pointOfSaleDetailsUpdatePage.setStatusInput('5');
        expect(pointOfSaleDetailsUpdatePage.getStatusInput()).toMatch('5');
        pointOfSaleDetailsUpdatePage.pickListVarietySelectLastOption();
        pointOfSaleDetailsUpdatePage.pickListCategorySelectLastOption();
        pointOfSaleDetailsUpdatePage.nurseryStockSelectLastOption();
        pointOfSaleDetailsUpdatePage.save();
        expect(pointOfSaleDetailsUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
