package kh.edu.paragoniu.ecom.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kh.edu.paragoniu.ecom.R
import kotlinx.android.synthetic.main.activity_user_location.*

class UserLocationKotlinActivity : AppCompatActivity() {

    private val LOCATION_REQUEST_CODE = 1

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_user_location)

        // Initialize FusedLocationProviderClient
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        // Checking the location permission
        val locationPermission = Manifest.permission.ACCESS_FINE_LOCATION
        if (ContextCompat.checkSelfPermission(this, locationPermission) == PackageManager.PERMISSION_GRANTED) { // Load last known location of the user
            loadLastKnownLocation()
        } else { // Request for permission
            requestLocationPermission()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_REQUEST_CODE) {
            val result = grantResults[0]
            if (result == PackageManager.PERMISSION_GRANTED) {
                loadLastKnownLocation()
            } else {
                Toast.makeText(this, "Our app could not access your location.", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun loadLastKnownLocation() {
        fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                if (task.result != null) {
                    val latitude = task.result!!.latitude
                    val longitude = task.result!!.longitude
                    txtLatitude.text = "Latitude: $latitude"
                    txtLongitude.text = "Longitude: $longitude"
                } else {
                    Toast.makeText(UserLocationActivity@this, "No location found.", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(UserLocationActivity@this, "Loading location failed.", Toast.LENGTH_LONG).show()
                Log.d("piuecom", "Loading location failed: " + task.exception!!.message)
            }
        }
    }

    private fun requestLocationPermission() {
        val locationPermission = Manifest.permission.ACCESS_FINE_LOCATION
        val permissionsToBeRequested = arrayOf(locationPermission)
        ActivityCompat.requestPermissions(this, permissionsToBeRequested, LOCATION_REQUEST_CODE)
    }

}