package com.example.googlemapsapplication.usecases

import com.example.googlemapsapplication.api.ApiService
import com.example.googlemapsapplication.pojo.Place
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PlaceUseCase {

    fun getPlaces():Observable<Place>{
        return ApiService.api.getPlaces()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}