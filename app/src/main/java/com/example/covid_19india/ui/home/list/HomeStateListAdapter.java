package com.example.covid_19india.ui.home.list;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.Context;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19india.R;
import com.example.covid_19india.network.model.StateWise;
import com.example.covid_19india.ui.home.model.CovidDataModel;
import com.example.covid_19india.ui.districts.Destrict;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonObject;
import java.util.ArrayList;


public class HomeStateListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    LayoutInflater inflater;
    ArrayList<StateWise> stateRecords;
    Context activityContext;
    JsonObject districtResponse;

    public HomeStateListAdapter(Context thisContext){
        this.inflater = LayoutInflater.from(thisContext);
        this.activityContext = thisContext;
        this.stateRecords = new ArrayList<>();
    }

    public void setStateRecords(ArrayList<StateWise> stateRecords) {
        this.stateRecords = stateRecords;
    }

    public void setDistrictResponse(JsonObject districtResponse) {
        this.districtResponse = districtResponse;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View view = inflater.inflate(R.layout.row_state_data,parent,false);
            return new ViewHolder(view,this.activityContext);
        } else if (viewType == TYPE_HEADER) {
            // Here Inflating your header view
            View view = inflater.inflate(R.layout.activity_headeritem,parent,false);
            return new HeaderViewHolder(view);
        }
        else return null;

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {


        if (holder instanceof HeaderViewHolder) {
            final HeaderViewHolder viewHolder = (HeaderViewHolder) holder;
            String confirmed =  stateRecords.get(position).getConfirmed();
            String active  =  stateRecords.get(position).getActive();
            String deceased = stateRecords.get(position).getDeaths();
            String recovered =  stateRecords.get(position).getRecovered();
            viewHolder.confirmed.setText(confirmed);
            viewHolder.active.setText(active);
            viewHolder.deceased.setText(deceased);
            viewHolder.recovered.setText(recovered);
            return;
        }

        final ViewHolder viewHolder = (ViewHolder) holder;
        // bind the data
        String confirmed = "Confirmed: " + stateRecords.get(position).getConfirmed();
        String active  = "Active: " + stateRecords.get(position).getActive();
        String deceased = "Deceased: " + stateRecords.get(position).getDeaths();
        String recovered = "Recovered: " + stateRecords.get(position).getRecovered();
        viewHolder.state.setText(stateRecords.get(position).getState());
        viewHolder.confirmed.setText(confirmed);
        viewHolder.active.setText(active);
        viewHolder.deceased.setText(deceased);
        viewHolder.recovered.setText(recovered);
        viewHolder.todayCaseConfirmed.setText(stateRecords.get(position).getDelta().getConfirmed());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = holder.getAdapterPosition();
                JsonObject jsonObject = (JsonObject) districtResponse.get(stateRecords.get(index).getState());
                if (jsonObject == null) {
                    Snackbar.make(v, "This state does not has cases of covid-19.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                    return;
                }
                CovidDataModel.getInstance().setSelectedDistrictResponse(jsonObject);
                Intent intent = new Intent(v.getContext(), Destrict.class);
                v.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return stateRecords.size();
    }


    public  class ViewHolder extends  RecyclerView.ViewHolder{
        TextView state;
        TextView confirmed;
        TextView active;
        TextView deceased;
        TextView recovered;
        TextView todayCaseConfirmed;
        Context activityContext;


        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);

            state = itemView.findViewById(R.id.stateNameText);
            confirmed = itemView.findViewById(R.id.confirmLabel);
            active = itemView.findViewById(R.id.activeLabel);
            deceased = itemView.findViewById(R.id.deceasedLabel);
            recovered = itemView.findViewById(R.id.recoveredLabel);
            todayCaseConfirmed = itemView.findViewById(R.id.today_case_label);
            this.activityContext = context;
        }
    }

    public  class HeaderViewHolder extends  RecyclerView.ViewHolder{


        TextView confirmed;
        TextView active;
        TextView deceased;
        TextView recovered;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);

            confirmed = itemView.findViewById(R.id.confirm_number_label);
            active = itemView.findViewById(R.id.active_number_label);
            deceased = itemView.findViewById(R.id.death_number_label);
            recovered = itemView.findViewById(R.id.recovered_number_label);
        }
    }

}
