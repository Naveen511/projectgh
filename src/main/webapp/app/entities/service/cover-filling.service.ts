import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICoverFilling } from 'app/shared/model/cover-filling.model';

type EntityResponseType = HttpResponse<ICoverFilling>;
type EntityArrayResponseType = HttpResponse<ICoverFilling[]>;

@Injectable({ providedIn: 'root' })
export class CoverFillingService {
    private resourceUrl = SERVER_API_URL + 'api/cover-fillings';

    constructor(private http: HttpClient) {}

    create(coverFilling: ICoverFilling): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(coverFilling);
        return this.http
            .post<ICoverFilling>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(coverFilling: ICoverFilling): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(coverFilling);
        return this.http
            .put<ICoverFilling>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ICoverFilling>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ICoverFilling[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(coverFilling: ICoverFilling): ICoverFilling {
        const copy: ICoverFilling = Object.assign({}, coverFilling, {
            date: coverFilling.date != null && coverFilling.date.isValid() ? coverFilling.date.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.date = res.body.date != null ? moment(res.body.date) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((coverFilling: ICoverFilling) => {
            coverFilling.date = coverFilling.date != null ? moment(coverFilling.date) : null;
        });
        return res;
    }
}
