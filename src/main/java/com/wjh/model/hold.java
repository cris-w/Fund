package com.wjh.model;

public class hold {
    private String code;
    private String buy_time;
    private double quotient;
    private double net_value;
    private double pay;
    private String hold_state;

    public hold(String code, String buy_time, double quotient, double net_value, double pay, String hold_state) {
        this.code = code;
        this.buy_time = buy_time;
        this.quotient = quotient;
        this.net_value = net_value;
        this.pay = pay;
        this.hold_state = hold_state;
    }

    public hold() {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBuy_time() {
        return buy_time;
    }

    public void setBuy_time(String buy_time) {
        this.buy_time = buy_time;
    }

    public double getQuotient() {
        return quotient;
    }

    public void setQuotient(double quotient) {
        this.quotient = quotient;
    }

    public double getNet_value() {
        return net_value;
    }

    public void setNet_value(double net_value) {
        this.net_value = net_value;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

    public String getHold_state() {
        return hold_state;
    }

    public void setHold_state(String hold_state) {
        this.hold_state = hold_state;
    }
}
