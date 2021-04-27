package com.example.googlemapsapplication.pojo


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable



class Coords:Serializable{
    @SerializedName("lat")
    @Expose
    var lat: Double = 0.0

    @SerializedName("lon")
    @Expose
    var lon: Double = 0.0
}



class Image: Serializable{
    @SerializedName("image")
    @Expose
    var image: String? = null
}
data class Place (
    @SerializedName("count")
    @Expose
    var count: Int = 0,

    @SerializedName("next")
    @Expose
    var next: String? = null,

    @SerializedName("previous")
    @Expose
    var previous: Any? = null,

    @SerializedName("results")
    @Expose
    var results: List<Result> = listOf()
)


class Result:Serializable{
    @SerializedName("id")
    @Expose
    var id: Int = 0

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("address")
    @Expose
    var address: String? = null

    @SerializedName("timetable")
    @Expose
    var timetable: String? = null

    @SerializedName("phone")
    @Expose
    var phone: String? = null

    @SerializedName("body_text")
    @Expose
    var bodyText: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("site_url")
    @Expose
    var siteUrl: String? = null

    @SerializedName("foreign_url")
    @Expose
    var foreignUrl: String? = null

    @SerializedName("coords")
    @Expose
    var coords: Coords? = null

    @SerializedName("subway")
    @Expose
    var subway: String? = null

    @SerializedName("favorites_count")
    @Expose
    var favoritesCount: Int = 0

    @SerializedName("images")
    @Expose
    var images: List<Image> = listOf()
}

//////////////////////////////////////////////////////////////////////
//
//data class Result (
//    @SerializedName("id")
//    @Expose
//    var id: Int = 0,
//
//    @SerializedName("title")
//    @Expose
//    var title: String? = null,
//
//    @SerializedName("address")
//    @Expose
//    var address: String? = null,
//
//    @SerializedName("timetable")
//    @Expose
//    var timetable: String? = null,
//
//    @SerializedName("phone")
//    @Expose
//    var phone: String? = null,
//
//    @SerializedName("body_text")
//    @Expose
//    var bodyText: String? = null,
//
//    @SerializedName("description")
//    @Expose
//    var description: String? = null,
//
//    @SerializedName("site_url")
//    @Expose
//    var siteUrl: String? = null,
//
//    @SerializedName("foreign_url")
//    @Expose
//    var foreignUrl: String? = null,
//
//    @SerializedName("coords")
//    @Expose
//    var coords: Coords? = null,
//
//    @SerializedName("subway")
//    @Expose
//    var subway: String? = null,
//
//    @SerializedName("favorites_count")
//    @Expose
//    var favoritesCount: Int = 0,
//
//    @SerializedName("images")
//    @Expose
//    var images: List<Image> = listOf()
//):Serializable


//data class Coords (
//    @SerializedName("lat")
//    @Expose
//    var lat: Double = 0.0,
//
//    @SerializedName("lon")
//    @Expose
//    var lon: Double = 0.0
//)

//data class Image (
//    @SerializedName("image")
//    @Expose
//    var image: String? = null
//
//)