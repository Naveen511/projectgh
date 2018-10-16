/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date: 2018/09/15 11:27:58
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
import { IMapNurseryWithSector, STATUS_ACTIVE } from 'app/shared/model/map-nursery-with-sector.model';

type EntityResponseType = HttpResponse<IMapNurseryWithSector>;
type EntityArrayResponseType = HttpResponse<IMapNurseryWithSector[]>;

/**
 * This class used to communicate between spring boot and angular through api url.
 * Methods used - Create, update, find single record, find all, delete, list of data
 */
@Injectable({ providedIn: 'root' })
export class MapNurseryWithSectorService {
    private resourceUrl = SERVER_API_URL + 'api/map-nursery-with-sectors';

    constructor(private http: HttpClient) {}

    /** Send a POST method request to create the mapNurseryWithSector and
     * get created record as response
     *
     * @param mapNurseryWithSector - single object
     * @returns object of values
     */
    create(mapNurseryWithSector: IMapNurseryWithSector): Observable<EntityResponseType> {
        // Call function to convert date value into database format
        const copy = this.convertDateFromClient(mapNurseryWithSector);
        return this.http
            .post<IMapNurseryWithSector>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    /**
     * Send a PUT method request to update the mapNurseryWithSector and
     * get updated record as response
     *
     * @param mapNurseryWithSector - single object
     * @returns object of values
     */
    update(mapNurseryWithSector: IMapNurseryWithSector): Observable<EntityResponseType> {
        // Call function to convert date value into database format
        const copy = this.convertDateFromClient(mapNurseryWithSector);
        return this.http
            .put<IMapNurseryWithSector>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    /**
     * Send particular row id in GET method to find particular mapNurseryWithSector and
     * return an object
     *
     * @param id - autoincrement id
     * @returns object of values
     */
    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IMapNurseryWithSector>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    /**
     * List all the mapNurseryWithSector. Pass a params like sort, page, total count,
     * filter, etc..
     *
     * @param req - contain multipe type value eg: page, totalCount,..
     * @returns Array of values
     */
    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IMapNurseryWithSector[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    /**
     * Send a DELETE method request to delete a particular mapNurseryWithSector using Id
     *
     * @param id - autoincrement id
     * @returns boolean true or false
     */
    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    /**
     * List all the record based on the nurseryId and status
     *
     * @param nurseryId - autoIncrement id of Nursery model
     * @returns Array of values
     */
    /* getParticularNurseryActiveRecord(nurseryId: number): Observable<EntityArrayResponseType> {
        return this.http
            .get<IMapNurseryWithSector[]>(`${this.resourceUrl}/nursery/${nurseryId}/${STATUS_ACTIVE}`, { observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    } */

    /**
     * List all the record based on the nurseryId
     *
     * @param nurseryId - autoIncrement id of Nursery model
     * @returns Array of values
     */
    /* getParticularNurseryRecord(nurseryId: number): Observable<EntityArrayResponseType> {
        return this.http
            .get<IMapNurseryWithSector[]>(`${this.resourceUrl}/nursery/${nurseryId}`, { observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    } */

    /**
     * Convert the date into database format before send data to server
     *
     * @param mapNurseryWithSector - single object
     * @returns Object of value
     */
    private convertDateFromClient(mapNurseryWithSector: IMapNurseryWithSector): IMapNurseryWithSector {
        const copy: IMapNurseryWithSector = Object.assign({}, mapNurseryWithSector, {
            fromDate:
                mapNurseryWithSector.fromDate != null && mapNurseryWithSector.fromDate.isValid()
                    ? mapNurseryWithSector.fromDate.format(DATE_FORMAT)
                    : null,
            toDate:
                mapNurseryWithSector.toDate != null && mapNurseryWithSector.toDate.isValid()
                    ? mapNurseryWithSector.toDate.format(DATE_FORMAT)
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
        res.body.forEach((mapNurseryWithSector: IMapNurseryWithSector) => {
            mapNurseryWithSector.fromDate = mapNurseryWithSector.fromDate != null ? moment(mapNurseryWithSector.fromDate) : null;
            mapNurseryWithSector.toDate = mapNurseryWithSector.toDate != null ? moment(mapNurseryWithSector.toDate) : null;
        });
        return res;
    }
}
