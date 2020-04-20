package com.totaloil.simpletest.data.rest.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
 data class ModelLogin(
     val first_name: String,
     val last_name: String,
     val picture: String,
     val role: String,
     val user_id: String): Parcelable

data class ModelError(
    val errors: Error
)

data class Error(
    val code: Int?,
    val id: Int?,
    val message: String?
)

