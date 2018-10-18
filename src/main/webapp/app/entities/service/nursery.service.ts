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
import { INursery } from 'app/shared/model/nursery.model';
import { STATUS_ACTIVE } from 'app/shared/constants/input.constants';

type EntityResponseType = HttpResponse<INursery>;
type EntityArrayResponseType = HttpResponse<INursery[]>;

/**
 * This class used to communicate between spring boot and angular through api url
 * Methods - Create, update, find single record, find all, delete, list of data
 */
@Injectable({ providedIn: 'root' })
export class NurseryService {
    private resourceUrl = SERVER_API_URL + 'api/nurseries';

    constructor(private http: HttpClient) { }

    /**
     * Send a POST method request to create the nursery and
     * get created record as response
     *
     * @param nursery - single object
     * @returns object of values
     */
    create(nursery: INursery): Observable<EntityResponseType> {
        return this.http
            .post<INursery>(this.resourceUrl, nursery, { observe: 'response' });
    }

    /**
     * Send a PUT method request to update the nursery and
     * get updated record as response
     *
     * @param nursery - single object
     * @returns object of values
     */
    update(nursery: INursery): Observable<EntityResponseType> {
        return this.http
            .put<INursery>(this.resourceUrl, nursery, { observe: 'response' });
    }

    /**
     * Send particular row id in GET method to find particular nursery and
     * return an object
     *
     * @param id - autoincrement id
     * @returns object of values
     */
    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<INursery>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    /**
     * List all the nursery. Pass a params like sort, page, totalCount, filter, etc..
     *
     * @param req - contain multipe type value eg: page, totalCount,..
     * @returns Array of values
     */
    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<INursery[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    /**
     * Send a DELETE method request to delete a particular nursery using Id
     *
     * @param id - autoincrement id
     * @returns boolean true or false
     */
    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    /**
     * Load particular sector nursery list
     *
     * @param sectorId - autoIncrement id of Sector model
     * @returns Array of values
     */
    /* getNurserys(sectorId: number): Observable<EntityArrayResponseType> {
        return this.http
            .get<INursery[]>(`${this.resourceUrl}/sector/${sectorId}`, { observe: 'response' });
    } */

    /**
     * Get the count of nursery based on active status
     *
     * @returns Mixed
     */
    getNurseryCount(): Observable<HttpResponse<any>> {
        return this.http.get<any>(`${this.resourceUrl}/count/${STATUS_ACTIVE}`, { observe: 'response' });
    }

    // getNurserys(sectorId: number): Observable<EntityArrayResponseType> {
    //     return this.http
    //         .get<INursery[]>(`${this.resourceUrl}/sector/${sectorId}`, { observe: 'response' })
    //         .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    // }

    // getMovedNursery(zoneId: number, sectorId: number): Observable<EntityArrayResponseType> {
    //     return this.http
    //         .get<INursery[]>(`${this.resourceUrl}/movednursery/${zoneId}/${sectorId}`, { observe: 'response' });
    // }
}
