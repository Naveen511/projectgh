import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { MapNurseryWithSectorComponentsPage, MapNurseryWithSectorUpdatePage } from './map-nursery-with-sector.page-object';

describe('MapNurseryWithSector e2e test', () => {
    let navBarPage: NavBarPage;
    let mapNurseryWithSectorUpdatePage: MapNurseryWithSectorUpdatePage;
    let mapNurseryWithSectorComponentsPage: MapNurseryWithSectorComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load MapNurseryWithSectors', () => {
        navBarPage.goToEntity('map-nursery-with-sector');
        mapNurseryWithSectorComponentsPage = new MapNurseryWithSectorComponentsPage();
        expect(mapNurseryWithSectorComponentsPage.getTitle()).toMatch(/projectghApp.mapNurseryWithSector.home.title/);
    });

    it('should load create MapNurseryWithSector page', () => {
        mapNurseryWithSectorComponentsPage.clickOnCreateButton();
        mapNurseryWithSectorUpdatePage = new MapNurseryWithSectorUpdatePage();
        expect(mapNurseryWithSectorUpdatePage.getPageTitle()).toMatch(/projectghApp.mapNurseryWithSector.home.createOrEditLabel/);
        mapNurseryWithSectorUpdatePage.cancel();
    });

    it('should create and save MapNurseryWithSectors', () => {
        mapNurseryWithSectorComponentsPage.clickOnCreateButton();
        mapNurseryWithSectorUpdatePage.setFromDateInput('2000-12-31');
        expect(mapNurseryWithSectorUpdatePage.getFromDateInput()).toMatch('2000-12-31');
        mapNurseryWithSectorUpdatePage.setToDateInput('2000-12-31');
        expect(mapNurseryWithSectorUpdatePage.getToDateInput()).toMatch('2000-12-31');
        mapNurseryWithSectorUpdatePage.setDescriptionInput('description');
        expect(mapNurseryWithSectorUpdatePage.getDescriptionInput()).toMatch('description');
        mapNurseryWithSectorUpdatePage.setStatusInput('5');
        expect(mapNurseryWithSectorUpdatePage.getStatusInput()).toMatch('5');
        mapNurseryWithSectorUpdatePage.nurserySelectLastOption();
        mapNurseryWithSectorUpdatePage.sectorSelectLastOption();
        mapNurseryWithSectorUpdatePage.save();
        expect(mapNurseryWithSectorUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
