package com.totaloil.simpletest.data.remote

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.totaloil.simpletest.data.remote.model.response.ModelError

object ApiResponse {

    private val mGson = Gson()

    fun onErrorResponseServer(e: Throwable): String {
        val mMessageError: String

        mMessageError = when (e) {
            is retrofit2.HttpException -> {
                val responseBody = (e).response()!!.errorBody()
                val dataMessage = responseBody!!.string()
                DeserializeError(dataMessage)
            }
            else -> {
                e.message.toString()
            }
        }
        return mMessageError
    }

    private fun DeserializeError(errorString: String): String {
        return try {
            mGson.fromJson(errorString, ModelError::class.java).errors.message ?: "som ting went wrong"
        } catch (e: JsonSyntaxException) {
            e.message.toString()
        }
    }
}