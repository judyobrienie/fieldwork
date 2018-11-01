package org.wit.fieldwork.models

import kotlinx.android.parcel.Parcelize
import android.net.Uri
import android.os.Parcelable


@Parcelize
data class UserModel(var idUser: Long = 0,
                     var name: String = "",
                     var email: String = "",
                     var password: String = "") : Parcelable
