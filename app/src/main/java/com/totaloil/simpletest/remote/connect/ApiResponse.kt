package com.totaloil.simpletest.remote.connect

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.totaloil.simpletest.remote.model.ModelError
import java.io.IOException

object ApiResponse {

        private val gson = Gson()

        fun onErrorResponseServer(e: Throwable): String {
            val mMessageError: String

            mMessageError = when (e) {
                is retrofit2.HttpException -> {
                    val responseBody = (e).response()!!.errorBody()
                    val dataMessage = responseBody!!.string()
                    DeserializeServerError(dataMessage)
                }
                is IOException -> {
                    "กรุณาตรวจสอบการเชื่อมต่ออินเตอร์เน็ต"
                }
                else -> {
                    e.message.toString()
                }
            }
            return mMessageError
        }

        private fun DeserializeServerError(errorString:String):String{
            return try {
                gson.fromJson(errorString, ModelError::class.java).error.message
            } catch (e: JsonSyntaxException) {
                "${e.message}"
            }
        }
}