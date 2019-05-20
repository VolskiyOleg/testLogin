package com.volskyioleh.test.api.models;

import com.google.gson.annotations.SerializedName;

public class ItemModel {


    @SerializedName("id")
    public int id;

    @SerializedName("ActualTime")
    public long actualTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getActualTime() {
        return actualTime;
    }

    public void setActualTime(long actualTime) {
        this.actualTime = actualTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPair() {
        return pair;
    }

    public void setPair(String pair) {
        this.pair = pair;
    }

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public int getTradingSystem() {
        return tradingSystem;
    }

    public void setTradingSystem(int tradingSystem) {
        this.tradingSystem = tradingSystem;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getSl() {
        return sl;
    }

    public void setSl(float sl) {
        this.sl = sl;
    }

    public float getTp() {
        return tp;
    }

    public void setTp(float tp) {
        this.tp = tp;
    }

    @SerializedName("Comment")
    public String comment;

    @SerializedName("Pair")
    public String pair;

    @SerializedName("Cmd")
    public int cmd;

    @SerializedName("TradingSystem")
    public int tradingSystem;

    @SerializedName("Period")
    public String period;

    @SerializedName("Price")
    public float price;

    @SerializedName("Sl")
    public float sl;

    @SerializedName("Tp")
    public float tp;


//    "Id":16760,
//            "ActualTime":1520217807,
//            "Comment":"Trend channel",
//            "Pair":"AUDUSD",
//            "Cmd":5,
//            "TradingSystem":3,
//            "Period":"H1",
//            "Price":0.77648,
//            "Sl":0.77846,
//            "Tp":0.7745
}
