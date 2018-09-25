import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { CoverFillingComponentsPage, CoverFillingUpdatePage } from './cover-filling.page-object';

describe('CoverFilling e2e test', () => {
    let navBarPage: NavBarPage;
    let coverFillingUpdatePage: CoverFillingUpdatePage;
    let coverFillingComponentsPage: CoverFillingComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load CoverFillings', () => {
        navBarPage.goToEntity('cover-filling');
        coverFillingComponentsPage = new CoverFillingComponentsPage();
        expect(coverFillingComponentsPage.getTitle()).toMatch(/projectghApp.coverFilling.home.title/);
    });

    it('should load create CoverFilling page', () => {
        coverFillingComponentsPage.clickOnCreateButton();
        coverFillingUpdatePage = new CoverFillingUpdatePage();
        expect(coverFillingUpdatePage.getPageTitle()).toMatch(/projectghApp.coverFilling.home.createOrEditLabel/);
        coverFillingUpdatePage.cancel();
    });

    it('should create and save CoverFillings', () => {
        coverFillingComponentsPage.clickOnCreateButton();
        coverFillingUpdatePage.setNoOfCoverInput('5');
        expect(coverFillingUpdatePage.getNoOfCoverInput()).toMatch('5');
        coverFillingUpdatePage.setDateInput('2000-12-31');
        expect(coverFillingUpdatePage.getDateInput()).toMatch('2000-12-31');
        coverFillingUpdatePage.setDescriptionInput('description');
        expect(coverFillingUpdatePage.getDescriptionInput()).toMatch('description');
        coverFillingUpdatePage.setStatusInput('5');
        expect(coverFillingUpdatePage.getStatusInput()).toMatch('5');
        coverFillingUpdatePage.setDamageQuantityInput('5');
        expect(coverFillingUpdatePage.getDamageQuantityInput()).toMatch('5');
        coverFillingUpdatePage.save();
        expect(coverFillingUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
