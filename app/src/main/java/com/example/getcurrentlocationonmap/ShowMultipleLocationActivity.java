package com.example.getcurrentlocationonmap;

import static android.os.Build.VERSION_CODES.N;

import static java.lang.Math.E;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ShowMultipleLocationActivity extends FragmentActivity implements OnMapReadyCallback {


    Button btncurrentlocation;

    double lat,lng;

    private GoogleMap mMap;
    String currentLocation,location;

    private LatLngBounds bounds;
    private LatLngBounds.Builder builder;


    // below are the latitude and longitude
    // of 4 different locations.
    LatLng mulund = new LatLng(
            19.1720555, 72.9562915);
    LatLng dombivli = new LatLng(19.218194, 73.086785);
    LatLng thane = new LatLng(19.1860408, 72.9758837);
    LatLng diva = new LatLng(19.188885, 73.0431215);

    // creating array list for adding all our locations.
    private ArrayList<LatLng> locationArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_multiple_location);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
        mapFragment.getMapAsync(this);

        btncurrentlocation=(Button)findViewById(R.id.btncurrentlocation);

        // in below line we are initializing our array list.
        locationArrayList = new ArrayList<>();

        // on below line we are adding our
        // locations in our array list.
        locationArrayList.add(mulund);
        locationArrayList.add(dombivli);
        locationArrayList.add(thane);
        locationArrayList.add(diva);


        btncurrentlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ShowMultipleLocationActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        final LatLng mumbai = new LatLng(19.0760,  72.8777);
//        googleMap.addMarker(new MarkerOptions().position(mumbai));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mumbai, 9.0f));


        // inside on map ready method
        // we will be displaying all our markers.
        // for adding markers we are running for loop and
        // inside that we are drawing marker on our map.
        for (int i = 0; i < locationArrayList.size(); i++) {

            if(i==0){
                location="Mulund Railway Station";
            }else if(i==1){
                location="Dombivli Railway Station";
            }else if(i==2){
                location="Thane Railway station";
            }else if(i==3){
                location="Diva Railway Station";
            }

            // below line is use to add marker to each location of our array list.
            mMap.addMarker(new MarkerOptions().position(locationArrayList.get(i)).title(location));

            // below lin is use to zoom our camera on map.
            mMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f));

            // below line is use to move our camera to the specific location.
            mMap.moveCamera(CameraUpdateFactory.newLatLng(locationArrayList.get(i)));
        }


    }


}