package com.example.findbeerapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_add_bar.*

class AddBarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_bar)

        //Add bar buttons
        val editBarName = findViewById<TextView>(R.id.edit_bar_name)
        val editBarPrice = findViewById<TextView>(R.id.edit_bar_price)
        val addBarButton = findViewById<Button>(R.id.add_bar_button)





        addBarButton.setOnClickListener {view ->
            addNewBar()
            Toast.makeText(this, "Bar Added", Toast.LENGTH_SHORT).show()
        }



    }

    private fun addNewBar() {
        val db = FirebaseFirestore.getInstance()

        val barName = edit_bar_name.text.toString()
        val barPrice : Int = edit_bar_price.text.toString().toInt()
        val bar = Bar(barName, barPrice)
        db.collection("bars").add(bar)
    }
}
