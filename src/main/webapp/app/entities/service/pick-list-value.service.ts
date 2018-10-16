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
import { IPickListValue } from 'app/shared/model/pick-list-value.model';

type EntityResponseType = HttpResponse<IPickListValue>;
type EntityArrayResponseType = HttpResponse<IPickListValue[]>;

/**
 * This class used to communicate between spring boot and angular through api url.
 * Methods used - Create, update, find single record, find all, delete, list of data
 */
@Injectable({ providedIn: 'root' })
export class PickListValueService {
    private resourceUrl = SERVER_API_URL + 'api/pick-list-values';

    constructor(private http: HttpClient) {}

    /** Send a POST method request to create the pickListValue and
     * get created record as response
     *
     * @param pickListValue - single object
     * @returns object of values
     */
    create(pickListValue: IPickListValue): Observable<EntityResponseType> {
        return this.http.post<IPickListValue>(this.resourceUrl, pickListValue, { observe: 'response' });
    }

    /**
     * Send a PUT method request to update the pickListValue and
     * get updated record as response
     *
     * @param pickListValue - single object
     * @returns object of values
     */
    update(pickListValue: IPickListValue): Observable<EntityResponseType> {
        return this.http.put<IPickListValue>(this.resourceUrl, pickListValue, { observe: 'response' });
    }

    /**
     * Send particular row id in GET method to find particular pickListValue and
     * return an object
     *
     * @param id - autoincrement id
     * @returns object of values
     */
    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPickListValue>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    /**
     * List all the pickListValue. Pass a params like sort, page, total count,
     * filter, etc..
     *
     * @param req - contain multipe type value eg: page, totalCount,..
     * @returns Array of values
     */
    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPickListValue[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    /**
     * Send a DELETE method request to delete a particular pickListValue using Id
     *
     * @param id - autoincrement id
     * @returns boolean true or false
     */
    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    /**
     * Get list of variety details based on pickList table id
     *
     * @param pickListId - autoIncrement id of pickList model
     * @returns Array of values
     */
    /* getVariety(pickListId: number): Observable<EntityArrayResponseType> {
        return this.http
            .get<IPickListValue[]>(`${this.resourceUrl}/variety/${pickListId}`, { observe: 'response' });
    } */

    /**
     * Get list of category details based on pickListValue table id
     *
     * @param pickValueId - autoIncrement id of pickListValue model
     * @returns Array of values
     */
    /* getCategory(pickValueId: number): Observable<EntityArrayResponseType> {
        return this.http
            .get<IPickListValue[]>(`${this.resourceUrl}/category/${pickValueId}`, { observe: 'response' });
    } */
}
