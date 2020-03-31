package com.example.covid_19india.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.covid_19india.network.JsonCovidApi;
import com.example.covid_19india.network.model.CovidResponse;
import com.example.covid_19india.network.model.StateWise;
import com.example.covid_19india.ui.home.model.CovidDataModel;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<ArrayList<StateWise>> stateList;
    private MutableLiveData<JsonObject> districtResponse;

    public HomeViewModel() {
        stateList = new MutableLiveData<>();
        stateList.setValue(new ArrayList<StateWise>());
        districtResponse = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<StateWise>> refreshStateList() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.covid19india.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonCovidApi jsonCovidApi = retrofit.create(JsonCovidApi.class);

        Call<CovidResponse> call = jsonCovidApi.getCovidResponse();

        call.enqueue(new Callback<CovidResponse>() {
            @Override
            public void onResponse(Call<CovidResponse> call, Response<CovidResponse> response) {
                if (!response.isSuccessful()) {
                    Log.d("Error","Error in response");
                    return;
                }
                CovidResponse covidResponse = response.body();
                ArrayList<StateWise> states=  covidResponse.getStatewise();
                stateList.setValue(states);
            }

            @Override
            public void onFailure(Call<CovidResponse> call, Throwable t) {
                Log.d("error", "error in on failure");
            }
        });
        return stateList;
    }

    public MutableLiveData<JsonObject> refreshDistrictList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.covid19india.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonCovidApi jsonCovidApi = retrofit.create(JsonCovidApi.class);

        Call<JsonElement> call = jsonCovidApi.getDistrictResponse();

        call.enqueue(new Callback<JsonElement>() {

            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (!response.isSuccessful()) {
                    Log.d("Error","Error in response");
                    return;
                }
                districtResponse.setValue(((JsonObject) response.body()));
                CovidDataModel.getInstance().updateLastUpdatedTime();
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.d("error", "error in on failure");
            }
        });

        return districtResponse;
    }

}