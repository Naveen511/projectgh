import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { CoverFillingDetailsComponentsPage, CoverFillingDetailsUpdatePage } from './cover-filling-details.page-object';

describe('CoverFillingDetails e2e test', () => {
    let navBarPage: NavBarPage;
    let coverFillingDetailsUpdatePage: CoverFillingDetailsUpdatePage;
    let coverFillingDetailsComponentsPage: CoverFillingDetailsComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load CoverFillingDetails', () => {
        navBarPage.goToEntity('cover-filling-details');
        coverFillingDetailsComponentsPage = new CoverFillingDetailsComponentsPage();
        expect(coverFillingDetailsComponentsPage.getTitle()).toMatch(/projectghApp.coverFillingDetails.home.title/);
    });

    it('should load create CoverFillingDetails page', () => {
        coverFillingDetailsComponentsPage.clickOnCreateButton();
        coverFillingDetailsUpdatePage = new CoverFillingDetailsUpdatePage();
        expect(coverFillingDetailsUpdatePage.getPageTitle()).toMatch(/projectghApp.coverFillingDetails.home.createOrEditLabel/);
        coverFillingDetailsUpdatePage.cancel();
    });

    it('should create and save CoverFillingDetails', () => {
        coverFillingDetailsComponentsPage.clickOnCreateButton();
        coverFillingDetailsUpdatePage.setQuantityInput('5');
        expect(coverFillingDetailsUpdatePage.getQuantityInput()).toMatch('5');
        coverFillingDetailsUpdatePage.setDateInput('2000-12-31');
        expect(coverFillingDetailsUpdatePage.getDateInput()).toMatch('2000-12-31');
        coverFillingDetailsUpdatePage.setStatusInput('5');
        expect(coverFillingDetailsUpdatePage.getStatusInput()).toMatch('5');
        coverFillingDetailsUpdatePage.setDescriptionInput('description');
        expect(coverFillingDetailsUpdatePage.getDescriptionInput()).toMatch('description');
        coverFillingDetailsUpdatePage.coverFillingSelectLastOption();
        coverFillingDetailsUpdatePage.damageTypeSelectLastOption();
        coverFillingDetailsUpdatePage.coverFillingDamageDescriptionSelectLastOption();
        coverFillingDetailsUpdatePage.save();
        expect(coverFillingDetailsUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
