package com.example.covid_19india.ui.districts.ui.destrict;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.covid_19india.R;
import com.example.covid_19india.ui.home.model.CovidDataModel;
import com.example.covid_19india.ui.districts.list.DistrictAdapter;
import com.example.covid_19india.network.model.DistrictData;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Iterator;

public class DestrictFragment extends Fragment {

    private DestrictViewModel mViewModel;

    RecyclerView recyclerView;

    DistrictAdapter adapter;

    Context thisContext;

    public static DestrictFragment newInstance() {
        return new DestrictFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.destrict_fragment, container, false);

        mViewModel =
                ViewModelProviders.of(this).get(DestrictViewModel.class);

        thisContext = container.getContext();
        recyclerView = root.findViewById(R.id.districtList);
        recyclerView.setLayoutManager(new LinearLayoutManager(thisContext));

        mViewModel.getDistrictList().observe(this, new Observer<ArrayList<DistrictData>>() {
            @Override
            public void onChanged(ArrayList<DistrictData> districtData) {
                adapter = new DistrictAdapter(thisContext, districtData);
                recyclerView.setAdapter(adapter);
            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DestrictViewModel.class);
        // TODO: Use the ViewModel
    }

}
