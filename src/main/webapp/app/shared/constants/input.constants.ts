import { Moment } from 'moment';

export const DATE_FORMAT = 'YYYY-MM-DD';
export const DATE_TIME_FORMAT = 'YYYY-MM-DDThh:mm';

// List of months
export const MONTHS = [
    { value: 1, name: 'January' },
    { value: 2, name: 'February' },
    { value: 3, name: 'March' },
    { value: 4, name: 'April' },
    { value: 5, name: 'May' },
    { value: 6, name: 'June' },
    { value: 7, name: 'July' },
    { value: 8, name: 'August' },
    { value: 9, name: 'September' },
    { value: 10, name: 'October' },
    { value: 11, name: 'November' },
    { value: 12, name: 'December' }
];

// Default Status
export const DEFAULT_STATUS = [
    { value: 1, name: 'ACTIVE' },
    { value: 2, name: 'INACTIVE' }
];

// Default damage status
export const SOFT_DELETE_STATUS = -1;

// Object class for filter
export class Filter {
    month?: string;
    fromDate?: Moment;
    toDate?: Moment;
}

// Constant for active status
export const STATUS_ACTIVE = 1;

// Constant for alert time out
export const ALERT_TIME_OUT_5000 = 5000;
export const ALERT_TIME_OUT_3000 = 3000;

// Constant for title - Whether it is movement or update
export const STATUS_MOVEMENT = 1;
export const STATUS_UPDATE = 2;
