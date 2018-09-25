import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPickList } from 'app/shared/model/pick-list.model';

type EntityResponseType = HttpResponse<IPickList>;
type EntityArrayResponseType = HttpResponse<IPickList[]>;

@Injectable({ providedIn: 'root' })
export class PickListService {
    private resourceUrl = SERVER_API_URL + 'api/pick-lists';

    constructor(private http: HttpClient) {}

    create(pickList: IPickList): Observable<EntityResponseType> {
        return this.http
            .post<IPickList>(this.resourceUrl, pickList, { observe: 'response' });
    }

    update(pickList: IPickList): Observable<EntityResponseType> {
        return this.http
            .put<IPickList>(this.resourceUrl, pickList, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPickList>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPickList[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    softDelete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, {observe: 'response'});
    }
}
