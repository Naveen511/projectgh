import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { INurseryStockDetails } from 'app/shared/model/nursery-stock-details.model';

type EntityResponseType = HttpResponse<INurseryStockDetails>;
type EntityArrayResponseType = HttpResponse<INurseryStockDetails[]>;

@Injectable({ providedIn: 'root' })
export class NurseryStockDetailsService {
    private resourceUrl = SERVER_API_URL + 'api/nursery-stock-details';

    constructor(private http: HttpClient) {}

    create(nurseryStockDetails: INurseryStockDetails): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(nurseryStockDetails);
        return this.http
            .post<INurseryStockDetails>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(nurseryStockDetails: INurseryStockDetails): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(nurseryStockDetails);
        return this.http
            .put<INurseryStockDetails>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<INurseryStockDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<INurseryStockDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(nurseryStockDetails: INurseryStockDetails): INurseryStockDetails {
        const copy: INurseryStockDetails = Object.assign({}, nurseryStockDetails, {
            date:
                nurseryStockDetails.date != null && nurseryStockDetails.date.isValid() ? nurseryStockDetails.date.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.date = res.body.date != null ? moment(res.body.date) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((nurseryStockDetails: INurseryStockDetails) => {
            nurseryStockDetails.date = nurseryStockDetails.date != null ? moment(nurseryStockDetails.date) : null;
        });
        return res;
    }
}
