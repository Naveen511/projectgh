/******************************************************************************
 *  Property of Nichehands
 *  Nichehands Confidential Proprietary
 *  Nichehands Copyright (C) 2018 All rights reserved
 *  ----------------------------------------------------------------------------
 *  Date  : 2018/08/02
 *  Target: yarn
 *  -----------------------------------------------------------------------------
 *  File Description    : This file performs PointOfSaleDetails Generation.
 *  Declared the table fields and data types for the PointOfSaleDetails table.
 *  Defined the following Relation for the PointOfSaleDetails Table :
 *  ManyToOne Relation  : PickListValue, NurseryStock
 *
 *******************************************************************************/
package com.niche.ng.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

 /**
 * PointOfSaleDetails Domain Class
 * 
 * PointOfSaleDetails class implements Serializable to convert instance class into 
 * series of bytes.
 * 
 * Generates the setter and getter for each fields in table.
 * Creates the default constructor which consist of all the fields in the table and 
 * empty constructor.
 * Generates the toString for get the values of the particular format.
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

    /**
     * Relation Name : ManyToOne - pickListVariety
     * Table Name    : Connect the PointOfSaleDetails Table to PickListValue Table
     * To get the pickListVariety.
     */
    @ManyToOne
    @JsonIgnoreProperties("pointOfSaleVarietys")
    private PickListValue pickListVariety;

    /**
     * Relation Name : ManyToOne - pickListCategory
     * Table Name    : Connect the PointOfSaleDetails Table to PickListValue Table
     * To get the pickListCategory.
     */
    @ManyToOne
    @JsonIgnoreProperties("pointOfSaleCategorys")
    private PickListValue pickListCategory;

    /**
     * Relation Name : ManyToOne - nurseryStock
     * Table Name    : Connect the PointOfSaleDetails Table to NurseryStock Table
     * To get the nurseryStock.
     */
    @ManyToOne
    @JsonIgnoreProperties("pointOfSaleDetails")
    private NurseryStock nurseryStock;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    /**
     * To Get the Id from pointOfSaleDetails table
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * To Set the Id for pointOfSaleDetails table
     *
     * @param id the id in pointOfSaleDetails table
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To Get the quantity from pointOfSaleDetails table
     * 
     * @return quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * To Set the quantity for pointOfSaleDetails table
     *
     * @param quantity the quantity in pointOfSaleDetails table
     * @return this
     */
    public PointOfSaleDetails quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    /**
     * To Set the quantity for pointOfSaleDetails table
     *
     * @param quantity the quantity in pointOfSaleDetails table
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * To Get the purposeOfTaking from pointOfSaleDetails table
     * 
     * @return purposeOfTaking
     */
    public String getPurposeOfTaking() {
        return purposeOfTaking;
    }

    /**
     * To Set the purposeOfTaking for pointOfSaleDetails table
     *
     * @param purposeOfTaking the purposeOfTaking in pointOfSaleDetails table
     * @return this
     */
    public PointOfSaleDetails purposeOfTaking(String purposeOfTaking) {
        this.purposeOfTaking = purposeOfTaking;
        return this;
    }

    /**
     * To Set the purposeOfTaking for pointOfSaleDetails table
     *
     * @param purposeOfTaking the purposeOfTaking in pointOfSaleDetails table
     */
    public void setPurposeOfTaking(String purposeOfTaking) {
        this.purposeOfTaking = purposeOfTaking;
    }

    /**
     * To Get the donorName from pointOfSaleDetails table
     * 
     * @return donorName
     */
    public String getDonorName() {
        return donorName;
    }

    /**
     * To Set the donorName for pointOfSaleDetails table
     *
     * @param donorName the donorName in pointOfSaleDetails table
     * @return this
     */
    public PointOfSaleDetails donorName(String donorName) {
        this.donorName = donorName;
        return this;
    }

    /**
     * To Set the donorName for pointOfSaleDetails table
     *
     * @param donorName the donorName in pointOfSaleDetails table
     */
    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    /**
     * To Get the donorAddress from pointOfSaleDetails table
     * 
     * @return donorAddress
     */
    public String getDonorAddress() {
        return donorAddress;
    }

    /**
     * To Set the donorAddress for pointOfSaleDetails table
     *
     * @param donorAddress the donorAddress in pointOfSaleDetails table
     * @return this
     */
    public PointOfSaleDetails donorAddress(String donorAddress) {
        this.donorAddress = donorAddress;
        return this;
    }

    /**
     * To Set the donorAddress for pointOfSaleDetails table
     *
     * @param donorAddress the donorAddress in pointOfSaleDetails table
     */
    public void setDonorAddress(String donorAddress) {
        this.donorAddress = donorAddress;
    }

    /**
     * To Get the contactNo from pointOfSaleDetails table
     * 
     * @return contactNo
     */
    public String getContactNo() {
        return contactNo;
    }

    /**
     * To Set the contactNo for pointOfSaleDetails table
     *
     * @param contactNo the contactNo in pointOfSaleDetails table
     * @return this
     */
    public PointOfSaleDetails contactNo(String contactNo) {
        this.contactNo = contactNo;
        return this;
    }

    /**
     * To Set the contactNo for pointOfSaleDetails table
     *
     * @param contactNo the contactNo in pointOfSaleDetails table
     */
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    /**
     * To Get the discount from pointOfSaleDetails table
     * 
     * @return discount
     */
    public Float getDiscount() {
        return discount;
    }

    /**
     * To Set the discount for pointOfSaleDetails table
     *
     * @param discount the discount in pointOfSaleDetails table
     * @return this
     */
    public PointOfSaleDetails discount(Float discount) {
        this.discount = discount;
        return this;
    }

    /**
     * To Set the discount for pointOfSaleDetails table
     *
     * @param discount the discount in pointOfSaleDetails table
     */
    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    /**
     * To Get the discountAmount from pointOfSaleDetails table
     * 
     * @return discountAmount
     */
    public Float getDiscountAmount() {
        return discountAmount;
    }

    /**
     * To Set the discountAmount for pointOfSaleDetails table
     *
     * @param discountAmount the discountAmount in pointOfSaleDetails table
     * @return this
     */
    public PointOfSaleDetails discountAmount(Float discountAmount) {
        this.discountAmount = discountAmount;
        return this;
    }

    /**
     * To Set the discountAmount for pointOfSaleDetails table
     *
     * @param discountAmount the discountAmount in pointOfSaleDetails table
     */
    public void setDiscountAmount(Float discountAmount) {
        this.discountAmount = discountAmount;
    }

    /**
     * To Get the collectedAmount from pointOfSaleDetails table
     * 
     * @return collectedAmount
     */
    public Float getCollectedAmount() {
        return collectedAmount;
    }

    /**
     * To Set the collectedAmount for pointOfSaleDetails table
     *
     * @param collectedAmount the collectedAmount in pointOfSaleDetails table
     * @return this
     */
    public PointOfSaleDetails collectedAmount(Float collectedAmount) {
        this.collectedAmount = collectedAmount;
        return this;
    }

    /**
     * To Set the collectedAmount for pointOfSaleDetails table
     *
     * @param collectedAmount the collectedAmount in pointOfSaleDetails table
     */
    public void setCollectedAmount(Float collectedAmount) {
        this.collectedAmount = collectedAmount;
    }

    /**
     * To Get the date from pointOfSaleDetails table
     * 
     * @return date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * To Set the date for pointOfSaleDetails table
     *
     * @param date the date in pointOfSaleDetails table
     * @return this
     */
    public PointOfSaleDetails date(LocalDate date) {
        this.date = date;
        return this;
    }

    /**
     * To Set the date for pointOfSaleDetails table
     *
     * @param date the date in pointOfSaleDetails table
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * To Get the status from pointOfSaleDetails table
     * 
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * To Set the status for pointOfSaleDetails table
     *
     * @param status the status in pointOfSaleDetails table
     * @return this
     */
    public PointOfSaleDetails status(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * To Set the status for pointOfSaleDetails table
     *
     * @param status the status in pointOfSaleDetails table
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * To Get the pickListVariety from pointOfSaleDetails table
     * 
     * @return pickListVariety
     */
    public PickListValue getPickListVariety() {
        return pickListVariety;
    }

    /**
     * To Set the pickListVariety for pointOfSaleDetails table
     *
     * @param pickListValue the pickListValue in pointOfSaleDetails table
     * @return this
     */
    public PointOfSaleDetails pickListVariety(PickListValue pickListValue) {
        this.pickListVariety = pickListValue;
        return this;
    }

    /**
     * To Set the pickListVariety for pointOfSaleDetails table
     *
     * @param pickListValue the pickListValue in pointOfSaleDetails table
     */
    public void setPickListVariety(PickListValue pickListValue) {
        this.pickListVariety = pickListValue;
    }

    /**
     * To Get the pickListCategory from pointOfSaleDetails table
     * 
     * @return pickListCategory
     */
    public PickListValue getPickListCategory() {
        return pickListCategory;
    }

    /**
     * To Set the pickListCategory for pointOfSaleDetails table
     *
     * @param pickListValue the pickListValue in pointOfSaleDetails table
     * @return this
     */
    public PointOfSaleDetails pickListCategory(PickListValue pickListValue) {
        this.pickListCategory = pickListValue;
        return this;
    }

    /**
     * To Set the pickListValue for pointOfSaleDetails table
     *
     * @param pickListValue the pickListValue in pointOfSaleDetails table
     */
    public void setPickListCategory(PickListValue pickListValue) {
        this.pickListCategory = pickListValue;
    }

    /**
     * To Get the nurseryStock from pointOfSaleDetails table
     * 
     * @return nurseryStock
     */
    public NurseryStock getNurseryStock() {
        return nurseryStock;
    }

    /**
     * To Set the nurseryStock for pointOfSaleDetails table
     *
     * @param nurseryStock the nurseryStock in pointOfSaleDetails table
     * @return this
     */
    public PointOfSaleDetails nurseryStock(NurseryStock nurseryStock) {
        this.nurseryStock = nurseryStock;
        return this;
    }

    /**
     * To Set the nurseryStock for pointOfSaleDetails table
     *
     * @param nurseryStock the nurseryStock in pointOfSaleDetails table
     */
    public void setNurseryStock(NurseryStock nurseryStock) {
        this.nurseryStock = nurseryStock;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    /**
     * To check the equals
     * 
     * @return objects
     */
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

    /**
     * hash code for the id
     * 
     * @return objects
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    /**
     * toString to get the values
     * 
     * @return values
     */
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
