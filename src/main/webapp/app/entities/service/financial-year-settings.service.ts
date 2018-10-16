import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IFinancialYearSettings, STATUS_ACTIVE } from 'app/shared/model/financial-year-settings.model';

type EntityResponseType = HttpResponse<IFinancialYearSettings>;
type EntityArrayResponseType = HttpResponse<IFinancialYearSettings[]>;

@Injectable({ providedIn: 'root' })
export class FinancialYearSettingsService {
    private resourceUrl = SERVER_API_URL + 'api/financial-year-settings';

    constructor(private http: HttpClient) {}

    // Create the financialYearSettings
    create(financialYearSettings: IFinancialYearSettings): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(financialYearSettings);
        return this.http
            .post<IFinancialYearSettings>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    // Update the financialYearSettings
    update(financialYearSettings: IFinancialYearSettings): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(financialYearSettings);
        return this.http
            .put<IFinancialYearSettings>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    // Find the financialYearSettings using Id
    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IFinancialYearSettings>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    // List all the financialYearSettings
    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IFinancialYearSettings[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    // Delete the financialYearSettings Id
    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    // List all the record based on the status
    getActiveRecord(): Observable<EntityArrayResponseType> {
        return this.http
            .get<IFinancialYearSettings[]>(`${this.resourceUrl}/active-record/${STATUS_ACTIVE}`, { observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    private convertDateFromClient(financialYearSettings: IFinancialYearSettings): IFinancialYearSettings {
        const copy: IFinancialYearSettings = Object.assign({}, financialYearSettings, {
            startDate:
                financialYearSettings.startDate != null && financialYearSettings.startDate.isValid()
                    ? financialYearSettings.startDate.format(DATE_FORMAT)
                    : null,
            endDate:
                financialYearSettings.endDate != null && financialYearSettings.endDate.isValid()
                    ? financialYearSettings.endDate.format(DATE_FORMAT)
                    : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.startDate = res.body.startDate != null ? moment(res.body.startDate) : null;
        res.body.endDate = res.body.endDate != null ? moment(res.body.endDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((financialYearSettings: IFinancialYearSettings) => {
            financialYearSettings.startDate = financialYearSettings.startDate != null ? moment(financialYearSettings.startDate) : null;
            financialYearSettings.endDate = financialYearSettings.endDate != null ? moment(financialYearSettings.endDate) : null;
        });
        return res;
    }
}
