export interface ICommonSettings {
    id?: number;
    daysToCloseBatch?: number;
    status?: number;
}

export class CommonSettings implements ICommonSettings {
    constructor(public id?: number, public daysToCloseBatch?: number, public status?: number) {}
}
