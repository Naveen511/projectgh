import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISectorIncharge } from 'app/shared/model/sector-incharge.model';

type EntityResponseType = HttpResponse<ISectorIncharge>;
type EntityArrayResponseType = HttpResponse<ISectorIncharge[]>;

@Injectable({ providedIn: 'root' })
export class SectorInchargeService {
    private resourceUrl = SERVER_API_URL + 'api/sector-incharges';

    constructor(private http: HttpClient) {}

    create(sectorIncharge: ISectorIncharge): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sectorIncharge);
        return this.http
            .post<ISectorIncharge>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(sectorIncharge: ISectorIncharge): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sectorIncharge);
        return this.http
            .put<ISectorIncharge>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISectorIncharge>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISectorIncharge[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(sectorIncharge: ISectorIncharge): ISectorIncharge {
        const copy: ISectorIncharge = Object.assign({}, sectorIncharge, {
            fromDate:
                sectorIncharge.fromDate != null && sectorIncharge.fromDate.isValid() ? sectorIncharge.fromDate.format(DATE_FORMAT) : null,
            toDate: sectorIncharge.toDate != null && sectorIncharge.toDate.isValid() ? sectorIncharge.toDate.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.fromDate = res.body.fromDate != null ? moment(res.body.fromDate) : null;
        res.body.toDate = res.body.toDate != null ? moment(res.body.toDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((sectorIncharge: ISectorIncharge) => {
            sectorIncharge.fromDate = sectorIncharge.fromDate != null ? moment(sectorIncharge.fromDate) : null;
            sectorIncharge.toDate = sectorIncharge.toDate != null ? moment(sectorIncharge.toDate) : null;
        });
        return res;
    }
}
