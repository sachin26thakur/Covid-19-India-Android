package com.example.covid_19india.ui.home;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.example.covid_19india.R;
import com.example.covid_19india.network.model.StateWise;
import com.example.covid_19india.ui.home.list.HomeStateListAdapter;
import com.google.gson.JsonObject;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    RecyclerView recyclerView;
    ArrayList<StateWise> items = new ArrayList<>();
    private HomeStateListAdapter adapter;
    Context thisContext;
    LinearLayoutManager layoutManager;
    SwipeRefreshLayout swipeRefreshLayout;
    Handler apiRequestHandler;
    private boolean isNetworkRequestInProgress = false;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        thisContext = container.getContext();
        recyclerView = root.findViewById(R.id.stateList);
        layoutManager = new LinearLayoutManager(thisContext);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HomeStateListAdapter(thisContext);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout =  root.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isNetworkRequestInProgress) {
                    fetchStatesAndDistricts();
                }
            }
        });

        apiRequestHandler = new Handler();
         Runnable runnableCode = new Runnable() {
            @Override
            public void run() {
                if (!isNetworkRequestInProgress) {
                    fetchStatesAndDistricts();
                }
                apiRequestHandler.postDelayed(this, 300000);
            }
        };
        apiRequestHandler.post(runnableCode);

        return root;
    }

    public void fetchStatesAndDistricts() {
        isNetworkRequestInProgress = true ;
        homeViewModel.refreshStateList().observe(this, new Observer<ArrayList<StateWise>>() {
            @Override
            public void onChanged(ArrayList<StateWise> stateWises) {
                adapter.setStateRecords(stateWises);
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
                isNetworkRequestInProgress = false;
            }
        });

        homeViewModel.refreshDistrictList().observe(this, new Observer<JsonObject>() {
            @Override
            public void onChanged(JsonObject jsonObject) {
                adapter.setDistrictResponse(jsonObject);
            }
        });
    }
}
