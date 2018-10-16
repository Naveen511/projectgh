import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDamage } from 'app/shared/model/damage.model';

type EntityResponseType = HttpResponse<IDamage>;
type EntityArrayResponseType = HttpResponse<IDamage[]>;

@Injectable({ providedIn: 'root' })
export class DamageService {
    private resourceUrl = SERVER_API_URL + 'api/damages';

    constructor(private http: HttpClient) {}

    // Create damage Record
    create(damage: IDamage): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(damage);
        return this.http
            .post<IDamage>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    // Update damage record based on the Id
    update(damage: IDamage): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(damage);
        return this.http
            .put<IDamage>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    // Find the Damage record using Damage table ID
    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IDamage>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    // Get all the damage List
    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IDamage[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    // Delete the damage details using the damage table id
    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    // Get damage from the particular batch
    getParticularBatchRecord(batchId: number): Observable<EntityArrayResponseType> {
        return this.http
            .get<IDamage[]>(`${this.resourceUrl}/batch/${batchId}`, { observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    // List all the damge based on the status
    queryDamage(status: number): Observable<EntityArrayResponseType> {
        return this.http
            .get<IDamage[]>(`${this.resourceUrl}/damage/${status}`, { observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    // Get particular batch damage count
    getParticularBatchDamageCount(batchId: number): Observable<HttpResponse<String>> {
        return this.http.get<String>(`${this.resourceUrl}/count/${batchId}`, { observe: 'response'});
    }

    private convertDateFromClient(damage: IDamage): IDamage {
        const copy: IDamage = Object.assign({}, damage, {
            date: damage.date != null && damage.date.isValid() ? damage.date.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.date = res.body.date != null ? moment(res.body.date) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((damage: IDamage) => {
            damage.date = damage.date != null ? moment(damage.date) : null;
        });
        return res;
    }
}
