package com.liuxe.energyweather.bean;

import java.util.ArrayList;
import java.util.List;

public class WeatherCityBean {
    private String city;
    private String cityid;
    private RealtimeBean realtime;
    private List<WeatherTypeBean> weatherTypeList;
    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public RealtimeBean getRealtime() {
        return realtime;
    }

    public void setRealtime(RealtimeBean realtime) {
        this.realtime = realtime;
    }

    public String getCity() {
        return city == null ? "" : city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityid() {
        return cityid == null ? "" : cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public List<WeatherTypeBean> getWeatherTypeList() {
        if (weatherTypeList == null) {
            return new ArrayList<>();
        }
        return weatherTypeList;
    }

    public void setWeatherTypeList(List<WeatherTypeBean> weatherTypeList) {
        this.weatherTypeList = weatherTypeList;
    }
}
