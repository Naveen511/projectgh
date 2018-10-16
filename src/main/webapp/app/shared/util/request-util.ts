import { HttpParams } from '@angular/common/http';

export const createRequestOption = (req?: any): HttpParams => {
    let options: HttpParams = new HttpParams();
    if (req) {
        Object.keys(req).forEach(key => {
            if (key !== 'sort') {
                options = options.set(key, req[key]);
            }
        });
        if (req.sort) {
            req.sort.forEach(val => {
                options = options.append('sort', val);
            });
        }
        if (req.filter) {
            for (const k in req.filter) {
                if (k) {
                    options = options.append(k, req.filter[k]);
               }
            }
        }
    }
    return options;
};
