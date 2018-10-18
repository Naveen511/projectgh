/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/22 11:27:58
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
import { INurseryStockDetails } from 'app/shared/model/nursery-stock-details.model';

type EntityResponseType = HttpResponse<INurseryStockDetails>;
type EntityArrayResponseType = HttpResponse<INurseryStockDetails[]>;

/**
 * This class used to communicate between spring boot and angular through api url.
 * Methods used - Create, update, find single record, find all, delete, list of data
 */
@Injectable({ providedIn: 'root' })
export class NurseryStockDetailsService {
    private resourceUrl = SERVER_API_URL + 'api/nursery-stock-details';

    constructor(private http: HttpClient) {}

    /**
     * Send a POST method request to create the nurseryStockDetails and
     * get created record as response
     *
     * @param nurseryStockDetails - single object
     * @returns object of values
     */
    create(nurseryStockDetails: INurseryStockDetails): Observable<EntityResponseType> {
        // Call function to convert date value into database format
        const copy = this.convertDateFromClient(nurseryStockDetails);
        return this.http
            .post<INurseryStockDetails>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    /**
     * Send a PUT method request to update the nurseryStockDetails and
     * get updated record as response
     *
     * @param nurseryStockDetails - single object
     * @returns object of values
     */
    update(nurseryStockDetails: INurseryStockDetails): Observable<EntityResponseType> {
        // Call function to convert date value into database format
        const copy = this.convertDateFromClient(nurseryStockDetails);
        return this.http
            .put<INurseryStockDetails>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    /**
     * Send particular row id in GET method to find particular nurseryStockDetails and
     * return an object
     *
     * @param id - autoincrement id
     * @returns object of values
     */
    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<INurseryStockDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    /**
     * List all the nurseryStockDetails. Pass a params like sort, page, total count,
     * filter, etc..
     *
     * @param req - contain multipe type value eg: page, totalCount,..
     * @returns Array of values
     */
    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<INurseryStockDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    /**
     * Send a DELETE method request to delete a particular nurseryStockDetails using Id
     *
     * @param id - autoincrement id
     * @returns boolean true or false
     */
    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    /**
     * Get a list of nursery stock details history of particular stock
     *
     * @param nurseryStockId - autoIncrement id of NurseryStock model
     * @returns Array of values
     */
    /* getParticularStocks(nurseryStockId: number): Observable<EntityArrayResponseType> {
        return this.http
            .get<INurseryStockDetails[]>(`${this.resourceUrl}/stock/${nurseryStockId}`, { observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    } */

    /**
     * List all the damge based on the status
     *
     * @param status
     * @returns Array of values
     */
    /* queryDamage(status: number): Observable<EntityArrayResponseType> {
        return this.http
            .get<INurseryStockDetails[]>(`${this.resourceUrl}/damage/${status}`, { observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    } */

    /**
     * List all the damge based on the status
     *
     * @param it_nursery_id - autoIncrement id of NurseryStock model
     * @returns Array of values
     */
    /* getParticularNurseryList(it_nursery_id: number): Observable<EntityArrayResponseType> {
        return this.http
            .get<INurseryStockDetails[]>(`${this.resourceUrl}/stock/itNurseryList/${it_nursery_id}`, { observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    } */

    /**
     * Convert the date into database format before send data to server
     *
     * @param nurseryStockDetails - single object
     * @returns Object of value
     */
    private convertDateFromClient(nurseryStockDetails: INurseryStockDetails): INurseryStockDetails {
        const copy: INurseryStockDetails = Object.assign({}, nurseryStockDetails, {
            date:
                nurseryStockDetails.date != null && nurseryStockDetails.date.isValid()
                    ? nurseryStockDetails.date.format(DATE_FORMAT)
                    : null
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
        res.body.forEach((nurseryStockDetails: INurseryStockDetails) => {
            nurseryStockDetails.date = nurseryStockDetails.date != null ? moment(nurseryStockDetails.date) : null;
        });
        return res;
    }
}
