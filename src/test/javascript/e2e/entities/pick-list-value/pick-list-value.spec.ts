import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { PickListValueComponentsPage, PickListValueUpdatePage } from './pick-list-value.page-object';

describe('PickListValue e2e test', () => {
    let navBarPage: NavBarPage;
    let pickListValueUpdatePage: PickListValueUpdatePage;
    let pickListValueComponentsPage: PickListValueComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load PickListValues', () => {
        navBarPage.goToEntity('pick-list-value');
        pickListValueComponentsPage = new PickListValueComponentsPage();
        expect(pickListValueComponentsPage.getTitle()).toMatch(/projectghApp.pickListValue.home.title/);
    });

    it('should load create PickListValue page', () => {
        pickListValueComponentsPage.clickOnCreateButton();
        pickListValueUpdatePage = new PickListValueUpdatePage();
        expect(pickListValueUpdatePage.getPageTitle()).toMatch(/projectghApp.pickListValue.home.createOrEditLabel/);
        pickListValueUpdatePage.cancel();
    });

    it('should create and save PickListValues', () => {
        pickListValueComponentsPage.clickOnCreateButton();
        pickListValueUpdatePage.setPickListValueInput('pickListValue');
        expect(pickListValueUpdatePage.getPickListValueInput()).toMatch('pickListValue');
        pickListValueUpdatePage.setStatusInput('5');
        expect(pickListValueUpdatePage.getStatusInput()).toMatch('5');
        pickListValueUpdatePage.pickListSelectLastOption();
        pickListValueUpdatePage.pickValueSelectLastOption();
        pickListValueUpdatePage.save();
        expect(pickListValueUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
