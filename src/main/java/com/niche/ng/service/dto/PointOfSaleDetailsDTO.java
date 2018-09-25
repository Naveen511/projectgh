package com.niche.ng.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the PointOfSaleDetails entity.
 */
public class PointOfSaleDetailsDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    private Integer quantity;

    private String purposeOfTaking;

    private String donorName;

    private String donorAddress;

    private String contactNo;

    private Float discount;

    private Float discountAmount;

    private Float collectedAmount;

    private LocalDate date;

    private Integer status;

    private Long pickListVarietyId;

    private String pickListVarietyPickListValue;

    private Long pickListCategoryId;

    private String pickListCategoryPickListValue;

    private Long nurseryStockId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getPurposeOfTaking() {
        return purposeOfTaking;
    }

    public void setPurposeOfTaking(String purposeOfTaking) {
        this.purposeOfTaking = purposeOfTaking;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getDonorAddress() {
        return donorAddress;
    }

    public void setDonorAddress(String donorAddress) {
        this.donorAddress = donorAddress;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Float getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Float discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Float getCollectedAmount() {
        return collectedAmount;
    }

    public void setCollectedAmount(Float collectedAmount) {
        this.collectedAmount = collectedAmount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getPickListVarietyId() {
        return pickListVarietyId;
    }

    public void setPickListVarietyId(Long pickListValueId) {
        this.pickListVarietyId = pickListValueId;
    }

    public String getPickListVarietyPickListValue() {
        return pickListVarietyPickListValue;
    }

    public void setPickListVarietyPickListValue(String pickListValuePickListValue) {
        this.pickListVarietyPickListValue = pickListValuePickListValue;
    }

    public Long getPickListCategoryId() {
        return pickListCategoryId;
    }

    public void setPickListCategoryId(Long pickListValueId) {
        this.pickListCategoryId = pickListValueId;
    }

    public String getPickListCategoryPickListValue() {
        return pickListCategoryPickListValue;
    }

    public void setPickListCategoryPickListValue(String pickListValuePickListValue) {
        this.pickListCategoryPickListValue = pickListValuePickListValue;
    }

    public Long getNurseryStockId() {
        return nurseryStockId;
    }

    public void setNurseryStockId(Long nurseryStockId) {
        this.nurseryStockId = nurseryStockId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PointOfSaleDetailsDTO pointOfSaleDetailsDTO = (PointOfSaleDetailsDTO) o;
        if (pointOfSaleDetailsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pointOfSaleDetailsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PointOfSaleDetailsDTO{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            ", purposeOfTaking='" + getPurposeOfTaking() + "'" +
            ", donorName='" + getDonorName() + "'" +
            ", donorAddress='" + getDonorAddress() + "'" +
            ", contactNo='" + getContactNo() + "'" +
            ", discount=" + getDiscount() +
            ", discountAmount=" + getDiscountAmount() +
            ", collectedAmount=" + getCollectedAmount() +
            ", date='" + getDate() + "'" +
            ", status=" + getStatus() +
            ", pickListVariety=" + getPickListVarietyId() +
            ", pickListVariety='" + getPickListVarietyPickListValue() + "'" +
            ", pickListCategory=" + getPickListCategoryId() +
            ", pickListCategory='" + getPickListCategoryPickListValue() + "'" +
            ", nurseryStock=" + getNurseryStockId() +
            "}";
    }
}
