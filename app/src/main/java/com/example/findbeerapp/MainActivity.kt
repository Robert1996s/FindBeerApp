package com.example.findbeerapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.common.primitives.UnsignedInts.sort
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.type.LatLng
import java.util.*
import java.util.Arrays.sort
import java.util.Collections.sort
import kotlin.Comparator


class MainActivity : AppCompatActivity() {

    private lateinit var locationProviderClient : FusedLocationProviderClient
    private val REQUEST_LOCATION = 1
    private var locationRequest : LocationRequest? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            println("!!! No permission")
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION)
        } else {
            locationProviderClient.lastLocation.addOnSuccessListener {location ->
                if(location != null) {
                    val lat = location.latitude
                    val lng = location.longitude
                }
            }
        }

        //connect firebase
        val db = FirebaseFirestore.getInstance()
        val beerPlacesList = mutableListOf<Bar>()
        val barsRef = db.collection("bars").orderBy("price")


        val recyclerView = findViewById<RecyclerView>(R.id.beer_list)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = BarRecycleAdapter(this, beerPlacesList)
        recyclerView.adapter = adapter



        //Buttons
        val nearmeButton = findViewById<ImageButton>(R.id.near_me_button)
        val priceButton = findViewById<ImageButton>(R.id.price_button)
        val favouriteButton = findViewById<ImageButton>(R.id.favourite_button)
        val mapButton = findViewById<ImageButton>(R.id.map_button)
        val addBarFabButton = findViewById<FloatingActionButton>(R.id.addBar_Fab_Button)


        nearmeButton.setOnClickListener {
            println("1")
        }

        priceButton.setOnClickListener {

        }

        favouriteButton.setOnClickListener {
            println("3")
        }

        //Shows the map in new activity
        mapButton.setOnClickListener { view ->
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }


        addBarFabButton.setOnClickListener { view ->
            val intent = Intent(this, AddBarActivity::class.java)
            startActivity(intent)
        }





        val docRef = db.collection("bars").document("bar")
        docRef.get()
            .addOnSuccessListener { document ->
                if(document != null) {
                    Log.d("exist", "Data: ${document.data}")

                    //textViewName.text = document.getString("name")
                }
            }

        //Get data and add as object
        barsRef.get().addOnSuccessListener { documentSnapshot ->
            for (document in documentSnapshot.documents) {
                val newBar = document.toObject(Bar::class.java)
                if(newBar != null)
                    beerPlacesList.add(newBar!!)


            }
        }

        //Reads any changes in firebase data
        barsRef.addSnapshotListener{ snapshot, e ->
            if( snapshot != null ) {
                beerPlacesList.clear()
                for (document in snapshot.documents) {
                    val newBar = document.toObject(Bar::class.java)
                    if(newBar != null)
                        beerPlacesList.add(newBar!!)
                    adapter.notifyDataSetChanged()
                    println("!!!db" + newBar?.ltLng)
                }
            }
        }





    } //onCreate


    /*private fun orderPrice() {
        db.collection("bars").document().collection("bars").orderBy("price")
        finish()
        startActivity(getIntent())
    }*/




    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == REQUEST_LOCATION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                println("!!!Permission granted")
        } else {
            println("!!! Permission denied")
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }



}






