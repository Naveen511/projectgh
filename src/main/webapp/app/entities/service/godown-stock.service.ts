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
import { IGodownStock } from 'app/shared/model/godown-stock.model';

type EntityResponseType = HttpResponse<IGodownStock>;
type EntityArrayResponseType = HttpResponse<IGodownStock[]>;

/**
 * This class used to communicate between spring boot and angular through api url.
 * Methods used - Create, update, find single record, find all, delete, list of data
 */
@Injectable({ providedIn: 'root' })
export class GodownStockService {
    private resourceUrl = SERVER_API_URL + 'api/godown-stocks';

    constructor(private http: HttpClient) {}

    /** Send a POST method request to create the godownStock and
     * get created record as response
     *
     * @param godownStock - single object
     * @returns object of values
     */
    create(godownStock: IGodownStock): Observable<EntityResponseType> {
        return this.http.post<IGodownStock>(this.resourceUrl, godownStock, { observe: 'response' });
    }

    /**
     * Send a PUT method request to update the godownStock and
     * get updated record as response
     *
     * @param godownStock - single object
     * @returns object of values
     */
    update(godownStock: IGodownStock): Observable<EntityResponseType> {
        return this.http.put<IGodownStock>(this.resourceUrl, godownStock, { observe: 'response' });
    }

    /**
     * Send particular row id in GET method to find particular godownStock
     * and return an object
     *
     * @param id - autoincrement id
     * @returns object of values
     */
    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IGodownStock>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    /**
     * List all the godownStock. Pass a params like sort, page, total count,
     * filter, etc..
     *
     * @param req - contain multipe type value eg: page, totalCount,..
     * @returns Array of values
     */
    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IGodownStock[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    /**
     * Send a DELETE method request to delete a particular godownStock using Id
     *
     * @param id - autoincrement id
     * @returns boolean true or false
     */
    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
