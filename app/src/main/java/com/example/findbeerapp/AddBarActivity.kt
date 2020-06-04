package com.example.findbeerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_add_bar.*

class AddBarActivity : AppCompatActivity() {

    var lat: Double? = null
    var lng: Double? = null
    lateinit var ltlng : myLatLng

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_bar)

        ltlng = intent.getSerializableExtra("ltLng") as myLatLng

        //Add bar buttons
        val editBarName = findViewById<TextView>(R.id.edit_bar_name)
        val editBarPrice = findViewById<TextView>(R.id.edit_bar_price)
        val addBarButton = findViewById<Button>(R.id.add_bar_button)
        val addBarLocation = findViewById<Button>(R.id.bar_location)

        val getBarName = editBarName
        val getBarPrice = editBarPrice


        addBarLocation.setOnClickListener {view ->
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }


        addBarButton.setOnClickListener {view ->
            checkEmpty() //addNewBar()
            val intent = Intent(this, MainActivity::class.java)
            //intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

    } //OnCreate



    //check that the user has enter a name and price
    private fun checkEmpty() {
        val getBarName = edit_bar_name.text
        val getBarPrice = edit_bar_price.text.toString()

        if (getBarName.isEmpty() || getBarName.isEmpty()) {
            Toast.makeText(this, "No Text", Toast.LENGTH_SHORT).show()
            return
        } else {
            addNewBar()
            Toast.makeText(this, "Bar Added", Toast.LENGTH_SHORT).show()
        }
    }


    private fun addNewBar() {
        val db = FirebaseFirestore.getInstance()
        val barName = edit_bar_name.text.toString()
        val barPrice : Int = edit_bar_price.text.toString().toInt()


        if(ltlng == null) {
            return
        }

        val bar = Bar(barName, barPrice, 0, 0, ltlng)
        db.collection("bars").add(bar)
        }
    }

