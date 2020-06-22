package kh.edu.paragoniu.ecom.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import kh.edu.paragoniu.ecom.R;

public class UserLocationActivity extends AppCompatActivity {

    private final int LOCATION_REQUEST_CODE = 1;

    private FusedLocationProviderClient fusedLocationProviderClient;

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
        } else {
            // Request for permission
            requestLocationPermission();
        }

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
                        double latitude = task.getResult().getLatitude();
                        double longitude = task.getResult().getLongitude();
                        TextView txtLatitude = findViewById(R.id.txtLatitude);
                        TextView txtLongitude = findViewById(R.id.txtLongitude);
                        txtLatitude.setText("Latitude: " + latitude);
                        txtLongitude.setText("Longitude: " + longitude);
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

    private void requestLocationPermission(){
        String locationPermission = Manifest.permission.ACCESS_FINE_LOCATION;
        String[] permissionsToBeRequested = new String[]{locationPermission};
        ActivityCompat.requestPermissions(this, permissionsToBeRequested, LOCATION_REQUEST_CODE);
    }

}
