import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { INurseryInventoryDetails } from 'app/shared/model/nursery-inventory-details.model';

type EntityResponseType = HttpResponse<INurseryInventoryDetails>;
type EntityArrayResponseType = HttpResponse<INurseryInventoryDetails[]>;

@Injectable({ providedIn: 'root' })
export class NurseryInventoryDetailsService {
    private resourceUrl = SERVER_API_URL + 'api/nursery-inventory-details';

    constructor(private http: HttpClient) {}

    create(nurseryInventoryDetails: INurseryInventoryDetails): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(nurseryInventoryDetails);
        return this.http
            .post<INurseryInventoryDetails>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(nurseryInventoryDetails: INurseryInventoryDetails): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(nurseryInventoryDetails);
        return this.http
            .put<INurseryInventoryDetails>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<INurseryInventoryDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<INurseryInventoryDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(nurseryInventoryDetails: INurseryInventoryDetails): INurseryInventoryDetails {
        const copy: INurseryInventoryDetails = Object.assign({}, nurseryInventoryDetails, {
            date:
                nurseryInventoryDetails.date != null && nurseryInventoryDetails.date.isValid()
                    ? nurseryInventoryDetails.date.format(DATE_FORMAT)
                    : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.date = res.body.date != null ? moment(res.body.date) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((nurseryInventoryDetails: INurseryInventoryDetails) => {
            nurseryInventoryDetails.date = nurseryInventoryDetails.date != null ? moment(nurseryInventoryDetails.date) : null;
        });
        return res;
    }
}
