import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGodownPurchaseDetails } from 'app/shared/model/godown-purchase-details.model';

type EntityResponseType = HttpResponse<IGodownPurchaseDetails>;
type EntityArrayResponseType = HttpResponse<IGodownPurchaseDetails[]>;

@Injectable({ providedIn: 'root' })
export class GodownPurchaseDetailsService {
    private resourceUrl = SERVER_API_URL + 'api/godown-purchase-details';

    constructor(private http: HttpClient) {}

    create(godownPurchaseDetails: IGodownPurchaseDetails): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(godownPurchaseDetails);
        return this.http
            .post<IGodownPurchaseDetails>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(godownPurchaseDetails: IGodownPurchaseDetails): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(godownPurchaseDetails);
        return this.http
            .put<IGodownPurchaseDetails>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IGodownPurchaseDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IGodownPurchaseDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(godownPurchaseDetails: IGodownPurchaseDetails): IGodownPurchaseDetails {
        const copy: IGodownPurchaseDetails = Object.assign({}, godownPurchaseDetails, {
            date:
                godownPurchaseDetails.date != null && godownPurchaseDetails.date.isValid()
                    ? godownPurchaseDetails.date.format(DATE_FORMAT)
                    : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.date = res.body.date != null ? moment(res.body.date) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((godownPurchaseDetails: IGodownPurchaseDetails) => {
            godownPurchaseDetails.date = godownPurchaseDetails.date != null ? moment(godownPurchaseDetails.date) : null;
        });
        return res;
    }
}
