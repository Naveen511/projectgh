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

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPickList } from 'app/shared/model/pick-list.model';

type EntityResponseType = HttpResponse<IPickList>;
type EntityArrayResponseType = HttpResponse<IPickList[]>;

/**
 * This class used to communicate between spring boot and angular through api url.
 * Methods used - Create, update, find single record, find all, delete, list of data
 */
@Injectable({ providedIn: 'root' })
export class PickListService {
    private resourceUrl = SERVER_API_URL + 'api/pick-lists';

    constructor(private http: HttpClient) {}

    /**
     * Send a POST method request to create the pickList and
     * get created record as response
     *
     * @param pickList - single object
     * @returns object of values
     */
    create(pickList: IPickList): Observable<EntityResponseType> {
        return this.http
            .post<IPickList>(this.resourceUrl, pickList, { observe: 'response' });
    }

    /**
     * Send a PUT method request to update the pickList and
     * get updated record as response
     *
     * @param pickList - single object
     * @returns object of values
     */
    update(pickList: IPickList): Observable<EntityResponseType> {
        return this.http
            .put<IPickList>(this.resourceUrl, pickList, { observe: 'response' });
    }

    /**
     * Send particular row id in GET method to find particular pickList and
     * return an object
     *
     * @param id - autoincrement id
     * @returns object of values
     */
    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPickList>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    /**
     * List all the pickList. Pass a params like sort, page, totalCount, filter, etc..
     *
     * @param req - contain multipe type value eg: page, totalCount,..
     * @returns Array of values
     */
    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPickList[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    /**
     * Send a DELETE method request to delete a particular pickList using Id
     *
     * @param id - autoincrement id
     * @returns boolean true or false
     */
    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    /**
     * To get the list of data based on diaplay name(type)
     *
     * @param displayLabelName - pickList type
     * @returns Array of values
     */
    /* getPickListId(displayLabelName: string): Observable<EntityArrayResponseType> {
        return this.http.get<IPickList[]>(`${this.resourceUrl}/displayName/${displayLabelName}`, { observe: 'response' });
    } */
}
