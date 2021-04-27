package com.example.googlemapsapplication.api

import com.example.googlemapsapplication.pojo.Place
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiFactory {
    companion object{
        private const val DEFAULT_FIELDS_PLACES="id,title,address,timetable,phone,images,description,body_text,site_url,foreign_url,coords,subway,favorites_count"
        private const val DEFAULT_CATEGORIES_PLACES="bar,brewery,clubs,restaurants,strip-club"
    }
    @GET("public-api/v1.4/places")
    fun getPlaces(
        @Query("page") page:Int=1,
        @Query("page_size") pageSize:Int=100,
        @Query("fields") fields:String= DEFAULT_FIELDS_PLACES,
        @Query("location") location:String = "msk",
        @Query("categories") categories:String = DEFAULT_CATEGORIES_PLACES
    ):Observable<Place>
}