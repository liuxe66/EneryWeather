package com.liuxe.energyweather.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WeatherTypeBean implements Serializable, MultiItemEntity {
    public static final int REALTIME = 1;
    public static final int DETAILS = 2;
    public static final int WEEKS = 3;
    public static final int INDEX = 4;
    public static final int SPACE = 5;

    private int itemType;

    public WeatherTypeBean(int itemType) {
        this.itemType = itemType;
    }

    private RealtimeBean realtime;
    private WeatherDetailsInfoBean weatherDetailsInfo;
    private WeathersBean weathers;
    private IndexesBean indexes;

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public RealtimeBean getRealtime() {
        return realtime;
    }

    public void setRealtime(RealtimeBean realtime) {
        this.realtime = realtime;
    }

    public WeatherDetailsInfoBean getWeatherDetailsInfo() {
        return weatherDetailsInfo;
    }

    public void setWeatherDetailsInfo(WeatherDetailsInfoBean weatherDetailsInfo) {
        this.weatherDetailsInfo = weatherDetailsInfo;
    }

    public WeathersBean getWeathers() {
        return weathers;
    }

    public void setWeathers(WeathersBean weathers) {
        this.weathers = weathers;
    }

    public IndexesBean getIndexes() {
        return indexes;
    }

    public void setIndexes(IndexesBean indexes) {
        this.indexes = indexes;
    }
}
