package com.example.findbeerapp




import android.content.Intent
import android.graphics.Point
import android.location.Location
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener,
    GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap

    //private lateinit var originPosition: Point
    //private lateinit var destinationPosition: Point
    //private var destinationMarker: Marker? = null

    var lat: Double? = null
    var lng: Double? = null
    var ltLng : LatLng? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val mapView = findViewById<View>(R.id.map_display)
        //val currentlocation : Location


        addBarMarker.isEnabled = false
        addBarMarker.findViewById<Button>(R.id.addBarMarker)
        addBarMarker.setOnClickListener { view ->
            val intent = Intent(this, AddBarActivity::class.java)

            intent.putExtra("lat", lat)
            intent.putExtra("lng", lng)
            intent.putExtra("ltLng", ltLng)
            startActivity(intent)
        }


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = this.supportFragmentManager
            .findFragmentById(R.id.map_display) as SupportMapFragment
        mapFragment.getMapAsync(this)




    } //onCreate


    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap
        mMap.setOnInfoWindowClickListener(this)
        mMap.isMyLocationEnabled = true
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(12.0f))


        //val newBarMarker = intent.getParcelableExtra<LatLng>("ltLng")
        //mMap.addMarker(MarkerOptions().position(ltLng))



        //Listens to map clicks
        mMap.setOnMapClickListener {latLng ->

            mMap.clear()
            mMap.addMarker(MarkerOptions().position(latLng))
            addBarMarker.isEnabled = true

            lat = latLng?.latitude
            lng = latLng?.longitude
            ltLng = latLng

        }

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
        TODO()
    }



}