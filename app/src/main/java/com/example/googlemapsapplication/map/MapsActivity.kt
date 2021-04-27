package com.example.googlemapsapplication.map

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.googlemapsapplication.MainActivity
import com.example.googlemapsapplication.R
import com.example.googlemapsapplication.pojo.Place
import com.example.googlemapsapplication.pojo.Result
import com.example.googlemapsapplication.usecases.PlaceUseCase

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import io.reactivex.disposables.CompositeDisposable

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var placeUseCase:PlaceUseCase
    private var place:Place?=null
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        placeUseCase = PlaceUseCase()
//        val disp = placeUseCase.getPlaces().subscribe {
//            place = it
//        }
        //compositeDisposable.add(disp)


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

          val msk  = LatLng(55.750263, 37.611503)

        val disp = placeUseCase.getPlaces().subscribe {
            place = it
            insertMarkers(mMap,place)
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLng(msk))
        mMap.setOnMarkerClickListener { p0 ->
            Log.d("TAG", p0?.title.toString())
            val res = findCurrentPlace(place,p0.title)
            goToDetails(res)
//            val intent = Intent(this,MapsActivity::class.java)
//            intent.putExtra("place",res)
//            startActivity(intent)
            true
        }

    }

    fun insertMarkers(googleMap: GoogleMap,place: Place?){
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

    fun findCurrentPlace(place: Place?,markerTitle:String): Result?{
        if (place==null) return null
        var result:Result?=null
        for(pl in place.results){
            if(pl.title.equals(markerTitle)){
                result = pl
            }
        }
        return result
    }
    fun goToDetails(place:Result?){
        if(place==null) return
        val intent = Intent(this@MapsActivity,MainActivity::class.java)
        intent.putExtra("place",place)
        startActivity(intent)

    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}