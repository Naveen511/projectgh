import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGodownStockDetails } from 'app/shared/model/godown-stock-details.model';
import { IGodownStock } from 'app/shared/model/godown-stock.model';

type EntityResponseType = HttpResponse<IGodownStockDetails>;
type EntityArrayResponseType = HttpResponse<IGodownStockDetails[]>;

@Injectable({ providedIn: 'root' })
export class GodownStockDetailsService {

    private resourceUrl = SERVER_API_URL + 'api/godown-stock-details';

    constructor(private http: HttpClient) {}

    getParticularBatchRecord(batchId: any): any {
        throw new Error('Method not implemented.');
    }

    // Create godown stock details
    create(godownStockDetails: IGodownStockDetails): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(godownStockDetails);
        return this.http
            .post<IGodownStockDetails>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    // Update godown stock details using Id
    update(godownStockDetails: IGodownStockDetails): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(godownStockDetails);
        return this.http
            .put<IGodownStockDetails>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    // Find the godown stock details using the ID
    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IGodownStockDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    // Load the godown stock details
    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IGodownStockDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    // Delete godown stock details using Id
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

    getParticularStocks(godownStockId: number): Observable<EntityArrayResponseType> {
        return this.http
            .get<IGodownStockDetails[]>(`${this.resourceUrl}/stock-list/${godownStockId}`, { observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    viewStockDetails(godownStockId: number): Observable<EntityArrayResponseType> {
        return this.http
            .get<IGodownStockDetails[]>(`${this.resourceUrl}/stock-list/${godownStockId}`, { observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }
}
