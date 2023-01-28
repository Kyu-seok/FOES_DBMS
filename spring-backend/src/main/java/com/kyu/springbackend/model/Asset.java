package com.kyu.springbackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Asset {
    @Id
    private String id;
    private String item;
    private String description;
    private String serialNo;
    private String brand;
    private int quantity;
    private String yearPurchased;
    private String originalCost;
    private String grant;
    private String warranty;
    private String modelNo;
    private String physicalCheck;
    private String conditionOfAsset;
    private String assetTagNumber;
    private String location;
    private String endUser;
    private String remark;

    public void update(Asset obj) {
        this.physicalCheck = obj.getPhysicalCheck();
        this.assetTagNumber = obj.getAssetTagNumber();
        this.item = obj.getItem();
        this.description = obj.getDescription();
        this.serialNo = obj.getSerialNo();
        this.yearPurchased = obj.getYearPurchased();
        this.warranty = obj.getWarranty();
        this.quantity = obj.getQuantity();
        this.location = obj.getLocation();
        this.originalCost = obj.getOriginalCost();
        this.conditionOfAsset = obj.getConditionOfAsset();
        this.endUser = obj.getEndUser();
        this.grant = obj.getGrant();
        this.brand = obj.getBrand();
        this.modelNo = obj.getModelNo();
        this.remark = obj.getRemark();
    }

}
