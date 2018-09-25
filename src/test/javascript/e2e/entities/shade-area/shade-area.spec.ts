import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { ShadeAreaComponentsPage, ShadeAreaUpdatePage } from './shade-area.page-object';

describe('ShadeArea e2e test', () => {
    let navBarPage: NavBarPage;
    let shadeAreaUpdatePage: ShadeAreaUpdatePage;
    let shadeAreaComponentsPage: ShadeAreaComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load ShadeAreas', () => {
        navBarPage.goToEntity('shade-area');
        shadeAreaComponentsPage = new ShadeAreaComponentsPage();
        expect(shadeAreaComponentsPage.getTitle()).toMatch(/projectghApp.shadeArea.home.title/);
    });

    it('should load create ShadeArea page', () => {
        shadeAreaComponentsPage.clickOnCreateButton();
        shadeAreaUpdatePage = new ShadeAreaUpdatePage();
        expect(shadeAreaUpdatePage.getPageTitle()).toMatch(/projectghApp.shadeArea.home.createOrEditLabel/);
        shadeAreaUpdatePage.cancel();
    });

    it('should create and save ShadeAreas', () => {
        shadeAreaComponentsPage.clickOnCreateButton();
        shadeAreaUpdatePage.setNoOfSeedlingsInput('5');
        expect(shadeAreaUpdatePage.getNoOfSeedlingsInput()).toMatch('5');
        shadeAreaUpdatePage.setDateInput('2000-12-31');
        expect(shadeAreaUpdatePage.getDateInput()).toMatch('2000-12-31');
        shadeAreaUpdatePage.setStatusInput('5');
        expect(shadeAreaUpdatePage.getStatusInput()).toMatch('5');
        shadeAreaUpdatePage.setDamageInput('5');
        expect(shadeAreaUpdatePage.getDamageInput()).toMatch('5');
        shadeAreaUpdatePage.setSaplingsInput('5');
        expect(shadeAreaUpdatePage.getSaplingsInput()).toMatch('5');
        shadeAreaUpdatePage.batchSelectLastOption();
        shadeAreaUpdatePage.financialYearShadeAreaSelectLastOption();
        shadeAreaUpdatePage.save();
        expect(shadeAreaUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
