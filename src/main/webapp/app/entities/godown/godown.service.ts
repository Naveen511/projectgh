import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGodown } from 'app/shared/model/godown.model';

type EntityResponseType = HttpResponse<IGodown>;
type EntityArrayResponseType = HttpResponse<IGodown[]>;

@Injectable({ providedIn: 'root' })
export class GodownService {
    private resourceUrl = SERVER_API_URL + 'api/godowns';

    constructor(private http: HttpClient) {}

    create(godown: IGodown): Observable<EntityResponseType> {
        return this.http.post<IGodown>(this.resourceUrl, godown, { observe: 'response' });
    }

    update(godown: IGodown): Observable<EntityResponseType> {
        return this.http.put<IGodown>(this.resourceUrl, godown, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IGodown>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IGodown[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
