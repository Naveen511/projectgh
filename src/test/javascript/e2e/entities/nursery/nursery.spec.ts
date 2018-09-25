import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { NurseryComponentsPage, NurseryUpdatePage } from './nursery.page-object';

describe('Nursery e2e test', () => {
    let navBarPage: NavBarPage;
    let nurseryUpdatePage: NurseryUpdatePage;
    let nurseryComponentsPage: NurseryComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Nurseries', () => {
        navBarPage.goToEntity('nursery');
        nurseryComponentsPage = new NurseryComponentsPage();
        expect(nurseryComponentsPage.getTitle()).toMatch(/projectghApp.nursery.home.title/);
    });

    it('should load create Nursery page', () => {
        nurseryComponentsPage.clickOnCreateButton();
        nurseryUpdatePage = new NurseryUpdatePage();
        expect(nurseryUpdatePage.getPageTitle()).toMatch(/projectghApp.nursery.home.createOrEditLabel/);
        nurseryUpdatePage.cancel();
    });

    it('should create and save Nurseries', () => {
        nurseryComponentsPage.clickOnCreateButton();
        nurseryUpdatePage.setNurseryNameInput('nurseryName');
        expect(nurseryUpdatePage.getNurseryNameInput()).toMatch('nurseryName');
        nurseryUpdatePage.setNurseryAddressInput('nurseryAddress');
        expect(nurseryUpdatePage.getNurseryAddressInput()).toMatch('nurseryAddress');
        nurseryUpdatePage.setStatusInput('5');
        expect(nurseryUpdatePage.getStatusInput()).toMatch('5');
        nurseryUpdatePage.setCodeInput('code');
        expect(nurseryUpdatePage.getCodeInput()).toMatch('code');
        nurseryUpdatePage.sectorSelectLastOption();
        nurseryUpdatePage.nurseryTypeSelectLastOption();
        nurseryUpdatePage.financialYearNurserySelectLastOption();
        nurseryUpdatePage.save();
        expect(nurseryUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
