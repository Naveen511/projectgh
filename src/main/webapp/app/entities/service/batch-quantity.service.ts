/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/10/01 11:27:58
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
import { IBatchQuantity } from 'app/shared/model/batch-quantity.model';

type EntityResponseType = HttpResponse<IBatchQuantity>;
type EntityArrayResponseType = HttpResponse<IBatchQuantity[]>;

/**
 * This class used to communicate between spring boot and angular through api url.
 * Methods used - Create, update, find single record, find all, delete, list of data
 */
@Injectable({ providedIn: 'root' })
export class BatchQuantityService {
    private resourceUrl = SERVER_API_URL + 'api/batch-quantities';

    constructor(private http: HttpClient) {}

    /**
     * Send a POST method request to create the batch quantity and
     * get created record as response
     *
     * @param batchQuantity - single object
     * @returns object of values
     */
    create(batchQuantity: IBatchQuantity): Observable<EntityResponseType> {
        // Call function to convert date value into database format
        const copy = this.convertDateFromClient(batchQuantity);
        return this.http
            .post<IBatchQuantity>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    /**
     * Send a PUT method request to update the batch quantity and
     * get updated record as response
     *
     * @param batchQuantity - single object
     * @returns object of values
     */
    update(batchQuantity: IBatchQuantity): Observable<EntityResponseType> {
        // Call function to convert date value into database format
        const copy = this.convertDateFromClient(batchQuantity);
        return this.http
            .put<IBatchQuantity>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    /**
     * Send particular row id in GET method to find particular batch quantity and
     * return an object
     *
     * @param id - autoincrement id
     * @returns object of values
     */
    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IBatchQuantity>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    /**
     * List all the batch quantity. Pass a params like sort, page, total count,
     * filter, etc..
     *
     * @param req - contain multipe type value eg: page, totalCount,..
     * @returns Array of values
     */
    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IBatchQuantity[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    /**
     * Send a DELETE method request to delete a particular batch quantity using Id
     *
     * @param id - autoincrement id
     * @returns boolean true or false
     */
    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    /**
     * Send particular row batch id in GET method to find particular batch quantity and
     * return an array of value
     *
     * @param batchId - autoincrement id of batch model
     * @returns Array of values
     */
    /* getParticularBatchRecord(batchId: number): Observable<EntityArrayResponseType> {
        return this.http
            .get<IBatchQuantity[]>(`${this.resourceUrl}/batch/${batchId}`, { observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    } */

    /**
     * Convert the date into database format before send data to server
     *
     * @param batchQuantity single object
     * @returns Object of value
     */
    private convertDateFromClient(batchQuantity: IBatchQuantity): IBatchQuantity {
        const copy: IBatchQuantity = Object.assign({}, batchQuantity, {
            date: batchQuantity.date != null && batchQuantity.date.isValid() ? batchQuantity.date.format(DATE_FORMAT) : null
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
        res.body.forEach((batchQuantity: IBatchQuantity) => {
            batchQuantity.date = batchQuantity.date != null ? moment(batchQuantity.date) : null;
        });
        return res;
    }
}
