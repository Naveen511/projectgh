package com.niche.ng.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the MapSectorWithZonal entity.
 */
public class MapSectorWithZonalDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate fromDate;

    private LocalDate toDate;

    private String description;

    @Max(value = 10)
    private Integer status;

    private Long zonalId;

    private String zonalZoneName;

    private Long sectorId;

    private String sectorSectorName;

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

    public Long getSectorId() {
        return sectorId;
    }

    public void setSectorId(Long sectorId) {
        this.sectorId = sectorId;
    }

    public String getSectorSectorName() {
        return sectorSectorName;
    }

    public void setSectorSectorName(String sectorSectorName) {
        this.sectorSectorName = sectorSectorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MapSectorWithZonalDTO mapSectorWithZonalDTO = (MapSectorWithZonalDTO) o;
        if (mapSectorWithZonalDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mapSectorWithZonalDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MapSectorWithZonalDTO{" +
            "id=" + getId() +
            ", fromDate='" + getFromDate() + "'" +
            ", toDate='" + getToDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            ", zonal=" + getZonalId() +
            ", zonal='" + getZonalZoneName() + "'" +
            ", sector=" + getSectorId() +
            ", sector='" + getSectorSectorName() + "'" +
            "}";
    }
}
