import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT, STATUS_ACTIVE } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

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

    getNurserys(sectorId: number): Observable<EntityArrayResponseType> {
        return this.http.get<INursery[]>(`${this.resourceUrl}/sector/${sectorId}`, { observe: 'response' });
    }

    getNurseryCount(): Observable<HttpResponse<any>> {
        return this.http.get<any>(`${this.resourceUrl}/count/${STATUS_ACTIVE}`, { observe: 'response' });
    }

    // getNurserys(sectorId: number): Observable<EntityArrayResponseType> {
    //     return this.http
    //         .get<INursery[]>(`${this.resourceUrl}/sector/${sectorId}`, { observe: 'response' })
    //         .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    // }

    getMovedNursery(zoneId: number, sectorId: number): Observable<EntityArrayResponseType> {
        return this.http.get<INursery[]>(`${this.resourceUrl}/movednursery/${zoneId}/${sectorId}`, { observe: 'response' });
    }
}
