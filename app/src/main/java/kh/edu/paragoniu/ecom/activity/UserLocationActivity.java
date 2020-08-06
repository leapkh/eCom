package kh.edu.paragoniu.ecom.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import kh.edu.paragoniu.ecom.R;

public class UserLocationActivity extends AppCompatActivity {

    private final int LOCATION_REQUEST_CODE = 1;

    private FusedLocationProviderClient fusedLocationProviderClient;

    private MapView mapView;
    private GoogleMap googleMap;

    private double latitude = 0;
    private double longitude = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_location);

        // Initialize FusedLocationProviderClient
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        // Checking the location permission
        String locationPermission = Manifest.permission.ACCESS_FINE_LOCATION;
        if (ContextCompat.checkSelfPermission(this, locationPermission) == PackageManager.PERMISSION_GRANTED) {
            // Load last known location of the user
            loadLastKnownLocation();
            requestLocationUpdate();
        } else {
            // Request for permission
            requestLocationPermission();
        }

        // Reference to MapView
        mapView = findViewById(R.id.map_view);

        // Forward container’s life cycle methods to MapView
        mapView.onCreate(savedInstanceState);

        // Load a map object from MapView
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mapObject) {
                Log.d("piuecom", "Map object is ready.");
                googleMap = mapObject;
                zoomMapToCurrentLocation();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        // Forward container’s life cycle methods to MapView
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Forward container’s life cycle methods to MapView
        mapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Forward container’s life cycle methods to MapView
        mapView.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == LOCATION_REQUEST_CODE){
            int result = grantResults[0];
            if(result == PackageManager.PERMISSION_GRANTED){
                loadLastKnownLocation();
            } else {
                Toast.makeText(this, "Our app could not access your location.", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void loadLastKnownLocation(){
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if (task.isSuccessful()) {
                    if (task.getResult() != null) {
                        Log.d("piuecom", "Last known location is ready.");
                        latitude = task.getResult().getLatitude();
                        longitude = task.getResult().getLongitude();
                        TextView txtLatitude = findViewById(R.id.txtLatitude);
                        TextView txtLongitude = findViewById(R.id.txtLongitude);
                        txtLatitude.setText("Latitude: " + latitude);
                        txtLongitude.setText("Longitude: " + longitude);
                        zoomMapToCurrentLocation();
                    } else {
                        Toast.makeText(UserLocationActivity.this, "No location found.", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(UserLocationActivity.this, "Loading location failed.", Toast.LENGTH_LONG).show();
                    Log.d("piuecom", "Loading location failed: " + task.getException().getMessage());
                }
            }
        });
    }

    private void requestLocationUpdate(){
        LocationRequest updateRequest = new LocationRequest();
        updateRequest.setInterval(10000);
        updateRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationCallback updateCallback = new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                Log.d("piuecom", "The location is updated.");
                latitude = locationResult.getLastLocation().getLatitude();
                longitude = locationResult.getLastLocation().getLongitude();
                zoomMapToCurrentLocation();
                // Remove update request
                //fusedLocationProviderClient.removeLocationUpdates(this);
            }
        };

        fusedLocationProviderClient.requestLocationUpdates(updateRequest, updateCallback, Looper.getMainLooper());
    }

    private void requestLocationPermission(){
        String locationPermission = Manifest.permission.ACCESS_FINE_LOCATION;
        String[] permissionsToBeRequested = new String[]{locationPermission};
        ActivityCompat.requestPermissions(this, permissionsToBeRequested, LOCATION_REQUEST_CODE);
    }

    private void zoomMapToCurrentLocation(){
        if(latitude != 0 && longitude != 0 && googleMap != null){
            Log.d("piuecom", "Start zooming map: lat=" + latitude + ", lng=" + longitude);
            LatLng latLng = new LatLng(latitude, longitude);
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 17);
            googleMap.animateCamera(cameraUpdate);
        }
    }

}
