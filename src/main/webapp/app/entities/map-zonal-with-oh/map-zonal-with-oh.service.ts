import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMapZonalWithOh } from 'app/shared/model/map-zonal-with-oh.model';

type EntityResponseType = HttpResponse<IMapZonalWithOh>;
type EntityArrayResponseType = HttpResponse<IMapZonalWithOh[]>;

@Injectable({ providedIn: 'root' })
export class MapZonalWithOhService {
    private resourceUrl = SERVER_API_URL + 'api/map-zonal-with-ohs';

    constructor(private http: HttpClient) {}

    create(mapZonalWithOh: IMapZonalWithOh): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(mapZonalWithOh);
        return this.http
            .post<IMapZonalWithOh>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(mapZonalWithOh: IMapZonalWithOh): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(mapZonalWithOh);
        return this.http
            .put<IMapZonalWithOh>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IMapZonalWithOh>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IMapZonalWithOh[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(mapZonalWithOh: IMapZonalWithOh): IMapZonalWithOh {
        const copy: IMapZonalWithOh = Object.assign({}, mapZonalWithOh, {
            fromDate:
                mapZonalWithOh.fromDate != null && mapZonalWithOh.fromDate.isValid() ? mapZonalWithOh.fromDate.format(DATE_FORMAT) : null,
            toDate: mapZonalWithOh.toDate != null && mapZonalWithOh.toDate.isValid() ? mapZonalWithOh.toDate.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.fromDate = res.body.fromDate != null ? moment(res.body.fromDate) : null;
        res.body.toDate = res.body.toDate != null ? moment(res.body.toDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((mapZonalWithOh: IMapZonalWithOh) => {
            mapZonalWithOh.fromDate = mapZonalWithOh.fromDate != null ? moment(mapZonalWithOh.fromDate) : null;
            mapZonalWithOh.toDate = mapZonalWithOh.toDate != null ? moment(mapZonalWithOh.toDate) : null;
        });
        return res;
    }
}
