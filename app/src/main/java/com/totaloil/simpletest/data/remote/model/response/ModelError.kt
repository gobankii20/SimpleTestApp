package com.totaloil.simpletest.data.remote.model.response

data class ModelError(
    val errors: Errors
)

data class Errors(val message: String?)