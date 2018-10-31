package org.wit.fieldwork.models
import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FieldworkModel(var id: Long=0,
                          var title: String = "",
                          var description: String = "",
                          var image: String = "",
                          var images: MutableList<String> = ArrayList<String>(),
                          var lat : Double = 0.0,
                          var lng: Double = 0.0,
                          var zoom: Float = 0f) : Parcelable


@Parcelize
data class Location(var lat: Double = 0.0,
                    var lng: Double = 0.0,
                    var zoom: Float = 0f) : Parcelable


