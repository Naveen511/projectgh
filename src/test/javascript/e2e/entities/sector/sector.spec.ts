import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { SectorComponentsPage, SectorUpdatePage } from './sector.page-object';

describe('Sector e2e test', () => {
    let navBarPage: NavBarPage;
    let sectorUpdatePage: SectorUpdatePage;
    let sectorComponentsPage: SectorComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Sectors', () => {
        navBarPage.goToEntity('sector');
        sectorComponentsPage = new SectorComponentsPage();
        expect(sectorComponentsPage.getTitle()).toMatch(/projectghApp.sector.home.title/);
    });

    it('should load create Sector page', () => {
        sectorComponentsPage.clickOnCreateButton();
        sectorUpdatePage = new SectorUpdatePage();
        expect(sectorUpdatePage.getPageTitle()).toMatch(/projectghApp.sector.home.createOrEditLabel/);
        sectorUpdatePage.cancel();
    });

    it('should create and save Sectors', () => {
        sectorComponentsPage.clickOnCreateButton();
        sectorUpdatePage.setSectorNameInput('sectorName');
        expect(sectorUpdatePage.getSectorNameInput()).toMatch('sectorName');
        sectorUpdatePage.setStatusInput('5');
        expect(sectorUpdatePage.getStatusInput()).toMatch('5');
        sectorUpdatePage.zonalSelectLastOption();
        sectorUpdatePage.financialYearSectorSelectLastOption();
        sectorUpdatePage.save();
        expect(sectorUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
