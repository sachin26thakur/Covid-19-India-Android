package com.example.covid_19india.ui.home.model;


import com.google.gson.JsonObject;

public class CovidDataModel  {

    private static CovidDataModel INSTANCE = null;

    private long last_update_time = 0;

    public JsonObject getSelectedDistrictResponse() {
        return selectedDistrictResponse;
    }

    public void setSelectedDistrictResponse(JsonObject selectedDistrictResponse) {
        this.selectedDistrictResponse = selectedDistrictResponse;
    }

    private JsonObject selectedDistrictResponse;

    private CovidDataModel() {};

    public static CovidDataModel getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CovidDataModel();
        }
        return(INSTANCE);
    }

    public String lastUpdatedTime() {
        long current_time = new java.util.Date().getTime();
        long diff = current_time - last_update_time;
        int seconds = (int) (diff / 1000);

        if (seconds < 60) {
            return "Last updated " + seconds + " seconds ago";
        } else if (seconds >  600){
            return "Pull to refresh statics";
        }else {
            int minutes = (int) (seconds / 60);
            int remaining_seconds = (int) (seconds % 60);
            return "Last updated about " + minutes + " min ago";
        }
    }

    public void updateLastUpdatedTime() {
        last_update_time = new java.util.Date().getTime();
    }

}