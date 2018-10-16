import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { MotherBedComponentsPage, MotherBedUpdatePage } from './mother-bed.page-object';

describe('MotherBed e2e test', () => {
    let navBarPage: NavBarPage;
    let motherBedUpdatePage: MotherBedUpdatePage;
    let motherBedComponentsPage: MotherBedComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load MotherBeds', () => {
        navBarPage.goToEntity('mother-bed');
        motherBedComponentsPage = new MotherBedComponentsPage();
        expect(motherBedComponentsPage.getTitle()).toMatch(/projectghApp.motherBed.home.title/);
    });

    it('should load create MotherBed page', () => {
        motherBedComponentsPage.clickOnCreateButton();
        motherBedUpdatePage = new MotherBedUpdatePage();
        expect(motherBedUpdatePage.getPageTitle()).toMatch(/projectghApp.motherBed.home.createOrEditLabel/);
        motherBedUpdatePage.cancel();
    });

    /* it('should create and save MotherBeds', () => {
        motherBedComponentsPage.clickOnCreateButton();
        motherBedUpdatePage.setValueInput('5');
        expect(motherBedUpdatePage.getValueInput()).toMatch('5');
        motherBedUpdatePage.setStatusInput('5');
        expect(motherBedUpdatePage.getStatusInput()).toMatch('5');
        motherBedUpdatePage.nurserySelectLastOption();
        motherBedUpdatePage.save();
        expect(motherBedUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });*/

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
