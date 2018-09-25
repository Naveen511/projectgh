import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { INurseryStock } from 'app/shared/model/nursery-stock.model';

type EntityResponseType = HttpResponse<INurseryStock>;
type EntityArrayResponseType = HttpResponse<INurseryStock[]>;

@Injectable({ providedIn: 'root' })
export class NurseryStockService {
    private resourceUrl = SERVER_API_URL + 'api/nursery-stocks';

    constructor(private http: HttpClient) {}

    create(nurseryStock: INurseryStock): Observable<EntityResponseType> {
        return this.http.post<INurseryStock>(this.resourceUrl, nurseryStock, { observe: 'response' });
    }

    update(nurseryStock: INurseryStock): Observable<EntityResponseType> {
        return this.http.put<INurseryStock>(this.resourceUrl, nurseryStock, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<INurseryStock>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<INurseryStock[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
