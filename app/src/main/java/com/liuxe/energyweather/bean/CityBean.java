package com.liuxe.energyweather.bean;

public class CityBean {
    private String cityid;
    private String city;
    private boolean isDot;

    public boolean isDot() {
        return isDot;
    }

    public void setDot(boolean dot) {
        isDot = dot;
    }

    public String getCityid() {
        return cityid == null ? "" : cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getCity() {
        return city == null ? "" : city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
