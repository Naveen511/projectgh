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
import { IMapZonalWithOh, STATUS_ACTIVE } from 'app/shared/model/map-zonal-with-oh.model';

type EntityResponseType = HttpResponse<IMapZonalWithOh>;
type EntityArrayResponseType = HttpResponse<IMapZonalWithOh[]>;

/**
 * This class used to communicate between spring boot and angular through api url.
 * Methods used - Create, update, find single record, find all, delete, list of data
 */
@Injectable({ providedIn: 'root' })
export class MapZonalWithOhService {
    private resourceUrl = SERVER_API_URL + 'api/map-zonal-with-ohs';

    constructor(private http: HttpClient) {}

    /** Send a POST method request to create the mapZonalWithOh and
     * get created record as response
     *
     * @param mapZonalWithOh - single object
     * @returns object of values
     */
    create(mapZonalWithOh: IMapZonalWithOh): Observable<EntityResponseType> {
        // Call function to convert date value into database format
        const copy = this.convertDateFromClient(mapZonalWithOh);
        return this.http
            .post<IMapZonalWithOh>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    /**
     * Send a PUT method request to update the mapZonalWithOh and
     * get updated record as response
     *
     * @param mapZonalWithOh - single object
     * @returns object of values
     */
    update(mapZonalWithOh: IMapZonalWithOh): Observable<EntityResponseType> {
        // Call function to convert date value into database format
        const copy = this.convertDateFromClient(mapZonalWithOh);
        return this.http
            .put<IMapZonalWithOh>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    /**
     * Send particular row id in GET method to find particular mapZonalWithOh and
     * return an object
     *
     * @param id - autoincrement id
     * @returns object of values
     */
    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IMapZonalWithOh>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    /**
     * List all the mapZonalWithOh. Pass a params like sort, page, total count,
     * filter, etc..
     *
     * @param req - contain multipe type value eg: page, totalCount,..
     * @returns Array of values
     */
    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IMapZonalWithOh[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    /**
     * Send a DELETE method request to delete a particular mapZonalWithOh using Id
     *
     * @param id - autoincrement id
     * @returns boolean true or false
     */
    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    /**
     * List all the record based on the zonalId and status
     *
     * @param zonalId - autoIncrement id of zonal model
     * @returns Array of values
     */
    /* getParticularZonalActiveRecord(zonalId: number): Observable<EntityArrayResponseType> {
        return this.http
            .get<IMapZonalWithOh[]>(`${this.resourceUrl}/zonal/${zonalId}/${STATUS_ACTIVE}`, { observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    } */

    /**
     * List all the record based on the zonalId
     *
     * @param zonalId - autoIncrement id of zonal model
     * @returns Array of values
     */
    /* getParticularZonalRecord(zonalId: number): Observable<EntityArrayResponseType> {
        return this.http
            .get<IMapZonalWithOh[]>(`${this.resourceUrl}/zonal/${zonalId}`, { observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    } */

    /**
     * Convert the date into database format before send data to server
     *
     * @param mapZonalWithOh - single object
     * @returns Object of value
     */
    private convertDateFromClient(mapZonalWithOh: IMapZonalWithOh): IMapZonalWithOh {
        const copy: IMapZonalWithOh = Object.assign({}, mapZonalWithOh, {
            fromDate:
                mapZonalWithOh.fromDate != null && mapZonalWithOh.fromDate.isValid() ? mapZonalWithOh.fromDate.format(DATE_FORMAT) : null,
            toDate: mapZonalWithOh.toDate != null && mapZonalWithOh.toDate.isValid() ? mapZonalWithOh.toDate.format(DATE_FORMAT) : null
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
        res.body.forEach((mapZonalWithOh: IMapZonalWithOh) => {
            mapZonalWithOh.fromDate = mapZonalWithOh.fromDate != null ? moment(mapZonalWithOh.fromDate) : null;
            mapZonalWithOh.toDate = mapZonalWithOh.toDate != null ? moment(mapZonalWithOh.toDate) : null;
        });
        return res;
    }
}
