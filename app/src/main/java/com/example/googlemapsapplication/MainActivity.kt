package com.example.googlemapsapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.googlemapsapplication.pojo.Place
import com.example.googlemapsapplication.pojo.Result

class MainActivity : AppCompatActivity() {
    lateinit var imageView:ImageView
    lateinit var textViewTitle: TextView
    lateinit var textViewAddress: TextView
    lateinit var textViewDescription: TextView
    private var place:Result?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        textViewTitle = findViewById(R.id.textViewTitle)
        textViewDescription = findViewById(R.id.textViewDescription)
        textViewAddress = findViewById(R.id.textViewAddress)

        place = getPlace()
        updateUI(place)


    }

    fun updateUI(place: Result?){
        if(place==null) return
        Glide.with(this).load(place.images[0].image).into(imageView)
        textViewTitle.text = place.title
        textViewAddress.text = place.address
        textViewDescription.text = place.description
        Log.d("TAG",place.images[0].image.toString())
    }

    private fun getPlace():Result{
        val intent = intent.extras?.getSerializable("place")
        return intent as Result
    }
}