package com.weather.app.model;

/**
 * Created by hasee on 2016/11/7.
 */

public class City {
    private int id;
    private String cityName;
    private String cityCode;
    private int provinceId;

    public int getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }
}
