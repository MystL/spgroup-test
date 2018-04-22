package com.vin.spgrouptest;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class RegionReadingItemViewHolder extends RecyclerView.ViewHolder{

    private TextView location_textview;
    private TextView psi_textview;
    private TextView o3_textview;
    private TextView co_textview;
    private TextView so2_textview;
    private TextView pm10_textview;
    private TextView pm25_textview;
    private TextView time_textview;

    public RegionReadingItemViewHolder(View itemView, TextView location_textview,
                                       TextView psi_textview, TextView o3_textview, TextView co_textview,
                                       TextView so2_textview, TextView pm10_textview, TextView pm25_textview, TextView time_textview) {
        super(itemView);
        this.location_textview = location_textview;
        this.psi_textview = psi_textview;
        this.o3_textview = o3_textview;
        this.co_textview = co_textview;
        this.so2_textview = so2_textview;
        this.pm10_textview = pm10_textview;
        this.pm25_textview = pm25_textview;
        this.time_textview = time_textview;
    }

    public TextView getLocation_textview() {
        return location_textview;
    }

    public TextView getPsi_textview() {
        return psi_textview;
    }

    public TextView getO3_textview() {
        return o3_textview;
    }

    public TextView getCo_textview() {
        return co_textview;
    }

    public TextView getSo2_textview() {
        return so2_textview;
    }

    public TextView getPm10_textview() {
        return pm10_textview;
    }

    public TextView getPm25_textview() {
        return pm25_textview;
    }

    public TextView getTime_textview() {
        return time_textview;
    }
}
