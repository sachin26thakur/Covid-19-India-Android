package com.example.covid_19india.network.model;

import com.google.gson.JsonElement;

public class DistrictData {

    public DistrictData(String district, JsonElement confirmed, JsonElement lastUpdatedTime) {
        this.district = district;
        this.confirmed = String.valueOf(confirmed);
        this.lastUpdatedTime = String.valueOf(lastUpdatedTime);
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(String lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    private String district;
    private String confirmed;
    private String lastUpdatedTime;
}
