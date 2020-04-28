package com.totaloil.simpletest.data.remote.model.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class ModelListNews(
    val `data`: List<DataListNews>,
    val message: String,
    val status: Int
)

@Parcelize
 class DataListNews(
    var create: String,
    val detail: String,
    val id: String,
    val image: String,
    val title: String,
    val uuid: String
):Parcelable

