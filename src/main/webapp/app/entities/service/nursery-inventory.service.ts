/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/22 11:27:58
 *  Target: yarn
 *******************************************************************************/

// Import needed model and dependency
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { INurseryInventory } from 'app/shared/model/nursery-inventory.model';

type EntityResponseType = HttpResponse<INurseryInventory>;
type EntityArrayResponseType = HttpResponse<INurseryInventory[]>;

/**
 * This class used to communicate between spring boot and angular through api url.
 * Methods used - Create, update, find single record, find all, delete, list of data
 */
@Injectable({ providedIn: 'root' })
export class NurseryInventoryService {
    private resourceUrl = SERVER_API_URL + 'api/nursery-inventories';

    constructor(private http: HttpClient) {}

    /**
     * Send a POST method request to create the Nursery Inventory of Seeds and Cover,
     * get created record as response
     *
     * @param nurseryInventory - single object
     * @returns object of values
     */
    create(nurseryInventory: INurseryInventory): Observable<EntityResponseType> {
        return this.http
            .post<INurseryInventory>(this.resourceUrl, nurseryInventory, { observe: 'response' });
    }

    /**
     * Send a PUT method request to update the Nursery Inventory of Seeds and Cover,
     * get updated record as response
     *
     * @param nurseryInventory - single object
     * @returns object of values
     */
    update(nurseryInventory: INurseryInventory): Observable<EntityResponseType> {
        return this.http
            .put<INurseryInventory>(this.resourceUrl, nurseryInventory, { observe: 'response' });
    }

    /**
     * Send particular row id in GET method to find particular nurseryInventory and
     * return an object
     *
     * @param id - autoincrement id
     * @returns object of values
     */
    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<INurseryInventory>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    /**
     * List all the nurseryInventory. Pass a params like sort, page,
     * totalCount, filter, etc..
     *
     * @param req - contain multipe type value eg: page, totalCount,..
     * @returns Array of values
     */
    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
         return this.http
             .get<INurseryInventory[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    /**
     * Send a DELETE method request to delete a particular nurseryInventory using Id
     *
     * @param id - autoincrement id
     * @returns boolean true or false
     */
    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
