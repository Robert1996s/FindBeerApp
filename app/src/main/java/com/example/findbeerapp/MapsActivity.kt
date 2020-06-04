package com.example.findbeerapp




import android.content.Intent
import android.graphics.Point
import android.location.Location
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_maps.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener,
    GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap

    var lat: Double? = null
    var lng: Double? = null
    var ltLng : myLatLng? = null
    var newMarker: Marker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val mapView = findViewById<View>(R.id.map_display)


        addBarMarker.isEnabled = false
        addBarMarker.findViewById<Button>(R.id.addBarMarker)
        addBarMarker.setOnClickListener { view ->
            val intent = Intent(this, AddBarActivity::class.java)


            intent.putExtra("ltLng", ltLng)
            startActivity(intent)
            println("!!!" + ltLng)
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
        val stockholm = LatLng(59.3,18.06)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(stockholm, 10.0f)) //gps



        //Listens to map clicks
        mMap.setOnMapClickListener {latLng ->
            newMarker?.remove()
            newMarker = mMap.addMarker(MarkerOptions().position(latLng))
            addBarMarker.isEnabled = true
            ltLng = myLatLng(latLng.latitude, latLng.longitude)
        }


        val db = FirebaseFirestore.getInstance()
        val barsRef = db.collection("bars").orderBy("price")
        val barPlaceList  = mutableListOf<Bar>()

        barsRef.get().addOnSuccessListener { documentSnapshot ->
            for (document in documentSnapshot.documents) {
                val newBar = document.toObject(Bar::class.java)
                if(newBar != null)
                    barPlaceList.add(newBar!!)



                for(bar in barPlaceList) {
                    if (bar.ltLng != null) {
                        val location : LatLng? = bar.ltLng?.convertToGoogleLatLng()
                        if (location != null) {
                            mMap.addMarker(
                                MarkerOptions().position(location))
                        }
                    }


                }
            }
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