package com.vin.spgrouptest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vin.spgrouptest.data.RegionPsiItem;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class RegionReadingsListAdapter extends RecyclerView.Adapter<RegionReadingItemViewHolder>{

    private Context context;
    private List<RegionPsiItem> items = new ArrayList<>();

    public RegionReadingsListAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public RegionReadingItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.psi_readings_display_layout, parent, false);
        TextView location_textview = v.findViewById(R.id.lbl_location_display);
        TextView psi_textview = v.findViewById(R.id.lbl_psi_24_hr_value);
        TextView o3_textview = v.findViewById(R.id.lbl_o3_index_value);
        TextView co_textview = v.findViewById(R.id.lbl_co_index_value);
        TextView so2_textview = v.findViewById(R.id.lbl_so2_index_value);
        TextView pm10_textview = v.findViewById(R.id.lbl_pm10_index_value);
        TextView pm25_textview = v.findViewById(R.id.lbl_pm25_index_value);
        TextView time_textview = v.findViewById(R.id.lbl_updated_time);
        return new RegionReadingItemViewHolder(v, location_textview, psi_textview, o3_textview,
                co_textview, so2_textview, pm10_textview, pm25_textview, time_textview);
    }

    @Override
    public void onBindViewHolder(@NonNull RegionReadingItemViewHolder holder, int position) {
        RegionPsiItem item = items.get(position);
        holder.getLocation_textview().setVisibility(View.GONE);
        if (item.getPsi24Hourly() >= 0.0 && item.getPsi24Hourly() < 101.0) {
            // show green text
            holder.getPsi_textview().setTextColor(context.getResources().getColor(R.color.Green));
        } else if (item.getPsi24Hourly() >= 101.0 && item.getPsi24Hourly() < 301.0) {
            // show yellow text
            holder.getPsi_textview().setTextColor(context.getResources().getColor(R.color.Yellow));
        } else if (item.getPsi24Hourly() >= 301) {
            // show red text
            holder.getPsi_textview().setTextColor(context.getResources().getColor(R.color.Red));
        }
        holder.getPsi_textview().setText(String.valueOf((int) item.getPsi24Hourly()));

        holder.getO3_textview().setText(String.valueOf((int) item.getO3SubIndex()));
        holder.getCo_textview().setText(String.valueOf((int) item.getCoSubIndex()));
        holder.getSo2_textview().setText(String.valueOf((int) item.getSo2SubIndex()));
        holder.getPm10_textview().setText(String.valueOf((int) item.getPm10SubIndex()));
        holder.getPm25_textview().setText(String.valueOf((int) item.getPm25SubIndex()));
        try {
            DateTime dateTime = new DateTime(item.getUpdateTimeStamp());
            holder.getTime_textview().setText(String.format("%s%s", context.getResources().getString(R.string.update_time_lbl), dateTime.toString("yyyy-MM-dd HH:mm")));
        } catch (Exception e) {
            e.printStackTrace();
            holder.getTime_textview().setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<RegionPsiItem> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }
}
