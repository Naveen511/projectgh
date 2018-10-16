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
import { IOperationalHead, STATUS_ACTIVE } from 'app/shared/model/operational-head.model';

type EntityResponseType = HttpResponse<IOperationalHead>;
type EntityArrayResponseType = HttpResponse<IOperationalHead[]>;

/**
 * This class used to communicate between spring boot and angular through api url.
 * Methods used - Create, update, find single record, find all, delete, list of data
 */
@Injectable({ providedIn: 'root' })
export class OperationalHeadService {
    private resourceUrl = SERVER_API_URL + 'api/operational-heads';

    constructor(private http: HttpClient) {}

    /** Send a POST method request to create the head office and
     * get created record as response
     *
     * @param operationalHead - single object
     *
     * @returns object of values
     */
    create(operationalHead: IOperationalHead): Observable<EntityResponseType> {
        return this.http.post<IOperationalHead>(this.resourceUrl, operationalHead, { observe: 'response' });
    }

    /**
     * Send a PUT method request to update the head office and
     * get updated record as response
     *
     * @param operationalHead - single object
     *
     * @returns object of values
     */
    update(operationalHead: IOperationalHead): Observable<EntityResponseType> {
        return this.http.put<IOperationalHead>(this.resourceUrl, operationalHead, { observe: 'response' });
    }

    /**
     * Send particular row id in GET method to find particular head office and
     * return the object
     *
     * @param id - autoincrement id
     *
     * @returns object of values
     */
    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IOperationalHead>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    /**
     * List all the head office. Pass a params like sort, page, totalCount, filter, etc..
     *
     * @param req contain multipe type value eg: page, totalCount,..
     *
     * @returns Array of values
     */
    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IOperationalHead[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    /**
     * Send a DELETE method request to delete a particular head office using Id
     *
     * @param id - autoincrement id
     *
     * @returns boolean true or false
     */
    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    /**
     * List all the record based on the active status
     *
     * @returns Array of values
     */
    /* getActiveList(): Observable<EntityArrayResponseType> {
        return this.http
            .get<IOperationalHead[]>(`${this.resourceUrl}/active-record/${STATUS_ACTIVE}`, { observe: 'response' });
    } */

    /**
     * Get the count of head office based on active status
     *
     * @returns Mixed
     */
    getOperationalHeadCount(): Observable<HttpResponse<any>> {
        return this.http.get<any>(`${this.resourceUrl}/count/${STATUS_ACTIVE}`, { observe: 'response' });
    }
}
