package com.liuxe.energyweather.base;

import com.liuxe.energyweather.bean.WeatherBean;

import java.util.ArrayList;
import java.util.List;

public class BaseResponse<T>{
    /**
     * code : 200
     * message :
     * redirect :
     * value : [{"alarms":[],"city":"焦作","cityid":101181101,"indexes":[{"abbreviation":"gm","alias":"","content":"感冒极易发生，避免去人群密集的场所，勤洗手勤通风有利于降低感冒几率。","level":"极易发","name":"感冒指数"},{"abbreviation":"pp","alias":"","content":"天气寒冷，多补水，选用滋润保湿型化妆品，使用润唇膏。","level":"保湿","name":"化妆指数"},{"abbreviation":"yd","alias":"","content":"空气轻度污染，不宜在户外运动。","level":"不适宜","name":"运动指数"},{"abbreviation":"xc","alias":"","content":"洗车后，可至少保持3天车辆清洁，适宜洗车。","level":"适宜","name":"洗车指数"},{"abbreviation":"ct","alias":"","content":"天气偏凉，可以穿上最时尚的那件大衣来凹造型，搭一条围巾时尚指数爆表。","level":"凉","name":"穿衣指数"},{"abbreviation":"uv","alias":"","content":"辐射弱，涂擦SPF8-12防晒护肤品。","level":"最弱","name":"紫外线强度指数"}],"pm25":{"advice":"0","aqi":"107","citycount":679,"cityrank":10,"co":"16","color":"0","level":"0","no2":"27","o3":"7","pm10":"89","pm25":"106","quality":"轻度","so2":"9","timestamp":"","upDateTime":"2019-12-19 12:00:00"},"provinceName":"河南省","realtime":{"img":"0","sD":"53","sendibleTemp":"5","temp":"6","time":"2019-12-19 12:45:08","wD":"东南风","wS":"2级","weather":"晴","ziwaixian":"N/A"},"weatherDetailsInfo":{"publishTime":"2019-12-19 16:00:00","weather3HoursDetailsInfos":[{"endTime":"2019-12-19 16:00:00","highestTemperature":"10","img":"0","isRainFall":"","lowerestTemperature":"10","precipitation":"0","startTime":"2019-12-19 13:00:00","wd":"","weather":"晴","ws":""},{"endTime":"2019-12-19 19:00:00","highestTemperature":"6","img":"0","isRainFall":"","lowerestTemperature":"6","precipitation":"0","startTime":"2019-12-19 16:00:00","wd":"","weather":"晴","ws":""},{"endTime":"2019-12-19 22:00:00","highestTemperature":"2","img":"0","isRainFall":"","lowerestTemperature":"2","precipitation":"0","startTime":"2019-12-19 19:00:00","wd":"","weather":"晴","ws":""},{"endTime":"2019-12-20 01:00:00","highestTemperature":"1","img":"0","isRainFall":"","lowerestTemperature":"1","precipitation":"0","startTime":"2019-12-19 22:00:00","wd":"","weather":"晴","ws":""},{"endTime":"2019-12-20 04:00:00","highestTemperature":"-2","img":"0","isRainFall":"","lowerestTemperature":"-2","precipitation":"0","startTime":"2019-12-20 01:00:00","wd":"","weather":"晴","ws":""},{"endTime":"2019-12-20 07:00:00","highestTemperature":"-3","img":"0","isRainFall":"","lowerestTemperature":"-3","precipitation":"0","startTime":"2019-12-20 04:00:00","wd":"","weather":"晴","ws":""},{"endTime":"2019-12-20 10:00:00","highestTemperature":"-6","img":"0","isRainFall":"","lowerestTemperature":"-6","precipitation":"0","startTime":"2019-12-20 07:00:00","wd":"","weather":"晴","ws":""}]},"weathers":[{"date":"2019-12-19","img":"1","sun_down_time":"17:17","sun_rise_time":"07:30","temp_day_c":"10","temp_day_f":"50.0","temp_night_c":"-2","temp_night_f":"28.4","wd":"","weather":"多云","week":"星期四","ws":""},{"date":"2019-12-20","img":"1","sun_down_time":"17:17","sun_rise_time":"07:30","temp_day_c":"8","temp_day_f":"46.4","temp_night_c":"-1","temp_night_f":"30.2","wd":"","weather":"多云","week":"星期五","ws":""},{"date":"2019-12-21","img":"1","sun_down_time":"17:17","sun_rise_time":"07:30","temp_day_c":"10","temp_day_f":"50.0","temp_night_c":"0","temp_night_f":"32.0","wd":"","weather":"多云","week":"星期六","ws":""},{"date":"2019-12-22","img":"0","sun_down_time":"17:17","sun_rise_time":"07:30","temp_day_c":"12","temp_day_f":"53.6","temp_night_c":"2","temp_night_f":"35.6","wd":"","weather":"晴","week":"星期日","ws":""},{"date":"2019-12-23","img":"1","sun_down_time":"17:17","sun_rise_time":"07:30","temp_day_c":"6","temp_day_f":"42.8","temp_night_c":"1","temp_night_f":"33.8","wd":"","weather":"多云","week":"星期一","ws":""},{"date":"2019-12-24","img":"1","sun_down_time":"17:17","sun_rise_time":"07:30","temp_day_c":"6","temp_day_f":"42.8","temp_night_c":"1","temp_night_f":"33.8","wd":"","weather":"多云","week":"星期二","ws":""},{"date":"2019-12-18","img":"1","sun_down_time":"17:17","sun_rise_time":"07:30","temp_day_c":"6","temp_day_f":"42.8","temp_night_c":"0","temp_night_f":"32.0","wd":"","weather":"多云","week":"星期三","ws":""}]}]
     */

    private int code;
    private String message;
    private String redirect;
    private List<T> value;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message == null ? "" : message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRedirect() {
        return redirect == null ? "" : redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public List<T> getValue() {
        if (value == null) {
            return new ArrayList<>();
        }
        return value;
    }

    public void setValue(List<T> value) {
        this.value = value;
    }
}
