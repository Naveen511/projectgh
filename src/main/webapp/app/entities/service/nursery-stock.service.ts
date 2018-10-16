/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/02 11:27:58
 *  Target: yarn
 *******************************************************************************/

// Import needed model and dependency
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { INurseryStock } from 'app/shared/model/nursery-stock.model';

type EntityResponseType = HttpResponse<INurseryStock>;
type EntityArrayResponseType = HttpResponse<INurseryStock[]>;

/**
 * This class used to communicate between spring boot and angular through api url.
 * Methods used - Create, update, find single record, find all, delete, list of data
 */
@Injectable({ providedIn: 'root' })
export class NurseryStockService {
    private resourceUrl = SERVER_API_URL + 'api/nursery-stocks';

    constructor(private http: HttpClient) {}

    /** Send a POST method request to create the nurseryStock and
     * get created record as response
     *
     * @param nurseryStock - single object
     * @returns object of values
     */
    create(nurseryStock: INurseryStock): Observable<EntityResponseType> {
        return this.http.post<INurseryStock>(this.resourceUrl, nurseryStock, { observe: 'response' });
    }

    /**
     * Send a PUT method request to update the nurseryStock and
     * get updated record as response
     *
     * @param nurseryStock - single object
     * @returns object of values
     */
    update(nurseryStock: INurseryStock): Observable<EntityResponseType> {
        return this.http.put<INurseryStock>(this.resourceUrl, nurseryStock, { observe: 'response' });
    }

    /**
     * Send particular row id in GET method to find particular nurseryStock and
     * return an object
     *
     * @param id - autoincrement id
     * @returns object of values
     */
    find(id: number): Observable<EntityResponseType> {
        return this.http.get<INurseryStock>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    /**
     * List all the nurseryStock. Pass a params like sort, page, total count, filter, etc..
     *
     * @param req - contain multipe type value eg: page, totalCount,..
     * @returns Array of values
     */
    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<INurseryStock[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    /**
     * Send a DELETE method request to delete a particular nurseryStock using Id
     *
     * @param id - autoincrement id
     * @returns boolean true or false
     */
    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    /**
     * Get list of nurser stock based on particular category and nursery
     *
     * @param nurseryId - autoIncrement id of Nursery model
     * @param pickListCategoryId - autoIncrement id of PickListValue model
     * @returns Array of values
     */
    /* getNurseryCategoryStock(nurseryId: number, pickListCategoryId: number): Observable<EntityArrayResponseType> {
        return this.http
            .get<INurseryStock[]>(`${this.resourceUrl}/stock/${nurseryId}/${pickListCategoryId}`, { observe: 'response' });
    } */

    /**
     * Get list of nurser stock based on particular nursery
     *
     * @param nurseryId - autoIncrement id of Nursery model
     * @returns Array of values
     */
    /* particularNursery(nurseryId: number): Observable<EntityArrayResponseType> {
        return this.http.get<INurseryStock[]>(`${this.resourceUrl}/nursery/${nurseryId}`, { observe: 'response' });
        // .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    } */
}
