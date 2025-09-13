package com.ritmofit.app.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import com.ritmofit.app.network.StudentRequest;
import com.ritmofit.app.network.ProfessorRequest;

public interface UserApi {
    @POST("users/student")
    Call<Object> createStudent(@Body StudentRequest student);

    @POST("users/professor")
    Call<Object> createProfessor(@Body ProfessorRequest professor);
}
