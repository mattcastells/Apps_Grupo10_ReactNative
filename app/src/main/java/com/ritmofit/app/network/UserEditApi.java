package com.ritmofit.app.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import com.ritmofit.app.network.UserRequest;

public interface UserEditApi {
    @PUT("users/{id}")
    Call<Object> updateUser(@Path("id") String id, @Body UserRequest user);
}
