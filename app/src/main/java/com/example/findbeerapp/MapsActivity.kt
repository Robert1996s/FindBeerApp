package com.example.findbeerapp

import android.R
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_maps)

        //val mapView = findViewById<MapsActivity>(R.id.map_View)


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        /*val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map_display) as SupportMapFragment
        mapFragment.getMapAsync(this)*/
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnInfoWindowClickListener(this)
        // Add a marker in Sydney and move the camera
        val stockholm = LatLng(59.517, 17.916)
        val marker = mMap.addMarker(MarkerOptions()
            .position(stockholm)
            .title("Stockholm")
            .snippet("Vackert här!"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(stockholm))
    }
    /*fun createLocationRequest() : LocationRequest {
    var a = LocationRequest.create()
    a.interval = 7000
    a.fastestInterval = 5000
}*/


    override fun onInfoWindowClick(marker: Marker?) {
        Toast.makeText(this, "Här är du", Toast.LENGTH_SHORT).show()
    }
}
