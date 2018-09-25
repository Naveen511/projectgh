package com.niche.ng.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A OperationalHead.
 */
@Entity
@Table(name = "operational_head")
public class OperationalHead extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 64)
    @Column(name = "name", length = 64, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Integer status;

    @OneToMany(mappedBy = "operationalHead")
    private Set<Zonal> zonals = new HashSet<>();

    @OneToMany(mappedBy = "operationalHead")
    private Set<MapZonalWithOh> mapZonalWithOhs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public OperationalHead name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public OperationalHead description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public OperationalHead status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<Zonal> getZonals() {
        return zonals;
    }

    public OperationalHead zonals(Set<Zonal> zonals) {
        this.zonals = zonals;
        return this;
    }

    public OperationalHead addZonal(Zonal zonal) {
        this.zonals.add(zonal);
        zonal.setOperationalHead(this);
        return this;
    }

    public OperationalHead removeZonal(Zonal zonal) {
        this.zonals.remove(zonal);
        zonal.setOperationalHead(null);
        return this;
    }

    public void setZonals(Set<Zonal> zonals) {
        this.zonals = zonals;
    }

    public Set<MapZonalWithOh> getMapZonalWithOhs() {
        return mapZonalWithOhs;
    }

    public OperationalHead mapZonalWithOhs(Set<MapZonalWithOh> mapZonalWithOhs) {
        this.mapZonalWithOhs = mapZonalWithOhs;
        return this;
    }

    public OperationalHead addMapZonalWithOh(MapZonalWithOh mapZonalWithOh) {
        this.mapZonalWithOhs.add(mapZonalWithOh);
        mapZonalWithOh.setOperationalHead(this);
        return this;
    }

    public OperationalHead removeMapZonalWithOh(MapZonalWithOh mapZonalWithOh) {
        this.mapZonalWithOhs.remove(mapZonalWithOh);
        mapZonalWithOh.setOperationalHead(null);
        return this;
    }

    public void setMapZonalWithOhs(Set<MapZonalWithOh> mapZonalWithOhs) {
        this.mapZonalWithOhs = mapZonalWithOhs;
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
        OperationalHead operationalHead = (OperationalHead) o;
        if (operationalHead.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), operationalHead.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OperationalHead{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
