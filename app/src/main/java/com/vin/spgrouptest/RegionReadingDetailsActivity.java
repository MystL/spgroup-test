package com.vin.spgrouptest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vin.spgrouptest.api.ApiClient;
import com.vin.spgrouptest.data.Location;
import com.vin.spgrouptest.data.PsiResponses;
import com.vin.spgrouptest.data.RegionPsiItem;

import org.joda.time.DateTime;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import rx.Observable;
import rx.subjects.BehaviorSubject;

import static com.vin.spgrouptest.CommonConstants.SELECTED_LOCATION_KEY;

public class RegionReadingDetailsActivity extends AppCompatActivity {

    private ApiLoaderHelper apiLoaderHelper;
    private RegionReadingsListAdapter adapter;
    private RecyclerView recyclerView;
    private BehaviorSubject<Location> selectedLocationSubject;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.region_reading_details_layout);
        selectedLocationSubject = BehaviorSubject.create();

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
                                updateAdapter(helper.getAllDayReadingsForRegion(tuple2.getB()));
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

    private void updateAdapter(final List<RegionPsiItem> items) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setItems(items);
            }
        });
    }
}
