package com.example.findbeerapp

import com.google.android.gms.maps.model.LatLng
import java.io.Serializable

class myLatLng (val latitude:Double? = null, val longitude:Double? = null): Serializable {

    fun convertToGoogleLatLng():LatLng?{
        if (latitude != null && longitude != null){
            return LatLng(latitude,longitude)
        } else {
            return null
        }
    }
}