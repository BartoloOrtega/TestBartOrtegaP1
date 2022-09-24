package com.example.testbartortega.data;

import android.telecom.Call;
import android.util.Log;

import com.example.testbartortega.data.model.LoggedInUser;
import com.example.testbartortega.data.model.User;
import com.example.testbartortega.services.APIClient;
import com.example.testbartortega.services.APIInterface;

import java.io.IOException;

import retrofit2.Retrofit;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    String resultTokenIntial="";
    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            LoginServer(username,password);
            LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            resultTokenIntial);
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }
    APIInterface apiInterface;
    public void LoginServer(String username, String password){
        apiInterface = APIClient.getClient().create(APIInterface.class);
        retrofit2.Call<User> call = apiInterface.getUser(username,password);
        call.enqueue(new  Call.Callback() {
            @Override
            public void onResponse(retrofit2 .Response<String> response,
                                   Retrofit retrofit) {
                Log.d("TAG", "onResponse: "+ response);
                resultTokenIntial=response;
            }

            @Override
            public void onFailure(Throwable t) {

                t.printStackTrace();
            }
        });
    }


    public void logout() {
        // TODO: revoke authentication
    }
}