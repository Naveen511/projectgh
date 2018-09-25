import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { GodownComponentsPage, GodownUpdatePage } from './godown.page-object';

describe('Godown e2e test', () => {
    let navBarPage: NavBarPage;
    let godownUpdatePage: GodownUpdatePage;
    let godownComponentsPage: GodownComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Godowns', () => {
        navBarPage.goToEntity('godown');
        godownComponentsPage = new GodownComponentsPage();
        expect(godownComponentsPage.getTitle()).toMatch(/projectghApp.godown.home.title/);
    });

    it('should load create Godown page', () => {
        godownComponentsPage.clickOnCreateButton();
        godownUpdatePage = new GodownUpdatePage();
        expect(godownUpdatePage.getPageTitle()).toMatch(/projectghApp.godown.home.createOrEditLabel/);
        godownUpdatePage.cancel();
    });

    it('should create and save Godowns', () => {
        godownComponentsPage.clickOnCreateButton();
        godownUpdatePage.setNameInput('name');
        expect(godownUpdatePage.getNameInput()).toMatch('name');
        godownUpdatePage.setAddressInput('address');
        expect(godownUpdatePage.getAddressInput()).toMatch('address');
        godownUpdatePage.setInchargeInput('incharge');
        expect(godownUpdatePage.getInchargeInput()).toMatch('incharge');
        godownUpdatePage.setStatusInput('5');
        expect(godownUpdatePage.getStatusInput()).toMatch('5');
        godownUpdatePage.financialYearGodownSelectLastOption();
        godownUpdatePage.save();
        expect(godownUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
