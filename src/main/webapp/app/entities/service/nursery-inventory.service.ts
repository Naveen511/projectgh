import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { INurseryInventory } from 'app/shared/model/nursery-inventory.model';

type EntityResponseType = HttpResponse<INurseryInventory>;
type EntityArrayResponseType = HttpResponse<INurseryInventory[]>;

@Injectable({ providedIn: 'root' })
export class NurseryInventoryService {
    private resourceUrl = SERVER_API_URL + 'api/nursery-inventories';

    constructor(private http: HttpClient) {}

    // Create Nursery Inventory of Seeds and Cover
    create(nurseryInventory: INurseryInventory): Observable<EntityResponseType> {
        return this.http
            .post<INurseryInventory>(this.resourceUrl, nurseryInventory, { observe: 'response' });
    }

    // Update the nursery Inventory
    update(nurseryInventory: INurseryInventory): Observable<EntityResponseType> {
        return this.http
            .put<INurseryInventory>(this.resourceUrl, nurseryInventory, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<INurseryInventory>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
         return this.http
             .get<INurseryInventory[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    getNurseryCategoryInventory(nurseryId: number, pickListCategoryId: number): Observable<EntityArrayResponseType> {
        return this.http
            .get<INurseryInventory[]>(`${this.resourceUrl}/inventory/${nurseryId}/${pickListCategoryId}`, { observe: 'response' });
    }

    // To get cover inventory details
    getCoverInventory(nurseryId: number, status: number): Observable<EntityArrayResponseType> {
        console.log(nurseryId);
        return this.http
            .get<INurseryInventory[]>(`${this.resourceUrl}/inventory/cover/${nurseryId}/${status}`, { observe: 'response' });
    }

    // To get the seeds details
    queryGetSeedsList(status: number): Observable<EntityArrayResponseType> {
        return this.http
            .get<INurseryInventory[]>(`${this.resourceUrl}/inventory/seeds/${status}`, {observe: 'response' });
    }

    // To get the cover details
    queryGetCoverList(status: number): Observable<EntityArrayResponseType> {
        return this.http.get<INurseryInventory[]>(`${this.resourceUrl}/inventory/seeds/${status}`, {observe: 'response' });
    }
}
