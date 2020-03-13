package com.liuxe.energyweather.bean;

import java.io.Serializable;
import java.util.List;

public class WeatherBean implements Serializable {

    /**
     * alarms : []
     * city : 焦作
     * cityid : 101181101
     * indexes : [{"abbreviation":"gm","alias":"","content":"感冒极易发生，避免去人群密集的场所，勤洗手勤通风有利于降低感冒几率。","level":"极易发","name":"感冒指数"},{"abbreviation":"pp","alias":"","content":"天气寒冷，多补水，选用滋润保湿型化妆品，使用润唇膏。","level":"保湿","name":"化妆指数"},{"abbreviation":"yd","alias":"","content":"空气轻度污染，不宜在户外运动。","level":"不适宜","name":"运动指数"},{"abbreviation":"xc","alias":"","content":"洗车后，可至少保持3天车辆清洁，适宜洗车。","level":"适宜","name":"洗车指数"},{"abbreviation":"ct","alias":"","content":"天气偏凉，可以穿上最时尚的那件大衣来凹造型，搭一条围巾时尚指数爆表。","level":"凉","name":"穿衣指数"},{"abbreviation":"uv","alias":"","content":"辐射弱，涂擦SPF8-12防晒护肤品。","level":"最弱","name":"紫外线强度指数"}]
     * pm25 : {"advice":"0","aqi":"107","citycount":679,"cityrank":10,"co":"16","color":"0","level":"0","no2":"27","o3":"7","pm10":"89","pm25":"106","quality":"轻度","so2":"9","timestamp":"","upDateTime":"2019-12-19 12:00:00"}
     * provinceName : 河南省
     * realtime : {"img":"0","sD":"53","sendibleTemp":"5","temp":"6","time":"2019-12-19 12:45:08","wD":"东南风","wS":"2级","weather":"晴","ziwaixian":"N/A"}
     * weatherDetailsInfo : {"publishTime":"2019-12-19 16:00:00","weather3HoursDetailsInfos":[{"endTime":"2019-12-19 16:00:00","highestTemperature":"10","img":"0","isRainFall":"","lowerestTemperature":"10","precipitation":"0","startTime":"2019-12-19 13:00:00","wd":"","weather":"晴","ws":""},{"endTime":"2019-12-19 19:00:00","highestTemperature":"6","img":"0","isRainFall":"","lowerestTemperature":"6","precipitation":"0","startTime":"2019-12-19 16:00:00","wd":"","weather":"晴","ws":""},{"endTime":"2019-12-19 22:00:00","highestTemperature":"2","img":"0","isRainFall":"","lowerestTemperature":"2","precipitation":"0","startTime":"2019-12-19 19:00:00","wd":"","weather":"晴","ws":""},{"endTime":"2019-12-20 01:00:00","highestTemperature":"1","img":"0","isRainFall":"","lowerestTemperature":"1","precipitation":"0","startTime":"2019-12-19 22:00:00","wd":"","weather":"晴","ws":""},{"endTime":"2019-12-20 04:00:00","highestTemperature":"-2","img":"0","isRainFall":"","lowerestTemperature":"-2","precipitation":"0","startTime":"2019-12-20 01:00:00","wd":"","weather":"晴","ws":""},{"endTime":"2019-12-20 07:00:00","highestTemperature":"-3","img":"0","isRainFall":"","lowerestTemperature":"-3","precipitation":"0","startTime":"2019-12-20 04:00:00","wd":"","weather":"晴","ws":""},{"endTime":"2019-12-20 10:00:00","highestTemperature":"-6","img":"0","isRainFall":"","lowerestTemperature":"-6","precipitation":"0","startTime":"2019-12-20 07:00:00","wd":"","weather":"晴","ws":""}]}
     * weathers : [{"date":"2019-12-19","img":"1","sun_down_time":"17:17","sun_rise_time":"07:30","temp_day_c":"10","temp_day_f":"50.0","temp_night_c":"-2","temp_night_f":"28.4","wd":"","weather":"多云","week":"星期四","ws":""},{"date":"2019-12-20","img":"1","sun_down_time":"17:17","sun_rise_time":"07:30","temp_day_c":"8","temp_day_f":"46.4","temp_night_c":"-1","temp_night_f":"30.2","wd":"","weather":"多云","week":"星期五","ws":""},{"date":"2019-12-21","img":"1","sun_down_time":"17:17","sun_rise_time":"07:30","temp_day_c":"10","temp_day_f":"50.0","temp_night_c":"0","temp_night_f":"32.0","wd":"","weather":"多云","week":"星期六","ws":""},{"date":"2019-12-22","img":"0","sun_down_time":"17:17","sun_rise_time":"07:30","temp_day_c":"12","temp_day_f":"53.6","temp_night_c":"2","temp_night_f":"35.6","wd":"","weather":"晴","week":"星期日","ws":""},{"date":"2019-12-23","img":"1","sun_down_time":"17:17","sun_rise_time":"07:30","temp_day_c":"6","temp_day_f":"42.8","temp_night_c":"1","temp_night_f":"33.8","wd":"","weather":"多云","week":"星期一","ws":""},{"date":"2019-12-24","img":"1","sun_down_time":"17:17","sun_rise_time":"07:30","temp_day_c":"6","temp_day_f":"42.8","temp_night_c":"1","temp_night_f":"33.8","wd":"","weather":"多云","week":"星期二","ws":""},{"date":"2019-12-18","img":"1","sun_down_time":"17:17","sun_rise_time":"07:30","temp_day_c":"6","temp_day_f":"42.8","temp_night_c":"0","temp_night_f":"32.0","wd":"","weather":"多云","week":"星期三","ws":""}]
     */

