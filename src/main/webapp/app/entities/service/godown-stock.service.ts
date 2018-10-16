import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGodownStock } from 'app/shared/model/godown-stock.model';

type EntityResponseType = HttpResponse<IGodownStock>;
type EntityArrayResponseType = HttpResponse<IGodownStock[]>;

@Injectable({ providedIn: 'root' })
export class GodownStockService {
    private resourceUrl = SERVER_API_URL + 'api/godown-stocks';

    constructor(private http: HttpClient) {}

    // Create Godown Stock
    create(godownStock: IGodownStock): Observable<EntityResponseType> {
        return this.http.post<IGodownStock>(this.resourceUrl, godownStock, { observe: 'response' });
    }

    // Update Godown Stock
    update(godownStock: IGodownStock): Observable<EntityResponseType> {
        return this.http.put<IGodownStock>(this.resourceUrl, godownStock, { observe: 'response' });
    }

    // Find Godown Stock using the Godown Stock Id
    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IGodownStock>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    // Load all the Godown Stock
    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IGodownStock[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    getGodownCategoryStock(godownId: number, categoryId: number): Observable<EntityArrayResponseType> {
        return this.http
        .get<IGodownStock[]>(`${this.resourceUrl}/${godownId}/${categoryId}`, {observe: 'response'});
    }

    // Delete the Godown Stock based on the Id
    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
