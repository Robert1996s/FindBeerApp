package com.example.findbeerapp


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMapClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener,
    GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap

    //private lateinit var originPosition: Point
    //private lateinit var destinationPosition: Point
    //private lateinit var destinationMarker: Marker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val mapView = findViewById<View>(R.id.map_display)




        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map_display) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnInfoWindowClickListener(this)
        mMap.isMyLocationEnabled = true

        //Listens to map clicks
        mMap.setOnMapClickListener {
            mMap.addMarker(MarkerOptions().position(it))

        }
        // Add a marker in Sydney and move the camera
        val stockholm = LatLng(59.517, 17.916)
        val marker = mMap.addMarker(
            MarkerOptions()
                .position(stockholm)
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(stockholm, 12.0f))
    }

    /*fun createLocationRequest() : LocationRequest {
    var a = LocationRequest.create()
    a.interval = 7000
    a.fastestInterval = 5000
}*/


    override fun onInfoWindowClick(marker: Marker?) {
        Toast.makeText(this, "Här är du", Toast.LENGTH_SHORT).show()
    }

    override fun onMarkerClick(point: Marker?): Boolean {
        TODO("Not yet implemented")
    }


}