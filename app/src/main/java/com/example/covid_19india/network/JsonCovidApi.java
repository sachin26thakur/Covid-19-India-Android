package com.example.covid_19india.network;

import com.example.covid_19india.network.model.CovidResponse;
import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonCovidApi {
    @GET("data.json")
    Call<CovidResponse> getCovidResponse();

    @GET("state_district_wise.json")
    Call<JsonElement> getDistrictResponse();
}
