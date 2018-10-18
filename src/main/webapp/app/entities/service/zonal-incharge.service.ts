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
import { IZonalIncharge } from 'app/shared/model/zonal-incharge.model';

type EntityResponseType = HttpResponse<IZonalIncharge>;
type EntityArrayResponseType = HttpResponse<IZonalIncharge[]>;

/**
 * This class used to communicate between spring boot and angular through api url.
 * Methods used - Create, update, find single record, find all, delete, list of data
 */
@Injectable({ providedIn: 'root' })
export class ZonalInchargeService {
    private resourceUrl = SERVER_API_URL + 'api/zonal-incharges';

    constructor(private http: HttpClient) {}

    /**
     * Send a POST method request to create the zonalIncharge and
     * get created record as response
     *
     * @param zonalIncharge - single object
     * @returns object of values
     */
    create(zonalIncharge: IZonalIncharge): Observable<EntityResponseType> {
        // Call function to convert date value into database format
        const copy = this.convertDateFromClient(zonalIncharge);
        return this.http
            .post<IZonalIncharge>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    /**
     * Send a PUT method request to update the zonalIncharge and
     * get updated record as response
     *
     * @param zonalIncharge - single object
     * @returns object of values
     */
    update(zonalIncharge: IZonalIncharge): Observable<EntityResponseType> {
        // Call function to convert date value into database format
        const copy = this.convertDateFromClient(zonalIncharge);
        return this.http
            .put<IZonalIncharge>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    /**
     * Send particular row id in GET method to find particular zonalIncharge and
     * return an object
     *
     * @param id - autoincrement id
     * @returns object of values
     */
    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IZonalIncharge>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    /**
     * List all the zonalIncharge. Pass a params like sort, page, total count,
     * filter, etc..
     *
     * @param req - contain multipe type value eg: page, totalCount,..
     * @returns Array of values
     */
    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IZonalIncharge[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    /**
     * Send a DELETE method request to delete a particular zonalIncharge using Id
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
     * @param zonalIncharge - single object
     * @returns Object of value
     */
    private convertDateFromClient(zonalIncharge: IZonalIncharge): IZonalIncharge {
        const copy: IZonalIncharge = Object.assign({}, zonalIncharge, {
            fromDate:
                zonalIncharge.fromDate != null && zonalIncharge.fromDate.isValid() ? zonalIncharge.fromDate.format(DATE_FORMAT) : null,
            toDate: zonalIncharge.toDate != null && zonalIncharge.toDate.isValid() ? zonalIncharge.toDate.format(DATE_FORMAT) : null
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
        res.body.fromDate = res.body.fromDate != null ? moment(res.body.fromDate) : null;
        res.body.toDate = res.body.toDate != null ? moment(res.body.toDate) : null;
        return res;
    }

    /**
     * Convert the date into client format after receive array of data from server
     *
     * @param res - array of value
     * @returns Array of value
     */
    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((zonalIncharge: IZonalIncharge) => {
            zonalIncharge.fromDate = zonalIncharge.fromDate != null ? moment(zonalIncharge.fromDate) : null;
            zonalIncharge.toDate = zonalIncharge.toDate != null ? moment(zonalIncharge.toDate) : null;
        });
        return res;
    }
}
