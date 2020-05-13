package com.example.findbeerapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = FirebaseFirestore.getInstance()

// Create a new user with a first and last name

        // Create a new user with a first and last name
        val bar: MutableMap<String, Any> = HashMap()
        bar["name"] = "Fridays"
        bar["Price"] = 59
        bar["Distance"] = 250

// Add a new document with a generated ID
        

// Add a new document with a generated ID
        db.collection("bars")
            .add(bar)
            .addOnSuccessListener { documentReference ->
                Log.d(
                    "!!!",
                    "DocumentSnapshot added with ID: " + documentReference.id
                )
            }
            .addOnFailureListener { e -> Log.w("!!!", "Error adding document", e) }


        val recyclerView = findViewById<RecyclerView>(R.id.beer_list)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = BarRecycleAdapter(this, DataManager.bars)

        recyclerView.adapter = adapter
    }
}
