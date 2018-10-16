package com.niche.ng.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A CommonSettings.
 */
@Entity
@Table(name = "common_settings")
public class CommonSettings extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "days_to_close_batch")
    private Integer daysToCloseBatch;

    @Column(name = "status")
    private Integer status;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDaysToCloseBatch() {
        return daysToCloseBatch;
    }

    public CommonSettings daysToCloseBatch(Integer daysToCloseBatch) {
        this.daysToCloseBatch = daysToCloseBatch;
        return this;
    }

    public void setDaysToCloseBatch(Integer daysToCloseBatch) {
        this.daysToCloseBatch = daysToCloseBatch;
    }

    public Integer getStatus() {
        return status;
    }

    public CommonSettings status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        CommonSettings commonSettings = (CommonSettings) o;
        if (commonSettings.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), commonSettings.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CommonSettings{" +
            "id=" + getId() +
            ", daysToCloseBatch=" + getDaysToCloseBatch() +
            ", status=" + getStatus() +
            "}";
    }
}
