package com.example.covid_19india.ui.districts.ui.destrict;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.covid_19india.network.model.DistrictData;
import com.example.covid_19india.ui.home.model.CovidDataModel;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Iterator;


public class DestrictViewModel extends ViewModel {

    private MutableLiveData<ArrayList<DistrictData>> districtList;

    public DestrictViewModel() {
        districtList = new MutableLiveData<>();
        districtList.setValue(new ArrayList<DistrictData>());
    }

    public MutableLiveData<ArrayList<DistrictData>> getDistrictList() {

        JsonObject jsonObject = CovidDataModel.getInstance().getSelectedDistrictResponse();
        JsonObject districts = (JsonObject) jsonObject.get("districtData");

        Iterator itr = districts.keySet().iterator();

        ArrayList<DistrictData> districtDataArrayList = new ArrayList<>();

        while (itr.hasNext())
        {
            String key = (String) itr.next();
            JsonObject jsonObject1 = (JsonObject) districts.get(key);
            DistrictData districtData = new DistrictData(key,jsonObject1.get("confirmed"),jsonObject1.get("lastupdatedtime"));
            districtDataArrayList.add(districtData);
        }

        districtList.setValue(districtDataArrayList);
        return districtList;
    }

}
