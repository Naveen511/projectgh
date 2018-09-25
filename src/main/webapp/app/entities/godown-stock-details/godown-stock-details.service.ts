import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGodownStockDetails } from 'app/shared/model/godown-stock-details.model';

type EntityResponseType = HttpResponse<IGodownStockDetails>;
type EntityArrayResponseType = HttpResponse<IGodownStockDetails[]>;

@Injectable({ providedIn: 'root' })
export class GodownStockDetailsService {
    private resourceUrl = SERVER_API_URL + 'api/godown-stock-details';

    constructor(private http: HttpClient) {}

    create(godownStockDetails: IGodownStockDetails): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(godownStockDetails);
        return this.http
            .post<IGodownStockDetails>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(godownStockDetails: IGodownStockDetails): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(godownStockDetails);
        return this.http
            .put<IGodownStockDetails>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IGodownStockDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IGodownStockDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(godownStockDetails: IGodownStockDetails): IGodownStockDetails {
        const copy: IGodownStockDetails = Object.assign({}, godownStockDetails, {
            date: godownStockDetails.date != null && godownStockDetails.date.isValid() ? godownStockDetails.date.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.date = res.body.date != null ? moment(res.body.date) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((godownStockDetails: IGodownStockDetails) => {
            godownStockDetails.date = godownStockDetails.date != null ? moment(godownStockDetails.date) : null;
        });
        return res;
    }
}
