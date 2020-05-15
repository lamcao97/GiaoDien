package com.example.testsgwd.model;

import java.util.Date;

public class Bills {

    private int mBillID;
    private int mUserID;
    private Double mBillTotalPrice;
    private Date mBillDateBook;
    private Date mBillDateShip;
    private Boolean mPayment;

    public Bills(int mBillID, int mUserID, Double mBillTotalPrice, Date mBillDateBook, Date mBillDateShip, Boolean mPayment, int mProHeight, int mProWidth, int mProSize) {
        this.mBillID = mBillID;
        this.mUserID = mUserID;
        this.mBillTotalPrice = mBillTotalPrice;
        this.mBillDateBook = mBillDateBook;
        this.mBillDateShip = mBillDateShip;
        this.mPayment = mPayment;
    }

    public int getmBillID() {
        return mBillID;
    }

    public void setmBillID(int mBillID) {
        this.mBillID = mBillID;
    }

    public int getmUserID() {
        return mUserID;
    }

    public void setmUserID(int mUserID) {
        this.mUserID = mUserID;
    }

    public Double getmBillTotalPrice() {
        return mBillTotalPrice;
    }

    public void setmBillTotalPrice(Double mBillTotalPrice) {
        this.mBillTotalPrice = mBillTotalPrice;
    }

    public Date getmBillDateBook() {
        return mBillDateBook;
    }

    public void setmBillDateBook(Date mBillDateBook) {
        this.mBillDateBook = mBillDateBook;
    }

    public Date getmBillDateShip() {
        return mBillDateShip;
    }

    public void setmBillDateShip(Date mBillDateShip) {
        this.mBillDateShip = mBillDateShip;
    }

    public Boolean getmPayment() {
        return mPayment;
    }

    public void setmPayment(Boolean mPayment) {
        this.mPayment = mPayment;
    }
}
