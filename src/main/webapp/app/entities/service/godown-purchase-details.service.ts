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
import { IGodownPurchaseDetails } from 'app/shared/model/godown-purchase-details.model';

type EntityResponseType = HttpResponse<IGodownPurchaseDetails>;
type EntityArrayResponseType = HttpResponse<IGodownPurchaseDetails[]>;

/**
 * This class used to communicate between spring boot and angular through api url.
 * Methods used - Create, update, find single record, find all, delete, list of data
 */
@Injectable({ providedIn: 'root' })
export class GodownPurchaseDetailsService {
    private resourceUrl = SERVER_API_URL + 'api/godown-purchase-details';

    constructor(private http: HttpClient) {}

    /**
     * Send a POST method request to create the godownPurchaseDetails and
     * get created record as response
     *
     * @param godownPurchaseDetails - single object
     * @returns object of values
     */
    create(godownPurchaseDetails: IGodownPurchaseDetails): Observable<EntityResponseType> {
        // Call function to convert date value into database format
        const copy = this.convertDateFromClient(godownPurchaseDetails);
        return this.http
            .post<IGodownPurchaseDetails>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    /**
     * Send a PUT method request to update the godownPurchaseDetails and
     * get updated record as response
     *
     * @param godownPurchaseDetails - single object
     * @returns object of values
     */
    update(godownPurchaseDetails: IGodownPurchaseDetails): Observable<EntityResponseType> {
        // Call function to convert date value into database format
        const copy = this.convertDateFromClient(godownPurchaseDetails);
        return this.http
            .put<IGodownPurchaseDetails>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    /**
     * Send particular row id in GET method to find particular godownPurchaseDetails and
     * return an object
     *
     * @param id - autoincrement id
     * @returns object of values
     */
    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IGodownPurchaseDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    /**
     * List all the godownpurchase details. Pass a params like sort, page,
     * total count, filter, etc..
     *
     * @param req - contain multipe type value eg: page, totalCount,..
     * @returns Array of values
     */
    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IGodownPurchaseDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    /**
     * Send a DELETE method request to delete a particular godownpurchase details
     * using Id
     *
     * @param id - autoincrement id
     * @returns boolean true or false
     */
    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    /**
     * Convert the date into database format before send data to server
     *
     * @param godownPurchaseDetails - single object
     * @returns Object of value
     */
    private convertDateFromClient(godownPurchaseDetails: IGodownPurchaseDetails): IGodownPurchaseDetails {
        const copy: IGodownPurchaseDetails = Object.assign({}, godownPurchaseDetails, {
            date:
                godownPurchaseDetails.date != null && godownPurchaseDetails.date.isValid()
                    ? godownPurchaseDetails.date.format(DATE_FORMAT)
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
        res.body.forEach((godownPurchaseDetails: IGodownPurchaseDetails) => {
            godownPurchaseDetails.date = godownPurchaseDetails.date != null ? moment(godownPurchaseDetails.date) : null;
        });
        return res;
    }
}
