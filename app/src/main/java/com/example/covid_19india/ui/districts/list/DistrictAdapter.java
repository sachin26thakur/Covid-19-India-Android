package com.example.covid_19india.ui.districts.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.covid_19india.R;
import com.example.covid_19india.network.model.DistrictData;


import java.util.ArrayList;

public class DistrictAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    LayoutInflater inflater;
    Context activityContext;

    ArrayList<DistrictData> districtDataArrayList;

    private static final int TYPE_HEADER_ = 0;
    private static final int TYPE_ITEM_ = 1;

    public DistrictAdapter(Context thisContext, ArrayList<DistrictData> districtDataArrayList){
        this.districtDataArrayList = districtDataArrayList;
        this.activityContext = thisContext;
        this.inflater = LayoutInflater.from(thisContext);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM_) {
            View view = inflater.inflate(R.layout.row_district_layout,parent,false);
            return new DistrictAdapter.DistrictViewHolder(view,this.activityContext);
        } else if (viewType == TYPE_HEADER_) {
            // Here Inflating your header view
            View view = inflater.inflate(R.layout.header_district_layout,parent,false);
            return new DistrictHeaderViewHolder(view);
        }
        else return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof DistrictHeaderViewHolder) {
            return;
        }

        final DistrictAdapter.DistrictViewHolder viewHolder = (DistrictAdapter.DistrictViewHolder) holder;
        // bind the data
        String confirmed =    districtDataArrayList.get(position).getConfirmed();
        String districtName = districtDataArrayList.get(position).getDistrict();
        viewHolder.districtName.setText(districtName);
        viewHolder.confirmed.setText(confirmed);
    }

    @Override
    public int getItemCount() {
        return districtDataArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {

        return TYPE_ITEM_;
    }

    public  class DistrictViewHolder extends  RecyclerView.ViewHolder{
        TextView districtName;
        TextView confirmed;

        public DistrictViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            districtName = itemView.findViewById(R.id.districtNameText);
            confirmed = itemView.findViewById(R.id.district_confirm_label);
        }
    }

    public  class DistrictHeaderViewHolder extends  RecyclerView.ViewHolder{

        public DistrictHeaderViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
