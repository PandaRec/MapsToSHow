package com.example.googlemapsapplication.map

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.googlemapsapplication.MainActivity
import com.example.googlemapsapplication.R
import com.example.googlemapsapplication.pojo.Place
import com.example.googlemapsapplication.pojo.Result
import com.example.googlemapsapplication.presenters.MapPresenter
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
    private lateinit var mapPresenter: MapPresenter
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        placeUseCase = PlaceUseCase()
        mapPresenter = MapPresenter()

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
            mapPresenter.insertMarkers(mMap,place)
        }
        compositeDisposable.addAll(disp)

        mMap.moveCamera(CameraUpdateFactory.newLatLng(msk))
        mMap.setOnMarkerClickListener { p0 ->
            Log.d("TAG", p0?.title.toString())
            val res = mapPresenter.findCurrentPlace(place,p0.title)
            goToDetails(res)
            true
        }

    }

    private fun goToDetails(place:Result?){
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