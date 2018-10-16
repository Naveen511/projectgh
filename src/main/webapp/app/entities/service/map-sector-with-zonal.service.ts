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
import { IMapSectorWithZonal, STATUS_ACTIVE } from 'app/shared/model/map-sector-with-zonal.model';

type EntityResponseType = HttpResponse<IMapSectorWithZonal>;
type EntityArrayResponseType = HttpResponse<IMapSectorWithZonal[]>;

/**
 * This class used to communicate between spring boot and angular through api url.
 * Methods used - Create, update, find single record, find all, delete, list of data
 */
@Injectable({ providedIn: 'root' })
export class MapSectorWithZonalService {
    private resourceUrl = SERVER_API_URL + 'api/map-sector-with-zonals';

    constructor(private http: HttpClient) {}

    /** Send a POST method request to create the mapSectorWithZonal and
     * get created record as response
     *
     * @param mapSectorWithZonal - single object
     * @returns object of values
     */
    create(mapSectorWithZonal: IMapSectorWithZonal): Observable<EntityResponseType> {
        // Call function to convert date value into database format
        const copy = this.convertDateFromClient(mapSectorWithZonal);
        return this.http
            .post<IMapSectorWithZonal>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    /**
     * Send a PUT method request to update the mapSectorWithZonal and
     * get updated record as response
     *
     * @param mapSectorWithZonal - single object
     * @returns object of values
     */
    update(mapSectorWithZonal: IMapSectorWithZonal): Observable<EntityResponseType> {
        // Call function to convert date value into database format
        const copy = this.convertDateFromClient(mapSectorWithZonal);
        return this.http
            .put<IMapSectorWithZonal>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    /**
     * Send particular row id in GET method to find particular mapSectorWithZonal and
     * return an object
     *
     * @param id - autoincrement id
     * @returns object of values
     */
    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IMapSectorWithZonal>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    /**
     * List all the mapSectorWithZonal. Pass a params like sort, page, total count,
     * filter, etc..
     *
     * @param req - contain multipe type value eg: page, totalCount,..
     * @returns Array of values
     */
    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IMapSectorWithZonal[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    /**
     * Send a DELETE method request to delete a particular mapSectorWithZonal using Id
     *
     * @param id - autoincrement id
     * @returns boolean true or false
     */
    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    /**
     * List all the record based on the sectorId and status
     *
     * @param sectorId - autoIncrement id of Sector model
     * @returns Array of values
     */
    /* getParticularSectorActiveRecord(sectorId: number): Observable<EntityArrayResponseType> {
        return this.http
            .get<IMapSectorWithZonal[]>(`${this.resourceUrl}/sector/${sectorId}/${STATUS_ACTIVE}`, { observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    } */

    /**
     * List all the record based on the sectorId
     *
     * @param sectorId - autoIncrement id of Sector model
     * @returns Array of values
     */
    /* getParticularSectorRecord(sectorId: number): Observable<EntityArrayResponseType> {
        return this.http
            .get<IMapSectorWithZonal[]>(`${this.resourceUrl}/sector/${sectorId}`, { observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    } */

    /**
     * Convert the date into database format before send data to server
     *
     * @param mapSectorWithZonal - single object
     * @returns Object of value
     */
    private convertDateFromClient(mapSectorWithZonal: IMapSectorWithZonal): IMapSectorWithZonal {
        const copy: IMapSectorWithZonal = Object.assign({}, mapSectorWithZonal, {
            fromDate:
                mapSectorWithZonal.fromDate != null && mapSectorWithZonal.fromDate.isValid()
                    ? mapSectorWithZonal.fromDate.format(DATE_FORMAT)
                    : null,
            toDate:
                mapSectorWithZonal.toDate != null && mapSectorWithZonal.toDate.isValid()
                    ? mapSectorWithZonal.toDate.format(DATE_FORMAT)
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
        res.body.forEach((mapSectorWithZonal: IMapSectorWithZonal) => {
            mapSectorWithZonal.fromDate = mapSectorWithZonal.fromDate != null ? moment(mapSectorWithZonal.fromDate) : null;
            mapSectorWithZonal.toDate = mapSectorWithZonal.toDate != null ? moment(mapSectorWithZonal.toDate) : null;
        });
        return res;
    }
}
