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
import { IZonal, STATUS_ACTIVE } from 'app/shared/model/zonal.model';

type EntityResponseType = HttpResponse<IZonal>;
type EntityArrayResponseType = HttpResponse<IZonal[]>;

/**
 * This class used to communicate between spring boot and angular through api url.
 * Methods used - Create, update, find single record, find all, delete, list of data
 */
@Injectable({ providedIn: 'root' })
export class ZonalService {
    private resourceUrl = SERVER_API_URL + 'api/zonals';

    constructor(private http: HttpClient) {}

    /** Send a POST method request to create the zonal and
     * get created record as response
     *
     * @param zonal - single object
     * @returns object of values
     */
    create(zonal: IZonal): Observable<EntityResponseType> {
        return this.http.post<IZonal>(this.resourceUrl, zonal, { observe: 'response' });
    }

    /**
     * Send a PUT method request to update the zonal and
     * get updated record as response
     *
     * @param zonal - single object
     * @returns object of values
     */
    update(zonal: IZonal): Observable<EntityResponseType> {
        return this.http.put<IZonal>(this.resourceUrl, zonal, { observe: 'response' });
    }

    /**
     * Send particular row id in GET method to find particular zonal and
     * return an object
     *
     * @param id - autoincrement id
     * @returns object of values
     */
    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IZonal>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    /**
     * List all the zonal. Pass a params like sort, page, total count, filter, etc..
     *
     * @param req - contain multipe type value eg: page, totalCount,..
     * @returns Array of values
     */
    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IZonal[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    /**
     * Send a DELETE method request to delete a particular zonal using Id
     *
     * @param id - autoincrement id
     * @returns boolean true or false
     */
    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    /**
     * List all the record based on the operationalHeadId
     *
     * @param operationalHeadId - autoIncrement id of OperationalHead model
     * @returns Array of values
     */
    /* getParticularHeadOfficeRecord(operationalHeadId: number): Observable<EntityArrayResponseType> {
        return this.http
            .get<IZonal[]>(`${this.resourceUrl}/headoffice/${operationalHeadId}/${STATUS_ACTIVE}`, { observe: 'response' });
    } */

    /**
     * Get the count of zonal based on active status
     *
     * @returns Mixed
     */
    getZonalCount(): Observable<HttpResponse<any>> {
        return this.http.get<any>(`${this.resourceUrl}/count/${STATUS_ACTIVE}`, { observe: 'response' });
    }
}
