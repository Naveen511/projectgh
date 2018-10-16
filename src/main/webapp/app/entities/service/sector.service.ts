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
import { ISector } from 'app/shared/model/sector.model';
import { STATUS_ACTIVE } from 'app/shared/constants/input.constants';

type EntityResponseType = HttpResponse<ISector>;
type EntityArrayResponseType = HttpResponse<ISector[]>;

/**
 * This class used to communicate between spring boot and angular through api url.
 * Methods used - Create, update, find single record, find all, delete, list of data
 */
@Injectable({ providedIn: 'root' })
export class SectorService {
    private resourceUrl = SERVER_API_URL + 'api/sectors';

    constructor(private http: HttpClient) {}

    /** Send a POST method request to create the sector and
     * get created record as response
     *
     * @param sector - single object
     * @returns object of values
     */
    create(sector: ISector): Observable<EntityResponseType> {
        return this.http.post<ISector>(this.resourceUrl, sector, { observe: 'response' });
    }

    /**
     * Send a PUT method request to update the sector and
     * get updated record as response
     *
     * @param sector - single object
     * @returns object of values
     */
    update(sector: ISector): Observable<EntityResponseType> {
        return this.http.put<ISector>(this.resourceUrl, sector, { observe: 'response' });
    }

    /**
     * Send particular row id in GET method to find particular sector and
     * return an object
     *
     * @param id - autoincrement id
     * @returns object of values
     */
    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ISector>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    /**
     * List all the sector. Pass a params like sort, page, total count, filter, etc..
     *
     * @param req - contain multipe type value eg: page, totalCount,..
     * @returns Array of values
     */
    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ISector[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    /**
     * Send a DELETE method request to delete a particular sector using Id
     *
     * @param id - autoincrement id
     * @returns boolean true or false
     */
    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    /**
     * Get the list of sectors based on zonal id
     *
     * @param zonalId - autoIncrement id of zonal model
     * @returns Array of values
     */
    /* getSectors(zonalId: number): Observable<EntityArrayResponseType> {
        return this.http
            .get<ISector[]>(`${this.resourceUrl}/zonal/${zonalId}`, { observe: 'response' });
    } */

    /**
     * Get the count of sector based on active status
     *
     * @returns Mixed
     */
    getSectorCount(): Observable<HttpResponse<any>> {
        return this.http.get<any>(`${this.resourceUrl}/count/${STATUS_ACTIVE}`, { observe: 'response' });
    }
}
