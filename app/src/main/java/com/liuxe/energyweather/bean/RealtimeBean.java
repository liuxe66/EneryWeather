package com.liuxe.energyweather.bean;

import java.io.Serializable;

public class RealtimeBean  implements Serializable {
    /**
     * img : 0
     * sD : 53
     * sendibleTemp : 5
     * temp : 6
     * time : 2019-12-19 12:45:08
     * wD : 东南风
     * wS : 2级
     * weather : 晴
     * ziwaixian : N/A
     */

    private String img;
    private String sD;
    private String sendibleTemp;
    private String temp;
    private String time;
    private String wD;
    private String wS;
    private String weather;
    private String ziwaixian;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSD() {
        return sD;
    }

    public void setSD(String sD) {
        this.sD = sD;
    }

    public String getSendibleTemp() {
        return sendibleTemp;
    }

    public void setSendibleTemp(String sendibleTemp) {
        this.sendibleTemp = sendibleTemp;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWD() {
        return wD;
    }

    public void setWD(String wD) {
        this.wD = wD;
    }

    public String getWS() {
        return wS;
    }

    public void setWS(String wS) {
        this.wS = wS;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getZiwaixian() {
        return ziwaixian;
    }

    public void setZiwaixian(String ziwaixian) {
        this.ziwaixian = ziwaixian;
    }
}
