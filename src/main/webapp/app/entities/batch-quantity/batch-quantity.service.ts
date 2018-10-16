import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IBatchQuantity } from 'app/shared/model/batch-quantity.model';

type EntityResponseType = HttpResponse<IBatchQuantity>;
type EntityArrayResponseType = HttpResponse<IBatchQuantity[]>;

@Injectable({ providedIn: 'root' })
export class BatchQuantityService {
    private resourceUrl = SERVER_API_URL + 'api/batch-quantities';

    constructor(private http: HttpClient) {}

    create(batchQuantity: IBatchQuantity): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(batchQuantity);
        return this.http
            .post<IBatchQuantity>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(batchQuantity: IBatchQuantity): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(batchQuantity);
        return this.http
            .put<IBatchQuantity>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IBatchQuantity>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IBatchQuantity[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(batchQuantity: IBatchQuantity): IBatchQuantity {
        const copy: IBatchQuantity = Object.assign({}, batchQuantity, {
            date: batchQuantity.date != null && batchQuantity.date.isValid() ? batchQuantity.date.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.date = res.body.date != null ? moment(res.body.date) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((batchQuantity: IBatchQuantity) => {
            batchQuantity.date = batchQuantity.date != null ? moment(batchQuantity.date) : null;
        });
        return res;
    }
}
