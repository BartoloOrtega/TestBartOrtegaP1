package com.bart.test.services


import com.bart.test.ResponseToken
import com.bart.test.User
import com.bart.test.UserLogin
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface RestApi {
    //@Headers("Content-Type: application/json")
    //@Headers("Content-Type: multipart/form-data")
   // @Multipart
    //@FormUrlEncoded
    @POST(".")
    fun loginUser( @Body user: RequestBody  ): Call<ResponseToken>
    //fun loginUser( @Part("email") userData: RequestBody  , @Part("password") userPass: RequestBody  ): Call<ResponseToken>
}