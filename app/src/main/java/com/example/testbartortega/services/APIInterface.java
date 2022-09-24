package com.example.testbartortega.services;

import com.example.testbartortega.data.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIInterface {
    @POST()
    Call<User> getUser(@Part String user, @Part String pass);
}
