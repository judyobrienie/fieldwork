package org.wit.fieldwork.models
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FieldworkModel(var title: String = "",
                          var description: String = ""):Parcelable


