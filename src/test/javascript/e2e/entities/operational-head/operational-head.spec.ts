import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { OperationalHeadComponentsPage, OperationalHeadUpdatePage } from './operational-head.page-object';

describe('OperationalHead e2e test', () => {
    let navBarPage: NavBarPage;
    let operationalHeadUpdatePage: OperationalHeadUpdatePage;
    let operationalHeadComponentsPage: OperationalHeadComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load OperationalHeads', () => {
        navBarPage.goToEntity('operational-head');
        operationalHeadComponentsPage = new OperationalHeadComponentsPage();
        expect(operationalHeadComponentsPage.getTitle()).toMatch(/projectghApp.operationalHead.home.title/);
    });

    it('should load create OperationalHead page', () => {
        operationalHeadComponentsPage.clickOnCreateButton();
        operationalHeadUpdatePage = new OperationalHeadUpdatePage();
        expect(operationalHeadUpdatePage.getPageTitle()).toMatch(/projectghApp.operationalHead.home.createOrEditLabel/);
        operationalHeadUpdatePage.cancel();
    });

    it('should create and save OperationalHeads', () => {
        operationalHeadComponentsPage.clickOnCreateButton();
        operationalHeadUpdatePage.setNameInput('name');
        expect(operationalHeadUpdatePage.getNameInput()).toMatch('name');
        operationalHeadUpdatePage.setDescriptionInput('description');
        expect(operationalHeadUpdatePage.getDescriptionInput()).toMatch('description');
        operationalHeadUpdatePage.setStatusInput('5');
        expect(operationalHeadUpdatePage.getStatusInput()).toMatch('5');
        operationalHeadUpdatePage.save();
        expect(operationalHeadUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
