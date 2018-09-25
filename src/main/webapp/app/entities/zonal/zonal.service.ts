import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IZonal } from 'app/shared/model/zonal.model';

type EntityResponseType = HttpResponse<IZonal>;
type EntityArrayResponseType = HttpResponse<IZonal[]>;

@Injectable({ providedIn: 'root' })
export class ZonalService {
    private resourceUrl = SERVER_API_URL + 'api/zonals';

    constructor(private http: HttpClient) {}

    create(zonal: IZonal): Observable<EntityResponseType> {
        return this.http.post<IZonal>(this.resourceUrl, zonal, { observe: 'response' });
    }

    update(zonal: IZonal): Observable<EntityResponseType> {
        return this.http.put<IZonal>(this.resourceUrl, zonal, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IZonal>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IZonal[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
