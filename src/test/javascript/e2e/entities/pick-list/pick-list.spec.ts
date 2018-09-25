import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { PickListComponentsPage, PickListUpdatePage } from './pick-list.page-object';

describe('PickList e2e test', () => {
    let navBarPage: NavBarPage;
    let pickListUpdatePage: PickListUpdatePage;
    let pickListComponentsPage: PickListComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load PickLists', () => {
        navBarPage.goToEntity('pick-list');
        pickListComponentsPage = new PickListComponentsPage();
        expect(pickListComponentsPage.getTitle()).toMatch(/projectghApp.pickList.home.title/);
    });

    it('should load create PickList page', () => {
        pickListComponentsPage.clickOnCreateButton();
        pickListUpdatePage = new PickListUpdatePage();
        expect(pickListUpdatePage.getPageTitle()).toMatch(/projectghApp.pickList.home.createOrEditLabel/);
        pickListUpdatePage.cancel();
    });

    it('should create and save PickLists', () => {
        pickListComponentsPage.clickOnCreateButton();
        pickListUpdatePage.setPickListNameInput('pickListName');
        expect(pickListUpdatePage.getPickListNameInput()).toMatch('pickListName');
        pickListUpdatePage.setStatusInput('5');
        expect(pickListUpdatePage.getStatusInput()).toMatch('5');
        pickListUpdatePage.save();
        expect(pickListUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
