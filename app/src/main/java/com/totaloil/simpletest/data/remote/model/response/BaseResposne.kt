package com.totaloil.simpletest.data.remote.model.response

data class BaseResposne(
    val access_token: String,
    val `data`: Any,
    val expires_in: Int,
    val message: String,
    val refresh_token: String,
    val status: Int
)