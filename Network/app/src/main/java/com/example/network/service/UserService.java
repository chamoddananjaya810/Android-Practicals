package com.example.network.service;

import com.example.network.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @GET("users")
    Call<List<User>> getAllUser();

    @GET("users/{id}")
    Call<User> getUserById(@Path("id") String id);

    @POST("users")
    boolean insertNewUser(User user);

}
