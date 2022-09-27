package com.bart.test

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("titular") val titular: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("solicitud") val solicitud: String?

)


data class UserLogin(

    val usuario: String,
    val password:String
)

data class ResponseToken(
    //val message: String
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("token")
    val token: String
)