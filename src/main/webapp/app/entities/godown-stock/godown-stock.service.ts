import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGodownStock } from 'app/shared/model/godown-stock.model';

type EntityResponseType = HttpResponse<IGodownStock>;
type EntityArrayResponseType = HttpResponse<IGodownStock[]>;

@Injectable({ providedIn: 'root' })
export class GodownStockService {
    private resourceUrl = SERVER_API_URL + 'api/godown-stocks';

    constructor(private http: HttpClient) {}

    create(godownStock: IGodownStock): Observable<EntityResponseType> {
        return this.http.post<IGodownStock>(this.resourceUrl, godownStock, { observe: 'response' });
    }

    update(godownStock: IGodownStock): Observable<EntityResponseType> {
        return this.http.put<IGodownStock>(this.resourceUrl, godownStock, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IGodownStock>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IGodownStock[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
