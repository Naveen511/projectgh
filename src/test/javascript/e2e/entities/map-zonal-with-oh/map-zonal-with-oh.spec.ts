import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { MapZonalWithOhComponentsPage, MapZonalWithOhUpdatePage } from './map-zonal-with-oh.page-object';

describe('MapZonalWithOh e2e test', () => {
    let navBarPage: NavBarPage;
    let mapZonalWithOhUpdatePage: MapZonalWithOhUpdatePage;
    let mapZonalWithOhComponentsPage: MapZonalWithOhComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load MapZonalWithOhs', () => {
        navBarPage.goToEntity('map-zonal-with-oh');
        mapZonalWithOhComponentsPage = new MapZonalWithOhComponentsPage();
        expect(mapZonalWithOhComponentsPage.getTitle()).toMatch(/projectghApp.mapZonalWithOh.home.title/);
    });

    it('should load create MapZonalWithOh page', () => {
        mapZonalWithOhComponentsPage.clickOnCreateButton();
        mapZonalWithOhUpdatePage = new MapZonalWithOhUpdatePage();
        expect(mapZonalWithOhUpdatePage.getPageTitle()).toMatch(/projectghApp.mapZonalWithOh.home.createOrEditLabel/);
        mapZonalWithOhUpdatePage.cancel();
    });

    it('should create and save MapZonalWithOhs', () => {
        mapZonalWithOhComponentsPage.clickOnCreateButton();
        mapZonalWithOhUpdatePage.setFromDateInput('2000-12-31');
        expect(mapZonalWithOhUpdatePage.getFromDateInput()).toMatch('2000-12-31');
        mapZonalWithOhUpdatePage.setToDateInput('2000-12-31');
        expect(mapZonalWithOhUpdatePage.getToDateInput()).toMatch('2000-12-31');
        mapZonalWithOhUpdatePage.setDescriptionInput('description');
        expect(mapZonalWithOhUpdatePage.getDescriptionInput()).toMatch('description');
        mapZonalWithOhUpdatePage.setStatusInput('5');
        expect(mapZonalWithOhUpdatePage.getStatusInput()).toMatch('5');
        mapZonalWithOhUpdatePage.zonalSelectLastOption();
        mapZonalWithOhUpdatePage.operationalHeadSelectLastOption();
        mapZonalWithOhUpdatePage.save();
        expect(mapZonalWithOhUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
