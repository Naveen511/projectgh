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
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IShadeArea } from 'app/shared/model/shade-area.model';

type EntityResponseType = HttpResponse<IShadeArea>;
type EntityArrayResponseType = HttpResponse<IShadeArea[]>;

/**
 * This class used to communicate between spring boot and angular through api url.
 * Methods used - Create, update, find single record, find all, delete, list of data
 */
@Injectable({ providedIn: 'root' })
export class ShadeAreaService {
    private resourceUrl = SERVER_API_URL + 'api/shade-areas';

    constructor(private http: HttpClient) {}

    /**
     * Send a POST method request to create the shadeArea and
     * get created record as response
     *
     * @param shadeArea - single object
     * @returns object of values
     */
    create(shadeArea: IShadeArea): Observable<EntityResponseType> {
        // Call function to convert date value into database format
        const copy = this.convertDateFromClient(shadeArea);
        return this.http
            .post<IShadeArea>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    /**
     * Send a PUT method request to update the shadeArea and
     * get updated record as response
     *
     * @param shadeArea - single object
     * @returns object of values
     */
    update(shadeArea: IShadeArea): Observable<EntityResponseType> {
        // Call function to convert date value into database format
        const copy = this.convertDateFromClient(shadeArea);
        return this.http
            .put<IShadeArea>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    /**
     * Send particular row id in GET method to find particular shadeArea and
     * return an object
     *
     * @param id - autoincrement id
     * @returns object of values
     */
    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IShadeArea>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    /**
     * List all the shadeArea. Pass a params like sort, page, total count,
     * filter, etc..
     *
     * @param req - contain multipe type value eg: page, totalCount,..
     * @returns Array of values
     */
    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IShadeArea[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    /**
     * Send a DELETE method request to delete a particular shadeArea using Id
     *
     * @param id - autoincrement id
     * @returns boolean true or false
     */
    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    /**
     * Send particular row batch id in GET method to find particular batch shadeArea and
     * return an array of value
     *
     * @param batchId - autoincrement id of batch model
     * @returns Array of values
     */
    /* getParticularBatchRecord(batchId: number): Observable<EntityArrayResponseType> {
        return this.http
            .get<IShadeArea[]>(`${this.resourceUrl}/batch/${batchId}`, { observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    } */

    /**
     * Send particular row batch id in GET method to get shade area(seedlings) count.
     *
     * @param batchId - autoincrement id of batch model
     * @returns String value
     */
    getParticularBatchShadeCount(batchId: number): Observable<HttpResponse<String>> {
        return this.http.get<String>(`${this.resourceUrl}/count/${batchId}`, { observe: 'response' });
    }

    /**
     * Convert the date into database format before send data to server
     *
     * @param shadeArea - single object
     * @returns Object of value
     */
    private convertDateFromClient(shadeArea: IShadeArea): IShadeArea {
        const copy: IShadeArea = Object.assign({}, shadeArea, {
            date: shadeArea.date != null && shadeArea.date.isValid() ? shadeArea.date.format(DATE_FORMAT) : null
        });
        return copy;
    }

    /**
     * Convert the date into client format after receive data from server
     *
     * @param res - Object of value
     * @returns Object
     */
    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.date = res.body.date != null ? moment(res.body.date) : null;
        return res;
    }

    /**
     * Convert the date into client format after receive array of data from server
     *
     * @param res - array of value
     * @returns Array of value
     */
    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((shadeArea: IShadeArea) => {
            shadeArea.date = shadeArea.date != null ? moment(shadeArea.date) : null;
        });
        return res;
    }
}
