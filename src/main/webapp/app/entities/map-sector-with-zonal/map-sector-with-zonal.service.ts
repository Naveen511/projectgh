import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMapSectorWithZonal } from 'app/shared/model/map-sector-with-zonal.model';

type EntityResponseType = HttpResponse<IMapSectorWithZonal>;
type EntityArrayResponseType = HttpResponse<IMapSectorWithZonal[]>;

@Injectable({ providedIn: 'root' })
export class MapSectorWithZonalService {
    private resourceUrl = SERVER_API_URL + 'api/map-sector-with-zonals';

    constructor(private http: HttpClient) {}

    create(mapSectorWithZonal: IMapSectorWithZonal): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(mapSectorWithZonal);
        return this.http
            .post<IMapSectorWithZonal>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(mapSectorWithZonal: IMapSectorWithZonal): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(mapSectorWithZonal);
        return this.http
            .put<IMapSectorWithZonal>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IMapSectorWithZonal>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IMapSectorWithZonal[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(mapSectorWithZonal: IMapSectorWithZonal): IMapSectorWithZonal {
        const copy: IMapSectorWithZonal = Object.assign({}, mapSectorWithZonal, {
            fromDate:
                mapSectorWithZonal.fromDate != null && mapSectorWithZonal.fromDate.isValid()
                    ? mapSectorWithZonal.fromDate.format(DATE_FORMAT)
                    : null,
            toDate:
                mapSectorWithZonal.toDate != null && mapSectorWithZonal.toDate.isValid()
                    ? mapSectorWithZonal.toDate.format(DATE_FORMAT)
                    : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.fromDate = res.body.fromDate != null ? moment(res.body.fromDate) : null;
        res.body.toDate = res.body.toDate != null ? moment(res.body.toDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((mapSectorWithZonal: IMapSectorWithZonal) => {
            mapSectorWithZonal.fromDate = mapSectorWithZonal.fromDate != null ? moment(mapSectorWithZonal.fromDate) : null;
            mapSectorWithZonal.toDate = mapSectorWithZonal.toDate != null ? moment(mapSectorWithZonal.toDate) : null;
        });
        return res;
    }
}
