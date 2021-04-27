package com.example.googlemapsapplication.presenters

import android.util.Log
import com.example.googlemapsapplication.pojo.Place
import com.example.googlemapsapplication.pojo.Result
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapPresenter {

    fun findCurrentPlace(place: Place?, markerTitle:String): Result?{
        if (place==null) return null
        var result: Result?=null
        for(pl in place.results){
            if(pl.title.equals(markerTitle)){
                result = pl
            }
        }
        return result
    }
    fun insertMarkers(googleMap: GoogleMap, place: Place?){
        Log.d("TAG","not")
        if(place==null) return
        for(pl in place.results){
            val coords = pl.coords?.let { LatLng(it.lat,it.lon) }
            val title = pl.title
            val point = coords?.let { MarkerOptions().position(it).title(title) }
            googleMap.addMarker(point)
            Log.d("TAG","added marker")
        }
    }
}