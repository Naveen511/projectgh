package com.niche.ng.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the CommonSettings entity.
 */
public class CommonSettingsDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    private Integer daysToCloseBatch;

    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDaysToCloseBatch() {
        return daysToCloseBatch;
    }

    public void setDaysToCloseBatch(Integer daysToCloseBatch) {
        this.daysToCloseBatch = daysToCloseBatch;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CommonSettingsDTO commonSettingsDTO = (CommonSettingsDTO) o;
        if (commonSettingsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), commonSettingsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CommonSettingsDTO{" +
            "id=" + getId() +
            ", daysToCloseBatch=" + getDaysToCloseBatch() +
            ", status=" + getStatus() +
            "}";
    }
}
