package com.arturo.beristain.gpstracker;

import androidx.annotation.BoolRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    SupportMapFragment mapFragment;
    GoogleMap googleMap;
    Marker marker;
    LocationBroadcastReceiver receiver;
    TextView txtLat, txtLong, txtLoclality, txtCP, txtAddress;
    Boolean mapready = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        receiver = new LocationBroadcastReceiver();
        setContentView(R.layout.activity_main);


        txtLat = findViewById(R.id.txtLat);
        txtLong = findViewById(R.id.txtLong);
        txtLoclality = findViewById(R.id.txtLocality);
        txtCP = findViewById(R.id.txtCP);
        txtAddress = findViewById(R.id.txtAddress);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
        mapFragment.getMapAsync(this);


        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                //Request Location
                startService();
            } else {
                //Request location permission
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

            }
        } else {
            //start the location service
            startService();
        }


    }

    void startService() {
        LocationBroadcastReceiver receiver = new LocationBroadcastReceiver();
        IntentFilter filter = new IntentFilter("ACT_LOC");
        registerReceiver(receiver, filter);
        Intent intent = new Intent(MainActivity.this, LocationService.class);
        startService(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startService();
                } else {
                    Toast.makeText(this, "Se necesitan permisos para correr el servicio.", Toast.LENGTH_LONG).show();
                }
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        if (this.googleMap == null)
            this.googleMap = googleMap;
        mapready = true;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(true);
    }

    public class LocationBroadcastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("ACT_LOC") && mapready){
                double latitude = intent.getDoubleExtra("latitude",0);
                double longitude = intent.getDoubleExtra("longitude",0);
                String cp = intent.getStringExtra("cp"), locality = intent.getStringExtra("locality"), address = intent.getStringExtra("address");
                if (googleMap != null){
                    LatLng latLng = new LatLng(latitude,longitude);
                    if (marker != null){
                        marker.setPosition(latLng);

                    }else{
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(latLng);
                        marker = googleMap.addMarker(markerOptions);
                    }

                    txtLat.setText("Latitude: " + latitude);
                    txtLong.setText("Longitude: " + longitude);


                    Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
                    List<Address> addresses = null;
                    try {
                        addresses = geocoder.getFromLocation(latitude,longitude,1);
                        txtCP.setText("C.P.: " + addresses.get(0).getPostalCode());
                        txtLoclality.setText("Localidad: " + addresses.get(0).getLocality());

                        txtAddress.setText("Dirección: " + addresses.get(0).getAddressLine(0) + ":: " + addresses.get(0).getSubLocality());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,20));
                }
            }
        }
    }
}