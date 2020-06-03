package com.example.findbeerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_add_bar.*

class AddBarActivity : AppCompatActivity() {

    var lat: Double? = null
    var lng: Double? = null
    lateinit var ltlng : Any

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_bar)

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
            checkEmpty()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        lat = intent.getDoubleExtra("lat",1.0)
        lng = intent.getDoubleExtra("lng",1.0)
        ltlng = intent.getParcelableExtra<LatLng>("ltLng")

        println("ltlng" + ltlng)



    } //OnCreate

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        lat = data?.getDoubleExtra("lat",1.0)
        lng = data?.getDoubleExtra("lng",1.0)
    } */


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

        //val barLat = lat?.toString()
        //val barLng = lng.toString().toInt()
        val bar = Bar(barName, barPrice, ltLng = ltlng)
        db.collection("bars").add(bar)/*.addOnSuccessListener { println("!!!SuccesBar") }.addOnFailureListener {
            println("!!!Fail" + it.localizedMessage)
        } */
    }
}
