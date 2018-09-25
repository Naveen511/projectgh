export class EntityAuditEvent {
    id?: string;
    entityId?: string;
    entityType?: string;
    action?: string;
    entityValue?: string;
    commitVersion?: number;
    modifiedBy?: string;
    modifiedDate?: Date;
}

export const ACTION_STATUS_UPDATE = 'UPDATE';
export const ACTION_STATUS_CREATE = 'CREATE';
