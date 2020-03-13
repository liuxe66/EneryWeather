package com.liuxe.energyweather.bean;

import java.io.Serializable;
import java.util.List;

public class WeatherDetailsInfoBean implements Serializable {
    /**
     * publishTime : 2019-12-19 16:00:00
     * weather3HoursDetailsInfos : [{"endTime":"2019-12-19 16:00:00","highestTemperature":"10","img":"0","isRainFall":"","lowerestTemperature":"10","precipitation":"0","startTime":"2019-12-19 13:00:00","wd":"","weather":"晴","ws":""},{"endTime":"2019-12-19 19:00:00","highestTemperature":"6","img":"0","isRainFall":"","lowerestTemperature":"6","precipitation":"0","startTime":"2019-12-19 16:00:00","wd":"","weather":"晴","ws":""},{"endTime":"2019-12-19 22:00:00","highestTemperature":"2","img":"0","isRainFall":"","lowerestTemperature":"2","precipitation":"0","startTime":"2019-12-19 19:00:00","wd":"","weather":"晴","ws":""},{"endTime":"2019-12-20 01:00:00","highestTemperature":"1","img":"0","isRainFall":"","lowerestTemperature":"1","precipitation":"0","startTime":"2019-12-19 22:00:00","wd":"","weather":"晴","ws":""},{"endTime":"2019-12-20 04:00:00","highestTemperature":"-2","img":"0","isRainFall":"","lowerestTemperature":"-2","precipitation":"0","startTime":"2019-12-20 01:00:00","wd":"","weather":"晴","ws":""},{"endTime":"2019-12-20 07:00:00","highestTemperature":"-3","img":"0","isRainFall":"","lowerestTemperature":"-3","precipitation":"0","startTime":"2019-12-20 04:00:00","wd":"","weather":"晴","ws":""},{"endTime":"2019-12-20 10:00:00","highestTemperature":"-6","img":"0","isRainFall":"","lowerestTemperature":"-6","precipitation":"0","startTime":"2019-12-20 07:00:00","wd":"","weather":"晴","ws":""}]
     */

    private String publishTime;
    private List<Weather3HoursDetailsInfosBean> weather3HoursDetailsInfos;

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public List<Weather3HoursDetailsInfosBean> getWeather3HoursDetailsInfos() {
        return weather3HoursDetailsInfos;
    }

    public void setWeather3HoursDetailsInfos(List<Weather3HoursDetailsInfosBean> weather3HoursDetailsInfos) {
        this.weather3HoursDetailsInfos = weather3HoursDetailsInfos;
    }

    public static class Weather3HoursDetailsInfosBean {
        /**
         * endTime : 2019-12-19 16:00:00
         * highestTemperature : 10
         * img : 0
         * isRainFall :
         * lowerestTemperature : 10
         * precipitation : 0
         * startTime : 2019-12-19 13:00:00
         * wd :
         * weather : 晴
         * ws :
         */

        private String endTime;
        private String highestTemperature;
        private String img;
        private String isRainFall;
        private String lowerestTemperature;
        private String precipitation;
        private String startTime;
        private String wd;
        private String weather;
        private String ws;

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getHighestTemperature() {
            return highestTemperature;
        }

        public void setHighestTemperature(String highestTemperature) {
            this.highestTemperature = highestTemperature;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getIsRainFall() {
            return isRainFall;
        }

        public void setIsRainFall(String isRainFall) {
            this.isRainFall = isRainFall;
        }

        public String getLowerestTemperature() {
            return lowerestTemperature;
        }

        public void setLowerestTemperature(String lowerestTemperature) {
            this.lowerestTemperature = lowerestTemperature;
        }

        public String getPrecipitation() {
            return precipitation;
        }

        public void setPrecipitation(String precipitation) {
            this.precipitation = precipitation;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
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

        public String getWs() {
            return ws;
        }

        public void setWs(String ws) {
            this.ws = ws;
        }
    }
}