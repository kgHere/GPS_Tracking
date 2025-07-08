package com.example.gps_tracker;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.gps_tracker.databinding.ActivityMaps3Binding;

public class MapsActivity3 extends FragmentActivity implements OnMapReadyCallback {
    String s,lat,lon;
    Double la,lo;
    Integer count,j=0;
    private GoogleMap mMap;
    private ActivityMaps3Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        s=intent.getStringExtra("coordinates");
        String lat="",lon="",word="",word1=",";
        Integer count = null,j=0;
        for(int i=0;i<s.length();i++){
            word = word+s.charAt(i);
            if(word.equals(word1)){
                count=i;
            }
            word="";
        }
        for(int i=0;i<count;i++){
            lat=lat+s.charAt(i);
        }
        for(int i=(count+1);i<s.length();i++){
            lon=lon+s.charAt(i);
        }
        la = Double.valueOf(lat);
        lo = Double.valueOf(lon);
        Toast.makeText(this, "Latitude = "+ la + " Longitude = "+ lo, Toast.LENGTH_SHORT).show();

        binding = ActivityMaps3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(la, lo);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in India"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}