package com.liuxe.energyweather.bean;

import java.io.Serializable;

public class IndexesBean implements Serializable {
    /**
     * abbreviation : gm
     * alias :
     * content : 感冒极易发生，避免去人群密集的场所，勤洗手勤通风有利于降低感冒几率。
     * level : 极易发
     * name : 感冒指数
     */

    private String abbreviation;
    private String alias;
    private String content;
    private String level;
    private String name;

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}