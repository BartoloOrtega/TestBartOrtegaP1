package com.bart.test.services


import com.bart.test.ResponseToken
import com.bart.test.UserLogin
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RestApiService {
    fun loginUser(userData: UserLogin, onResult: (ResponseToken?) -> Unit){
        val email = RequestBody.create(MediaType.parse("text/plain"), userData.usuario)
        val password = RequestBody.create(MediaType.parse("text/plain"), userData.password)
        val requestBody: RequestBody= MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("email", userData.usuario)
            .addFormDataPart("password", userData.password)
            .build()

        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.loginUser(requestBody).enqueue(
            object : Callback<ResponseToken> {
                override fun onFailure(call: Call<ResponseToken>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<ResponseToken>, response: Response<ResponseToken>) {
                    val addedUser = response.message()

                    onResult( response.body())
                }
            }
        )
    }
}