package com.example.networkpactical.api;

import com.example.networkpactical.dto.LoginRequestDTO;
import com.example.networkpactical.dto.TokenDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApi {
    @POST("auth/refesh")
    Call<TokenDTO> refeshAcessTocken(@Body TokenDTO tokenDTO);

    @POST("auth/login")
    Call<TokenDTO> userLogin(@Body LoginRequestDTO requestDTO);
}
