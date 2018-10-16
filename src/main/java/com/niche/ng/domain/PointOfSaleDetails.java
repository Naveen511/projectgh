package com.niche.ng.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A PointOfSaleDetails.
 */
@Entity
@Table(name = "point_of_sale_details")
public class PointOfSaleDetails extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "purpose_of_taking")
    private String purposeOfTaking;

    @Column(name = "donor_name")
    private String donorName;

    @Column(name = "donor_address")
    private String donorAddress;

    @Column(name = "contact_no")
    private String contactNo;

    @Column(name = "discount")
    private Float discount;

    @Column(name = "discount_amount")
    private Float discountAmount;

    @Column(name = "collected_amount")
    private Float collectedAmount;

    @Column(name = "jhi_date")
    private LocalDate date;

    @Column(name = "status")
    private Integer status;

    @ManyToOne
    @JsonIgnoreProperties("pointOfSaleVarietys")
    private PickListValue pickListVariety;

    @ManyToOne
    @JsonIgnoreProperties("pointOfSaleCategorys")
    private PickListValue pickListCategory;

    @ManyToOne
    @JsonIgnoreProperties("pointOfSaleDetails")
    private NurseryStock nurseryStock;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public PointOfSaleDetails quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getPurposeOfTaking() {
        return purposeOfTaking;
    }

    public PointOfSaleDetails purposeOfTaking(String purposeOfTaking) {
        this.purposeOfTaking = purposeOfTaking;
        return this;
    }

    public void setPurposeOfTaking(String purposeOfTaking) {
        this.purposeOfTaking = purposeOfTaking;
    }

    public String getDonorName() {
        return donorName;
    }

    public PointOfSaleDetails donorName(String donorName) {
        this.donorName = donorName;
        return this;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getDonorAddress() {
        return donorAddress;
    }

    public PointOfSaleDetails donorAddress(String donorAddress) {
        this.donorAddress = donorAddress;
        return this;
    }

    public void setDonorAddress(String donorAddress) {
        this.donorAddress = donorAddress;
    }

    public String getContactNo() {
        return contactNo;
    }

    public PointOfSaleDetails contactNo(String contactNo) {
        this.contactNo = contactNo;
        return this;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Float getDiscount() {
        return discount;
    }

    public PointOfSaleDetails discount(Float discount) {
        this.discount = discount;
        return this;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Float getDiscountAmount() {
        return discountAmount;
    }

    public PointOfSaleDetails discountAmount(Float discountAmount) {
        this.discountAmount = discountAmount;
        return this;
    }

    public void setDiscountAmount(Float discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Float getCollectedAmount() {
        return collectedAmount;
    }

    public PointOfSaleDetails collectedAmount(Float collectedAmount) {
        this.collectedAmount = collectedAmount;
        return this;
    }

    public void setCollectedAmount(Float collectedAmount) {
        this.collectedAmount = collectedAmount;
    }

    public LocalDate getDate() {
        return date;
    }

    public PointOfSaleDetails date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public PointOfSaleDetails status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public PickListValue getPickListVariety() {
        return pickListVariety;
    }

    public PointOfSaleDetails pickListVariety(PickListValue pickListValue) {
        this.pickListVariety = pickListValue;
        return this;
    }

    public void setPickListVariety(PickListValue pickListValue) {
        this.pickListVariety = pickListValue;
    }

    public PickListValue getPickListCategory() {
        return pickListCategory;
    }

    public PointOfSaleDetails pickListCategory(PickListValue pickListValue) {
        this.pickListCategory = pickListValue;
        return this;
    }

    public void setPickListCategory(PickListValue pickListValue) {
        this.pickListCategory = pickListValue;
    }

    public NurseryStock getNurseryStock() {
        return nurseryStock;
    }

    public PointOfSaleDetails nurseryStock(NurseryStock nurseryStock) {
        this.nurseryStock = nurseryStock;
        return this;
    }

    public void setNurseryStock(NurseryStock nurseryStock) {
        this.nurseryStock = nurseryStock;
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
        PointOfSaleDetails pointOfSaleDetails = (PointOfSaleDetails) o;
        if (pointOfSaleDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pointOfSaleDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PointOfSaleDetails{" +
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
            "}";
    }
}
