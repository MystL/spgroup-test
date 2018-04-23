package com.vin.spgrouptest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.vin.spgrouptest.api.ApiClient;
import com.vin.spgrouptest.data.Location;
import com.vin.spgrouptest.data.PsiResponses;
import com.vin.spgrouptest.data.RegionPsiItem;
import com.vin.spgrouptest.utils.PairUp;
import com.vin.spgrouptest.utils.Tuple2;

import org.joda.time.DateTime;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import rx.Observable;
import rx.subjects.BehaviorSubject;

import static com.vin.spgrouptest.CommonConstants.SELECTED_LOCATION_KEY;

public class RegionReadingDetailsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ApiLoaderHelper apiLoaderHelper;
    private RegionReadingsListAdapter adapter;
    private RecyclerView recyclerView;
    private BehaviorSubject<Location> selectedLocationSubject;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.region_reading_details_layout);
        selectedLocationSubject = BehaviorSubject.create();
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.White));
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.White));
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        adapter = new RegionReadingsListAdapter(RegionReadingDetailsActivity.this);
        recyclerView = findViewById(R.id.region_readings_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(adapter);

        try {
            publishSelectedLocation(getIntent());
            ApiClient apiClient = new ApiClient(this, new URL(getResources().getString(R.string.api_endpoint)));
            apiLoaderHelper = new ApiLoaderHelper(apiClient);
            apiLoaderHelper.fetchReadingsForDate(DateTime.now());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (apiLoaderHelper != null) {
            Observable.combineLatest(apiLoaderHelper.getPsiReadingsForDateObs(), selectedLocationSubject,
                    new PairUp<PsiResponses, Location>())
                    .subscribe(new LoggingSubscriber<Tuple2<PsiResponses, Location>>() {
                        @Override
                        public void onNext(Tuple2<PsiResponses, Location> tuple2) {
                            if (tuple2.getA() != null && tuple2.getB() != null) {
                                PsiReadingHelper helper = new PsiReadingHelper(tuple2.getA());
                                List<RegionPsiItem> regionPsiItems = helper.getAllDayReadingsForRegion(tuple2.getB());
                                Collections.sort(regionPsiItems, new Comparator<RegionPsiItem>() {
                                    @Override
                                    public int compare(RegionPsiItem o1, RegionPsiItem o2) {
                                        return new DateTime(o2.getUpdateTimeStamp()).compareTo(new DateTime(o1.getUpdateTimeStamp()));
                                    }
                                });
                                updateUI(tuple2.getB().name(), regionPsiItems);
                            }
                        }
                    });


        }
    }

    private void publishSelectedLocation(Intent intent) {
        if (intent != null && intent.hasExtra(SELECTED_LOCATION_KEY)) {
            String selectedLocation = intent.getStringExtra(SELECTED_LOCATION_KEY);
            for (Location location : Location.values()) {
                if (location.name().equalsIgnoreCase(selectedLocation)) {
                    selectedLocationSubject.onNext(location);
                    return;
                }
            }
        }
    }

    private void updateUI(final String regionName, final List<RegionPsiItem> items) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(getSupportActionBar() != null){
                    if(regionName.equalsIgnoreCase(Location.NATIONAL.name())){
                        getSupportActionBar().setTitle(String.format("%s%s%s",regionName.toLowerCase(), " wide" ," readings"));
                    } else {
                        getSupportActionBar().setTitle(String.format("%s%s%s",regionName.toLowerCase(), " region" ," readings"));
                    }
                    getSupportActionBar().setSubtitle(DateTime.now().toString("dd/MM/yyyy"));
                }
                adapter.setItems(items);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
