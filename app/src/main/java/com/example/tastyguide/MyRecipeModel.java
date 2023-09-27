package com.example.tastyguide;

import java.io.Serializable;

public class MyRecipeModel implements Serializable {

    String FoodName;
    String FoodIng;
    String currentDate;
    String currentTime;
    String FoodDesc;
    int totalPrice;
    String documentId;


    public MyRecipeModel() {

    }

    public MyRecipeModel(String productName, String foodDesc, String currentDate, String currentTime, String foodIng, int totalPrice) {

        this.FoodName = productName;
        this.FoodDesc = foodDesc;
        this.currentDate = currentDate;
        this.currentTime = currentTime;
        this.FoodIng = foodIng;
        this.totalPrice = totalPrice;
    }

    public String getDocumentId() {
        return documentId;

    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getFoodName() {
        return FoodName;
    }

    public void setProductName(String productName) {
        this.FoodName = productName;
    }

    public String getFoodIng() {
        return FoodIng;
    }

    public void setFoodIng(String productPrice) {
        this.FoodIng = productPrice;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getFoodDesc() {
        return FoodDesc;
    }

    public void setFoodDesc(String totalQuantity) {
        this.FoodDesc = totalQuantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}