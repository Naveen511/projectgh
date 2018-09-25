package com.niche.ng.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the MapZonalWithOh entity.
 */
public class MapZonalWithOhDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    private LocalDate fromDate;

    private LocalDate toDate;

    private String description;

    private Integer status;

    private Long zonalId;

    private String zonalZoneName;

    private Long operationalHeadId;

    private String operationalHeadName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getZonalId() {
        return zonalId;
    }

    public void setZonalId(Long zonalId) {
        this.zonalId = zonalId;
    }

    public String getZonalZoneName() {
        return zonalZoneName;
    }

    public void setZonalZoneName(String zonalZoneName) {
        this.zonalZoneName = zonalZoneName;
    }

    public Long getOperationalHeadId() {
        return operationalHeadId;
    }

    public void setOperationalHeadId(Long operationalHeadId) {
        this.operationalHeadId = operationalHeadId;
    }

    public String getOperationalHeadName() {
        return operationalHeadName;
    }

    public void setOperationalHeadName(String operationalHeadName) {
        this.operationalHeadName = operationalHeadName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MapZonalWithOhDTO mapZonalWithOhDTO = (MapZonalWithOhDTO) o;
        if (mapZonalWithOhDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mapZonalWithOhDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MapZonalWithOhDTO{" +
            "id=" + getId() +
            ", fromDate='" + getFromDate() + "'" +
            ", toDate='" + getToDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            ", zonal=" + getZonalId() +
            ", zonal='" + getZonalZoneName() + "'" +
            ", operationalHead=" + getOperationalHeadId() +
            ", operationalHead='" + getOperationalHeadName() + "'" +
            "}";
    }
}
