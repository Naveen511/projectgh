import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMotherBed } from 'app/shared/model/mother-bed.model';

type EntityResponseType = HttpResponse<IMotherBed>;
type EntityArrayResponseType = HttpResponse<IMotherBed[]>;

@Injectable({ providedIn: 'root' })
export class MotherBedService {
    private resourceUrl = SERVER_API_URL + 'api/mother-beds';

    constructor(private http: HttpClient) {}

    create(motherBed: IMotherBed): Observable<EntityResponseType> {
        return this.http.post<IMotherBed>(this.resourceUrl, motherBed, { observe: 'response' });
    }

    update(motherBed: IMotherBed): Observable<EntityResponseType> {
        return this.http.put<IMotherBed>(this.resourceUrl, motherBed, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IMotherBed>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IMotherBed[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
