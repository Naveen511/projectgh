import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { INurseryIncharge } from 'app/shared/model/nursery-incharge.model';

type EntityResponseType = HttpResponse<INurseryIncharge>;
type EntityArrayResponseType = HttpResponse<INurseryIncharge[]>;

@Injectable({ providedIn: 'root' })
export class NurseryInchargeService {
    private resourceUrl = SERVER_API_URL + 'api/nursery-incharges';

    constructor(private http: HttpClient) {}

    create(nurseryIncharge: INurseryIncharge): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(nurseryIncharge);
        return this.http
            .post<INurseryIncharge>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(nurseryIncharge: INurseryIncharge): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(nurseryIncharge);
        return this.http
            .put<INurseryIncharge>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<INurseryIncharge>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<INurseryIncharge[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(nurseryIncharge: INurseryIncharge): INurseryIncharge {
        const copy: INurseryIncharge = Object.assign({}, nurseryIncharge, {
            fromDate:
                nurseryIncharge.fromDate != null && nurseryIncharge.fromDate.isValid()
                    ? nurseryIncharge.fromDate.format(DATE_FORMAT)
                    : null,
            toDate: nurseryIncharge.toDate != null && nurseryIncharge.toDate.isValid() ? nurseryIncharge.toDate.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.fromDate = res.body.fromDate != null ? moment(res.body.fromDate) : null;
        res.body.toDate = res.body.toDate != null ? moment(res.body.toDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((nurseryIncharge: INurseryIncharge) => {
            nurseryIncharge.fromDate = nurseryIncharge.fromDate != null ? moment(nurseryIncharge.fromDate) : null;
            nurseryIncharge.toDate = nurseryIncharge.toDate != null ? moment(nurseryIncharge.toDate) : null;
        });
        return res;
    }
}
