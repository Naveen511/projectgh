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
import { IMotherBed, STATUS_AVAILABLE } from 'app/shared/model/mother-bed.model';

type EntityResponseType = HttpResponse<IMotherBed>;
type EntityArrayResponseType = HttpResponse<IMotherBed[]>;

/**
 * This class used to communicate between spring boot and angular through api url
 * Methods - Create, update, find single record, find all, delete, list of data
 */
@Injectable({ providedIn: 'root' })
export class MotherBedService {
    private resourceUrl = SERVER_API_URL + 'api/mother-beds';

    constructor(private http: HttpClient) {}

    /** Send a POST method request to create the Motherbed and
     * get created record as response
     *
     * @param Motherbed - single object
     * @returns object of values
     */
    create(motherBed: IMotherBed): Observable<EntityResponseType> {
        return this.http.post<IMotherBed>(this.resourceUrl, motherBed, { observe: 'response' });
    }

    /**
     * Send a PUT method request to update the motherBed and
     * get updated record as response
     *
     * @param motherBed - single object
     * @returns object of values
     */
    update(motherBed: IMotherBed): Observable<EntityResponseType> {
        return this.http.put<IMotherBed>(this.resourceUrl, motherBed, { observe: 'response' });
    }

    /**
     * Send particular row id in GET method to find particular motherBed and
     * return an object
     *
     * @param id - autoincrement id
     * @returns object of values
     */
    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IMotherBed>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    /**
     * List all the motherBed. Pass a params like sort, page, total count, filter, etc..
     *
     * @param req - contain multipe type value eg: page, totalCount,..
     * @returns Array of values
     */
    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IMotherBed[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    /**
     * Send a DELETE method request to delete a particular motherBed using Id
     *
     * @param id - autoincrement id
     * @returns boolean true or false
     */
    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    /**
     * Get Motherbed details based on the nursery Id
     *
     * @param nurseryId - autoincrement id of Nursery model
     * @returns Array of values
     */
    /* getMotherBed(nurseryId: number): Observable<EntityArrayResponseType> {
        return this.http.get<IMotherBed[]>(`${this.resourceUrl}/nursery/${nurseryId}`, { observe: 'response' });
    } */

    /**
     * Get Motherbed details based on the nursery Id and available status
     *
     * @param nurseryId - autoincrement id of Nursery model
     * @returns Array of values
     */
    /* getMotherBedNursery(nurseryId: number): Observable<EntityArrayResponseType> {
        return this.http.get<IMotherBed[]>(`${this.resourceUrl}/nursery/${nurseryId}/${STATUS_AVAILABLE}`, { observe: 'response' });
    } */
}
