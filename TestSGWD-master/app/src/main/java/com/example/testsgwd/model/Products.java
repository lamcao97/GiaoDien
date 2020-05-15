package com.example.testsgwd.model;


public class Products {

    private int mProductsId;
    private String mProCode;
    private String mProName;
    private String mProDescription;
    private String mProStatus;
    private String mProUnit;
    private String mProWarranty;
    private String mProPrice;
    private String mProImg;
    private int mProDoorOrWindow;
    private int mCateId;

    public Products(int mProductsId, String mProCode, String mProName, String mProDescription, String mProStatus, String mProUnit, String mProWarranty, String mProPrice, String mProImg, int mProDoorOrWindow, int mCateId) {
        this.mProductsId = mProductsId;
        this.mProCode = mProCode;
        this.mProName = mProName;
        this.mProDescription = mProDescription;
        this.mProStatus = mProStatus;
        this.mProUnit = mProUnit;
        this.mProWarranty = mProWarranty;
        this.mProPrice = mProPrice;
        this.mProImg = mProImg;
        this.mProDoorOrWindow = mProDoorOrWindow;
        this.mCateId = mCateId;
    }

    public int getmProductsId() {
        return mProductsId;
    }

    public void setmProductsId(int mProductsId) {
        this.mProductsId = mProductsId;
    }

    public String getmProCode() {
        return mProCode;
    }

    public void setmProCode(String mProCode) {
        this.mProCode = mProCode;
    }

    public String getmProName() {
        return mProName;
    }

    public void setmProName(String mProName) {
        this.mProName = mProName;
    }

    public String getmProDescription() {
        return mProDescription;
    }

    public void setmProDescription(String mProDescription) {
        this.mProDescription = mProDescription;
    }

    public String getmProStatus() {
        return mProStatus;
    }

    public void setmProStatus(String mProStatus) {
        this.mProStatus = mProStatus;
    }

    public String getmProUnit() {
        return mProUnit;
    }

    public void setmProUnit(String mProUnit) {
        this.mProUnit = mProUnit;
    }

    public String getmProWarranty() {
        return mProWarranty;
    }

    public void setmProWarranty(String mProWarranty) {
        this.mProWarranty = mProWarranty;
    }

    public String getmProPrice() {
        return mProPrice;
    }

    public void setmProPrice(String mProPrice) {
        this.mProPrice = mProPrice;
    }

    public String getmProImg() {
        return mProImg;
    }

    public void setmProImg(String mProImg) {
        this.mProImg = mProImg;
    }

    public int getmProDoorOrWindow() {
        return mProDoorOrWindow;
    }

    public void setmProDoorOrWindow(int mProDoorOrWindow) {
        this.mProDoorOrWindow = mProDoorOrWindow;
    }

    public int getmCateId() {
        return mCateId;
    }

    public void setmCateId(int mCateId) {
        this.mCateId = mCateId;
    }
}
