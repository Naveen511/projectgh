import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICommonSettings } from 'app/shared/model/common-settings.model';

type EntityResponseType = HttpResponse<ICommonSettings>;
type EntityArrayResponseType = HttpResponse<ICommonSettings[]>;

@Injectable({ providedIn: 'root' })
export class CommonSettingsService {
    private resourceUrl = SERVER_API_URL + 'api/common-settings';

    constructor(private http: HttpClient) {}

    create(commonSettings: ICommonSettings): Observable<EntityResponseType> {
        return this.http.post<ICommonSettings>(this.resourceUrl, commonSettings, { observe: 'response' });
    }

    update(commonSettings: ICommonSettings): Observable<EntityResponseType> {
        return this.http.put<ICommonSettings>(this.resourceUrl, commonSettings, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICommonSettings>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICommonSettings[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
