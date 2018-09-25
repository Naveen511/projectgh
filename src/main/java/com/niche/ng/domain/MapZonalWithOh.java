package com.niche.ng.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A MapZonalWithOh.
 */
@Entity
@Table(name = "map_zonal_with_oh")
public class MapZonalWithOh extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Integer status;

    @ManyToOne
    @JsonIgnoreProperties("mapZonalWithOhs")
    private Zonal zonal;

    @ManyToOne
    @JsonIgnoreProperties("mapZonalWithOhs")
    private OperationalHead operationalHead;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public MapZonalWithOh fromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
        return this;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public MapZonalWithOh toDate(LocalDate toDate) {
        this.toDate = toDate;
        return this;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public String getDescription() {
        return description;
    }

    public MapZonalWithOh description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public MapZonalWithOh status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Zonal getZonal() {
        return zonal;
    }

    public MapZonalWithOh zonal(Zonal zonal) {
        this.zonal = zonal;
        return this;
    }

    public void setZonal(Zonal zonal) {
        this.zonal = zonal;
    }

    public OperationalHead getOperationalHead() {
        return operationalHead;
    }

    public MapZonalWithOh operationalHead(OperationalHead operationalHead) {
        this.operationalHead = operationalHead;
        return this;
    }

    public void setOperationalHead(OperationalHead operationalHead) {
        this.operationalHead = operationalHead;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MapZonalWithOh mapZonalWithOh = (MapZonalWithOh) o;
        if (mapZonalWithOh.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mapZonalWithOh.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MapZonalWithOh{" +
            "id=" + getId() +
            ", fromDate='" + getFromDate() + "'" +
            ", toDate='" + getToDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
