import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT, STATUS_ACTIVE } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMotherBed, STATUS_AVAILABLE } from 'app/shared/model/mother-bed.model';

type EntityResponseType = HttpResponse<IMotherBed>;
type EntityArrayResponseType = HttpResponse<IMotherBed[]>;

@Injectable({ providedIn: 'root' })
export class MotherBedService {
    private resourceUrl = SERVER_API_URL + 'api/mother-beds';

    constructor(private http: HttpClient) {}

    // Create Motherbed
    create(motherBed: IMotherBed): Observable<EntityResponseType> {
        return this.http.post<IMotherBed>(this.resourceUrl, motherBed, { observe: 'response' });
    }

    // Update Motherbed
    update(motherBed: IMotherBed): Observable<EntityResponseType> {
        return this.http.put<IMotherBed>(this.resourceUrl, motherBed, { observe: 'response' });
    }

    // Find Motherbed using Id
    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IMotherBed>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    // Load all the motherbed
    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IMotherBed[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    // Delete Motherbed from the Motherbed table using Id
    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    // Get Motherbed details based on the nursery Id
    getMotherBed(nurseryId: number): Observable<EntityArrayResponseType> {
        return this.http.get<IMotherBed[]>(`${this.resourceUrl}/nursery/${nurseryId}`, { observe: 'response' });
    }

    getMotherBedNursery(nurseryId: number): Observable<EntityArrayResponseType> {
        return this.http.get<IMotherBed[]>(`${this.resourceUrl}/nursery/${nurseryId}/${STATUS_AVAILABLE}`, { observe: 'response' });
    }
}
