import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IZonalIncharge } from 'app/shared/model/zonal-incharge.model';

type EntityResponseType = HttpResponse<IZonalIncharge>;
type EntityArrayResponseType = HttpResponse<IZonalIncharge[]>;

@Injectable({ providedIn: 'root' })
export class ZonalInchargeService {
    private resourceUrl = SERVER_API_URL + 'api/zonal-incharges';

    constructor(private http: HttpClient) {}

    create(zonalIncharge: IZonalIncharge): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(zonalIncharge);
        return this.http
            .post<IZonalIncharge>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(zonalIncharge: IZonalIncharge): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(zonalIncharge);
        return this.http
            .put<IZonalIncharge>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IZonalIncharge>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IZonalIncharge[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(zonalIncharge: IZonalIncharge): IZonalIncharge {
        const copy: IZonalIncharge = Object.assign({}, zonalIncharge, {
            fromDate:
                zonalIncharge.fromDate != null && zonalIncharge.fromDate.isValid() ? zonalIncharge.fromDate.format(DATE_FORMAT) : null,
            toDate: zonalIncharge.toDate != null && zonalIncharge.toDate.isValid() ? zonalIncharge.toDate.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.fromDate = res.body.fromDate != null ? moment(res.body.fromDate) : null;
        res.body.toDate = res.body.toDate != null ? moment(res.body.toDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((zonalIncharge: IZonalIncharge) => {
            zonalIncharge.fromDate = zonalIncharge.fromDate != null ? moment(zonalIncharge.fromDate) : null;
            zonalIncharge.toDate = zonalIncharge.toDate != null ? moment(zonalIncharge.toDate) : null;
        });
        return res;
    }
}
