import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { MapSectorWithZonalComponentsPage, MapSectorWithZonalUpdatePage } from './map-sector-with-zonal.page-object';

describe('MapSectorWithZonal e2e test', () => {
    let navBarPage: NavBarPage;
    let mapSectorWithZonalUpdatePage: MapSectorWithZonalUpdatePage;
    let mapSectorWithZonalComponentsPage: MapSectorWithZonalComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load MapSectorWithZonals', () => {
        navBarPage.goToEntity('map-sector-with-zonal');
        mapSectorWithZonalComponentsPage = new MapSectorWithZonalComponentsPage();
        expect(mapSectorWithZonalComponentsPage.getTitle()).toMatch(/projectghApp.mapSectorWithZonal.home.title/);
    });

    it('should load create MapSectorWithZonal page', () => {
        mapSectorWithZonalComponentsPage.clickOnCreateButton();
        mapSectorWithZonalUpdatePage = new MapSectorWithZonalUpdatePage();
        expect(mapSectorWithZonalUpdatePage.getPageTitle()).toMatch(/projectghApp.mapSectorWithZonal.home.createOrEditLabel/);
        mapSectorWithZonalUpdatePage.cancel();
    });

    it('should create and save MapSectorWithZonals', () => {
        mapSectorWithZonalComponentsPage.clickOnCreateButton();
        mapSectorWithZonalUpdatePage.setFromDateInput('2000-12-31');
        expect(mapSectorWithZonalUpdatePage.getFromDateInput()).toMatch('2000-12-31');
        mapSectorWithZonalUpdatePage.setToDateInput('2000-12-31');
        expect(mapSectorWithZonalUpdatePage.getToDateInput()).toMatch('2000-12-31');
        mapSectorWithZonalUpdatePage.setDescriptionInput('description');
        expect(mapSectorWithZonalUpdatePage.getDescriptionInput()).toMatch('description');
        mapSectorWithZonalUpdatePage.setStatusInput('5');
        expect(mapSectorWithZonalUpdatePage.getStatusInput()).toMatch('5');
        mapSectorWithZonalUpdatePage.zonalSelectLastOption();
        mapSectorWithZonalUpdatePage.sectorSelectLastOption();
        mapSectorWithZonalUpdatePage.save();
        expect(mapSectorWithZonalUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
