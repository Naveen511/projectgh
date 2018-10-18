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
import {
    IFinancialYearSettings, STATUS_ACTIVE
} from 'app/shared/model/financial-year-settings.model';

type EntityResponseType = HttpResponse<IFinancialYearSettings>;
type EntityArrayResponseType = HttpResponse<IFinancialYearSettings[]>;

/**
 * This class used to communicate between spring boot and angular through api url.
 * Methods used - Create, update, find single record, find all, delete, list of data
 */
@Injectable({ providedIn: 'root' })
export class FinancialYearSettingsService {
    private resourceUrl = SERVER_API_URL + 'api/financial-year-settings';

    constructor(private http: HttpClient) {}

    /**
     * Send a POST method request to create the financialYearSettings and
     * get created record as response
     *
     * @param financialYearSettings - single object
     * @returns object of values
     */
    create(financialYearSettings: IFinancialYearSettings): Observable<EntityResponseType> {
        // Call function to convert date value into database format
        const copy = this.convertDateFromClient(financialYearSettings);
        return this.http
            .post<IFinancialYearSettings>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    /**
     * Send a PUT method request to update the financialYearSettings and
     * get updated record as response
     *
     * @param financialYearSettings - single object
     * @returns object of values
     */
    update(financialYearSettings: IFinancialYearSettings): Observable<EntityResponseType> {
        // Call function to convert date value into database format
        const copy = this.convertDateFromClient(financialYearSettings);
        return this.http
            .put<IFinancialYearSettings>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    /**
     * Send particular row id in GET method to find particular financialYearSettings and
     * return an object
     *
     * @param id - autoincrement id
     * @returns object of values
     */
    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IFinancialYearSettings>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    /**
     * List all the financialYearSettings. Pass a params like sort, page, total count,
     * filter, etc..
     *
     * @param req - contain multipe type value eg: page, totalCount,..
     * @returns Array of values
     */
    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IFinancialYearSettings[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    /**
     * Send a DELETE method request to delete a particular financialYearSettings using Id
     *
     * @param id - autoincrement id
     * @returns boolean true or false
     */
    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    /**
     * Send status in GET method to get list of active record
     *
     * @returns Array of values
     */
    /* getActiveRecord(): Observable<EntityArrayResponseType> {
        return this.http
            .get<IFinancialYearSettings[]>(`${this.resourceUrl}/active-record/${STATUS_ACTIVE}`, { observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    } */

    /**
     * Convert the date into database format before send data to server
     *
     * @param financialYearSettings - single object
     * @returns Object of value
     */
    private convertDateFromClient(financialYearSettings: IFinancialYearSettings): IFinancialYearSettings {
        const copy: IFinancialYearSettings = Object.assign({}, financialYearSettings, {
            startDate:
                financialYearSettings.startDate != null && financialYearSettings.startDate.isValid()
                    ? financialYearSettings.startDate.format(DATE_FORMAT)
                    : null,
            endDate:
                financialYearSettings.endDate != null && financialYearSettings.endDate.isValid()
                    ? financialYearSettings.endDate.format(DATE_FORMAT)
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
        res.body.startDate = res.body.startDate != null ? moment(res.body.startDate) : null;
        res.body.endDate = res.body.endDate != null ? moment(res.body.endDate) : null;
        return res;
    }

    /**
     * Convert the date into client format after receive array of data from server
     *
     * @param res - array of value
     * @returns Array of value
     */
    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((financialYearSettings: IFinancialYearSettings) => {
            financialYearSettings.startDate = financialYearSettings.startDate != null ? moment(financialYearSettings.startDate) : null;
            financialYearSettings.endDate = financialYearSettings.endDate != null ? moment(financialYearSettings.endDate) : null;
        });
        return res;
    }
}