    private String city;
    private String cityid;
    private Pm25Bean pm25;
    private String provinceName;
    private RealtimeBean realtime;
    private WeatherDetailsInfoBean weatherDetailsInfo;
    private List<?> alarms;
    private List<IndexesBean> indexes;
    private List<WeathersBean> weathers;
    private boolean selected =false;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public Pm25Bean getPm25() {
        return pm25;
    }

    public void setPm25(Pm25Bean pm25) {
        this.pm25 = pm25;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
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

    public List<?> getAlarms() {
        return alarms;
    }

    public void setAlarms(List<?> alarms) {
        this.alarms = alarms;
    }

    public List<IndexesBean> getIndexes() {
        return indexes;
    }

    public void setIndexes(List<IndexesBean> indexes) {
        this.indexes = indexes;
    }

    public List<WeathersBean> getWeathers() {
        return weathers;
    }

    public void setWeathers(List<WeathersBean> weathers) {
        this.weathers = weathers;
    }

    public static class Pm25Bean implements Serializable {
        /**
         * advice : 0
         * aqi : 107
         * citycount : 679
         * cityrank : 10
         * co : 16
         * color : 0
         * level : 0
         * no2 : 27
         * o3 : 7
         * pm10 : 89
         * pm25 : 106
         * quality : 轻度
         * so2 : 9
         * timestamp :
         * upDateTime : 2019-12-19 12:00:00
         */

        private String advice;
        private String aqi;
        private int citycount;
        private int cityrank;
        private String co;
        private String color;
        private String level;
        private String no2;
        private String o3;
        private String pm10;
        private String pm25;
        private String quality;
        private String so2;
        private String timestamp;
        private String upDateTime;

        public String getAdvice() {
            return advice;
        }

        public void setAdvice(String advice) {
            this.advice = advice;
        }

        public String getAqi() {
            return aqi;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
        }

        public int getCitycount() {
            return citycount;
        }

        public void setCitycount(int citycount) {
            this.citycount = citycount;
        }

        public int getCityrank() {
            return cityrank;
        }

        public void setCityrank(int cityrank) {
            this.cityrank = cityrank;
        }

        public String getCo() {
            return co;
        }

        public void setCo(String co) {
            this.co = co;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getNo2() {
            return no2;
        }

        public void setNo2(String no2) {
            this.no2 = no2;
        }

        public String getO3() {
            return o3;
        }

        public void setO3(String o3) {
            this.o3 = o3;
        }

        public String getPm10() {
            return pm10;
        }

        public void setPm10(String pm10) {
            this.pm10 = pm10;
        }

        public String getPm25() {
            return pm25;
        }

        public void setPm25(String pm25) {
            this.pm25 = pm25;
        }

        public String getQuality() {
            return quality;
        }

        public void setQuality(String quality) {
            this.quality = quality;
        }

        public String getSo2() {
            return so2;
        }

        public void setSo2(String so2) {
            this.so2 = so2;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getUpDateTime() {
            return upDateTime;
        }

        public void setUpDateTime(String upDateTime) {
            this.upDateTime = upDateTime;
        }
    }

}
