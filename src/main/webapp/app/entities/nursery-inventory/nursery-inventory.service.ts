import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { INurseryInventory } from 'app/shared/model/nursery-inventory.model';

type EntityResponseType = HttpResponse<INurseryInventory>;
type EntityArrayResponseType = HttpResponse<INurseryInventory[]>;

@Injectable({ providedIn: 'root' })
export class NurseryInventoryService {
    private resourceUrl = SERVER_API_URL + 'api/nursery-inventories';

    constructor(private http: HttpClient) {}

    create(nurseryInventory: INurseryInventory): Observable<EntityResponseType> {
        return this.http.post<INurseryInventory>(this.resourceUrl, nurseryInventory, { observe: 'response' });
    }

    update(nurseryInventory: INurseryInventory): Observable<EntityResponseType> {
        return this.http.put<INurseryInventory>(this.resourceUrl, nurseryInventory, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<INurseryInventory>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<INurseryInventory[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
