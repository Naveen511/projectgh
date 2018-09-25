import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { STATUS_ACTIVE } from 'app/shared/constants/input.constants';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPickListValue } from 'app/shared/model/pick-list-value.model';

type EntityResponseType = HttpResponse<IPickListValue>;
type EntityArrayResponseType = HttpResponse<IPickListValue[]>;

@Injectable({ providedIn: 'root' })
export class PickListValueService {
    private resourceUrl = SERVER_API_URL + 'api/pick-list-values';

    constructor(private http: HttpClient) {}

    create(pickListValue: IPickListValue): Observable<EntityResponseType> {
        return this.http
            .post<IPickListValue>(this.resourceUrl, pickListValue, { observe: 'response' });
    }

    update(pickListValue: IPickListValue): Observable<EntityResponseType> {
        return this.http
            .put<IPickListValue>(this.resourceUrl, pickListValue, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPickListValue>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPickListValue[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    softDelete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, {observe: 'response'});
    }

    getVariety(pickListId: number): Observable<EntityArrayResponseType> {
        return this.http
            .get<IPickListValue[]>(`${this.resourceUrl}/variety/${pickListId}`, { observe: 'response' });
    }

    getCategory(pickValueId: number): Observable<EntityArrayResponseType> {
        return this.http
            .get<IPickListValue[]>(`${this.resourceUrl}/category/${pickValueId}`, { observe: 'response' });
    }
}
