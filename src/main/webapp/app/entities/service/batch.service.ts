/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/06 11:27:58
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
import { IBatch } from 'app/shared/model/batch.model';

type EntityResponseType = HttpResponse<IBatch>;
type EntityArrayResponseType = HttpResponse<IBatch[]>;

/**
 * This class used to communicate between spring boot and angular through api url.
 * Methods used - Create, update, find single record, find all, delete, list of data
 */
@Injectable({ providedIn: 'root' })
export class BatchService {
    private resourceUrl = SERVER_API_URL + 'api/batches';

    constructor(private http: HttpClient) {}

    /** Send a POST method request to create the batch and get created record as response
     *
     * @param batch - single object
     * @returns object of values
     */
    create(batch: IBatch): Observable<EntityResponseType> {
        // Call function to convert date value into database format
        const copy = this.convertDateFromClient(batch);
        return this.http
            .post<IBatch>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    /**
     * Send a PUT method request to update the batch and get updated record as response
     *
     * @param batch - single object
     * @returns object of values
     */
    update(batch: IBatch): Observable<EntityResponseType> {
        // Call function to convert date value into database format
        const copy = this.convertDateFromClient(batch);
        return this.http
            .put<IBatch>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    /**
     * Send particular row id in GET method to find particular batch and
     * return an object
     *
     * @param id - autoincrement id
     * @returns object of values
     */
    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IBatch>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    /**
     * List all the batch. Pass a params like sort, page, total count,
     * filter, etc..
     *
     * @param req - contain multipe type value eg: page, totalCount,..
     * @returns Array of values
     */
    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IBatch[]>(this.resourceUrl, { params: options, observe: 'response' })
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

    // Get all the record of batch based on filter
    /**
     * Send a request to get list of record based on filter data
     *
     * @param fromDate - as date
     * @param toDate - as date
     * @returns Array of values
     */
    getReport(fromDate: any, toDate: any): Observable<EntityArrayResponseType> {
        // console.log(fromDate.format(DATE_FORMAT));
        // console.log(fromDate);
        return this.http
            .get<IBatch[]>(`${this.resourceUrl}/filter/${fromDate.format(DATE_FORMAT)}/${toDate.format(DATE_FORMAT)}`, {
                observe: 'response'
            })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    /**
     * Convert the date into database format before send data to server
     *
     * @param batch - single object
     * @returns Object of value
     */
    private convertDateFromClient(batch: IBatch): IBatch {
        const copy: IBatch = Object.assign({}, batch, {
            sowingDate: batch.sowingDate != null && batch.sowingDate.isValid() ? batch.sowingDate.format(DATE_FORMAT) : null,
            closedDate: batch.closedDate != null && batch.closedDate.isValid() ? batch.closedDate.format(DATE_FORMAT) : null
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
        res.body.sowingDate = res.body.sowingDate != null ? moment(res.body.sowingDate) : null;
        res.body.closedDate = res.body.closedDate != null ? moment(res.body.closedDate) : null;
        return res;
    }

    /**
     * Convert the date into client format after receive array of data from server
     *
     * @param res - array of value
     * @returns Array of value
     */
    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((batch: IBatch) => {
            batch.sowingDate = batch.sowingDate != null ? moment(batch.sowingDate) : null;
            batch.closedDate = batch.closedDate != null ? moment(batch.closedDate) : null;
        });
        return res;
    }
}
