import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IOperationalHead } from 'app/shared/model/operational-head.model';

type EntityResponseType = HttpResponse<IOperationalHead>;
type EntityArrayResponseType = HttpResponse<IOperationalHead[]>;

@Injectable({ providedIn: 'root' })
export class OperationalHeadService {
    private resourceUrl = SERVER_API_URL + 'api/operational-heads';

    constructor(private http: HttpClient) {}

    create(operationalHead: IOperationalHead): Observable<EntityResponseType> {
        return this.http.post<IOperationalHead>(this.resourceUrl, operationalHead, { observe: 'response' });
    }

    update(operationalHead: IOperationalHead): Observable<EntityResponseType> {
        return this.http.put<IOperationalHead>(this.resourceUrl, operationalHead, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IOperationalHead>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IOperationalHead[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
