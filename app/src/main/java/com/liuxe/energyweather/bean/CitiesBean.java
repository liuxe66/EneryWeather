package com.liuxe.energyweather.bean;

import java.util.ArrayList;
import java.util.List;

public class CitiesBean {
    private List<CityBean> cities;

    public List<CityBean> getCities() {
        if (cities == null) {
            return new ArrayList<>();
        }
        return cities;
    }

    public void setCities(List<CityBean> cities) {
        this.cities = cities;
    }
}
