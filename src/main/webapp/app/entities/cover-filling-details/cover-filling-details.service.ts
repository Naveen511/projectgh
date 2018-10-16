import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICoverFillingDetails } from 'app/shared/model/cover-filling-details.model';

type EntityResponseType = HttpResponse<ICoverFillingDetails>;
type EntityArrayResponseType = HttpResponse<ICoverFillingDetails[]>;

@Injectable({ providedIn: 'root' })
export class CoverFillingDetailsService {
    private resourceUrl = SERVER_API_URL + 'api/cover-filling-details';

    constructor(private http: HttpClient) {}

    create(coverFillingDetails: ICoverFillingDetails): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(coverFillingDetails);
        return this.http
            .post<ICoverFillingDetails>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(coverFillingDetails: ICoverFillingDetails): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(coverFillingDetails);
        return this.http
            .put<ICoverFillingDetails>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ICoverFillingDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ICoverFillingDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(coverFillingDetails: ICoverFillingDetails): ICoverFillingDetails {
        const copy: ICoverFillingDetails = Object.assign({}, coverFillingDetails, {
            date:
                coverFillingDetails.date != null && coverFillingDetails.date.isValid() ? coverFillingDetails.date.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.date = res.body.date != null ? moment(res.body.date) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((coverFillingDetails: ICoverFillingDetails) => {
            coverFillingDetails.date = coverFillingDetails.date != null ? moment(coverFillingDetails.date) : null;
        });
        return res;
    }
}
