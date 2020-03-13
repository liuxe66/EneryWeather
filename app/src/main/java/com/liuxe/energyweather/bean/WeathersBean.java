package com.liuxe.energyweather.bean;

import java.io.Serializable;

public class WeathersBean implements Serializable {
    /**
     * date : 2019-12-19
     * img : 1
     * sun_down_time : 17:17
     * sun_rise_time : 07:30
     * temp_day_c : 10
     * temp_day_f : 50.0
     * temp_night_c : -2
     * temp_night_f : 28.4
     * wd :
     * weather : 多云
     * week : 星期四
     * ws :
     */

    private String date;
    private String img;
    private String sun_down_time;
    private String sun_rise_time;
    private String temp_day_c;
    private String temp_day_f;
    private String temp_night_c;
    private String temp_night_f;
    private String wd;
    private String weather;
    private String week;
    private String ws;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSun_down_time() {
        return sun_down_time;
    }

    public void setSun_down_time(String sun_down_time) {
        this.sun_down_time = sun_down_time;
    }

    public String getSun_rise_time() {
        return sun_rise_time;
    }

    public void setSun_rise_time(String sun_rise_time) {
        this.sun_rise_time = sun_rise_time;
    }

    public String getTemp_day_c() {
        return temp_day_c;
    }

    public void setTemp_day_c(String temp_day_c) {
        this.temp_day_c = temp_day_c;
    }

    public String getTemp_day_f() {
        return temp_day_f;
    }

    public void setTemp_day_f(String temp_day_f) {
        this.temp_day_f = temp_day_f;
    }

    public String getTemp_night_c() {
        return temp_night_c;
    }

    public void setTemp_night_c(String temp_night_c) {
        this.temp_night_c = temp_night_c;
    }

    public String getTemp_night_f() {
        return temp_night_f;
    }

    public void setTemp_night_f(String temp_night_f) {
        this.temp_night_f = temp_night_f;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getWs() {
        return ws;
    }

    public void setWs(String ws) {
        this.ws = ws;
    }
}
