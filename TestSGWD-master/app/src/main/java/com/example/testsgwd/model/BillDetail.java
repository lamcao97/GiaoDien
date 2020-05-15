package com.example.testsgwd.model;

public class BillDetail {
    private int mBillDetailID;
    private int mProDetailID;
    private int mBillID;
    private String mProWidth;
    private String mProHeight;
    private int mQuantity;
    private String mBillDetailPrice;

    public BillDetail(int mBillDetailID, int mProDetailID, int mBillID, String mProWidth, String mProHeight, int mQuantity, String mBillDetailPrice) {
        this.mBillDetailID = mBillDetailID;
        this.mProDetailID = mProDetailID;
        this.mBillID = mBillID;
        this.mProWidth = mProWidth;
        this.mProHeight = mProHeight;
        this.mQuantity = mQuantity;
        this.mBillDetailPrice = mBillDetailPrice;
    }

    public int getmBillDetailID() {
        return mBillDetailID;
    }

    public void setmBillDetailID(int mBillDetailID) {
        this.mBillDetailID = mBillDetailID;
    }

    public int getmProDetailID() {
        return mProDetailID;
    }

    public void setmProDetailID(int mProDetailID) {
        this.mProDetailID = mProDetailID;
    }

    public int getmBillID() {
        return mBillID;
    }

    public void setmBillID(int mBillID) {
        this.mBillID = mBillID;
    }

    public String getmProWidth() {
        return mProWidth;
    }

    public void setmProWidth(String mProWidth) {
        this.mProWidth = mProWidth;
    }

    public String getmProHeight() {
        return mProHeight;
    }

    public void setmProHeight(String mProHeight) {
        this.mProHeight = mProHeight;
    }

    public int getmQuantity() {
        return mQuantity;
    }

    public void setmQuantity(int mQuantity) {
        this.mQuantity = mQuantity;
    }

    public String getmBillDetailPrice() {
        return mBillDetailPrice;
    }

    public void setmBillDetailPrice(String mBillDetailPrice) {
        this.mBillDetailPrice = mBillDetailPrice;
    }
}
