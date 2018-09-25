import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { INursery } from 'app/shared/model/nursery.model';

type EntityResponseType = HttpResponse<INursery>;
type EntityArrayResponseType = HttpResponse<INursery[]>;

@Injectable({ providedIn: 'root' })
export class NurseryService {
    private resourceUrl = SERVER_API_URL + 'api/nurseries';

    constructor(private http: HttpClient) {}

    create(nursery: INursery): Observable<EntityResponseType> {
        return this.http.post<INursery>(this.resourceUrl, nursery, { observe: 'response' });
    }

    update(nursery: INursery): Observable<EntityResponseType> {
        return this.http.put<INursery>(this.resourceUrl, nursery, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<INursery>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<INursery[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
