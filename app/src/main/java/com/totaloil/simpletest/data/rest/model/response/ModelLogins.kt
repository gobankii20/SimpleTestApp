package com.totaloil.simpletest.data.rest.model.response

data class ModelLogins(
    val `data`: DataLogin
)

data class DataLogin(
    val address: String,
    val code: String,
    val email: String,
    val id: Int,
    val image: String,
    val lat: String,
    val long: String,
    val name: String,
    val roleId: Int,
    val roleName: String,
    val telephone: String,
    val token: String
)