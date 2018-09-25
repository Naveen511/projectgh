import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMapNurseryWithSector } from 'app/shared/model/map-nursery-with-sector.model';

type EntityResponseType = HttpResponse<IMapNurseryWithSector>;
type EntityArrayResponseType = HttpResponse<IMapNurseryWithSector[]>;

@Injectable({ providedIn: 'root' })
export class MapNurseryWithSectorService {
    private resourceUrl = SERVER_API_URL + 'api/map-nursery-with-sectors';

    constructor(private http: HttpClient) {}

    create(mapNurseryWithSector: IMapNurseryWithSector): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(mapNurseryWithSector);
        return this.http
            .post<IMapNurseryWithSector>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(mapNurseryWithSector: IMapNurseryWithSector): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(mapNurseryWithSector);
        return this.http
            .put<IMapNurseryWithSector>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IMapNurseryWithSector>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IMapNurseryWithSector[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(mapNurseryWithSector: IMapNurseryWithSector): IMapNurseryWithSector {
        const copy: IMapNurseryWithSector = Object.assign({}, mapNurseryWithSector, {
            fromDate:
                mapNurseryWithSector.fromDate != null && mapNurseryWithSector.fromDate.isValid()
                    ? mapNurseryWithSector.fromDate.format(DATE_FORMAT)
                    : null,
            toDate:
                mapNurseryWithSector.toDate != null && mapNurseryWithSector.toDate.isValid()
                    ? mapNurseryWithSector.toDate.format(DATE_FORMAT)
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
        res.body.forEach((mapNurseryWithSector: IMapNurseryWithSector) => {
            mapNurseryWithSector.fromDate = mapNurseryWithSector.fromDate != null ? moment(mapNurseryWithSector.fromDate) : null;
            mapNurseryWithSector.toDate = mapNurseryWithSector.toDate != null ? moment(mapNurseryWithSector.toDate) : null;
        });
        return res;
    }
}
