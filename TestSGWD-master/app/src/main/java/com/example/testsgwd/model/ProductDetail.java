package com.example.testsgwd.model;

public class ProductDetail {

    private int mProDetailID;
    private String mProColor;
    private String mProPrice;
    private String mProImage;
    private int mProID;

    public ProductDetail(int mProDetailID, String mProColor, String mProPrice, String mProImage, int mProID) {
        this.mProDetailID = mProDetailID;
        this.mProColor = mProColor;
        this.mProPrice = mProPrice;
        this.mProImage = mProImage;
        this.mProID = mProID;
    }

    public int getmProDetailID() {
        return mProDetailID;
    }

    public void setmProDetailID(int mProDetailID) {
        this.mProDetailID = mProDetailID;
    }

    public String getmProColor() {
        return mProColor;
    }

    public void setmProColor(String mProColor) {
        this.mProColor = mProColor;
    }

    public String getmProPrice() {
        return mProPrice;
    }

    public void setmProPrice(String mProPrice) {
        this.mProPrice = mProPrice;
    }

    public String getmProImage() {
        return mProImage;
    }

    public void setmProImage(String mProImage) {
        this.mProImage = mProImage;
    }

    public int getmProID() {
        return mProID;
    }

    public void setmProID(int mProID) {
        this.mProID = mProID;
    }
}
