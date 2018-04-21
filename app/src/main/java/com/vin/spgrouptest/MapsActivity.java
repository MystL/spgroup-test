package com.vin.spgrouptest;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.vin.spgrouptest.api.ApiClient;
import com.vin.spgrouptest.data.Location;
import com.vin.spgrouptest.data.PsiResponses;
import com.vin.spgrouptest.data.RegionLocation;
import com.vin.spgrouptest.data.RegionMetadata;
import com.vin.spgrouptest.data.RegionReadingInfo;

import org.joda.time.DateTime;

import java.net.MalformedURLException;
import java.net.URL;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String API_ENDPOINT = "https://api.data.gov.sg";
    private ApiLoaderHelper apiLoaderHelper;
    private View nationPsiDisplayCard;
    private BehaviorSubject<GoogleMap> onMapReadyObs;
    private PublishSubject<Marker> onInfoWindowClickedSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        onMapReadyObs = BehaviorSubject.create();
        onInfoWindowClickedSubject = PublishSubject.create();

//         Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        nationPsiDisplayCard = findViewById(R.id.national_readings_display);
        nationPsiDisplayCard.setVisibility(View.GONE);


        try {
            ApiClient apiClient = new ApiClient(this, new URL(API_ENDPOINT));
            apiLoaderHelper = new ApiLoaderHelper(apiClient);
            apiLoaderHelper.fetchLatestPsiReadings();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (apiLoaderHelper != null) {
            apiLoaderHelper.getLatestPsiResponsesObs().subscribe(new LoggingSubscriber<PsiResponses>() {
                @Override
                public void onNext(PsiResponses psiResponses) {
                    if (psiResponses != null) {
                        PsiReadingHelper psiReadingHelper = new PsiReadingHelper(psiResponses);
                        loadNationalReadings(psiReadingHelper.getRegionReadingInfoForLocation(Location.NATIONAL));
                    }
                }
            });

            Observable.combineLatest(onMapReadyObs, apiLoaderHelper.getLatestPsiResponsesObs()
                    .observeOn(AndroidSchedulers.mainThread()), new PairUp<GoogleMap, PsiResponses>())
                    .subscribe(new LoggingSubscriber<Tuple2<GoogleMap, PsiResponses>>() {
                        @Override
                        public void onNext(Tuple2<GoogleMap, PsiResponses> tuple2) {
                            GoogleMap mMap = tuple2.getA();
                            PsiReadingHelper psiReadingHelper = new PsiReadingHelper(tuple2.getB());

                            RegionMetadata westRegion = psiReadingHelper.getRegionMetadataForLocation(Location.WEST);
                            int westPsiReading = (int) psiReadingHelper.getRegionReadingInfoForLocation(Location.WEST).getPsi24Hourly();
                            RegionLocation westLocation = westRegion.getLocation();
                            LatLng westLatLng = new LatLng(westLocation.getLatitude(), westLocation.getLongitude());
                            MarkerOptions westMarkerOptions = new MarkerOptions();
                            westMarkerOptions.position(westLatLng).title(westRegion.getName() + " region");
                            westMarkerOptions.snippet(String.format("%s%s", "PSI : ", westPsiReading));

                            RegionMetadata eastRegion = psiReadingHelper.getRegionMetadataForLocation(Location.EAST);
                            int eastPsiReading = (int) psiReadingHelper.getRegionReadingInfoForLocation(Location.EAST).getPsi24Hourly();
                            RegionLocation eastLocation = eastRegion.getLocation();
                            LatLng eastLatLng = new LatLng(eastLocation.getLatitude(), eastLocation.getLongitude());
                            MarkerOptions eastMarkerOptions = new MarkerOptions();
                            eastMarkerOptions.position(eastLatLng).title(eastRegion.getName() + " region");
                            eastMarkerOptions.snippet(String.format("%s%s", "PSI : ", eastPsiReading));

                            RegionMetadata northRegion = psiReadingHelper.getRegionMetadataForLocation(Location.NORTH);
                            int northPsiReading = (int) psiReadingHelper.getRegionReadingInfoForLocation(Location.NORTH).getPsi24Hourly();
                            RegionLocation northLocation = northRegion.getLocation();
                            LatLng northLatLng = new LatLng(northLocation.getLatitude(), northLocation.getLongitude());
                            MarkerOptions northMarkerOptions = new MarkerOptions();
                            northMarkerOptions.position(northLatLng).title(northRegion.getName() + " region");
                            northMarkerOptions.snippet(String.format("%s%s", "PSI : ", northPsiReading));

                            RegionMetadata southRegion = psiReadingHelper.getRegionMetadataForLocation(Location.SOUTH);
                            int southPsiReading = (int) psiReadingHelper.getRegionReadingInfoForLocation(Location.SOUTH).getPsi24Hourly();
                            RegionLocation southLocation = southRegion.getLocation();
                            LatLng southLatLng = new LatLng(southLocation.getLatitude(), southLocation.getLongitude());
                            MarkerOptions southMarkerOptions = new MarkerOptions();
                            southMarkerOptions.position(southLatLng).title(southRegion.getName()  + " region");
                            southMarkerOptions.snippet(String.format("%s%s", "PSI : ", southPsiReading));

                            RegionMetadata centralRegion = psiReadingHelper.getRegionMetadataForLocation(Location.CENTRAL);
                            int centralPsiReading = (int) psiReadingHelper.getRegionReadingInfoForLocation(Location.CENTRAL).getPsi24Hourly();
                            RegionLocation centralLocation = centralRegion.getLocation();
                            LatLng centralLatLng = new LatLng(centralLocation.getLatitude(), centralLocation.getLongitude());
                            MarkerOptions centralMarkerOptions = new MarkerOptions();
                            centralMarkerOptions.position(centralLatLng).title(centralRegion.getName()  + " region");
                            centralMarkerOptions.snippet(String.format("%s%s", "PSI : ", centralPsiReading));

                            mMap.addMarker(westMarkerOptions);
                            mMap.addMarker(eastMarkerOptions);
                            mMap.addMarker(northMarkerOptions);
                            mMap.addMarker(southMarkerOptions);
                            mMap.addMarker(centralMarkerOptions);
                            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                @Override
                                public boolean onMarkerClick(Marker marker) {
                                    if(!marker.isInfoWindowShown()){
                                        marker.showInfoWindow();
                                    }
                                    // to prevent centering of map camera when clicking on marker
                                    return true;
                                }
                            });
                            mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                                @Override
                                public void onInfoWindowClick(Marker marker) {
                                    onInfoWindowClickedSubject.onNext(marker);
                                }
                            });

                            LatLng cameraLatLng = new LatLng(northLocation.getLatitude() + 0.01, northLocation.getLongitude());
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cameraLatLng, 10.5f));
                        }
                    });
        }

        onInfoWindowClickedSubject.subscribe(new LoggingSubscriber<Marker>() {
            @Override
            public void onNext(Marker marker) {
                // TODO - load that Region's detail Actiivty if got time
            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        onMapReadyObs.onNext(googleMap);
    }

    private void loadNationalReadings(final RegionReadingInfo info) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                nationPsiDisplayCard.setVisibility(View.VISIBLE);
                TextView locationTextView = nationPsiDisplayCard.findViewById(R.id.lbl_location_display);
                locationTextView.setText(getResources().getString(R.string.nation_wide_display_title));

                TextView psiLbl = nationPsiDisplayCard.findViewById(R.id.lbl_psi_24_hr_value);
                if (info.getPsi24Hourly() >= 0.0 && info.getPsi24Hourly() < 101.0) {
                    // show green text
                    psiLbl.setTextColor(getResources().getColor(R.color.Green));
                } else if (info.getPsi24Hourly() >= 101.0 && info.getPsi24Hourly() < 301.0) {
                    // show yellow text
                    psiLbl.setTextColor(getResources().getColor(R.color.Yellow));
                } else if (info.getPsi24Hourly() >= 301) {
                    // show red text
                    psiLbl.setTextColor(getResources().getColor(R.color.Red));
                }
                psiLbl.setText(String.valueOf((int) info.getPsi24Hourly()));

                TextView o3Lbl = nationPsiDisplayCard.findViewById(R.id.lbl_o3_index_value);
                o3Lbl.setText(String.valueOf((int) info.getO3SubIndex()));

                TextView coLbl = nationPsiDisplayCard.findViewById(R.id.lbl_co2_index_value);
                coLbl.setText(String.valueOf((int) info.getCoSubIndex()));

                TextView so2Lbl = nationPsiDisplayCard.findViewById(R.id.lbl_so2_index_value);
                so2Lbl.setText(String.valueOf((int) info.getSo2SubIndex()));

                TextView pm10Lbl = nationPsiDisplayCard.findViewById(R.id.lbl_pm10_index_value);
                pm10Lbl.setText(String.valueOf((int) info.getPm10SubIndex()));

                TextView pm25Lbl = nationPsiDisplayCard.findViewById(R.id.lbl_pm25_index_value);
                pm25Lbl.setText(String.valueOf((int) info.getPm25SubIndex()));

                TextView updateTime = nationPsiDisplayCard.findViewById(R.id.lbl_updated_time);
                try {
                    DateTime dateTime = new DateTime(info.getUpdatedTimestampString());
                    updateTime.setText(String.format("%s%s", getResources().getString(R.string.update_time_lbl), dateTime.toString("yyyy-MM-dd HH:mm")));
                } catch (Exception e) {
                    e.printStackTrace();
                    updateTime.setVisibility(View.GONE);
                }

            }
        });

    }
}
