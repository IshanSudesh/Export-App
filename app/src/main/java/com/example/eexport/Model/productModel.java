package com.example.eexport.Model;

public class productModel {

    private String productName;
    private String productCode;
    private String productPrice;
    private String productDescription ;
    private String ownerName;
    private String ownerEmail;
    private String ownerPhone;
    private String imageUrl ;


    public productModel(){
    }

    public productModel(String productName, String productCode, String productPrice, String productDescription, String ownerName, String ownerEmail, String ownerPhone, String imageUrl){
        this.productName = productName;
        this.productCode = productCode;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.ownerName = ownerName;
        this.ownerEmail = ownerEmail;
        this.ownerPhone = ownerPhone;
        this.imageUrl = imageUrl;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productCode;
    }

    public void setProductId(String productId) {
        this.productCode = productId;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getDescription() {
        return productDescription;
    }

    public void setDescription(String description) {
        this.productDescription = description;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}


