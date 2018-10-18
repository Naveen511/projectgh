/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/08/10 11:27:58
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
import { ICoverFillingDetails } from 'app/shared/model/cover-filling-details.model';

type EntityResponseType = HttpResponse<ICoverFillingDetails>;
type EntityArrayResponseType = HttpResponse<ICoverFillingDetails[]>;

/**
 * This class used to communicate between spring boot and angular through api url.
 * Methods used - Create, update, find single record, find all, delete, list of data
 */
@Injectable({ providedIn: 'root' })
export class CoverFillingDetailsService {
    private resourceUrl = SERVER_API_URL + 'api/cover-filling-details';

    constructor(private http: HttpClient) {}

    /**
     * Send a POST method request to create the coverFillingDetails and
     * get created record as response
     *
     * @param coverFillingDetails - single object
     * @returns object of values
     */
    create(coverFillingDetails: ICoverFillingDetails): Observable<EntityResponseType> {
        // Call function to convert date value into database format
        const copy = this.convertDateFromClient(coverFillingDetails);
        return this.http
            .post<ICoverFillingDetails>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    /**
     * Send a PUT method request to update the coverFillingDetails and
     * get updated record as response
     *
     * @param coverFillingDetails - single object
     * @returns object of values
     */
    update(coverFillingDetails: ICoverFillingDetails): Observable<EntityResponseType> {
        // Call function to convert date value into database format
        const copy = this.convertDateFromClient(coverFillingDetails);
        return this.http
            .put<ICoverFillingDetails>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    /**
     * Send particular row id in GET method to find particular coverFillingDetails and
     * return an object
     *
     * @param id - autoincrement id
     * @returns object of values
     */
    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ICoverFillingDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    /**
     * List all the coverFillingDetails. Pass a params like sort, page, total count,
     * filter, etc..
     *
     * @param req - contain multipe type value eg: page, totalCount,..
     * @returns Array of values
     */
    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ICoverFillingDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    /**
     * Send a DELETE method request to delete a particular coverFillingDetails using Id
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
     * @param coverFilling single object
     * @returns Object of value
     */
    private convertDateFromClient(coverFillingDetails: ICoverFillingDetails): ICoverFillingDetails {
        const copy: ICoverFillingDetails = Object.assign({}, coverFillingDetails, {
            date:
                coverFillingDetails.date != null && coverFillingDetails.date.isValid() ? coverFillingDetails.date.format(DATE_FORMAT) : null
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
        res.body.forEach((coverFillingDetails: ICoverFillingDetails) => {
            coverFillingDetails.date = coverFillingDetails.date != null ? moment(coverFillingDetails.date) : null;
        });
        return res;
    }
}
