import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT, STATUS_ACTIVE } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGodown } from 'app/shared/model/godown.model';

type EntityResponseType = HttpResponse<IGodown>;
type EntityArrayResponseType = HttpResponse<IGodown[]>;

@Injectable({ providedIn: 'root' })
export class GodownService {
    private resourceUrl = SERVER_API_URL + 'api/godowns';

    constructor(private http: HttpClient) {}

    // Create Godown
    create(godown: IGodown): Observable<EntityResponseType> {
        return this.http.post<IGodown>(this.resourceUrl, godown, { observe: 'response' });
    }

    // Update Godown
    update(godown: IGodown): Observable<EntityResponseType> {
        return this.http.put<IGodown>(this.resourceUrl, godown, { observe: 'response' });
    }

    // Find Godown using ID
    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IGodown>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    // Load all the godown
    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IGodown[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    // Delete the Godown using the Godown Id
    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
