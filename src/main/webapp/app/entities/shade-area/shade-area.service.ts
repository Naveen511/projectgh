import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IShadeArea } from 'app/shared/model/shade-area.model';

type EntityResponseType = HttpResponse<IShadeArea>;
type EntityArrayResponseType = HttpResponse<IShadeArea[]>;

@Injectable({ providedIn: 'root' })
export class ShadeAreaService {
    private resourceUrl = SERVER_API_URL + 'api/shade-areas';

    constructor(private http: HttpClient) {}

    create(shadeArea: IShadeArea): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(shadeArea);
        return this.http
            .post<IShadeArea>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(shadeArea: IShadeArea): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(shadeArea);
        return this.http
            .put<IShadeArea>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IShadeArea>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IShadeArea[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(shadeArea: IShadeArea): IShadeArea {
        const copy: IShadeArea = Object.assign({}, shadeArea, {
            date: shadeArea.date != null && shadeArea.date.isValid() ? shadeArea.date.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.date = res.body.date != null ? moment(res.body.date) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((shadeArea: IShadeArea) => {
            shadeArea.date = shadeArea.date != null ? moment(shadeArea.date) : null;
        });
        return res;
    }
}
