package org.wit.fieldwork.models
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FieldworkModel(var id: Long=0,
                          var title: String = "",
                          var description: String = ""):Parcelable


