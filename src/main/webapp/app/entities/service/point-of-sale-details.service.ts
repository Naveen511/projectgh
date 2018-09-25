import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPointOfSaleDetails } from 'app/shared/model/point-of-sale-details.model';

type EntityResponseType = HttpResponse<IPointOfSaleDetails>;
type EntityArrayResponseType = HttpResponse<IPointOfSaleDetails[]>;

@Injectable({ providedIn: 'root' })
export class PointOfSaleDetailsService {
    private resourceUrl = SERVER_API_URL + 'api/point-of-sale-details';

    constructor(private http: HttpClient) {}

    create(pointOfSaleDetails: IPointOfSaleDetails): Observable<EntityResponseType> {
        // console.log('create sale');
        const copy = this.convertDateFromClient(pointOfSaleDetails);
        return this.http
            .post<IPointOfSaleDetails>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(pointOfSaleDetails: IPointOfSaleDetails): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(pointOfSaleDetails);
        return this.http
            .put<IPointOfSaleDetails>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPointOfSaleDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPointOfSaleDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(pointOfSaleDetails: IPointOfSaleDetails): IPointOfSaleDetails {
        const copy: IPointOfSaleDetails = Object.assign({}, pointOfSaleDetails, {
            date: pointOfSaleDetails.date != null && pointOfSaleDetails.date.isValid() ? pointOfSaleDetails.date.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.date = res.body.date != null ? moment(res.body.date) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((pointOfSaleDetails: IPointOfSaleDetails) => {
            pointOfSaleDetails.date = pointOfSaleDetails.date != null ? moment(pointOfSaleDetails.date) : null;
        });
        return res;
    }
}
